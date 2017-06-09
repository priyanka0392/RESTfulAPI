package org.avenuecode.assessment.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_info")
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private String productName;
    private String manufacturerName;
    private int modelNumber;
    private float productPrice;
    private int productUnit;

    @Transient
    private int quantity;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "product")
    private Set<OrderProduct> orders = new HashSet<>();

    public Product(String productName, float productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }
    public Product()
    {

    }

    public Product(String productName, String manufacturerName, int modelNumber, float productPrice, int productUnit) {
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.modelNumber = modelNumber;
        this.productPrice = productPrice;
        this.productUnit = productUnit;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public int getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(int modelNumber) {
        this.modelNumber = modelNumber;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(int productUnit) {
        this.productUnit = productUnit;
    }

    public Set<OrderProduct> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderProduct> orders) {
        this.orders = orders;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
