package com.example.productservice.product.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@Getter
@Table(name = "PRODUCTS_MS")
@NoArgsConstructor
@AllArgsConstructor
public class ProductsMs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 기본키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    /**
     * 카테고리 외래키
     */
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    /**
     * 판매자 아이디
     */
    @Column(name = "user_id", nullable = false)
    private String userId;

    /**
     * 상품명
     */
    @Column(name = "product_name", nullable = false)
    private String productName;

    /**
     * 상품설명
     */
    @Column(name = "product_desc", nullable = false)
    private String productDesc;

    /**
     * 장소
     */
    @Column(name = "product_location")
    private String productLocation;

    /**
     * 판매상태코드
     */
    @Column(name = "product_status")
    private String productStatus;

    /**
     * 이미지정보들
     */
    @Column(name = "product_images")
    private String productImages;

    /**
     * 금액
     */
    @Column(name = "product_price", nullable = false)
    private Integer productPrice;

    /**
     * 수량
     */
    @Column(name = "qty", nullable = false)
    private Integer qty;

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

    public void changeProductQty(Integer qty, String what) {
        if ("buy".equals(what)) {
            this.qty = this.qty - qty;
        } else {
            this.qty = this.qty + qty;
        }
    }

}
