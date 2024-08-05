package com.app.college.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.college.DTO.LibraryDto;
import com.app.college.models.Library;

import jakarta.validation.Valid;

public interface LibraryService {

	Library createLibrary(@Valid LibraryDto libraryDtoReq);

	Page<Library> getAllLibrary(Pageable pageable);

	Library updateStudentById(Long id, @Valid LibraryDto libraryDtoReq);

	String deleteLibraryById(Long id);

	Library getLibraryById(Long id);

	Page<Library> getLibraryBySearch(String search, Pageable pageable);

}
