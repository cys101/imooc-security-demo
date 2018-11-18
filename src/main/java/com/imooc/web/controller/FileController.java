package com.imooc.web.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




import com.imooc.dto.FileInfo;

@RestController
@RequestMapping("/file")
public class FileController {
	private final static String folder="D:/workspace/imooc-security-demo/src/main/java/com/imooc/web/controller";
	@PostMapping
	public FileInfo update(MultipartFile file) throws Exception{
		 System.out.println(file.getName());
		 System.out.println(file.getSize());
		 System.out.println(file.getOriginalFilename());
		 File localFile=new File(folder,new Date().getTime()+".txt");
		 file.transferTo(localFile);
		 return new FileInfo(localFile.getAbsolutePath());
		 
	}
	@GetMapping("/{id}")
	public void download(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		try (InputStream in=new FileInputStream(new File(folder,id+".txt"));
			 OutputStream out=response.getOutputStream();	)
		{
			
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			IOUtils.copy(in, out);
			out.flush();
		} 
	}

}
