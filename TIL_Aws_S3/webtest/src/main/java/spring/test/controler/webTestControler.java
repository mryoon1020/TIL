package spring.test.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.test.model.webTestDTO;
import spring.test.service.webTestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class webTestControler {

    private static final Logger log = LoggerFactory.getLogger(webTestControler.class);

    @Autowired
    @Qualifier("spring.test.service.webTestServiceImpl")
    private webTestService webTestService;

@GetMapping("/")
    public String home(HttpServletRequest request){

    List<webTestDTO> list = webTestService.list();
    request.setAttribute("list", list);

    return "main";



}


}
