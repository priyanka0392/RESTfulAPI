package org.avenuecode.assessment.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

@Entity
@Table(name = "order_product")
public class OrderProduct {

    @Transient
    private int  orderId;

    @JsonIgnore
    @Id
    @GeneratedValue
    private int id;
    @JsonIgnore
    @ManyToOne
    private Product product;

    @JsonIgnore
    @ManyToOne
    private Order order;

    @JsonIgnore
    private int productQuantity;

    @Transient
    private Set<Product> productSet;

    public OrderProduct()
    {

    }
    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public OrderProduct(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}

