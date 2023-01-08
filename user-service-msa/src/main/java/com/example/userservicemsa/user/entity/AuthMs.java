package com.example.userservicemsa.user.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "AUTH_MS")
@Getter
@Builder
public class AuthMs {

    /**
     * 기본키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auth_id", nullable = false)
    private Integer authId;

    /**
     * 유저 외래키
     */
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberMs memberMs;

    /**
     * 권한
     */
    @Column(name = "auth_type", nullable = false)
    private String authType;

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
