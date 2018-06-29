package com.mrxu.cloud.file.service.process.gls;

import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.domain.process.sync.FileRequestSyncDTO;
import com.mrxu.cloud.file.domain.process.sync.FileResponseSyncDTO;
import com.mrxu.cloud.file.service.file.IFileService;
import com.mrxu.cloud.file.service.process.IFileProcessService;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * PDF转图文集
 * @author ifocusing-xuzhiwei
 * @since 2018/6/19
 */
@Service("pdfConvertGlsProcess")
public class PdfConvertGlsProcessServiceImpl implements IFileProcessService<FileRequestSyncDTO, FileResponseSyncDTO> {

    @Resource(name = "fdfsService")
    IFileService fdfsService;

    @Override
    public FileResponseSyncDTO process(FileRequestSyncDTO fileRequestSync) throws MrxuException {
        return null;
       /* //源文件地址
        String filePath = fileRequestSync.getFilePath();
        //图文集拓展
        FileResponseExtendSyncDTO picAlbumExtend = new FileResponseExtendSyncDTO();
        //生成图文集
        picAlbumExtend.setExtendType(TransTypeEnum.PicAlbum.getItemValue());
        //图文集封面缩略图
        String thumbnail = null;

        List<FileResponseExtendDetailSyncDTO> targetList = new ArrayList<>();
        PDDocument doc = null;
        try {
            doc = PDDocument.load(new File(filePath));
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 200);
                String imagePath = FileUtil.getFileNameWithoutSuffix(filePath) + "_" + i + ".png";
                this.compressPic(image, imagePath, 0.4f);
                //计算转成的图片MD5
                String unionCode = FileUtil.calculateFileMD5(new File(imagePath), false);
                //转成图片的缩略图
                String imageThumbnailPath = FileUtil.getFileNameWithoutSuffix(filePath) + "_tmp_" + i + ".png";
                try {
                    double scale = MathUtil.getImageScale(imagePath);
                    if(scale == 0){
                        scale = 0.2D;
                    }
                    Thumbnails.of(imagePath).scale(scale).outputQuality(1f).toFile(imageThumbnailPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //PDF转成图片 -- 上传全路径并删除
                String imageUrl = fdfsService.uploadFileToServer(imagePath, true, true);
                //PDF转成图片缩略图 -- 上传全路径并删除
                String imageThumbnailUrl = fdfsService.uploadFileToServer(imageThumbnailPath,  true, true);
                //第一张 --> pdf的缩略图
                if(0 == i){
                    //返回数据 -- 缩略图
                    thumbnail = imageThumbnailUrl;
                }
                FileResponseExtendDetailSyncDTO target = new FileResponseExtendDetailSyncDTO();
                target.setTargetUrl(imageUrl);
                target.setThumbnail(imageThumbnailUrl);
                target.setUnionKey(unionCode);
                target.setTargetType(TransTypeEnum.PicAlbum.getItemValue());
                targetList.add(target);
            }
            //图文集列表
            picAlbumExtend.setTargetList(targetList);
        } catch (IOException e) {
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, "PDF文件解析失败" );
        } finally {
            if(null != doc){
                try {
                    doc.close();
                } catch (IOException e) {
                    throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, "PDF文件解析失败" );
                }
            }
            //删除本地临时存放的pdf文件
            this.deleteFile(fileRequestSync.getFilePath());
        }

        FileResponseSyncDTO fileResponseSync = new FileResponseSyncDTO();
        BeanUtils.copyProperties(fileRequestSync, fileResponseSync);
        fileResponseSync.setExtend(picAlbumExtend);
        fileResponseSync.setThumbnail(thumbnail);
        return fileResponseSync;*/
    }

    /**
     * 图片压缩
     * @param srcBufferedImage
     * @param descFilePath
     * @param quality 压缩质量
     * @return
     * @throws IOException
     */
    public static boolean compressPic(BufferedImage srcBufferedImage, String descFilePath, float quality) throws IOException {

        FileOutputStream out;
        ImageWriter imgWriter;
        ImageWriteParam imgWriteParams;
        // 指定写图片的方式为 jpg
        imgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数quality是u取值0~1范围内，
        imgWriteParams.setCompressionQuality(quality);
        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
        //ColorModel colorModel = srcBufferedImage.getColorModel();
        //imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
        try {
            if (null == srcBufferedImage) {
                return false;
            } else {
                out = new FileOutputStream(descFilePath);
                imgWriter.reset();
                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
                // OutputStream构造
                imgWriter.setOutput(ImageIO.createImageOutputStream(out));
                // 调用write方法，就可以向输入流写图片
                imgWriter.write(null, new IIOImage(srcBufferedImage, null, null), imgWriteParams);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 删除文件
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName){
        File file = new File(fileName);
        if(file.isFile() && file.exists()){
            Boolean succeedDelete = file.delete();
            if(succeedDelete){
                System.out.println("删除单个文件"+fileName+"成功！");
                return true;
            }
            else{
                System.out.println("删除单个文件"+fileName+"失败！");
                return true;
            }
        }else{
            System.out.println("删除单个文件"+fileName+"失败！");
            return false;
        }
    }
}
