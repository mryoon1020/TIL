package spring.test.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import spring.test.model.WebTestDTO;
import spring.test.service.WebTestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WebTestControler {

    private static final Logger log = LoggerFactory.getLogger(WebTestControler.class);
    @Autowired
    @Qualifier("spring.test.service.webTestServiceImpl")
    private WebTestService webTestService;

@GetMapping("/")
    public String home(HttpServletRequest request){

    List<WebTestDTO> list = webTestService.list();
    request.setAttribute("list", list);

    return "main";

}

@PostMapping("/upload")
@ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){

    webTestService.saveFile(file);
        return "저장에 성공했습니다";
    }

}
