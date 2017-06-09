package org.avenuecode.assessment.ordertest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.avenuecode.assessment.Model.OrderProduct;
import org.avenuecode.assessment.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class GetOrderTest {

    private RestTemplate restTemplate = new RestTemplate();
    private JsonNode jsonNode;
    private ObjectMapper objectMapper;

    /*
    This function tests the results got for an order by a given id and returns true if both
    the data and the status code matches.
     */
    @Test
    public void shouldGetOrderById() throws Exception {
        String data="[{\"orderId\":1,\"productSet\":[{\"productId\":1,\"productName\":\"Soap\",\"manufacturerName\":\"TeaTree\",\"modelNumber\":10,\"productPrice\":5.0,\"productUnit\":20,\"quantity\":5}]},{\"orderId\":1,\"productSet\":[{\"productId\":2,\"productName\":\"Shampoo\",\"manufacturerName\":\"Paul Mitchell\",\"modelNumber\":15,\"productPrice\":6.0,\"productUnit\":30,\"quantity\":10}]}]";
        objectMapper=new ObjectMapper();
        File file = new ClassPathResource("/META-INF/orders.json").getFile();
        jsonNode=objectMapper.readTree(file);
        String uri="http://localhost:8080/orders/1";
        ResponseEntity<String> result = restTemplate.getForEntity(uri,String.class);
        assertEquals(data,result.getBody().toString());
        assertEquals(result.getStatusCode().value(),200);
    }

    /*
    This function gets all the orders and checks from a file called order.json if the result is correct or not.
     */
    @Test
    public void shouldGetAllOrder() throws Exception {

        objectMapper=new ObjectMapper();
        File file = new ClassPathResource("/META-INF/orders.json").getFile();
        jsonNode=objectMapper.readTree(file);
        String uri="http://localhost:8080/orders/";
         ResponseEntity<JsonNode> result = restTemplate.getForEntity(uri,JsonNode.class);
        assertEquals(jsonNode,result.getBody());
        assertEquals(result.getStatusCode().value(),200);

    }

}
