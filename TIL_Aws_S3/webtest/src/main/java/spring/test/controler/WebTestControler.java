package spring.test.controler;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.test.service.WebTestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
//
//    List<WebTestDTO> list = webTestService.list();
//
//    List<String> list =  webTestService.listAllFiles();
//    request.setAttribute("list", list);
//
    return "main";

}

    /*새로운 리스트 방법 */
@GetMapping("/fileList")
    public String fileList(HttpServletRequest request, HttpServletResponse response) throws IOException{

    ObjectListing objectListing = s3client.listObjects("myrentcar");
    List<String> arrayKeyList = new ArrayList<>();
    List<Date> arrayModTimeList = new ArrayList<>();

    for(S3ObjectSummary s : objectListing.getObjectSummaries()){
        arrayKeyList.add(s.getKey());
        arrayModTimeList.add(s.getLastModified());
    }

    Date max = Collections.max(arrayModTimeList);
    String fileName = arrayKeyList.get(arrayModTimeList.indexOf(max));
    String url = "https://"+bucketName+".s3."+region+".amzonaws.com/"+fileName;

    return url;

    }

@PostMapping("/upload")
@ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){

    webTestService.saveFile(file);

        return "저장에 성공했습니다";
    }

    @PostMapping("/delete/{filename}")
    public  String deleteFile(@PathVariable("filename") String filename){
        return webTestService.deleteFile(filename);
    }

}
