package com.app.college.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.college.exception.ConfigDataNotFound;
import com.app.college.models.ClassRoom;
import com.app.college.repository.ClassRoomRepository;
import com.app.college.service.ClassRoomService;

@Service
public class ClassRoomServiceImpl implements ClassRoomService{
	
	@Autowired
	private ClassRoomRepository roomRepo;

	@Override
	public ClassRoom createRoom( ClassRoom room) {
		
		return roomRepo.save(room);
	}

	@Override
	public ClassRoom updateRoomById(Long id, ClassRoom room) {
		roomRepo.findById(id).orElseThrow(() -> new ConfigDataNotFound("No class room found with id : "+id));
		room.setId(id);
		
		return roomRepo.save(room);
	}

	@Override
	public ClassRoom getRoomById(Long id) {
		return roomRepo.findById(id).orElseThrow(() -> new ConfigDataNotFound("No class room found with id : "+id));
	}

	@Override
	public Page<ClassRoom> getAllRooms(Pageable pageable) {
		return roomRepo.findAll(pageable);
	}

	@Override
	public String deleteRoomById(Long id) {
		roomRepo.deleteById(id);
		return "deleted";
	}

}
