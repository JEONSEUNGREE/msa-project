package com.example.userservicemsa.user.controller;

import com.example.userservicemsa.RestDocsConfiguration;
import com.example.userservicemsa.interceptor.CommonArgumentResolver;
import com.example.userservicemsa.user.dto.SignupDTO;
import com.example.userservicemsa.user.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.example.util.ApiDocumentUtils.getDocumentRequest;
import static com.example.util.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MemberController.class)
//@SpringBootTest??? ?????? WebMvcTest?????????
// Configuration error: found multiple declarations of @BootstrapWith for test class ??? ?????? ?????? ??????
// @BootstrapWith ???????????? ??????????????????
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
// test property ??????
@TestPropertySource(locations = "classpath:/application.yaml")
// mock???????????? security csrf 403?????? ?????? ??????
@AutoConfigureMockMvc(addFilters=false)
@Import(RestDocsConfiguration.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new MemberControllerTest())
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .setCustomArgumentResolvers(new CommonArgumentResolver())
                .build();
    }

    @Test
    public void memberSignupTest() throws Exception {

        //given
        SignupDTO signupDTO = SignupDTO.builder()
                .name("??????")
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
                .andDo(document("signup is success",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestHeaders(
                                headerWithName("X-API-VERSION").description("??????")
                        ),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("??????"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("id").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("pw").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("phoneNum").type(JsonFieldType.STRING).description("???????????????").optional(),
                                fieldWithPath("birthInfo").type(JsonFieldType.STRING).description("????????????").optional(),
                                fieldWithPath("address").type(JsonFieldType.STRING).description("??????").optional()
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("msg").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("responseData").type(JsonFieldType.STRING).description("???????????????").optional(),
                                fieldWithPath("_links.main.href").type(JsonFieldType.STRING).description("????????????").optional()
                        )
                ));
    }

}