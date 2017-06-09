package org.avenuecode.assessment.service;


import org.avenuecode.assessment.Model.Dao.OrderProductRepository;
import org.avenuecode.assessment.Model.Dao.OrderRepository;
import org.avenuecode.assessment.Model.OrderProduct;
import org.avenuecode.assessment.Model.Order;
import org.avenuecode.assessment.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/*
This is a service class for orders. Service class is a third layer to provide an extra security layer
so that the world cannot see the implementation of your functions. Here you can find all the
logics of the function related to order.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    /*Returns a list of all the orders.It contains the details of the order such as order id, date
    and the list of all products that have been ordered.*/
    public List<OrderProduct> getAllOrders() {

        List<OrderProduct> orderProducts = orderProductRepository.findAll();
        List<OrderProduct> orderList = new ArrayList<>();
        Set<OrderProduct> set = new HashSet<OrderProduct>(orderProducts);
        for (OrderProduct products : set) {
            OrderProduct order = new OrderProduct();
            order.setOrderId(products.getOrder().getOrderId());
            HashSet setOfProducts = new HashSet();
            Product product = products.getProduct();
            product.setQuantity(products.getProductQuantity());
            setOfProducts.add(product);
            order.setProductSet(setOfProducts);
            orderList.add(order);
        }
        return orderList;
    }

/*
Returns the order information when provided by an id. Gives the information of an
order on a particular id.
 */
    public List<OrderProduct> getOrder(int orderId) {

        List<OrderProduct> orderProducts = orderProductRepository.findByOrderOrderId(orderId);
        List<OrderProduct> dataList = new ArrayList<>();
        Set<OrderProduct> set = new HashSet<OrderProduct>(orderProducts);
        for (OrderProduct products : set) {
            OrderProduct data = new OrderProduct();
            data.setOrderId(products.getOrder().getOrderId());
            HashSet set1 = new HashSet();
            Product product1 = products.getProduct();
            product1.setQuantity(products.getProductQuantity());
            set1.add(product1);
            data.setProductSet(set1);
            dataList.add(data);
        }
        return dataList;
    }

    /*
    This function adds an order in the database when provided by an object of the Order.
     */
    public OrderProduct addOrder(OrderProduct order) {

        Order orderA = new Order(new Date());
        Set<Product> products = order.getProductSet();
        HashSet<OrderProduct> orderProductHashSet = new HashSet<>();

        for (Product product : products) {
            OrderProduct orderProduct = new OrderProduct(orderA);
            orderProduct.setProductQuantity(product.getQuantity());
            orderProduct.setProduct(product);
            orderProductHashSet.add(orderProduct);
            orderA.setOrderProducts(orderProductHashSet);
        }
        orderRepository.save(orderA);
        return order;
    }

    /*
    This function deletes an order on a particular id. Takes the id as an input parameter.
     */
    public void deleteOrder(int id)
    {
     orderRepository.delete(id);
    }

    /*
    This function updates the order by updating the quantity of the product.
    The parameters passed are the order id, product id and the quantity.
     */
    public OrderProduct updateOrder(int orderId, int productID,int productQuantity) {
        List<OrderProduct> orderProducts = orderProductRepository.findByOrderOrderId(orderId);
        OrderProduct orderProductfinal=null;
        for (OrderProduct orderProduct : orderProducts) {
            if (orderProduct.getOrder().getOrderId() == orderId & orderProduct.getProduct().getProductId() == productID) {
                orderProductfinal=orderProduct;
                orderProduct.setProductQuantity(productQuantity);
                orderProductRepository.save(orderProduct);
            }
        }
        return orderProductfinal;
    }

    /*
    This function upddates the order by deleting the product from the given order.
    The arguements needed to be passed are the order id and product id.
     */
    @Transactional
    public List<OrderProduct> deleteByOrderProduct(int orderId, int productID) {
        List<OrderProduct> orderProducts = orderProductRepository.findByOrderOrderId(orderId);
        for (OrderProduct orderProduct : orderProducts) {
            if (orderProduct.getOrder().getOrderId() == orderId & orderProduct.getProduct().getProductId() == productID) {
                orderProductRepository.delete(orderProduct.getId());
            }

        }
        return orderProducts;
    }

}

