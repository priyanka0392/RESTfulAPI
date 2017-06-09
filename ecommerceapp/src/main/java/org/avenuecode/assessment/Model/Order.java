package org.avenuecode.assessment.Model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orderInfo")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    @Temporal(TemporalType.DATE)
    private Date date;

    @Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<OrderProduct> orderProducts;

    public Order()
    {
    }
    public Order(Date date) {
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}

