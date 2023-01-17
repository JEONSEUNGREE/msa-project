package com.example.userservicemsa.user.controller;


import com.example.userservicemsa.constants.CommonConstants;
import com.example.userservicemsa.interceptor.annotation.CurrentUser;
import com.example.userservicemsa.interceptor.annotation.LoginCheck;
import com.example.userservicemsa.servletUtil.JsonResponse;
import com.example.userservicemsa.user.dto.SignupDTO;
import com.example.userservicemsa.user.service.MemberService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
//@RequestMapping("/user-service")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @LoginCheck
    @GetMapping("/home")
    public ResponseEntity<JsonResponse> main(@CurrentUser String email) {
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
        response.add(linkTo(methodOn(MemberController.class).main("email")).withRel("main"));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
