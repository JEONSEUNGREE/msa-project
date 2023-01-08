package com.example.userservicemsa.user.controller;


import com.example.userservicemsa.constants.CommonConstants;
import com.example.userservicemsa.servletutil.JsonResponse;
import com.example.userservicemsa.user.dto.SignupDTO;
import com.example.userservicemsa.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public ResponseEntity<EntityModel<JsonResponse>> main() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/signup", headers = "X-API-VERSION=1")
    public ResponseEntity<EntityModel<JsonResponse>> signup(@RequestBody(required = true) SignupDTO signupDTO) {
        JsonResponse success = JsonResponse.builder()
                .status(HttpStatus.CREATED)
                .msg(CommonConstants.SUCCESS)
                .build();

        memberService.signupMember(signupDTO);

        EntityModel<JsonResponse> response = EntityModel.of(success);
        response.add(linkTo(methodOn(MemberController.class).main()).withRel("main"));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
