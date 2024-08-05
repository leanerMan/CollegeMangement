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
import com.app.college.DTO.StudentDtoRequest;
import com.app.college.models.Course;
import com.app.college.models.Department;
import com.app.college.models.Student;
import com.app.college.util.DateTimeGenerator;

@Component
public class StudentMapper {

	@Value("${application.bucket.name}")
	private String bucketName;

	@Autowired
	private AmazonS3 s3Client;

//	public Student toEntity(StudentDtoRequest studentDtoRequest) {
//		Student student = new Student();
//		// student.setId(studentDtoRequest.getId());
//		student.setFirstName(studentDtoRequest.getFirstName());
//		student.setLastName(studentDtoRequest.getLastName());
//		student.setGender(studentDtoRequest.getGender());
//		student.setMobile(studentDtoRequest.getMobile());
//		student.setDesignation(studentDtoRequest.getDesignation());
//		student.setDepartments(studentDtoRequest.getDepartments());
//		student.setEmail(studentDtoRequest.getEmail());
////		LocalDate localDate = LocalDate.parse(studentDtoRequest.getAdmissionDate());
////		student.setAdmissionDate(localDate);
//		student.setAdmissionDate(studentDtoRequest.getAdmissionDate());
//		student.setProfilePhoto(studentDtoRequest.getProfilePhoto());
////		student.setProfilePhoto(studentDtoRequest.getProfilePhoto().getOriginalFilename());
//		student.setAddress(studentDtoRequest.getAddress());
//		student.setEducation(studentDtoRequest.getEducation());
//		student.setProfileCreatedAt(DateTimeGenerator.getAutoLocalDateTime());
//		return student;
//	}

//	public Student updateToEntity( StudentDtoRequest studentDtoRequest) {
//		Student student = new Student();
//		student.setId(studentDtoRequest.getId());
//		student.setFirstName(studentDtoRequest.getFirstName());
//		student.setLastName(studentDtoRequest.getLastName());
//		student.setGender(studentDtoRequest.getGender());
//		student.setMobile(studentDtoRequest.getMobile());
//		student.setDesignation(studentDtoRequest.getDesignation());
//		student.setDepartments(studentDtoRequest.getDepartments());
//		student.setEmail(studentDtoRequest.getEmail());
////		LocalDate localDate = LocalDate.parse(studentDtoRequest.getAdmissionDate());
////		student.setAdmissionDate(localDate);
//		student.setAdmissionDate(studentDtoRequest.getAdmissionDate());
//
//		student.setProfilePhoto(studentDtoRequest.getProfilePhoto());
//		// student.setProfilePhoto(studentDtoRequest.getProfilePhoto().getOriginalFilename());
//		student.setAddress(studentDtoRequest.getAddress());
//		student.setEducation(studentDtoRequest.getEducation());
//		student.setProfileUpdatedDate(DateTimeGenerator.getAutoLocalDateTime());
//		return student;
//	}

