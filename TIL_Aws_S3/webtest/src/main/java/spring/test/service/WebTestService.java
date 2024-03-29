package spring.test.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface WebTestService {

    String saveFile(MultipartFile file);

    byte[] downloadFile(String filename);

    String deleteFile(String filename);

    List<String> listAllFiles();
}
