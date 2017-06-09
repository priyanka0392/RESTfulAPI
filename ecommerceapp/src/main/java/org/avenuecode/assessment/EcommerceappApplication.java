package org.avenuecode.assessment;
import org.avenuecode.assessment.Model.*;
import org.avenuecode.assessment.Model.Dao.*;
import org.avenuecode.assessment.service.OrderService;
import org.avenuecode.assessment.service.ProductService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;

import javax.transaction.Transactional;
import java.util.*;

/*
This is the start class. The spring boot application starts from here.
I have also added some dummy data in the database to test the functions.
 */
@SpringBootApplication
public class EcommerceappApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(EcommerceappApplication.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceappApplication.class, args);
	}

	@Autowired
	private OrderProductRepository orderProductRepository;

	@Override
	@Transactional
	public void run(String... strings) throws Exception {
		logger.info("Initialized database with some  dummy data");
		logger.info(" save a couple of products");
		Product productA = new Product("Soap","TeaTree",10,5,20);
		Product productB = new Product("Shampoo","Paul Mitchell",15,6,30);
		productA.setQuantity(5);
		productB.setQuantity(10);
		productService.saveProduct(productA);
		productService.saveProduct(productB);
		HashSet<Product> orderProductHashSet =new HashSet<>();
		orderProductHashSet.add(productA);
		orderProductHashSet.add(productB);
		OrderProduct orderProduct =new OrderProduct();
		orderProduct.setProductSet(orderProductHashSet);
		logger.info("save an order");
		orderService.addOrder(orderProduct);

	}

}
