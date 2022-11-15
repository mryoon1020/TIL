package spring.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WebTestDTO {

//    private int fileNo;
//    private String fileName;
//    private MultipartFile fileNameMF;

    private List<String> fileNames;

    public static WebTestDTO from(List<String> fileNames) {
        return new WebTestDTO(fileNames);
    }
}
