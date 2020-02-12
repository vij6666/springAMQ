package com.vijaysidhu.activemq.springactivemq.controllers;

import com.vijaysidhu.activemq.springactivemq.domain.Product;
import com.vijaysidhu.activemq.springactivemq.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


import java.util.Collections;
import java.util.List;
import java.util.logging.LogManager;

@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;


    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }


    @RequestMapping(value = "/product", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody List<Product> getAllProducts(){
        return productService.listAll();
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Product getProductById(@PathVariable Long id){
        return productService.getById(id);
    }

    @RequestMapping(value = "/product/jpql/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Product getProductByIdJpql(@PathVariable Long id){
        return productService.getByIdJpql(id);
    }

    @RequestMapping(value = "/product/native/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody Product getProductByIdNative(@PathVariable Long id){
        return productService.getByIdNative(id);
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity createProduct(@RequestBody Product product){
        Product product1 = productService.saveOrUpdate(product);
        ResponseEntity responseEntity = new ResponseEntity(Collections.singletonMap("id", product1.getId()), HttpStatus.OK);
        return  responseEntity;

    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity deleteProductById(@PathVariable(value = "id") Long id){
        ResponseEntity responseEntity;
        productService.delete(id);
        responseEntity = new ResponseEntity(HttpStatus.OK);
        return responseEntity;
    }

}
