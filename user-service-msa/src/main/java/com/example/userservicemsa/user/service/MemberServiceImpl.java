package com.example.userservicemsa.user.service;

import com.example.userservicemsa.exception.UserNotFoundException;
import com.example.userservicemsa.user.dto.SignupDTO;
import com.example.userservicemsa.user.entity.AuthMs;
import com.example.userservicemsa.user.entity.MemberMs;
import com.example.userservicemsa.user.repository.AuthMsRepository;
import com.example.userservicemsa.user.repository.HistoryMsRepository;
import com.example.userservicemsa.user.repository.MemberMsRepository;
import com.example.userservicemsa.user.vo.MemberMsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private MemberMsRepository memberMsRepository;

    private AuthMsRepository authMsRepository;

    private HistoryMsRepository historyMsRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberMsRepository memberMsRepository,
                             AuthMsRepository authMsRepository,
                             HistoryMsRepository historyMsRepository,
                             BCryptPasswordEncoder passwordEncoder) {
        this.memberMsRepository = memberMsRepository;
        this.authMsRepository = authMsRepository;
        this.historyMsRepository = historyMsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 유저 정보 조회
     * @param memberMsVO
     * @return MemberMsVO
     */
    @Override
    public MemberMsVO getMemberInfo(MemberMsVO memberMsVO) throws UserNotFoundException {

        // 유저정보 조회
        Optional<MemberMs> memberMs = memberMsRepository.findByUserId(memberMsVO.getUserId());
        memberMs.orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // VO로 변환
        MemberMsVO memberVo = MemberMsVO.builder()
                .memberName(memberMs.get().getMemberName())
                .userId(memberMs.get().getUserId())
                .memberPw(memberMs.get().getMemberPw().toString())
                .memberEmail(memberMs.get().getMemberEmail())
                .memberAddress(memberMs.get().getMemberAddress())
                .memberRoadAddress(memberMs.get().getMemberRoadAddress())
                .memberPhoneNumber(memberMs.get().getMemberPhoneNumber())
                .build();

        return memberVo;
    }

    /**
     * 회원가입
     * @param signupDTO
     * @return boolean
     */
    @Override
    public boolean signupMember(SignupDTO signupDTO) {
        try
        {
            MemberMs memberInfo = MemberMs.builder()
                    .userId(signupDTO.getId())
                    .memberEmail(signupDTO.getEmail())
                    .memberName(signupDTO.getName())
                    .memberPhoneNumber(signupDTO.getPhoneNum())
                    .memberPw(passwordEncoder.encode(signupDTO.getPw()))
                    .build();

            AuthMs authInfo = AuthMs.builder()
                    .memberMs(memberInfo)
                    .authType("USER")
                    .regDate(new Date())
                    .modifyDate(new Date())
                    .build();

            memberMsRepository.save(memberInfo);
            authMsRepository.save(authInfo);
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        MemberMs memberMs = memberMsRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        // 권한설정
        return new User(memberMs.getUserId(), memberMs.getMemberPw(), new ArrayList<>());
    }
}
