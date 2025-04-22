package com.a2a2lab.module.upload;

import org.springframework.stereotype.Repository;

@Repository
public interface UploadDao {
	
	public int localUpload(UploadDto dto);

}
