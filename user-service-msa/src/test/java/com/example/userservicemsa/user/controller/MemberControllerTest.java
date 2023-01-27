package com.example.userservicemsa.user.controller;

import com.example.userservicemsa.RestDocsConfiguration;
import com.example.userservicemsa.user.dto.SignupDTO;
import com.example.userservicemsa.user.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.example.util.ApiDocumentUtils.getDocumentRequest;
import static com.example.util.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MemberController.class)
//@SpringBootTest와 같이 WebMvcTest선언시
// Configuration error: found multiple declarations of @BootstrapWith for test class 와 같은 에러 발생
// @BootstrapWith 동일하게 선언되어있음
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
// test property 설정
@TestPropertySource(locations = "classpath:/application.yaml")
// mock테스트시 security csrf 403에러 필터 제외
@AutoConfigureMockMvc(addFilters=false)
@Import(RestDocsConfiguration.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    public void memberSignupTest() throws Exception {

        //given
        SignupDTO signupDTO = SignupDTO.builder()
                .name("토리")
                .email("tory@naver.com")
                .id("tory")
                .pw("tory")
                .phoneNum("01099827271")
                .build();

        //when
        ResultActions result = this.mockMvc.perform(
                post("/signup")
                        .content(objectMapper.writeValueAsString(signupDTO))
                        .header("X-API-VERSION", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );
        //then
        result.andExpect(status().isCreated())
                .andDo(document("Get user orderList is success",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("id").type(JsonFieldType.STRING).description("아이디"),
                                fieldWithPath("pw").type(JsonFieldType.STRING).description("패스워드"),
                                fieldWithPath("phoneNum").type(JsonFieldType.STRING).description("핸드폰번호").optional(),
                                fieldWithPath("birthInfo").type(JsonFieldType.STRING).description("주소").optional(),
                                fieldWithPath("address").type(JsonFieldType.STRING).description("생년월일").optional()
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태코드"),
                                fieldWithPath("msg").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("responseData").type(JsonFieldType.STRING).description("응답데이터").optional(),
                                fieldWithPath("_links.main.href").type(JsonFieldType.STRING).description("메인주소").optional()
                        )
                ));
    }

}