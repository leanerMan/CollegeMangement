package com.app.college.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.college.models.ClassRoom;

import jakarta.validation.Valid;

public interface ClassRoomService {

	ClassRoom createRoom(@Valid ClassRoom room);

	ClassRoom updateRoomById(Long id, ClassRoom room);

	ClassRoom getRoomById(Long id);

	Page<ClassRoom> getAllRooms(Pageable pageable);

	String deleteRoomById(Long id);

}
