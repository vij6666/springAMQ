package com.vijaysidhu.activemq.springactivemq.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.sql.Timestamp;

@Entity
@Data
public class Product{

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Long size;
    private boolean messageReceived;
    private Integer messageCount = 0;
    // Version used for Locking
    @Version
    private Timestamp timestamp;


}
