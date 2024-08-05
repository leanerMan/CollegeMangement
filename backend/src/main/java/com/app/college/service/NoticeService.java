package com.app.college.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.app.college.DTO.NoticeDto;
import jakarta.validation.Valid;

public interface NoticeService {

	NoticeDto createNotice(@Valid NoticeDto noticeDto, MultipartFile noticeImgPdfFile);

	Page<NoticeDto> getAllNotice(Pageable pageable, Long categoryId, Long departmentId);

	NoticeDto getNoticeById(Long id);

	String deleteNoticeById(Long id);

	List<Object> getAllNoticeForApp(Pageable pageable, Long categoryId, Long departmentId);

}
