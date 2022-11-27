package spring.test.controler;

import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.test.service.WebTestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

@Controller
public class WebTestControler {

    private static final Logger log = LoggerFactory.getLogger(WebTestControler.class);
    @Autowired
    @Qualifier("spring.test.service.webTestServiceImpl")
    private WebTestService webTestService;

//    @Autowired
    private AmazonS3 s3client;

    @Value("${region}")
    private String region;

    @Value("${bucketName}")
    private  String bucketName;
@GetMapping("/")
    public String home(HttpServletRequest request){

    return "main";

}

@PostMapping("/list/files")
@ResponseBody
    public ResponseEntity<List<String>> getListOfFiles() {

        List fileList =webTestService.listAllFiles();
        System.out.println(fileList);

        return new ResponseEntity<>(fileList, HttpStatus.OK);
    }

@PostMapping("/upload")
@ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){

    webTestService.saveFile(file);

        return "등록성공";
    }

    @RequestMapping(value="/deleteFile/{filename}", method = {RequestMethod.GET, RequestMethod.POST})
    public  String deleteFile(@PathVariable("filename") String filename){

        webTestService.deleteFile(filename);

        return "redirect:/";
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable("filename") String filename){
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-type", MediaType.ALL_VALUE);
        headers.add("Content-Disposition", "attachment; filename="+filename);
        byte[] bytes = webTestService.downloadFile(filename);
        return  ResponseEntity.status(HTTP_OK).headers(headers).body(bytes);
    }

}
