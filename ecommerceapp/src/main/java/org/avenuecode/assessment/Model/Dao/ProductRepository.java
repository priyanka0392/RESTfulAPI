package org.avenuecode.assessment.Model.Dao;

import org.avenuecode.assessment.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/*
This the interface for the class Products which extends JPA Repository to implement
all the CRUD operations of a product.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

}