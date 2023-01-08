package com.example.userservicemsa.user.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "MEMBER_MS")
@Getter
@Builder
public class MemberMs {

    /**
     * 기본키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    /**
     * 아이디
     */
    @Column(name = "user_id", nullable = false)
    private String userId;

    /**
     * 패스워드
     */
    @Column(name = "member_pw", nullable = false)
    private String memberPw;

    /**
     * 이름
     */
    @Column(name = "member_name", nullable = false)
    private String memberName;

    /**
     * 핸드폰번호
     */
    @Column(name = "member_phone_number")
    private String memberPhoneNumber;

    /**
     * 이메일
     */
    @Column(name = "member_email")
    private String memberEmail;

    /**
     * 주소
     */
    @Column(name = "member_address")
    private String memberAddress;

    /**
     * 지번
     */
    @Column(name = "member_road_address")
    private String memberRoadAddress;

    /**
     * 히스토리키
     */
    @Column(name = "history_key")
    private String historyKey;

    /**
     * 생성일자
     */
    @Column(name = "reg_date")
    private Date regDate;

    /**
     * 수정일자
     */
    @Column(name = "modify_date")
    private Date modifyDate;

    /* 양방향 매핑 */

    // 히스토리키
    @OneToMany(mappedBy = "memberMs")
    private List<HistoryMs> historyMs = new ArrayList<>();

    // 권한
    @OneToMany(mappedBy = "memberMs")
    private List<AuthMs> AuthMs = new ArrayList<>();

}
