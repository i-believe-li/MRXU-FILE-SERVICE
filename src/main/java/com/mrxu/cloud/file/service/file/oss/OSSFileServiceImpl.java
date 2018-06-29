package com.mrxu.cloud.file.service.file.oss;

import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.config.AliyunOSSConfig;
import com.mrxu.cloud.file.domain.file.FileResultVO;
import com.mrxu.cloud.file.domain.trans.TransCodingResultVO;
import com.mrxu.cloud.file.service.file.IFileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * oss的文件服务功能
 * 
 * @author xudaker
 *
 */
@Service("ossService")
public class OSSFileServiceImpl implements IFileService {

	Logger logger = Logger.getLogger(OSSFileServiceImpl.class);
	@Autowired
	private AliyunOSSConfig aliyunOSSConfig;

	@Override
	public FileResultVO uploadFile(MultipartFile file) throws MrxuException {
		return null;
	}

	@Override
	public List<FileResultVO> uploadMultipleFile(MultipartFile[] files) throws MrxuException {
		return null;
	}

	@Override
	public String uploadFileToServer(String file, boolean isFullPath, boolean isDelete) throws MrxuException {
		return null;
	}

	@Override
	public String downloadFile(String url) {
		// 不需要实现
		return null;
	}

	@Override
	public int deleteFile(String key) {
		/*// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(aliyunOSSConfig.getOss_endpoint(), aliyunOSSConfig.getOss_accessKey(),
				aliyunOSSConfig.getOss_secretKey());
		// 删除源文件
		ossClient.deleteObject(aliyunOSSConfig.getOss_bucket(), key);*/
		return 0;
	}

	@Override
	public boolean exist(String key) {
		/*// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(aliyunOSSConfig.getOss_endpoint(), aliyunOSSConfig.getOss_accessKey(),
				aliyunOSSConfig.getOss_secretKey());

		// 默认不存在
		boolean isExist = false;
		try {
			isExist = ossClient.doesObjectExist(aliyunOSSConfig.getOss_bucket(), key);
		} catch (Exception e) {
			logger.error(e);
		}
		return isExist;*/
		return false;
	}

	@Override
	public TransCodingResultVO findTransInfo(String fileUrl, String targetType, String mediaPlatform) throws MrxuException {
		return null;
	}
}
