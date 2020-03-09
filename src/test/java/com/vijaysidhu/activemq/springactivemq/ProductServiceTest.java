package com.vijaysidhu.activemq.springactivemq;

import com.vijaysidhu.activemq.springactivemq.domain.Product;
import com.vijaysidhu.activemq.springactivemq.repositories.ProductRepository;
import com.vijaysidhu.activemq.springactivemq.services.ProductService;
import com.vijaysidhu.activemq.springactivemq.services.ProductServiceImpl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {"spring.cloud.consul.config.enabled=false"})
public class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private JmsTemplate jmsTemplate;

    @Test
    void getAllProductsTest(){
        Product product = new Product();
        product.setId(new Long(1));
        product.setDescription("Test Product");
        product.setSize(new Long(5));
        product.setMessageReceived(false);
        JmsTemplate jmsTemplate = new JmsTemplate();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Mockito.when(productRepository.findAll())
                .thenReturn(productList);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, jmsTemplate);

        List<Product> products = productService.listAll();
        Product product1 = products.get(0);

        assertEquals(new Long(1), product1.getId());

    }
}
