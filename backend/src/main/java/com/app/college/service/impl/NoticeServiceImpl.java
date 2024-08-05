package com.app.college.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.app.college.DTO.NoticeDto;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.models.Department;
import com.app.college.models.Notice;
import com.app.college.repository.DepartmentRepository;
import com.app.college.repository.NoticeRepository;
import com.app.college.service.NoticeService;
import com.app.college.util.DateTimeGenerator;

import jakarta.validation.Valid;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private DepartmentRepository departmentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Value("${application.bucket.name}")
	private String bucketName;
	
	private AmazonS3 s3Client;

	private NoticeRepository noticeRepo;

	public NoticeServiceImpl(AmazonS3 s3Client, NoticeRepository noticeRepo) {
		this.s3Client = s3Client;
		this.noticeRepo = noticeRepo;
	}



	@Override
    public NoticeDto createNotice(@Valid NoticeDto noticeDto, MultipartFile noticeFile) {
        Notice notice = modelMapper.map(noticeDto, Notice.class);

        if (noticeDto.getMemoNo() == null || noticeDto.getMemoNo().isBlank()) {
        	Department department = departmentRepo.findById(noticeDto.getDepartmentId()).get();
        	notice.setMemoNo(generateMemoNo(department.getDepartment()));
        }
        if(noticeDto.getDatePosted() == null) {
        	notice.setDatePosted(DateTimeGenerator.getAutoLocalDateTime());
        }
        
        try {
            if (noticeFile != null && !noticeFile.isEmpty()) {
                // Create a temporary file
                File tempFile = File.createTempFile("temp", null);
                noticeFile.transferTo(tempFile);
                File uploadedFile = tempFile;
                tempFile.deleteOnExit();
                
                String originalFilename = StringUtils.cleanPath(noticeFile.getOriginalFilename());
                String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
                
                String contentType = noticeFile.getContentType();
                
                // Set attachment filename based on current time and file extension
                String filename = System.currentTimeMillis() + "Nprofile." + extension;
                String key = "notice/" + filename;
                
                notice.setAttachment(filename);
                
                // Set content type for metadata
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(contentType);
                
                // Upload the file to S3
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, uploadedFile);
                putObjectRequest.setMetadata(metadata);
                s3Client.putObject(putObjectRequest);

                uploadedFile.delete();
            } else {
                // If no file is uploaded, set the attachment from DTO
                notice.setAttachment(noticeDto.getAttachment());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Notice save = noticeRepo.save(notice);
        return modelMapper.map(save, NoticeDto.class);
    }

	private String generateMemoNo(String department) {
		String departmentCode = department.length() >= 3 ? department.substring(0, 3).toUpperCase() : "XXX";

        int currentYear = LocalDate.now().getYear();

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10)); 
        }
        return "RU/" + departmentCode + "/" + currentYear + "/" + sb;
	}



	@Override
	public Page<NoticeDto> getAllNotice(Pageable pageable, Long categoryId, Long departmentId) {
	    Page<Notice> noticesPage = noticeRepo.getAllNotice(pageable, categoryId, departmentId); 
	    List<NoticeDto> dtos = new ArrayList<>();
	    
	    for (Notice notice : noticesPage.getContent()) {
	    	NoticeDto dto = modelMapper.map(notice, NoticeDto.class);	        
	        dtos.add(dto);
	    }    
	    return new PageImpl<>(dtos, pageable, noticesPage.getTotalElements()); 
	}



	@Override
	public NoticeDto getNoticeById(Long id) {
		Notice notice = noticeRepo.findById(id).orElseThrow(()-> new ConfigDataNotFound("No Notice found with Id: "+id));
	 	NoticeDto dto = modelMapper.map(notice, NoticeDto.class);
		return dto;
	}



	@Override
	public String deleteNoticeById(Long id) {
		if(noticeRepo.existsById(id)) {
			noticeRepo.deleteById(id);
			return "Notice deleted with id: " + id;
		}
		throw new ConfigDataNotFound("No Notice found with id: " + id);
	}



	@Override
	public List<Object> getAllNoticeForApp(Pageable pageable, Long category, Long department) {
		List<Notice> noticesPage = noticeRepo.getAllNotice(category, department); 
		List<Object> resultList = new ArrayList<>();

	    for (Notice notice : noticesPage) {
	        Map<String, Object> mp = new HashMap<>();
	        Map<String, Object> headerMap = new HashMap<>();
	        Map<String, Object> bodyMap = new HashMap<>();
	        
	        if (notice.getCategory() != null) {
	            headerMap.put("category", notice.getCategory().getCategory());
	            headerMap.put("categoryId", notice.getCategory().getId());
	        }

	        if (notice.getDepartment() != null) {
	            headerMap.put("department", notice.getDepartment().getDepartment());
	            headerMap.put("departmentId", notice.getDepartment().getId());
	        }
	        headerMap.put("memoNo", notice.getMemoNo());
	        headerMap.put("datePosted", notice.getDatePosted());
	        headerMap.put("id", notice.getId());
	        headerMap.put("publishDate", notice.getPublishDate());
	        headerMap.put("status", notice.getStatus());

	        bodyMap.put("title", notice.getTitle());
	        bodyMap.put("content", notice.getContent());
	        bodyMap.put("postedBy", notice.getPostedBy());
	        bodyMap.put("signedBy", notice.getSignedBy());
	        bodyMap.put("attachment", notice.getAttachment());
	        
	        mp.put("header", headerMap);
	        mp.put("body", bodyMap);

	        resultList.add(mp);
	    }   
	    return resultList;
//	    return new PageImpl<>(resultList, pageable, noticesPage.getTotalElements()); 
	}
}