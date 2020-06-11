package edu.spring.ex05.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.spring.ex05.util.FileUploadUtil;
import edu.spring.ex05.util.MediaUtil;

@Controller
public class FileUploadController {
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	// servlet-context.xml 파일에 설정된 문자열 리소스를 주입(inject)
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	@GetMapping(value = "/upload")
	public void uploadGet() {
		logger.info("uploadGet() 호출 : " + uploadPath);
	} // end uploadGet()
	
	@PostMapping(value = "/upload")
	public void uploadPost(MultipartFile file, Model model) {
		logger.info("uploadPost() 호출");
		logger.info("파일 이름 : " + file.getOriginalFilename());
		logger.info("파일 크기 : " + file.getSize());
		
		String savedFile = saveUploadFile(file);
		model.addAttribute("savedFile", savedFile);
	} // end uploadPost()

	@PostMapping(value = "/upload2")
	public String uploadPost2(MultipartFile[] files, Model model) {
		logger.info("uploadPost2() 호출 : 파일 개수 = " + files.length);
		String result = "";
		for(MultipartFile f : files) {
			result += saveUploadFile(f) + " ";
		}
		model.addAttribute("savedFile", result);
		return "upload";
	} // end uploadPost2()
	
	@GetMapping(value = "/upload-ajax")
	public void uploadAjaxGET() {
		logger.info("uploadAjaxGET() 호출");
	} // end uploadAjaxGET()
	
	@PostMapping(value = "/upload-ajax")
	@ResponseBody
	public ResponseEntity<String> uploadAjaxPost(MultipartFile[] files) throws IOException {
		logger.info("uploadAjaxPOST() 호출");
		
		// 파일 하나만 저장
		String result = "";
//		result = FileUploadUtil.saveUploadedFile(
//				uploadPath,
//				files[0].getOriginalFilename(),
//				files[0].getBytes());
		
		// 파일 여러개 저장
		for(MultipartFile f : files) {
			result += FileUploadUtil.saveUploadedFile(
					uploadPath,
					f.getOriginalFilename(),
					f.getBytes()) + " ";
		}
		
		return new ResponseEntity<String> (result, HttpStatus.OK);
	} // end uploadAjaxPost()
	
	@GetMapping(value = "/display")
	public ResponseEntity<byte[]> display(String fileName) throws IOException {
		logger.info("display() 호출");
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		
		String filePath = uploadPath + fileName;
		in = new FileInputStream(filePath);
		
		// 파일 확장자
		String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
		
		// 응답 헤더(response header)에 Content-Type 설정
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaUtil.getMediaType(extension));
		
		// 데이터 전송
		entity = new ResponseEntity<byte[]>(
				IOUtils.toByteArray(in), // 파일에서 읽은 데이터
				httpHeaders, // 응답 헤더
				HttpStatus.OK // 응답 코드
				);
		
		return entity;
	} // end display()
	
	
	private String saveUploadFile(MultipartFile file) {
		// UUID : 업로드하는 파일 이름이 중복되지 않도록
		UUID uuid = UUID.randomUUID();
		String savedName = uuid + "_" + file.getOriginalFilename();
		File target = new File(uploadPath, savedName);
		
		try {
			FileCopyUtils.copy(file.getBytes(), target);
			logger.info("파일 저장 성공 : " + savedName);
			return savedName;
		} catch (IOException e) {
			logger.error("파일 저장 실패");
			return null;
		}
	} // end saveUploadFile()
	
} // end FileUploadController
