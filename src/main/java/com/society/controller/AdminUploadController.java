package com.society.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.society.constant.FileType;
import com.society.model.domain.AdminUploadDomain;
import com.society.service.AdminUploadService;

@Controller
public class AdminUploadController {
	
	private static final Logger logger = LogManager.getLogger(AdminUploadController.class);
	
	@Autowired
	private AdminUploadService uploadService;
	
	@RequestMapping(value = "uploadFile", method = RequestMethod.GET)
	public ModelAndView getCommonFile(HttpSession session) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		
		AdminUploadDomain uploadDomain = new AdminUploadDomain();
		uploadDomain.setSocietyId(societyId);
		
		List<AdminUploadDomain> uploadFileList = uploadService.getFile(uploadDomain);
		
		ModelAndView modelAndView = new ModelAndView("uploadFile", "adminUploadDomain", uploadDomain);
		modelAndView.addObject("commonFileList", uploadService.getFileTypeSpecificFile(uploadFileList, FileType.COMMFILE.value()));
		modelAndView.addObject("flatSpecificFileList", uploadService.getFileTypeSpecificFile(uploadFileList, FileType.FLFILE.value()));
		modelAndView.addObject("reportFileList", uploadService.getFileTypeSpecificFile(uploadFileList, FileType.RPT.value()));
		return modelAndView;
	}
	
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public String postUpload(@RequestParam("uploadFile") MultipartFile file, @RequestParam("fileType") String fileType, RedirectAttributes redirectAttributes, HttpSession session) {
		
		String societyName = (String)session.getAttribute("DISPLAYNAME");
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		Integer userId = (Integer)session.getAttribute("USERID");
		
		String rootPath = session.getServletContext().getRealPath("/");
		if(StringUtils.isNotBlank(rootPath)){
			rootPath = rootPath.concat("society-file\\");
			
			AdminUploadDomain uploadDomain = new AdminUploadDomain();
			uploadDomain.setSocietyName(societyName);
			uploadDomain.setSocietyId(societyId);
			uploadDomain.setUserId(userId);
			uploadDomain.setFileType(fileType);
			
			if(uploadService.uploadFile(rootPath, file, uploadDomain)) {
				uploadService.saveUploadFileDetails(uploadDomain);
				redirectAttributes.addFlashAttribute("uploadSuccess", true);
			}
			else {
				redirectAttributes.addFlashAttribute("uploadSuccess", false);
			}
		}
		return "redirect:/uploadFile";
	}
	
	@RequestMapping(value = "viewUploadFile", method = RequestMethod.GET)
	public void viewUploadFile(@RequestParam(value="id", required=true)Integer fileId, HttpSession session, HttpServletResponse response) {
		
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		String societyName = (String)session.getAttribute("DISPLAYNAME"); 
		
		AdminUploadDomain uploadDomain = new AdminUploadDomain();
		uploadDomain.setFileId(fileId);
		uploadDomain.setSocietyId(societyId);
		uploadDomain.setSocietyName(societyName);
		
		InputStream fileInputStream = null;
		File societyFile = null;
		try {
			String rootPath = session.getServletContext().getRealPath("/");
			if(StringUtils.isNotBlank(rootPath)){
				
				rootPath = rootPath.concat("society-file\\");
				uploadService.getUploadFile(uploadDomain);
				if(uploadDomain.getFileName() != null) {
					rootPath = rootPath.concat(uploadDomain.getSocietyName() +"\\" + uploadDomain.getFileName());
					societyFile = new File(rootPath);
				}
				else
					throw new Exception("File name in database row is empty");
				
			}
			if(societyFile != null) {
				
				String mimeType= URLConnection.guessContentTypeFromName(uploadDomain.getFileName());
		        if(mimeType==null){
		            logger.info("mimetype is not detectable, will take default");
		            mimeType = "application/octet-stream";
		        }
		        response.setContentType(mimeType);
		        response.setHeader("Content-disposition", "attachment; filename=" + uploadDomain.getFileName());
				
				fileInputStream =new BufferedInputStream(new FileInputStream(societyFile));
				FileCopyUtils.copy(fileInputStream, response.getOutputStream());
				

		       
		        //response.getOutputStream().flush();
			}
		} 
		catch (FileNotFoundException e) {
			logger.error("File Not Found " + e.getMessage());
		}
		catch (IOException e) {
			logger.error("IO Exception is occurred " + e.getMessage());
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		finally{
			if(fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}
	
	@RequestMapping(value = "deleteUploadFile", method = RequestMethod.GET)
	public String deleteUploadFile(@RequestParam(value="id", required=true)Integer fileId, HttpSession session, RedirectAttributes redirectAttributes) {
		Integer societyId = (Integer)session.getAttribute("SOCIETYID");
		String societyName = (String)session.getAttribute("DISPLAYNAME"); 
		
		AdminUploadDomain uploadDomain = new AdminUploadDomain();
		uploadDomain.setFileId(fileId);
		uploadDomain.setSocietyId(societyId);
		uploadDomain.setSocietyName(societyName);
		
		File societyFile = null;
		try {
			String rootPath = session.getServletContext().getRealPath("/");
			if(StringUtils.isNotBlank(rootPath)){
				
				rootPath = rootPath.concat("society-file\\");
				uploadService.getUploadFile(uploadDomain);
				if(uploadDomain.getFileName() != null) {
					rootPath = rootPath.concat(uploadDomain.getSocietyName() +"\\" + uploadDomain.getFileName());
					societyFile = new File(rootPath);
				}
				else
					throw new Exception("File name in database row is empty");
				
			}
			if(societyFile != null && (societyFile.delete() && uploadService.deleteFile(uploadDomain)))
				redirectAttributes.addFlashAttribute("deleteSuccess", true);
			else
				redirectAttributes.addFlashAttribute("deleteSuccess", false);
		}
		catch(Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/uploadFile";
	}
}
