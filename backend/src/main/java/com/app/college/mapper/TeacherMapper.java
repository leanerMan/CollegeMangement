package com.app.college.mapper;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.app.college.DTO.TeacherDtoRequest;
import com.app.college.models.Department;
import com.app.college.models.Teacher;
import com.app.college.util.DateTimeGenerator;

@Component
public class TeacherMapper {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${application.bucket.name}")
	private String bucketName;


	public Teacher toEntityWithPhoto(TeacherDtoRequest teacherDtoRequest, MultipartFile profilePhoto) {
		Teacher teacher = new Teacher();
		teacher.setFirstName(teacherDtoRequest.getFirstName());
		teacher.setLastName(teacherDtoRequest.getLastName());
		teacher.setGender(teacherDtoRequest.getGender());
		teacher.setMobile(teacherDtoRequest.getMobile());
		teacher.setDesignation(teacherDtoRequest.getDesignation());
		if(teacherDtoRequest.getDepartmentId()!=null) {
			teacher.setDepartment(new Department(teacherDtoRequest.getDepartmentId()));
		}
		teacher.setDegree(teacherDtoRequest.getDegree());
		teacher.setEmail(teacherDtoRequest.getEmail());
		teacher.setJoiningDate(teacherDtoRequest.getJoiningDate());
		teacher.setAddress(teacherDtoRequest.getAddress());
		teacher.setEducation(teacherDtoRequest.getEducation());
		teacher.setProfileCreatedAt(DateTimeGenerator.getAutoLocalDateTime());
//		 this is for uploading in s3 bucket
		try {
			// this is for uploading in s3 bucket
			if (profilePhoto != null && !profilePhoto.isEmpty() && !profilePhoto.getOriginalFilename().isEmpty()) {

				// Create a temporary file
				File tempFile = File.createTempFile("temp", null);
				profilePhoto.transferTo(tempFile);

				// Create a File object for the uploaded file
				File imageFile = tempFile;

				tempFile.deleteOnExit();
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentType(profilePhoto.getContentType());
				int lastDotIndex = profilePhoto.getOriginalFilename().lastIndexOf('.');
				String fileExtension = lastDotIndex == -1 ? ""
						: profilePhoto.getOriginalFilename().substring(lastDotIndex + 1);
//            String filename = student.getId() + "_profile_image." + fileExtension;

				String filename = System.currentTimeMillis() + "profile." + fileExtension;
				// Specify the key for the S3 object (e.g., images/my_image.jpg)
				String key = "teachers/" + filename;
				teacher.setProfilePhoto(filename);
				// Create a PutObjectRequest to upload the image to S3
				PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, imageFile);
				putObjectRequest.setMetadata(metadata);
				if (teacher != null) {
					// Upload the image to S3
					s3Client.putObject(putObjectRequest);
				}
				// Optionally, you can delete the local file after uploading to S3
				imageFile.delete();
			} else {
				teacher.setProfilePhoto(teacherDtoRequest.getProfilePhoto());
			}
		} catch (IOException e) {
			// Handle exception (e.g., log error, return error page)
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return teacher;

		// Assuming student profile photos will be saved in the following directory
//		String directoryPath = "D:/college_admin_profilephoto/teachers/";
//
//		if (profilePhoto != null && !profilePhoto.isEmpty() && !profilePhoto.getOriginalFilename().isEmpty()) {
//		    String fileName = profilePhoto.getOriginalFilename();
//		    String extension = fileName.substring(fileName.lastIndexOf("."));
//		    if (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg") || profilePhoto.getSize() <= 1048576) {
//		        String newFileName = System.currentTimeMillis() + "profile" + extension;
//		        File newFile = new File(directoryPath + newFileName);
//		        try (FileOutputStream fos = new FileOutputStream(newFile);
//		             InputStream is = profilePhoto.getInputStream()) {
//		            byte[] buffer = new byte[1024];
//		            int length;
//		            while ((length = is.read(buffer)) > 0) {
//		                fos.write(buffer, 0, length);
//		            }
//		            teacher.setProfilePhoto(newFileName);
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		            throw new RuntimeException("Error occurred while saving profile photo locally");
//		        }
//		    } else {
//		        throw new ConfigDataNotFound("Upload valid image file");
//		    }
//		} else {
//		    teacher.setProfilePhoto(teacherDtoRequest.getProfilePhoto());
////			student.setProfilePhoto(studentDtoRequest.getProfilePhoto().getOriginalFilename());
//		
//		}
//		return teacher;
	}

	public TeacherDtoRequest toDto(Teacher teacher) {
		TeacherDtoRequest teacherDtoRequest = new TeacherDtoRequest();
		teacherDtoRequest.setId(teacher.getId());
		teacherDtoRequest.setFirstName(teacher.getFirstName());
		teacherDtoRequest.setLastName(teacher.getLastName());
		teacherDtoRequest.setGender(teacher.getGender());
		teacherDtoRequest.setMobile(teacher.getMobile());
		teacherDtoRequest.setDesignation(teacher.getDesignation());
		if(teacher.getDepartment()!=null) {
			teacherDtoRequest.setDepartmentId(teacher.getDepartment().getId());
			teacherDtoRequest.setDepartmentName(teacher.getDepartment().getDepartment());
		}
		teacherDtoRequest.setDegree(teacher.getDegree());
		teacherDtoRequest.setEmail(teacher.getEmail());
		teacherDtoRequest.setJoiningDate(teacher.getJoiningDate());
		teacherDtoRequest.setAddress(teacher.getAddress());
		teacherDtoRequest.setEducation(teacher.getEducation());
		teacherDtoRequest.setProfilePhoto(teacher.getProfilePhoto());
		return teacherDtoRequest;
	}

	public Teacher updateToEntity(Long id, TeacherDtoRequest teacherDtoRequest) {
		Teacher teacher = new Teacher();
		teacher.setId(id);
		teacher.setFirstName(teacherDtoRequest.getFirstName());
		teacher.setLastName(teacherDtoRequest.getLastName());
		teacher.setGender(teacherDtoRequest.getGender());
		teacher.setMobile(teacherDtoRequest.getMobile());
		teacher.setDesignation(teacherDtoRequest.getDesignation());
		if(teacherDtoRequest.getDepartmentId()!=null) {
			teacher.setDepartment(new Department(teacherDtoRequest.getDepartmentId()));
		}
		teacher.setDegree(teacherDtoRequest.getDegree());
		teacher.setEmail(teacherDtoRequest.getEmail());
		// LocalDate localdate=LocalDate.parse(teacherDtoRequest.getJoiningDate(),
		// DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//		teacher.setJoiningDate(teacherDtoRequest.getJoiningDate());
		teacher.setJoiningDate(teacherDtoRequest.getJoiningDate());
		teacher.setProfilePhoto(teacherDtoRequest.getProfilePhoto());
		teacher.setAddress(teacherDtoRequest.getAddress());
		teacher.setEducation(teacherDtoRequest.getEducation());
		teacher.setProfileUpdatedDate(DateTimeGenerator.getAutoLocalDateTime());
		return teacher;
	}

}
