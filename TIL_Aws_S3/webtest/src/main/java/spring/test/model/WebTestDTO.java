package spring.test.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class WebTestDTO {

    private int fileNo;
    private String fileName;
    private MultipartFile fileNameMF;

}
