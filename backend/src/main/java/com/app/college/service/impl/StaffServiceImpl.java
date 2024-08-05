package com.app.college.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.app.college.DTO.StaffDtoRequest;
import com.app.college.exception.ConfigDataNotFound;
import com.app.college.mapper.StaffMapper;
import com.app.college.models.Staff;
import com.app.college.repository.StaffRepository;
import com.app.college.service.StaffService;

import jakarta.validation.Valid;

@Service
public class StaffServiceImpl implements StaffService {
	
	private StaffRepository staffRepository;
	
	public StaffServiceImpl(StaffRepository staffRepository) {
		this.staffRepository=staffRepository;
	}

	@Autowired
	private StaffMapper staffMapper;
	
	@Value("${application.bucket.name}")
    private String bucketName;
	
	@Autowired
    private AmazonS3 s3Client;

	@Override
	public Staff createStaffs(@Valid StaffDtoRequest staffDtoReq) {
		return staffRepository.save(staffMapper.toEntity(staffDtoReq));
	}

	@Override
	public Staff updateStaffById(Long id, @Valid StaffDtoRequest staffDtoReq) {
		if(staffRepository.existsById(id)) {
			return staffRepository.save(staffMapper.updateToEntity(id, staffDtoReq));
		}
		throw  new ConfigDataNotFound("No Staff found with id: "+id);
	}
	
	@Override
	public String deleteStaffById(Long id) {
		if(staffRepository.existsById(id)) {
			 staffRepository.deleteById(id);
			 return "Staff with id " + id + " deleted sussesfully!!";
		}
		throw new ConfigDataNotFound("No Staff found with id "+id);
	}


	@Override
	public Staff getStaffById(Long id) {
		if(staffRepository.existsById(id)) {
			return staffRepository.findById(id).get();
		}
		throw new ConfigDataNotFound("No Staff found with id "+id);
	}

	@Override
	public Page<Staff> getAllStaff(Pageable pageable) {
		return staffRepository.findAll(pageable);
	}


//	@Override
//	public Staff createStaffsWithPhoto(@Valid StaffDtoRequest staffDtoReq, MultipartFile profilePhotoFile) {
//	    Staff staff = new Staff();
//	    BeanUtils.copyProperties(staffDtoReq, staff);
//	    String directoryPath = "D:" + File.separator + "Abijeet" + File.separator + "Photos" + File.separator;
//
//	    if (profilePhotoFile != null && !profilePhotoFile.isEmpty() && !profilePhotoFile.getOriginalFilename().isEmpty()) {
//	        String fileName = profilePhotoFile.getOriginalFilename();
//	        String extension = fileName.substring(fileName.lastIndexOf("."));
//	        if (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg") && profilePhotoFile.getSize() < 1048576) {
//	            String newFileName = System.currentTimeMillis() + "_profile" + extension;
//	            File newFile = new File(directoryPath + newFileName);
//	            try (FileOutputStream fos = new FileOutputStream(newFile);
//	                 InputStream is = profilePhotoFile.getInputStream()) {
//	                byte[] buffer = new byte[1024];
//	                int length;
//	                while ((length = is.read(buffer)) > 0) {
//	                    fos.write(buffer, 0, length);
//	                }
//	                staff.setProfilePhoto(newFileName);
//	            } catch (IOException e) {
//	                throw new RuntimeException("Error occurred while saving profile photo locally", e);
//	            }
//	        } else {
//	            throw new ConfigDataNotFound("Upload valid image file i.e jpg or jpeg & less then 1024kb");
//	        }
//	    } else {
//	        staff.setProfilePhoto(staffDtoReq.getProfilePhoto());
//	    }
//	    return staffRepository.save(staff);
//	}
	
