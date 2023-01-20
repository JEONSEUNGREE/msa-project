package com.example.orderservice.order.orderEntity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ORDER_MS")
public class OrderMs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 기본키
     */
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    /**
     * 거래명
     */
    @Column(name = "order_name")
    private String orderName;

    /**
     * 거래상태
     */
    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    /**
     * 구매자이름
     */
    @Column(name = "category_id", nullable = false)
    private String categoryId;

    /**
     * 금액
     */
    @Column(name = "order_price", nullable = false)
    private String orderPrice;

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
