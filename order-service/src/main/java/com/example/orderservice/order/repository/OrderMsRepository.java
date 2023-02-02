package com.example.orderservice.order.repository;

import com.example.orderservice.order.orderEntity.OrderMs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderMsRepository extends JpaRepository<OrderMs, Integer> {
    public Optional<List<OrderMs>> findAllByUserId(String userId);

    @Query("SELECT max(o.order_sequence) FROM OrderMs AS o WHERE o.userId = :userId")
    public Optional<Integer> getMaxSequence(@Param(value = "userId") String userId);
}