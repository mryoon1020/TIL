package spring.test.model;

import lombok.Data;
@Data
public class webTestDTO {

    private int fileNo;
    private String fileName;

    public webTestDTO() {
    }

    public webTestDTO(int fileNo, String fileName) {
        this.fileNo = fileNo;
        this.fileName = fileName;
    }
}
