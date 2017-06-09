package org.avenuecode.assessment.producttest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.avenuecode.assessment.Model.Order;
import org.avenuecode.assessment.Model.OrderProduct;
import org.avenuecode.assessment.Model.Product;
import org.avenuecode.assessment.service.OrderService;
import org.avenuecode.assessment.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ProductTest {

    @Autowired
    private ProductService productService;

    private RestTemplate restTemplate = new RestTemplate();
    private JsonNode jsonNode;
    private ObjectMapper objectMapper;

/*
This function gets all the products from the file products.json and checks if the result is correct or not. If the result is
correct it verifies with the status code of 201 which defines for successful creation.
 */
    @Test
    public void shouldGetAllProduct() throws Exception {

        objectMapper=new ObjectMapper();
        File file = new ClassPathResource("/META-INF/products.json").getFile();
        jsonNode=objectMapper.readTree(file);
        String uri="http://localhost:8080/products";
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<JsonNode> result = restTemplate.getForEntity(uri,JsonNode.class);
        assertEquals(jsonNode,result.getBody());
        assertEquals(result.getStatusCode().value(),200);
    }

    /*
    This function gets the product by its id and get the information of the product on the given id. If the result is
correct it verifies with the status code of 201 which defines for successful creation.
     */
    @Test
    public void shouldGetProductById() throws Exception {
        objectMapper=new ObjectMapper();
        File file = new ClassPathResource("/META-INF/products.json").getFile();
        jsonNode=objectMapper.readTree(file);
        String uri="http://localhost:8080/products/1/";
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<JsonNode> result = restTemplate.getForEntity(uri,JsonNode.class);
        assertEquals(jsonNode.get(0),result.getBody());
        assertEquals(result.getStatusCode().value(),200);

    }

/*
This function checks of a new product is being added or not. It it is added successfully it verifies
with the status code of 201 which defines for successful creation.
 */
    @Test
    public void shouldAddProduct() {
        String uri="http://localhost:8080/products/";
        Product product=new Product("Car", "Mustang", 10, 5, 20);
        ResponseEntity<String> result = restTemplate.postForEntity(uri ,product, String.class);
        assertEquals(result.getStatusCode().value(),201);

    }








}
