package com.example.userservicemsa.globalMsg.contoller;


import com.example.commonsource.response.JsonResponse;
import com.example.userservicemsa.globalMsg.BusRepository;
import com.example.userservicemsa.globalMsg.entity.BusMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
public class BusMessageController {

    private final BusRepository busRepository;

    @GetMapping("/home")
    public ResponseEntity<JsonResponse> main() {
        BusMessage busMessage = busRepository.findAll().stream()
                .filter(item -> "N".equals(item.getTransYn()))
                .findFirst()
                .orElseThrow();
        return ResponseEntity.status(HttpStatus.OK).body(success);
    }
}
