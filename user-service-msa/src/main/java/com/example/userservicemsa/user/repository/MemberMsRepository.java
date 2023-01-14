package com.example.userservicemsa.user.repository;

import com.example.userservicemsa.user.entity.MemberMs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberMsRepository extends JpaRepository<MemberMs, Long> {

    public Optional<MemberMs> findByUserId(String userId);
}
