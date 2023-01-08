package com.example.userservicemsa.user.repository;

import com.example.userservicemsa.user.entity.MemberMs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMsRepository extends JpaRepository<MemberMs, Long> {

    public MemberMs findAllByUserId(String userId);

}
