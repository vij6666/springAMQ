package com.vijaysidhu.activemq.springactivemq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"spring.cloud.consul.config.enabled=false"})
class SpringactivemqApplicationTests {

    @Test
    void contextLoads() {
    }

}
