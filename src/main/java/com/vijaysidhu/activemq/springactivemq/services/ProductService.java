package com.vijaysidhu.activemq.springactivemq.services;

import com.vijaysidhu.activemq.springactivemq.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    List<Product> listAll();

    Product getById(Long id);

    Product getByIdJpql(Long id);

    Product getByIdNative(Long id);


    Product saveOrUpdate(Product product);

    void delete(Long id);

    void sendMessage(String id);
}
