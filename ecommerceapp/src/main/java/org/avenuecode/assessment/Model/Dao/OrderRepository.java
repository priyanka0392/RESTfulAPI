package org.avenuecode.assessment.Model.Dao;

import org.avenuecode.assessment.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/*
This is the interface for the class Order which extends JPA Repository to perform
 the CRUD operations for the orders.
 */
public interface OrderRepository extends JpaRepository<Order, Integer>
{

}