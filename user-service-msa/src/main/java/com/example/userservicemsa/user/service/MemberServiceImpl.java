package com.example.userservicemsa.user.service;

import com.example.userservicemsa.exception.UserNotFoundException;
import com.example.userservicemsa.user.dto.SignupDTO;
import com.example.userservicemsa.user.entity.AuthMs;
import com.example.userservicemsa.user.entity.MemberMs;
import com.example.userservicemsa.user.repository.AuthMsRepository;
import com.example.userservicemsa.user.repository.HistoryMsRepository;
import com.example.userservicemsa.user.repository.MemberMsRepository;
import com.example.userservicemsa.user.vo.AuthMsVO;
import com.example.userservicemsa.user.vo.MemberMsVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberMsRepository memberMsRepository;

    private AuthMsRepository authMsRepository;

    private HistoryMsRepository historyMsRepository;

    public MemberServiceImpl(MemberMsRepository memberMsRepository,
                             AuthMsRepository authMsRepository,
                             HistoryMsRepository historyMsRepository) {
        this.memberMsRepository = memberMsRepository;
        this.authMsRepository = authMsRepository;
        this.historyMsRepository = historyMsRepository;
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

        try {
            MemberMs memberInfo = MemberMs.builder()
                    .userId(signupDTO.getId())
                    .memberEmail(signupDTO.getEmail())
                    .memberName(signupDTO.getName())
                    .memberPhoneNumber(signupDTO.getPhoneNum())
                    .memberPw(signupDTO.getPw())
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
            return false;
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return memberMsRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
