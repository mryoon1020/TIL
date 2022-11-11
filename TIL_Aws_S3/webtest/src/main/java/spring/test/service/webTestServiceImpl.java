package spring.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.test.model.webTestDTO;

import java.util.List;

@Service("spring.test.service.webTestServiceImpl")
public class webTestServiceImpl implements webTestService {

    @Autowired
    private webTestMapper mapper;
    @Override
    public List<webTestDTO> list() {
        return mapper.list();
    }
}
