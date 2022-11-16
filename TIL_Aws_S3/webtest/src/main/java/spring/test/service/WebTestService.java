package spring.test.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface WebTestService {

//    List<WebTestDTO> list();

    String saveFile(MultipartFile file);

    byte[] downloadFile(String filename);

    String deleteFile(String filename);

    List<String> listAllFiles();
}
