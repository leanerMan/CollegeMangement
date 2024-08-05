package com.app.college.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.college.DTO.TimetableDto;
import com.app.college.models.TimeTable;

public interface TimetableRepository extends JpaRepository<TimeTable, Long> {

	@Query(value = "SELECT * FROM timetables WHERE (LOWER(room_no) LIKE CONCAT('%', LOWER(:search), '%') OR room_no IS NULL)   OR (LOWER(semester) LIKE CONCAT('%', LOWER(:search), '%') OR semester IS NULL)   OR (LOWER(subject) LIKE CONCAT('%', LOWER(:search), '%') OR subject IS NULL)  OR (LOWER(teacher) LIKE CONCAT('%', LOWER(:search), '%') OR teacher IS NULL)", nativeQuery = true, countQuery = "SELECT COUNT(*) FROM timetables WHERE (LOWER(room_no) LIKE CONCAT('%', LOWER(:search), '%') OR room_no IS NULL)   OR (LOWER(semester) LIKE CONCAT('%', LOWER(:search), '%') OR semester IS NULL)   OR (LOWER(subject) LIKE CONCAT('%', LOWER(:search), '%') OR subject IS NULL)  OR (LOWER(teacher) LIKE CONCAT('%', LOWER(:search), '%') OR teacher IS NULL)")
	Page<TimeTable> getTimetableBySearch(@Param("search") String search, Pageable pageable);

	@Query("select new  com.app.college.DTO.TimetableDto( "
	+"t.id,t.course.id,t.course.courseName,t.course.courseCode," 
	+"t.sem,t.stream,t.paper,"
	+"t.room.id,t.room.roomNo,t.startTime,"
	+"t.endTime,t.teacher.id,t.teacher.empId,t.teacher.firstName,"
	+"t.teacher.lastName,t.day,t.theme ) from TimeTable t left join t.course"
	+" left join t.teacher left join t.room")
	Page<TimetableDto> findAllDto(Pageable pageable);
	
	@Query("select new  com.app.college.DTO.TimetableDto( "
	+"t.id,t.course.id,t.course.courseName,t.course.courseCode," 
	+"t.sem,t.stream,t.paper,"
	+"t.room.id,t.room.roomNo,t.startTime,"
	+"t.endTime,t.teacher.id,t.teacher.empId,t.teacher.firstName,"
	+"t.teacher.lastName,t.day,t.theme ) from TimeTable t left join t.course"
	+" left join t.teacher left join t.room")
	List<TimetableDto> findAllDto();

	@Query("SELECT NEW com.app.college.DTO.TimetableDto("
	        + "t.id, t.course.id, t.course.courseName, t.course.courseCode,"
	        + "t.sem, t.stream, t.paper,"
	        + "t.room.id, t.room.roomNo,t.room.block,t.room.building,t.room.floor, t.startTime,"
	        + "t.endTime, t.teacher.id, t.teacher.empId, t.teacher.firstName,"
	        + "t.teacher.lastName, t.day, t.theme) "
	        + "FROM TimeTable t "
	        + "LEFT JOIN t.course "
	        + "LEFT JOIN t.teacher "
	        + "LEFT JOIN t.room "
	        + "WHERE (t.course.id = :courseId OR :courseId IS NULL) "
	        + "AND (t.sem = :sem OR :sem IS NULL) "
	        + "AND (t.stream = :stream OR :stream IS NULL) "
	        + "AND (t.paper = :paper OR :paper IS NULL) "
	        + "AND (t.day = :day OR :day IS NULL) "
	        + "AND (t.department.id = :departmentId OR :departmentId IS NULL) "
	        + "AND (t.teacher.username = :teacherUsername OR :teacherUsername IS NULL) ")
	List<TimetableDto> findAllDto(Long courseId, String sem, String stream, String paper,String day,String teacherUsername, Long departmentId );

	@Query("SELECT NEW com.app.college.DTO.TimetableDto("
	        + "t.id, t.course.id, t.course.courseName, t.course.courseCode,"
	        + "t.sem, t.stream, t.paper,"
	        + "t.room.id, t.room.roomNo,t.room.block,t.room.building,t.room.floor, t.startTime,"
	        + "t.endTime, t.teacher.id, t.teacher.empId, t.teacher.firstName,"
	        + "t.teacher.lastName, t.day, t.theme) "
	        + "FROM TimeTable t "
	        + "LEFT JOIN t.course "
	        + "LEFT JOIN t.teacher "
	        + "LEFT JOIN t.room "
	        + "WHERE (t.day = :day OR :day IS NULL) "
	        + "AND (t.teacher.username = :username OR :username IS NULL) ")
	List<TimetableDto> getTeacherTotalSubLecDayWise(String day, String username);

}
