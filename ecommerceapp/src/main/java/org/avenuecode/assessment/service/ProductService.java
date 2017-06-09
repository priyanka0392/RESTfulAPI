package org.avenuecode.assessment.service;

import org.avenuecode.assessment.Model.Dao.ProductRepository;
import org.avenuecode.assessment.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    /*
    This is a product service class which implements all the logic for the functions of a product.
     */
    @Autowired
    private ProductRepository productRepository;

    /*
    This functions lists all the products from the product catalog
     */
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    /*
    This functions lets you view a product on a particular id. Takes an id on which lists
    the product information of the given id
     */
    public Product getProduct(int id) {
        return productRepository.findOne(id);
    }

    /*
    This function lets you add a product in the catalog.
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

}

