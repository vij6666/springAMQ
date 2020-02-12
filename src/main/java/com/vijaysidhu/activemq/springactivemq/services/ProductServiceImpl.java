package com.vijaysidhu.activemq.springactivemq.services;

import com.vijaysidhu.activemq.springactivemq.SpringactivemqApplication;
import com.vijaysidhu.activemq.springactivemq.domain.Product;
import com.vijaysidhu.activemq.springactivemq.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductRepository productRepository;
    private JmsTemplate jmsTemplate;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, JmsTemplate jmsTemplate){
        this.productRepository = productRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Long id) {
        sendMessage(id.toString());
        return productRepository.findById(id).orElse(null);
    }


    public Product getByIdJpql(Long id) {
        return productRepository.findProductByIdJpql(id);
    }


    public Product getByIdNative(Long id) {
        return productRepository.findProductByIdNative(id);
    }


    @Override
    public Product saveOrUpdate(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public void sendMessage(String id) {
        Map<String, String> actionMap = new HashMap<>();
        actionMap.put("id", id);
        log.info("Sending the index request through queue message");
        jmsTemplate.convertAndSend(SpringactivemqApplication.PRODUCT_MESSAGE_QUEUE, actionMap);

    }
}
