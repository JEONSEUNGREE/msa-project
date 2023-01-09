package com.example.userservicemsa.user.service;

import com.example.userservicemsa.user.dto.SignupDTO;
import com.example.userservicemsa.user.entity.AuthMs;
import com.example.userservicemsa.user.entity.HistoryMs;
import com.example.userservicemsa.user.entity.MemberMs;
import com.example.userservicemsa.user.repository.AuthMsRepository;
import com.example.userservicemsa.user.repository.HistoryMsRepository;
import com.example.userservicemsa.user.repository.MemberMsRepository;
import com.example.userservicemsa.user.vo.MemberMsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {

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
    public MemberMsVO getMemberInfo(MemberMsVO memberMsVO) {

        // 유저정보 조회
        MemberMs memberMs = memberMsRepository.findAllByUserId(memberMsVO.getUserId());
        MemberMsVO memberVo = new MemberMsVO();

        // VO로 변환
        BeanUtils.copyProperties(memberMs, memberVo);

        return memberMsVO;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
