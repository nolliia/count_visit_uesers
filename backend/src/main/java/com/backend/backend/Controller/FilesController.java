package com.backend.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.backend.Response.ResponseMessage;
import com.backend.backend.Services.FilesStorageService;

@Controller
@RestController
@CrossOrigin("http://localhost:3000")
public class FilesController {

  @Autowired
  FilesStorageService storageService;

  @PostMapping("/uploadFile")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      String response = storageService.save(file);
      
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(response));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }
}