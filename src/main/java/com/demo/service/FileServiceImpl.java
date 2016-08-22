package com.demo.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.demo.model.UploadFile;

@Service
public class FileServiceImpl implements FileService {
	private static Logger logger = Logger.getLogger(FileService.class);

    public UploadFile saveFile(HttpServletRequest request) throws IOException {
        logger.debug("获取上传文件...");

        // 转换为文件类型的request
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 获取对应file对象
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        Iterator<String> fileIterator = multipartRequest.getFileNames();

        // 获取项目的相对路径（http://localhost:8080/file）
        String requestURL = request.getRequestURL().toString();
        String prePath = requestURL.substring(0, requestURL.indexOf("/"));

        while (fileIterator.hasNext()) {
            String fileKey = fileIterator.next();
            logger.debug("文件名为:" + fileKey);

            // 获取对应文件
            MultipartFile multipartFile = fileMap.get(fileKey);

            if (multipartFile.getSize() != 0L) {

                //validateImage(multipartFile);

                // 调用saveImage方法保存
                UploadFile file = saveImage(multipartFile);
                file.setPrePath(prePath);//设置绝对路径

                return file;
            }
        }

        return null;
    }
    
    private UploadFile saveImage(MultipartFile image) throws IOException {
        String originalFilename = image.getOriginalFilename();
        logger.debug("文件原始名称为:" + originalFilename);

        String contentType = image.getContentType();
        String type = contentType.substring(contentType.indexOf("/") + 1);//获取后缀名类型
        String fileName = new Random().nextInt(100) + "." + type;
  
        // 封装了一个简单的file对象，增加了几个属性
        UploadFile file = new UploadFile("C:/Users/Administrator/Desktop/文件上传/", fileName);
        file.setContentType(contentType);
        logger.debug("文件保存路径:" + file.getSaveDirectory());

        // 通过org.apache.commons.io.FileUtils的writeByteArrayToFile对图片进行保存
        FileUtils.writeByteArrayToFile(file.getFile(), image.getBytes());

        return file;
    }

}
