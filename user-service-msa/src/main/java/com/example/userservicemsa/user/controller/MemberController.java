package com.example.userservicemsa.user.controller;


import com.example.commonsource.constant.CommonConstants;
import com.example.commonsource.orderDto.OrderResultDto;
import com.example.commonsource.response.JsonResponse;
import com.example.userservicemsa.interceptor.LoginInfo;
import com.example.userservicemsa.interceptor.annotation.CurrentUser;
import com.example.userservicemsa.interceptor.annotation.LoginCheck;
import com.example.userservicemsa.interceptor.annotation.VersionCheck;
import com.example.userservicemsa.user.dto.SignupDTO;
import com.example.userservicemsa.user.service.MemberService;
import io.micrometer.core.annotation.Timed;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.bouncycastle.asn1.cmc.CMCStatus.success;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
//@RequestMapping("/user-service")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @VersionCheck(versionKey = "API-VERSION", versionValue = "1")
    @GetMapping("/home")
    public ResponseEntity<JsonResponse> main() {
        JsonResponse success = JsonResponse.builder()
                .status(HttpStatus.OK)
                .msg("WELCOME HOME")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @PostMapping(value = "/signup", headers = "X-API-VERSION=1")
    public ResponseEntity<EntityModel<JsonResponse>> signup(@RequestBody(required = true) SignupDTO signupDTO) {
        HttpStatus status = HttpStatus.CREATED;
        String msg = CommonConstants.SUCCESS;
        boolean isSignup = false;

        isSignup = memberService.signupMember(signupDTO);

        if (!isSignup) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            msg = CommonConstants.FAIL;
        }

        JsonResponse success = JsonResponse.builder()
                .status(status)
                .msg(msg)
                .build();

        EntityModel<JsonResponse> response = EntityModel.of(success);
        response.add(linkTo(methodOn(MemberController.class).main()).withRel("main"));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @LoginCheck
    // 사용시 metrics 화면에 집계됨
    @Timed(value = "user.getOrderList", longTask = true)
    @GetMapping(value = "/getUserOrderList")
    public ResponseEntity<EntityModel<JsonResponse>> getOneUserOrderList(@CurrentUser LoginInfo loginInfo) {

        HttpStatus status = HttpStatus.OK;
        String msg = CommonConstants.ORDERED_LIST_NOT_EXIST;


        List<OrderResultDto> orderList = memberService.getOrderList(loginInfo.getJwtToken());

        if (orderList == null) {
            orderList = new ArrayList<>();
        } else {
            msg = CommonConstants.ORDERED_LIST_EXIST;
        }

        JsonResponse success = JsonResponse.builder()
                .status(status)
                .msg(msg)
                .responseData(orderList)
                .build();

        EntityModel<JsonResponse> response = EntityModel.of(success);
        response.add(linkTo(methodOn(MemberController.class).main()).withRel("main"));

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
