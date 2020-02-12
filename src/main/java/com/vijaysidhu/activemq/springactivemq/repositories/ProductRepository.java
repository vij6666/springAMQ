package com.vijaysidhu.activemq.springactivemq.repositories;

import com.vijaysidhu.activemq.springactivemq.domain.Product;
import org.hibernate.LockMode;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    // JPQL query with named parameter
    // Pessimistic write means no other transaction can read, update or delete the entity
    // Pessimistic force increment is the same but forces the version to update
    // Pessimistic read means a shared lock can be obtained for a read but prevents the entity
    // from being updated or deleted
    // Cant lock a native sql query
    @Transactional
    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    @Query("select p from Product p where p.id = :id")
    Product findProductByIdJpql(@Param("id") Long id);

    // Native SQL Query
    //@Transactional
    //@Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    // Can't set lock mode for Native Sql through JPA annotations, need to set
    // a QueryHint for NATIVE_LOCKMODE before executing the query
    @Query(value = "SELECT * FROM Product p WHERE p.id = :id", nativeQuery = true)
    Product findProductByIdNative(@Param("id") Long id);

}
