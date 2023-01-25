package com.backend.backend.Services;

import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
  
  public String save(MultipartFile file);
}