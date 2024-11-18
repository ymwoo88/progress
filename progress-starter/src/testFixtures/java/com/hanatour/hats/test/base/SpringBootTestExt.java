package com.hanatour.hats.test.base;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@WithMockUser
public abstract class SpringBootTestExt implements DefaultTest {
}