	public Student toEntityWithProfilePhoto(StudentDtoRequest studentDtoRequest, MultipartFile profilePhotoFile,MultipartFile profileSignatureFile) {
		Student student = new Student();
		student.setStudentName(studentDtoRequest.getStudentName());
		student.setFathersName(studentDtoRequest.getFathersName());
		student.setMothersName(studentDtoRequest.getMothersName());
		student.setGender(studentDtoRequest.getGender());
		student.setMobile(studentDtoRequest.getMobile());
		student.setAadhaarNo(studentDtoRequest.getAadhaarNo());
		student.setAdmissionDate(studentDtoRequest.getAdmissionDate());
		if(studentDtoRequest.getDepartmentId()!=null) {
			student.setDepartment(new Department(studentDtoRequest.getDepartmentId()));
		}
		student.setEmail(studentDtoRequest.getEmail());
		student.setDob(studentDtoRequest.getDob());
		student.setCategory(studentDtoRequest.getCategory());
		student.setAltMobile(studentDtoRequest.getAltMobile());
		if(studentDtoRequest.getCourseId()!=null) {
			student.setCourse(new Course(studentDtoRequest.getCourseId()));
		}	
		student.setSem(studentDtoRequest.getSem());
		student.setStream(studentDtoRequest.getStream());
		student.setPaper(studentDtoRequest.getPaper());
		student.setPAddress(studentDtoRequest.getPAddress());
		student.setPCity(studentDtoRequest.getPCity());
		student.setPPin(studentDtoRequest.getPPin());
		student.setPState(studentDtoRequest.getPState());
		student.setCAddress(studentDtoRequest.getCAddress());
		student.setCCity(studentDtoRequest.getCCity());
		student.setCPin(studentDtoRequest.getCPin());
		student.setCState(studentDtoRequest.getCState());
		student.setCollege(studentDtoRequest.getCollege());
		student.setMaritalStatus(studentDtoRequest.getMaritalStatus());
		student.setProfileSignature(studentDtoRequest.getProfileSignature());
		student.setAltMobile(studentDtoRequest.getAltMobile());
		student.setBloodGroup(studentDtoRequest.getBloodGroup());
		student.setRollNo(studentDtoRequest.getRollNo());
//		LocalDate localDate = LocalDate.parse(studentDtoRequest.getAdmissionDate());
//		student.setAdmissionDate(localDate);
		student.setProfileCreatedAt(DateTimeGenerator.getAutoLocalDateTime());
//		try {
//			// this is for uploading in s3 bucket
//			if (profilePhotoFile != null && !profilePhotoFile.isEmpty() && !profilePhotoFile.getOriginalFilename().isEmpty()) {
//
//				// Create a temporary file
//				File tempFile = File.createTempFile("temp", null);
//				profilePhotoFile.transferTo(tempFile);
//
//				// Create a File object for the uploaded file
//				File imageFile = tempFile;
//
//				tempFile.deleteOnExit();
//				ObjectMetadata metadata = new ObjectMetadata();
//				metadata.setContentType(profilePhotoFile.getContentType());
//				int lastDotIndex = profilePhotoFile.getOriginalFilename().lastIndexOf('.');
//				String fileExtension = lastDotIndex == -1 ? ""
//						: profilePhotoFile.getOriginalFilename().substring(lastDotIndex + 1);
////            String filename = student.getId() + "_profile_image." + fileExtension;
//
//				String filename =System.currentTimeMillis() + "profile." + fileExtension;
//				// Specify the key for the S3 object (e.g., images/my_image.jpg)
//				String key = "students/" + filename;
//				student.setProfilePhoto(filename);
//				// Create a PutObjectRequest to upload the image to S3
//				PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, imageFile);
//				putObjectRequest.setMetadata(metadata);
//				if (student != null) {
//					// Upload the image to S3
//					s3Client.putObject(putObjectRequest);
//				}
//				// Optionally, you can delete the local file after uploading to S3
//				imageFile.delete();
//			} else {
//				student.setProfilePhoto(studentDtoRequest.getProfilePhoto());
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		try {
		    // Handling profilePhotoFile
		    if (profilePhotoFile != null && !profilePhotoFile.isEmpty() && !profilePhotoFile.getOriginalFilename().isEmpty()) {
		        File tempPhotoFile = File.createTempFile("temp_photo", null);
		        profilePhotoFile.transferTo(tempPhotoFile);

		        File photoFile = tempPhotoFile;

		        tempPhotoFile.deleteOnExit();
		        ObjectMetadata photoMetadata = new ObjectMetadata();
		        photoMetadata.setContentType(profilePhotoFile.getContentType());
		        int photoLastDotIndex = profilePhotoFile.getOriginalFilename().lastIndexOf('.');
		        String photoFileExtension = photoLastDotIndex == -1 ? "" : profilePhotoFile.getOriginalFilename().substring(photoLastDotIndex + 1);

		        String photoFilename = System.currentTimeMillis() + "profile." + photoFileExtension;
		        String photoKey = "students/" + photoFilename;
		        student.setProfilePhoto(photoFilename);

		        PutObjectRequest photoPutObjectRequest = new PutObjectRequest(bucketName, photoKey, photoFile);
		        photoPutObjectRequest.setMetadata(photoMetadata);
		        if (student != null) {
		            s3Client.putObject(photoPutObjectRequest);
		        }

		        photoFile.delete();
		    } else {
		        student.setProfilePhoto(null);
		    }

		    // Handling profileSignatureFile
		    if (profileSignatureFile != null && !profileSignatureFile.isEmpty() && !profileSignatureFile.getOriginalFilename().isEmpty()) {
		        File tempSignatureFile = File.createTempFile("temp_signature", null);
		        profileSignatureFile.transferTo(tempSignatureFile);

		        File signatureFile = tempSignatureFile;

		        tempSignatureFile.deleteOnExit();
		        ObjectMetadata signatureMetadata = new ObjectMetadata();
		        signatureMetadata.setContentType(profileSignatureFile.getContentType());
		        int signatureLastDotIndex = profileSignatureFile.getOriginalFilename().lastIndexOf('.');
		        String signatureFileExtension = signatureLastDotIndex == -1 ? "" : profileSignatureFile.getOriginalFilename().substring(signatureLastDotIndex + 1);

		        String signatureFilename = System.currentTimeMillis() + "signature." + signatureFileExtension;
		        String signatureKey = "students/" + signatureFilename;
		        student.setProfileSignature(signatureFilename);

		        PutObjectRequest signaturePutObjectRequest = new PutObjectRequest(bucketName, signatureKey, signatureFile);
		        signaturePutObjectRequest.setMetadata(signatureMetadata);
		        if (student != null) {
		            s3Client.putObject(signaturePutObjectRequest);
		        }

		        signatureFile.delete();
		    } else {
		        student.setProfileSignature(null);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (Exception e) {
		    e.printStackTrace();
		}

		
		return student;

//			String FileName = profilePhoto.getOriginalFilename();
//			String extension = FileName.substring(FileName.lastIndexOf("."));
//			if(extension == "jpg" || extension =="jpeg" || profilePhoto.getSize() <= 1048576) {
////				String newFileName = studentDtoRequest.getId()+"profile"+ extension;
//				String newFileName = "stu"+ "_"+System.currentTimeMillis()+"profile"+ extension;
//				profilePhoto.getOriginalFilename().replace(FileName, newFileName);
//				student.setProfilePhoto(newFileName);
//				try {
////				String key = "Cust" + "/" +studentDtoRequest.getId()+"/"+ newFileName;
////				String key = "Stu" + "/" +System.currentTimeMillis()+"/"+ newFileName;
//				String key = "students" + "/"  +newFileName;
//				 ObjectMetadata metadata = new ObjectMetadata();
//				 System.out.println(metadata);
//		        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, profilePhoto.getInputStream(), null);
//		        s3Client.putObject(putObjectRequest);		
//				}catch(IOException e) {
//					e.printStackTrace();
//				}
//				}else {
//					throw new ConfigDataNotFound("Upload valid image file");
//				}
//			}else {
//				student.setProfilePhoto(studentDtoRequest.getProfilePhoto());
//			}
//			return student;

		// Assuming student profile photos will be saved in the following directory
//		String directoryPath = "D:/college_admin_profilephoto/students/";
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
//		            student.setProfilePhoto(newFileName);
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		            throw new RuntimeException("Error occurred while saving profile photo locally");
//		        }
//		    } else {
//		        throw new ConfigDataNotFound("Upload valid image file");
//		    }
//		} else {
//		    student.setProfilePhoto(studentDtoRequest.getProfilePhoto());
////			student.setProfilePhoto(studentDtoRequest.getProfilePhoto().getOriginalFilename());
//		}
//		return student;
//		}

	}

	public StudentDtoRequest toDto(Student studentRequest) {
		StudentDtoRequest studentDtoRequest = new StudentDtoRequest();
		studentDtoRequest.setId(studentRequest.getId());
		studentDtoRequest.setStudentName(studentRequest.getStudentName());
		studentDtoRequest.setFathersName(studentRequest.getFathersName());
		studentDtoRequest.setMothersName(studentRequest.getMothersName());
		studentDtoRequest.setGender(studentRequest.getGender());
		studentDtoRequest.setMobile(studentRequest.getMobile());
		studentDtoRequest.setAadhaarNo(studentRequest.getAadhaarNo());
		studentDtoRequest.setAdmissionDate(studentRequest.getAdmissionDate());
		
		if(studentRequest.getDepartment()!=null) {
			studentDtoRequest.setDepartmentId(studentRequest.getDepartment().getId());
		studentDtoRequest.setDepartmentName(studentRequest.getDepartment().getDepartment());
		}
		studentDtoRequest.setEmail(studentRequest.getEmail());
		studentDtoRequest.setDob(studentRequest.getDob());
		studentDtoRequest.setCollege(studentRequest.getCollege());
		studentDtoRequest.setMaritalStatus(studentRequest.getMaritalStatus());
		studentDtoRequest.setCategory(studentRequest.getCategory());
		studentDtoRequest.setAltMobile(studentRequest.getAltMobile());
		if(studentRequest.getCourse()!=null) {
			studentDtoRequest.setCourseId(studentRequest.getCourse().getId());
			studentDtoRequest.setCourseCode(studentRequest.getCourse().getCourseCode());
			studentDtoRequest.setCourseName(studentRequest.getCourse().getCourseName());
		}

		studentDtoRequest.setSem(studentDtoRequest.getSem());
		studentDtoRequest.setStream(studentDtoRequest.getStream());
		studentDtoRequest.setPaper(studentDtoRequest.getPaper());
		studentDtoRequest.setPAddress(studentRequest.getPAddress());
		studentDtoRequest.setPCity(studentRequest.getPCity());
		studentDtoRequest.setPPin(studentRequest.getPPin());
		studentDtoRequest.setPState(studentRequest.getPState());
		studentDtoRequest.setCAddress(studentRequest.getCAddress());
		studentDtoRequest.setCCity(studentRequest.getCCity());
		studentDtoRequest.setCPin(studentRequest.getCPin());
		studentDtoRequest.setCState(studentRequest.getCState());
		studentDtoRequest.setProfileSignature(studentRequest.getProfileSignature());
		studentDtoRequest.setAltMobile(studentRequest.getAltMobile());
//		LocalDate localDate = LocalDate.parse(studentDtoRequest.getAdmissionDate());
//		student.setAdmissionDate(localDate);
		studentDtoRequest.setBloodGroup(studentRequest.getBloodGroup());
		studentDtoRequest.setProfilePhoto(studentRequest.getProfilePhoto());
		studentDtoRequest.setAdmissionDate(studentRequest.getAdmissionDate());
		studentDtoRequest.setRollNo(studentRequest.getRollNo());
//			student.setProfilePhoto(studentDtoRequest.getProfilePhoto().getOriginalFilename());

		return studentDtoRequest;
	}
}
