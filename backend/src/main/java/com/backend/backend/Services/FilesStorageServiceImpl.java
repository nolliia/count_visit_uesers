package com.backend.backend.Services;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.backend.Model.UserVisited;
import com.backend.backend.Repository.UserVisitedRepository;
import com.backend.backend.helper.CSVHelper;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  @Autowired
  UserVisitedRepository repository;

  @Override
  public String save(MultipartFile file) {
    String response = "";
    try {
        List<UserVisited> tutorials = CSVHelper.csvToUsersVisited(file.getInputStream());
        repository.deleteAll(); // remove this if you want to add to your previous data
        repository.saveAllAndFlush(tutorials);
        List<Object[]> groupResults =  repository.getResultsCount();
        for( Object[] object : groupResults ){
          for( int i =0 ; i< object.length; i++  ){

            response += object[i].toString();
            if( i == 0){
              response+= " : ";
            }
          }
          response+= ",";
        }

    } catch (Exception e) {
      if (e instanceof FileAlreadyExistsException) {
        throw new RuntimeException("A file of that name already exists.");
      }

      throw new RuntimeException(e.getMessage());
    }
    return response;
  }

 
}