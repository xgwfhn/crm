package com.demo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.model.UploadFile;
import com.demo.service.FileService;

@Controller
@RequestMapping(value = "/fileUpload")
public class FileUploadController {
	 private static Logger logger = Logger.getLogger(FileUploadController.class);
	 
	 @Autowired
	 private FileService fileService;
	 
	 /**跳转到文件上传页面_1
		 * @param request
		 * @param model
		 * @return
		 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public String fileUpload(HttpServletRequest request, Model model) {
		return "fileUpload";
	}
	
	/**跳转到文件上传页面_2
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/fileUpload1", method = RequestMethod.GET)
	public String fileUpload1(HttpServletRequest request, Model model) {
		return "fileUpload1";
	}
		
	@RequestMapping("/upload")
    public void index(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("获取上传文件...");
        try {
            UploadFile uploadFiles = fileService.saveFile(request);

            //renderJsonDone(response, uploadFiles);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.getMessage(), e);
            e.printStackTrace();
           // renderJsonError(response, "文件上传失败");
        }
    }
}
