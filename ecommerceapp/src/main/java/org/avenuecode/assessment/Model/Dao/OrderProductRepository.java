package org.avenuecode.assessment.Model.Dao;

import org.avenuecode.assessment.Model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {

/*
This is a repository of the association class which extends tha JPA repository.
All the CRUD operations are in the JPA repository are in-built and hence we don't
have to implement any of them.
 */
    List<OrderProduct> findByOrderOrderId(int id);
    List<OrderProduct> findAll();
    @Modifying
    @Transactional
    @Query("delete from OrderProduct u where u.id =?1")
    void delete(int id);
}
