package com.vijaysidhu.activemq.springactivemq.listeners;

import com.vijaysidhu.activemq.springactivemq.SpringactivemqApplication;
import com.vijaysidhu.activemq.springactivemq.domain.Product;
import com.vijaysidhu.activemq.springactivemq.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageListener {

    private ProductRepository productRepository;

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    public MessageListener(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @JmsListener(destination = SpringactivemqApplication.PRODUCT_MESSAGE_QUEUE, containerFactory = "jmsFactory")
    public void receiveMessage(Map<String, String> message){
        log.info("Received <" + message + ">");
        Long id = Long.valueOf(message.get("id"));
        Product product = productRepository.findById(id).orElse(null);
        product.setMessageReceived(true);
        product.setMessageCount(product.getMessageCount() + 1);
        productRepository.save(product);
        log.info("Message processed ...");
    }
}
