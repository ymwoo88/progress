package com.hanatour.hats.test.base;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.test.context.support.WithMockUser;

@DataJpaTest
@WithMockUser
public abstract class DataJpaTestExt implements DefaultTest {

    @Autowired
    protected EntityManager em;
}
