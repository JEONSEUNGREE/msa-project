package com.example.productservice.product.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Builder
@Table(name = "PRODUCTS_VIDEO")
@NoArgsConstructor
@AllArgsConstructor
public class ProductsVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 기본키
     */
    @Id
    @Column(name = "product_video_id", nullable = false)
    private Integer productVideoId;

    /**
     * 상품 외래키
     */
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    /**
     * 비디오정보
     */
    @Column(name = "url")
    private String url;

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
