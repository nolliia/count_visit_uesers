package com.backend.backend.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.backend.backend.Model.UserVisited;

public class CSVHelper {
    
    public static String TYPE = "text/csv";
    static String[] HEADERs = {  "email", "phone", "source" };
  
    public static boolean hasCSVFormat(MultipartFile file) {
  
      if (!TYPE.equals(file.getContentType())) {
        return false;
      }
  
      return true;
    }
  
    public static List<UserVisited> csvToUsersVisited(InputStream is) {
      try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
          CSVParser csvParser = new CSVParser(fileReader,
              CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
  
        List<UserVisited> tutorials = new ArrayList<UserVisited>();
  
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
  
        for (CSVRecord csvRecord : csvRecords) {
            if(!csvRecord.get("email").equals("") && !csvRecord.get("phone").equals("") && !csvRecord.get("source").equals("")){
              UserVisited tutorial = new UserVisited(
                  csvRecord.get("email"),
                  csvRecord.get("phone"),
                  csvRecord.get("source")
                );

              tutorials.add(tutorial);
          }
        }
  
        return tutorials;
      } catch (IOException e) {
        throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
      }
    }
  
}
