package com.example.orderservice.order.orderEntity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_MS")
public class OrderMs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 주문번호 pk
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    /**
     * 아이디
     */
    @Column(name = "user_id", nullable = false)
    private String userId;

    /**
     * 카테고리  pk
     */
    @Column(name = "category_id")
    private String category_id;

    /**
     * 상품  pk
     */
    @Column(name = "product_id", nullable = false)
    private Integer product_id;

    /**
     * 주문차수
     */
    @Column(name = "order_sequence", nullable = false)
    private Integer order_sequence;

    /**
     * 상품명
     */
    @Column(name = "product_name", nullable = false)
    private String product_name;

    /**
     * 수량
     */
    @Column(name = "qty", nullable = false)
    private int qty;

    /**
     * 거래 상태
     */
    @Column(name = "order_status", nullable = false)
    private String order_status;

    /**
     * 금액
     */
    @Column(name = "order_price", nullable = false)
    private int order_price;

    /**
     * 생성일자
     */
    @Column(name = "reg_date", nullable = false)
    private Date regDate;

    /**
     * 수정일자
     */
    @Column(name = "modify_date", nullable = false)
    private Date modifyDate;

}
