package com.mrxu.cloud.file.service.file.local;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mrxu.cloud.common.enums.MrxuExceptionEnums;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.common.util.FileUtil;
import com.mrxu.cloud.file.config.ApplicationContextRegister;
import com.mrxu.cloud.file.config.FDSFConfig;
import com.mrxu.cloud.file.depend.VestaService;
import com.mrxu.cloud.file.domain.*;
import com.mrxu.cloud.file.domain.entity.file.FileResultVO;
import com.mrxu.cloud.file.domain.entity.process.sync.FileRequestSyncDTO;
import com.mrxu.cloud.file.domain.entity.process.sync.FileResponseExtendSyncDTO;
import com.mrxu.cloud.file.domain.entity.process.sync.FileResponseExtendTargetSyncDTO;
import com.mrxu.cloud.file.domain.entity.process.sync.FileResponseSyncDTO;
import com.mrxu.cloud.file.domain.entity.trans.TransCodingResultVO;
import com.mrxu.cloud.file.enums.ProcessModeEnum;
import com.mrxu.cloud.file.enums.StatusEnum;
import com.mrxu.cloud.file.enums.TransTypeEnum;
import com.mrxu.cloud.file.mapper.FileTransScheduleMapper;
import com.mrxu.cloud.file.mapper.FileTypeExtensionMapper;
import com.mrxu.cloud.file.mapper.FileTypeMapper;
import com.mrxu.cloud.file.mapper.FilesMapper;
import com.mrxu.cloud.file.service.file.IFileService;
import com.mrxu.cloud.file.service.file.custom.FileAuxiliaryService;
import com.mrxu.cloud.file.service.process.IFileProcessService;
import com.mrxu.cloud.file.util.FileTypeUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FDFS文件服务器上传处理
 * @author ifocusing-xuzhiwei
 * @since 2018/3/13
 */
@Service("fdfsService")
public class FDFSFileServiceImpl implements IFileService {

	private static Logger LOG = Logger.getLogger(FDFSFileServiceImpl.class);

	@Autowired
	FDSFConfig config;
	@Autowired
	VestaService vestaService;
	@Autowired
	FilesMapper filesMapper;
	@Autowired
	FileAuxiliaryService fileCustomService;
	@Autowired
	FileTransScheduleMapper fileTransScheduleMapper;
	@Autowired
	FileTypeMapper fileTypeMapper;
	@Autowired
	FileTypeExtensionMapper fileTypeExtensionMapper;

	private static StorageClient1 storageClient1 = null;

	@Override
	public FileResultVO uploadFile(MultipartFile file) throws MrxuException {
		//初始化
		this.init();
		//获取文件请求信息(用于同步处理)
		FileRequestSyncDTO fileRequestSync = this.findFileRequestInfoSync(file);
		//文件校验 -- 若已上传，直接返回数据库保存信息
		FileResultVO fileResult = this.fileCustomService.findFileResultByUnionKey(fileRequestSync.getUnionKey());
		if(null != fileResult){
			return fileResult;
		}
		//若没有上传 -- 文件上传开始 -->
		fileResult = this.uploadFileOper(fileRequestSync);
		if(null == fileResult){
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		}
		return fileResult;
	}

	@Override
	public List<FileResultVO> uploadMultipleFile(MultipartFile[] files) throws MrxuException {
		if(null == files || files.length <= 0) return null;

		List<FileResultVO> fileResultList = new ArrayList<>();
		for(MultipartFile multipartFile : files){
			FileResultVO fileResult = this.uploadFile(multipartFile);
			fileResultList.add(fileResult);
		}
		return fileResultList;
	}

