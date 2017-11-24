package com.society.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