	@Override
	public Staff createStaffsWithPhoto(@Valid StaffDtoRequest staffDtoReq, MultipartFile profilePhotoFile) {
		Staff staff = new Staff();
	    BeanUtils.copyProperties(staffDtoReq, staff);
	    Staff a1=null;
	    try {
            // Check if the file is not empty
            if (profilePhotoFile != null && !profilePhotoFile.isEmpty()) {
                // Create a temporary file
                File tempFile = File.createTempFile("temp", null);
                tempFile.deleteOnExit();

                // Transfer the content of MultipartFile to the temporary file
                profilePhotoFile.transferTo(tempFile);

                // Create a File object for the uploaded file
                File imageFile = tempFile;

                // Set up metadata for the S3 object
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType(profilePhotoFile.getContentType());

                String filename;
                if (staff.getProfilePhoto() == null || staff.getProfilePhoto().isEmpty()) {
                    int lastDotIndex = profilePhotoFile.getOriginalFilename().lastIndexOf('.');
                    String fileExtension = lastDotIndex == -1 ? "" : profilePhotoFile.getOriginalFilename().substring(lastDotIndex + 1);
                    filename = "staff" + "_" + System.currentTimeMillis() + "profile." + fileExtension;
                } else {
                    filename = staff.getProfilePhoto();
                }
                // Specify the key for the S3 object (e.g., images/my_image.jpg)
                String key = "staff/" + filename;
                staff.setProfilePhoto(filename);

                // Create a PutObjectRequest to upload the image to S3
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, imageFile);
                putObjectRequest.setMetadata(metadata);
                a1 = staffRepository.save(staff);
                if (a1 != null) {
                    // Upload the image to S3
                    s3Client.putObject(putObjectRequest);
                }
                // Optionally, you can delete the local file after uploading to S3
                imageFile.delete();

                // Redirect to a success page or return a success message
            } else {
            	throw new ConfigDataNotFound("Upload valid image file i.e jpg or jpeg & less then 1024kb!!!");
            }
        } catch (IOException e) {
        	throw new RuntimeException("Error occurred while saving profile photo!!!", e);
        }catch (Exception e) {
            e.printStackTrace();
            
        }
		return a1;
	}

	@Override
	public Staff updateStaffByIdWithPhoto(Long id, @Valid StaffDtoRequest staffDtoReq, MultipartFile profilePhotoFile) {
	    Optional<Staff> findById = staffRepository.findById(id);
	    if (findById.isPresent()) {
	        Staff staff = findById.get();
	        BeanUtils.copyProperties(staffDtoReq, staff, "profilePhoto");
	        Staff a1 = null;
	        LocalDateTime lc = LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
	        try {
	            // Check if the file is not empty
	            if (profilePhotoFile != null && !profilePhotoFile.isEmpty()) {
	                // Create a temporary file
	                File tempFile = File.createTempFile("temp", null);
	                tempFile.deleteOnExit();

	                // Transfer the content of MultipartFile to the temporary file
	                profilePhotoFile.transferTo(tempFile);

	                // Create a File object for the uploaded file
	                File imageFile = tempFile;

	                // Set up metadata for the S3 object
	                ObjectMetadata metadata = new ObjectMetadata();
	                metadata.setContentType(profilePhotoFile.getContentType());

	                String filename;
	                if (staff.getProfilePhoto() == null || staff.getProfilePhoto().isEmpty()) {
	                    int lastDotIndex = profilePhotoFile.getOriginalFilename().lastIndexOf('.');
	                    String fileExtension = lastDotIndex == -1 ? "" : profilePhotoFile.getOriginalFilename().substring(lastDotIndex + 1);
	                    filename = "staff" + "_" + System.currentTimeMillis() + "profile." + fileExtension;
	                } else {
	                    filename = staff.getProfilePhoto();
	                }
	                // Specify the key for the S3 object (e.g., images/my_image.jpg)
	                String key = "staff/" + filename;
	                staff.setProfilePhoto(filename);

	                // Create a PutObjectRequest to upload the image to S3
	                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, imageFile);
	                putObjectRequest.setMetadata(metadata);

	                staff.setProfileUpdatedDate(lc);
	                a1 = staffRepository.save(staff);
	                if (a1 != null) {
	                    // Upload the image to S3
	                    s3Client.putObject(putObjectRequest);
	                }
	                // Optionally, you can delete the local file after uploading to S3
	                imageFile.delete();

	                // Redirect to a success page or return a success message
	            } else {
	                staff.setProfileUpdatedDate(lc);
	                a1 = staffRepository.save(staff);
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("Error occurred while saving profile photo!!!", e);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return a1;
	    }
	    throw new ConfigDataNotFound("No Staff found with id: " + id);
	}


	@Override
	public Page<Staff> getStaffBySearch(String search,Pageable pageable) {
		
		return staffRepository.getStaffBySearch(search, pageable);
	}

}