	/**
	 * 本地上传文件服务器
	 * @param file
	 * @param isFullPath 是否全路径，不是-使用默认路径
	 * @param isDelete 是否删除，是-删除
	 * @return
	 */
	@Override
	public String uploadFileToServer(String file, boolean isFullPath, boolean isDelete) throws MrxuException{
		//FDFS初始化
		this.init();

		if (StringUtils.isEmpty(file)) {
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		}
		String localFileID = file;
		if (!isFullPath) {
			localFileID = config.getLocalPath() + file;
		}
		String file_id;
		TrackerServer trackerServer = null;
		try {
			file_id = storageClient1.upload_file1(localFileID, FileUtil.getFileExtName(file), null);
		} catch (Exception e) {
			LOG.error(e);
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR.getCode(),
					MrxuExceptionEnums.RC_COMMON_ERROR.getMessage());
		} finally {
			if (trackerServer != null) {
				try {
					trackerServer.close();
				} catch (IOException e) {
					LOG.error(e);
				}
			}
			if (isDelete) {
				new File(localFileID).delete();
			}
		}
		return config.getFtpServerUrl() + file_id;
	}

	@Override
	public int deleteFile(String url) {
		this.init();
		int result;
		try {
			String fileId = url.substring(config.getFtpServerUrl().length());
			result = storageClient1.delete_file1(fileId);
			if (result == 0) {
				FilesExample info = new FilesExample();
				info.createCriteria().andUrlEqualTo(url);
				filesMapper.deleteByExample(info);
			}
		} catch (Exception e) {
			LOG.error(e);
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		}
		return result;
	}

	@Override
	public String downloadFile(String url) {
		//初始化
		this.init();

		FileOutputStream out = null;
		try {
			String fileId = url.substring(config.getFtpServerUrl().length());
			if (fileId.indexOf("?attname=") != -1) {
				fileId = fileId.substring(0, fileId.indexOf("?attname="));
			}
			byte[] bytes = storageClient1.download_file1(fileId);
			File file = new File(config.getLocalPath() + vestaService + "."
					+ FileUtil.getFileExtName(url));

			if (!file.exists()) {
				file.delete();
			}
			file.createNewFile();

			out = new FileOutputStream(file);
			out.write(bytes);

			return file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR.getCode(),
					MrxuExceptionEnums.RC_COMMON_ERROR.getMessage());
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean exist(String key) {
		this.init();
		try {
			FileInfo info = storageClient1.get_file_info1(key.substring(config.getFtpServerUrl().length()));

			if (info != null) {
				return true;
			}
		} catch (Exception e) {
			LOG.error(e);
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR.getCode(),
					MrxuExceptionEnums.RC_COMMON_ERROR.getMessage());
		}
		return false;
	}

	@Override
	public TransCodingResultVO findTransInfo(String fileUrl, String targetType, String mediaPlatform)
			throws MrxuException {

		// 获取数据库中的转码信息
		TransCodingResultVO transCodingResult = this.findTransInfo(fileUrl, targetType);
		if (!StringUtils.isEmpty(transCodingResult.getTargetUrl())) {
			return transCodingResult;
		}
		String transParentId = transCodingResult.getOriginId();
		// 判断是否已经加入转码任务中
		// 未加入主动请求提高转码优先级，并撤出主动转码
		FileTransScheduleExample fileTransScheduleExample = new FileTransScheduleExample();
		fileTransScheduleExample.createCriteria().andFileIdEqualTo(transParentId).andTargetTypeEqualTo(targetType);
		List<FileTransSchedule> fileTransScheduleList = fileTransScheduleMapper.selectByExample(fileTransScheduleExample);
		return null;
	}

	/**
	 * 上传文件操作
	 * @param fileRequestSync
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = {Exception.class})
	protected FileResultVO uploadFileOper(FileRequestSyncDTO fileRequestSync) throws MrxuException{
		//文件类型
		String fileType = fileRequestSync.getFileType();
		//源文件上传, 使用全路径，不删除源文件
		String url = this.uploadFileToServer(fileRequestSync.getFilePath(), true, false);
		fileRequestSync.setUrl(url);
		//当前时间戳
		Long currentTime = System.currentTimeMillis();
		//源文件拓展类型
		String extension = fileRequestSync.getExtension();

		//封装文件上传结果
		FileResponseSyncDTO fileResponseSync = null;
		//异步任务列表
		List<FileTransSchedule> transScheduleList = new ArrayList<>();

		//根据文件类型获取Process实例
		FileTypeExample fileTypeExample = new FileTypeExample();
		fileTypeExample.createCriteria().andTypeEqualTo(fileType);
		List<FileType> typeList = this.fileTypeMapper.selectByExample(fileTypeExample);
		if(null != typeList && !typeList.isEmpty()){
			for(FileType fileTypeEntity : typeList){
				//同步、异步
				//获取执行的JavaBean实例
				String processInstance = fileTypeEntity.getProcessInstance();
				//处理模式(sync、async)
				String processMode = fileTypeEntity.getProcessMode();
				//处理
				if(processMode.equalsIgnoreCase(ProcessModeEnum.sync.getItemValue())){
					//同步：直接封装fileResult
					//获取JavaBean实例
					IFileProcessService<FileRequestSyncDTO, FileResponseSyncDTO> processService = (IFileProcessService<FileRequestSyncDTO, FileResponseSyncDTO>) ApplicationContextRegister.getBean(processInstance);
					fileResponseSync = processService.process(fileRequestSync);
					if(null == fileResponseSync){
						throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
					}
				} else if(processMode.equalsIgnoreCase(ProcessModeEnum.async.getItemValue())){
					if(processInstance.toLowerCase().contains(extension.toLowerCase())){
						//处理转码目标类型就是自己，无需处理
						continue;
					}
					//异步：加入异步任务
					//获取执行的JavaBean实例
					FileTransSchedule fileTransSchedule = new FileTransSchedule();
					fileTransSchedule.setGmtCreate(currentTime);
					fileTransSchedule.setGmtModified(currentTime);
					fileTransSchedule.setScheduleNum(0);
					fileTransSchedule.setOriginUrl(url);
					fileTransSchedule.setFileName(fileRequestSync.getFileName());
					fileTransSchedule.setFilePath(fileRequestSync.getFilePath());
					fileTransSchedule.setTargetType(fileTypeEntity.getTargetType());
					fileTransSchedule.setProcessInstance(processInstance);
					transScheduleList.add(fileTransSchedule);
				} else {
					LOG.error("不支持的模式！");
					throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
				}
			}
		} else {
			//无需进行拓展
			fileResponseSync = JSON.parseObject(JSONObject.toJSONString(fileRequestSync), FileResponseSyncDTO.class);
		}
		//*保存上传文件信息 --> 数据库
		return this.saveFileInfoAndSchedule(fileResponseSync, transScheduleList);
	}

	/**
	 * 保存文件信息和任务到数据库
	 * @param fileResponseSync
	 * @param transScheduleList
	 * @return
	 * @throws MrxuException
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = {Exception.class})
	public FileResultVO saveFileInfoAndSchedule(FileResponseSyncDTO fileResponseSync, List<FileTransSchedule>
			transScheduleList) throws MrxuException{
		//当前时间戳
		Long currentTime = System.currentTimeMillis();
		//创建人ID
		String creatorId = fileResponseSync.getCreatorId();
		if(StringUtils.isEmpty(creatorId)) creatorId = "0";
		//缩略图
		String thumbnail = fileResponseSync.getThumbnail();

		//插入主表信息
		Files insertFiles = new Files();
		insertFiles.setGmtCreate(currentTime);
		insertFiles.setStatus(StatusEnum.on.getItemValue());
		insertFiles.setCreatorId(creatorId);
		if(StringUtils.isNotEmpty(thumbnail)){
			insertFiles.setThumbnail(thumbnail);
		}
		insertFiles.setType(fileResponseSync.getFileType());
		insertFiles.setUrl(fileResponseSync.getUrl());
		//源文件的转码类型就是自己的拓展名
		insertFiles.setTransType(fileResponseSync.getExtension());
		insertFiles.setFileName(fileResponseSync.getFileName());
		insertFiles.setParentId("0");
		insertFiles.setUnionKey(fileResponseSync.getUnionKey());
		insertFiles.setDescription("源文件");
		insertFiles.setId(String.valueOf(vestaService.genId()));
		this.filesMapper.insert(insertFiles);

		//** 若异步任务存在，则添加到任务表中,且需保留上传文件，留待异步转码
		if(!transScheduleList.isEmpty()){
			for(FileTransSchedule fileTransSchedule : transScheduleList){
				fileTransSchedule.setId(String.valueOf(vestaService.genId()));
				fileTransSchedule.setFileId(insertFiles.getId());
				this.fileTransScheduleMapper.insert(fileTransSchedule);
			}
		} else {
			//若没有异步任务需要删除上传文件
			FileUtil.delete(fileResponseSync.getFilePath());
		}

		//返回对象 -- 实例copy
		FileResultVO fileResult = JSON.parseObject(JSONObject.toJSONString(fileResponseSync), FileResultVO.class);

		//拓展信息
		FileResponseExtendSyncDTO extend = fileResponseSync.getExtend();
		//不存在拓展信息，直接返回
		if(null == extend){
			return fileResult;
		}

		//存在拓展数据
		List<FileResponseExtendTargetSyncDTO> targetList = extend.getTargetList();
		if(null != targetList && !targetList.isEmpty()){
			//有同步的转码拓展信息，封装入库
			int index = 1;
			for(FileResponseExtendTargetSyncDTO target : targetList){
				Files filesExtend = new Files();

				filesExtend.setId(String.valueOf(vestaService.genId()) + (index++));
				filesExtend.setParentId(insertFiles.getId());
				filesExtend.setUnionKey(target.getUnionKey());
				//文件URL
				String targetUrl = target.getTargetUrl();
				filesExtend.setUrl(targetUrl);
				//获取目标文件名称
				String originFileName = fileResponseSync.getFileName();
				String targetExtension = targetUrl.substring(targetUrl.lastIndexOf(".") + 1, targetUrl.length());
				String fileName = originFileName.substring(0, originFileName.lastIndexOf(".")) + "." + targetExtension;
				filesExtend.setFileName(fileName);
				//当前文件类型
				filesExtend.setType(FileTypeUtil.findResTypeEnum(target.getTargetUrl()));
				//处理的目标转码类型
				filesExtend.setTransType(target.getTargetType());
				filesExtend.setGmtCreate(currentTime);
				if(StringUtils.isNotEmpty(target.getThumbnail())){
					filesExtend.setThumbnail(target.getThumbnail());
				} else if(StringUtils.isNotEmpty(thumbnail)){
					filesExtend.setThumbnail(thumbnail);
				}
				//创建人ID
				filesExtend.setCreatorId(creatorId);
				//描述
				filesExtend.setDescription("拓展信息");
				filesExtend.setStatus(StatusEnum.on.getItemValue());
				this.filesMapper.insert(filesExtend);
			}
		}
		return fileResult;
	}

	/**
	 * 检验文件正确性
	 * @param file
	 * @return
	 */
	private String fileValidationAndWriteLocal(String tmpFileNamePrefix, MultipartFile file) throws MrxuException {
		// 获得源文件名
		String uploadFileName = file.getOriginalFilename();
		// 拓展名
		String fileExtName = FileUtil.getFileExtName(uploadFileName);
		if (fileExtName == null) {
			return null;
		}
		// 生成临时文件
		String tmpFilePath = tmpFileNamePrefix + "." + FileUtil.getFileExtName(uploadFileName);
		//写入本地
		this.fileWriteLocal(tmpFileNamePrefix, file);
		return tmpFilePath;
	}

	/**
	 * 文件写入本地
	 * @param file
	 * @return
	 */
	private void fileWriteLocal(String filePath, MultipartFile file) throws MrxuException {
		try {
			file.transferTo(new File(filePath));
		} catch (IOException e) {
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		}
	}

	/**
	 * 获取转码信息
	 * @param fileUrl
	 * @param targetType
	 * @return
	 */
	private TransCodingResultVO findTransInfo(String fileUrl, String targetType) throws MrxuException{

		if(!TransTypeEnum.contains(targetType)){
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, "暂不支持 targetType=" + targetType + " 转码类型！");
		}

		TransCodingResultVO transCodingResultVO = new TransCodingResultVO();
		transCodingResultVO.setRequestUrl(fileUrl);
		transCodingResultVO.setTargetType(targetType);

		//检查fileUrl是否存在
		FilesExample filesExample = new FilesExample();
		filesExample.createCriteria().andUrlLike("%" + fileUrl + "%");
		List<Files> filesList = filesMapper.selectByExample(filesExample);
		if(null == filesList || filesList.isEmpty()){
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		}
		Files requestFile = filesList.get(0);
		String fileId = requestFile.getId();
		String requestFileParentId = requestFile.getParentId();
		String transParentId;
		String originFileName;
		String targetUrl = null;
		if(requestFileParentId.toString().equals("0")){
			//本身就是源文件
			transParentId = requestFile.getId();
			originFileName = requestFile.getFileName();
			if(requestFile.getTransType().equalsIgnoreCase(targetType)){
				targetUrl = requestFile.getUrl();
			}
		} else {
			//属于已经转码的文件
			transParentId = requestFile.getParentId();
			//需要获取源文件，来判断是否是需要的目标格式
			Files filesOrigin = filesMapper.selectByPrimaryKey(transParentId);
			originFileName = filesOrigin.getFileName();
			if(null != filesOrigin && filesOrigin.getTransType().equalsIgnoreCase(targetType)){
				targetUrl = filesOrigin.getUrl();
			}
		}
		if(StringUtils.isEmpty(targetUrl)){
			//查询其他转码满足要求的文件信息
			List<Files> filesTrans = fileCustomService.findTransFileInfo(transParentId);
			for(Files fileTrans : filesTrans){
				if(fileTrans.getType().equals(targetType)){
					targetUrl = fileTrans.getUrl();
					break;
				}
			}
			//依然没找到
			if(StringUtils.isEmpty(targetUrl)){
				//检查转码表，当转码进度小于等于1时，返回转码中的状态
				FileTransScheduleExample fileTransScheduleExample = new FileTransScheduleExample();
				fileTransScheduleExample.createCriteria().andFileIdEqualTo(fileId).andTargetTypeEqualTo(targetType);
				List<FileTransSchedule> transScheduleList = this.fileTransScheduleMapper.selectByExample(fileTransScheduleExample);
				if(null != transScheduleList && !transScheduleList.isEmpty()){
					FileTransSchedule fileTransSchedule = transScheduleList.get(0);
					if(fileTransSchedule.getScheduleNum() <= 1){
						throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, "文件处于转码中，预计3-10分钟，请耐心等待！");
					}
				}
			}
		}
		transCodingResultVO.setOriginId(transParentId);
		transCodingResultVO.setOriginFileName(originFileName);
		transCodingResultVO.setTargetUrl(targetUrl);
		return transCodingResultVO;
	}

	/**
	 * 全路径上传不删除
	 * 
	 * @param file
	 * @return
	 */
	private String uploadFileByFullPathAndNotDelete(String file) {
		boolean isFullPath = true;
		boolean isDelete = false;
		return uploadFileToServer(file, isFullPath, isDelete);
	}

	/**
	 * 获取上传文件信息
	 *     将上传文件删除
	 * @param file
	 * @return
	 */
	public FileRequestSyncDTO findFileRequestInfoSync(MultipartFile file) throws MrxuException{
		//* 源文件名
		String uploadFileName = file.getOriginalFilename();
		//* 拓展名
		String extension = FileUtil.getFileExtName(uploadFileName).toLowerCase();
		if (StringUtils.isEmpty(extension)) {
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		}
		//* 文件大小
		long fileSize = file.getSize();
		if(fileSize <= 0){
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		}
		//* 临时文件
		String filePath = this.config.getLocalPath() + String.valueOf(vestaService.genId()) + "." + extension;
		//* 写入本地
		this.fileWriteLocal(filePath, file);
		//* 计算MD5
		String unionKey = FileUtil.calculateFileMD5(new File(filePath), false);
		//* 获取文件类型
		String type = FileTypeUtil.findResTypeEnum(uploadFileName);
		FileTypeExtensionExample fileTypeExtensionExample = new FileTypeExtensionExample();
		fileTypeExtensionExample.createCriteria().andExtensionEqualTo(extension);
		List<FileTypeExtension> fileTypeExtensionList = this.fileTypeExtensionMapper.selectByExample(fileTypeExtensionExample);
		if(null != fileTypeExtensionList && !fileTypeExtensionList.isEmpty()){
			FileTypeExtension fileTypeExtension = fileTypeExtensionList.get(0);
			type = fileTypeExtension.getType();
		}

		//封装上传文件信息
		FileRequestSyncDTO fileRequestInfo = new FileRequestSyncDTO();
		fileRequestInfo.setFileName(uploadFileName);
		fileRequestInfo.setExtension(extension);
		fileRequestInfo.setFileDir(this.config.getLocalPath());
		fileRequestInfo.setFileSize(fileSize);
		fileRequestInfo.setFilePath(filePath);
		fileRequestInfo.setUnionKey(unionKey);
		fileRequestInfo.setFileType(type);
		return fileRequestInfo;
	}

	/**
	 * 初始化 storageClient1
	 */
	private synchronized void init() {
		try {
			if (storageClient1 == null) {
				ClientGlobal.initByProperties(config.getProperties());
				TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
				TrackerServer trackerServer = trackerClient.getConnection();
				if (trackerServer == null)
					throw new IllegalStateException("getConnection return null");
				StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
				if (storageServer == null)
					throw new IllegalStateException("getStoreStorage return null");
				storageClient1 = new StorageClient1(trackerServer, storageServer);
			}
		} catch (Exception e) {
			LOG.error(e);
			throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
		}
	}
}
