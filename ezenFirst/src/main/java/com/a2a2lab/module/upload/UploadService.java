//package com.a2a2lab.module.upload;
//
//import java.io.IOException;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//
//@Service
//public class UploadService {
//	
//	@Autowired
//	private AmazonS3Client amazonS3Client;
//	@Value("${cloud.aws.s3.bucket}")
//	private String bucket;
//	
//	@Autowired
//	UploadDao dao;
//	
//	// aws s3 file upload
//	public String s3Upload(MultipartFile file, String dirName, String uuid) throws IOException {
//		
//		String fileName = dirName + "/" + uuid + "_" + file.getOriginalFilename();
//		ObjectMetadata metadata = new ObjectMetadata();
//		metadata.setContentLength(file.getSize());
//		amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
//		
//		return amazonS3Client.getUrl(bucket, fileName).toString();
//	}
//	
//	// local DB upload
//	public String localUpload(MultipartFile file) throws IOException {
//		
//		// setting
//		String fileName = file.getOriginalFilename();
//		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
//		String uuid = UUID.randomUUID().toString();
//		String uuidFileName = uuid + "." + ext;
//		
//		// aws s3 파일 업로드
//		String s3Url = s3Upload(file, "productImg", uuid);
//		
//		// DB에 파일 Insert
//		UploadDto uploadDto = new UploadDto();
//		uploadDto.setType(1);
//		uploadDto.setSort(1);
//		uploadDto.setPath(s3Url);
//		uploadDto.setOriginalName(fileName);
//		uploadDto.setUuidName(uuidFileName);
//		uploadDto.setExt(ext);
//		uploadDto.setSize(file.getSize());
//		
//		dao.localUpload(uploadDto);
//		
//		return uploadDto.getSeq();
//	}
//	
//}
