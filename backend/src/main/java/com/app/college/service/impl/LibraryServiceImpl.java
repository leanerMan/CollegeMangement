package com.app.college.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.college.DTO.LibraryDto;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.models.Library;
import com.app.college.repository.LibraryRepository;
import com.app.college.service.LibraryService;

import jakarta.validation.Valid;

@Service
public class LibraryServiceImpl implements LibraryService {
	
	private LibraryRepository libraryRepository;

	public LibraryServiceImpl(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	@Override
	public Library createLibrary(@Valid LibraryDto libraryDtoReq) {
		Library library = new Library();
        BeanUtils.copyProperties(libraryDtoReq, library);
        return libraryRepository.save(library);
	}

	@Override
	public Page<Library> getAllLibrary(Pageable pageable) {
		 return libraryRepository.findAll(pageable);
	}

	@Override
	public Library updateStudentById(Long id, @Valid LibraryDto libraryDtoReq) {
		if (libraryRepository.existsById(id)) {
			Library library = new Library();
	        BeanUtils.copyProperties(libraryDtoReq, library);
	        library.setId(id);
			return libraryRepository.save(library);
		}
		throw new ConfigDataNotFound("No Department found with id: " + id);
	}

	@Override
	public String deleteLibraryById(Long id) {
		if (libraryRepository.existsById(id)) {
			libraryRepository.deleteById(id);
			return "Library deleted with id: " + id;
		}
		throw new ConfigDataNotFound("No Library found with id: " + id);
	}

	@Override
	public Library getLibraryById(Long id) {
		if (libraryRepository.existsById(id)) {
			 return libraryRepository.findById(id).get();
		}
		throw new ConfigDataNotFound("No Library found with id: " + id);
	}

	@Override
	public Page<Library> getLibraryBySearch(String search, Pageable pageable) {
		Page<Library> libraryBySearch = libraryRepository.getLibraryBySearch(search, pageable);
		//libraryBySearch.forEach(System.out::println);
		return libraryBySearch;
	}

}
