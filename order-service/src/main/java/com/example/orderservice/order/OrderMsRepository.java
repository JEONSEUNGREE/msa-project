package com.example.orderservice.order;

import com.example.orderservice.order.orderEntity.OrderMs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderMsRepository extends JpaRepository<OrderMs, Integer>, JpaSpecificationExecutor<OrderMs> {

}