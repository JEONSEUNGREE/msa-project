package com.example.orderservice.order.controller;

import com.example.commonsource.constant.CommonConstants;
import com.example.commonsource.orderDto.OrderResultDto;
import com.example.commonsource.orderDto.OrderViewDto;
import com.example.commonsource.response.JsonResponse;
import com.example.orderservice.interceptor.LoginInfo;
import com.example.orderservice.interceptor.annotation.CurrentUser;
import com.example.orderservice.interceptor.annotation.LoginCheck;
import com.example.orderservice.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/home")
    public ResponseEntity<JsonResponse> orderHome() {
        JsonResponse success = JsonResponse.builder()
                .status(HttpStatus.OK)
                .msg("WELCOME HOME")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }

    @LoginCheck
    @PostMapping(value = "/order")
    public EntityModel<JsonResponse> orderProduct(@CurrentUser LoginInfo loginInfo, @RequestBody OrderViewDto orderViewDto) {

        orderService.orderProduct(orderViewDto, loginInfo);

        JsonResponse result = JsonResponse.builder()
                .msg(CommonConstants.SUCCESS)
                .responseData(CommonConstants.SUCCESS)
                .status(HttpStatus.CREATED)
                .build();

        EntityModel<JsonResponse> response = EntityModel.of(result);
        return response;
    }

    @LoginCheck
    @GetMapping(value = "/orderList")
    public EntityModel<JsonResponse> getOrderList(@CurrentUser LoginInfo loginInfo) {

        String msg = "";
        List<OrderResultDto> userOrderList = new ArrayList<>();

        userOrderList = orderService.getOneUserOrderList((String) loginInfo.getUserId());

        if (userOrderList.isEmpty()) {
            msg = CommonConstants.ORDERED_LIST_NOT_EXIST;
        }

        JsonResponse result = JsonResponse.builder()
                .msg(msg)
                .status(HttpStatus.OK)
                .responseData(userOrderList)
                .build();

        EntityModel<JsonResponse> response = EntityModel.of(result);

        response.add(linkTo(methodOn(OrderController.class).orderHome()).withRel("orderHome"));
        /* hetaos 사용시 string 리턴하는 메서드 사용시 아래와 같은 오류 발생 */
        /* could not generate CGLIB subclass of class java.lang.String:
        Common causes of this problem include using a final class or a non-visible class;
        nested exception is java.lang.IllegalArgumentException: Cannot subclass final class java.lang.String"
         */

        return response;
    }


}
