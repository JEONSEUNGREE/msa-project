package com.example.userservicemsa.user.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "HISTORY_MS")
@Getter
@Builder
public class HistoryMs {

    /**
     * 기본키
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id", nullable = false)
    private Integer historyId;

    /**
     * 유저 외래키
     */
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberMs memberMs;

    /**
     * 히스토리키
     */
    @Column(name = "history_key", nullable = false)
    private String historyKey;

    /**
     * 타입
     */
    @Column(name = "history_type", nullable = false)
    private String historyType;

    /**
     * 메타 정보
     */
    @Column(name = "history_meta", nullable = false)
    private String historyMeta;

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
