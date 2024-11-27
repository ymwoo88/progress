package com.woo.progress;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanatour.hats.test.base.SpringBootTestExt;
import com.woo.progress.repository.MenuRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class TestTest extends SpringBootTestExt {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EntityManager em;

//    @ParameterizedTestExt
//    void test(UserTemp userTemp) {

//        System.out.printf(userTemp.getUserId());
//        Iterable<Menu> all = menuRepository.findAll();
//        all.forEach(s -> System.out.printf(s.getName()));
//    }
}
