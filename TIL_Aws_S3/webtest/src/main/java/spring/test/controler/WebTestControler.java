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
//
//    List<WebTestDTO> list = webTestService.list();
//
//    List<String> list =  webTestService.listAllFiles();
//    request.setAttribute("list", list);
//
    return "main";

}

    /*새로운 리스트 방법 */
//@GetMapping("/fileList")
//    public String fileList(HttpServletRequest request, HttpServletResponse response) throws IOException{
//
//    log.info("!!!!!!!!!!@@@@@request",request);
//
//    ObjectListing objectListing = s3client.listObjects("myrentcar");
//    List<String> arrayKeyList = new ArrayList<>();
//    List<Date> arrayModTimeList = new ArrayList<>();
//
//    for(S3ObjectSummary s : objectListing.getObjectSummaries()){
//        arrayKeyList.add(s.getKey());
//        arrayModTimeList.add(s.getLastModified());
//    }
//
//    Date max = Collections.max(arrayModTimeList);
//    String fileName = arrayKeyList.get(arrayModTimeList.indexOf(max));
//    String url = "https://"+bucketName+".s3."+region+".amzonaws.com/"+fileName;
//
//    return url;
//
//    }

    /*골자는 같지만 새로운 방법*/
    @PostMapping("/list/files")
    @ResponseBody
    public ResponseEntity<List<String>> getListOfFiles() {

        List fileList =webTestService.listAllFiles();
        log.info("@@@@@테스트",fileList);
        System.out.println(fileList);

        return new ResponseEntity<>(fileList, HttpStatus.OK);
    }

@PostMapping("/upload")
@ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){

    webTestService.saveFile(file);

        return "등록성공";
    }

 // @PostMapping("/deleteFile/{filename}")
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
