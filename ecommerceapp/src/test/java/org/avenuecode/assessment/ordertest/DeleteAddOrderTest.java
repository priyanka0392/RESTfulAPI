package org.avenuecode.assessment.ordertest;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.avenuecode.assessment.Model.Dao.OrderRepository;
import org.avenuecode.assessment.Model.Dao.ProductRepository;
import org.avenuecode.assessment.Model.OrderProduct;
import org.avenuecode.assessment.Model.Product;
import org.avenuecode.assessment.service.OrderService;
import org.avenuecode.assessment.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.*;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class DeleteAddOrderTest {

    private RestTemplate restTemplate = new RestTemplate();
    private static final Logger logger = LoggerFactory.getLogger(DeleteAddOrderTest.class);

    /*
    This function tests if the order has been added or not.
     */
    @Test
    public void shouldAddOrder() {

        String uri="http://localhost:8080/orders/";
        Set<Product> productSet=new HashSet<>();
        Product product1=new Product("Soap", "TeaTree", 10, 5, 20);
        product1.setQuantity(5);
        product1.setProductId(2);
        productSet.add(product1);
        OrderProduct orderProduct =new OrderProduct();
        orderProduct.setProductSet(productSet);
        ResponseEntity<String> result = restTemplate.postForEntity(uri ,orderProduct, String.class);
        assertEquals(result.getStatusCode().value(),201);
        logger.info("added successfully");
    }

/*
This function checks if the updated value is correct or not. This method deleted a product and update the order.
 */
    @Test
    public void shouldUpdateOrderDeleteProduct()
    {
        String uri="http://localhost:8080/orders/1/1";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);
        assertEquals(result.getStatusCode().value(),204);
        logger.info("deleted product and updated order successfully");
    }

    /*
    This function updates the quantity of the product and checks if the set quantity is correct or not.
     */
    @Test
    public void shouldUpdateOrder()
    {
        final String uri = "http://localhost:8080/orders/1/1";
        Product product=new Product();
        product.setQuantity(15);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri,product);
        HttpEntity<Product> entity = new HttpEntity<Product>(product);
        ResponseEntity<Product> p = restTemplate.exchange(uri, HttpMethod.PUT, entity, Product.class);
        assertEquals(p.getStatusCode().value(),204);
        logger.info("update order by updating quantity");
    }

    /*
    This function tests if the order has been deleted or not.
    */
    @Test
    public void shouldDeleteOrder()
    {
        String uri="http://localhost:8080/orders/1/";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class);
        assertEquals(result.getStatusCode().value(),204);
        logger.info("deleted order successfully");
    }


}









