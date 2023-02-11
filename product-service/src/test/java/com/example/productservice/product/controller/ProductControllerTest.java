package com.example.productservice.product.controller;

import com.example.commonsource.productDto.ProductViewDto;
import com.example.productservice.RestDocsConfiguration;
import com.example.productservice.interceptor.CommonArgumentResolver;
import com.example.productservice.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.example.productservice.util.ApiDocumentUtils.getDocumentRequest;
import static com.example.productservice.util.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
@TestPropertySource(locations = "classpath:/application.yaml")
@Import(RestDocsConfiguration.class)
@AutoConfigureMockMvc(addFilters=false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;


    @BeforeEach
    public void setUp() {
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new ProductControllerTest())
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .setCustomArgumentResolvers(new CommonArgumentResolver())
                .build();
    }

    @Test
    @WithMockUser
    public void getProductInfo() throws Exception {
        /* given */

        /* when */
        ResultActions result = this.mockMvc.perform(
                get("/getProductInfo")
                        .param("productId", "1")
                        .accept(MediaType.APPLICATION_JSON)
        );
        /* then */
        result.andExpect(status().isOk())
                .andDo(document("getProductInfo is success",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("productId").description("상품 아이디")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태코드"),
                                fieldWithPath("msg").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("responseData").type(JsonFieldType.ARRAY).description("응답데이터").optional(),
                                fieldWithPath("_links.self.href").type(JsonFieldType.STRING).description("상품정보").optional(),
                                fieldWithPath("_links.buyProduct.href").type(JsonFieldType.STRING).description("구매 URI").optional(),
                                fieldWithPath("_links.cancelProduct.href").type(JsonFieldType.STRING).description("취소 URI").optional()
                        )
                ));
    }

    @Test
    @WithMockUser
    public void buyProduct() throws Exception {
        /* given */

        ProductViewDto productViewDto = ProductViewDto.builder()
                .productId(1)
                .qty(7)
                .build();

        /* when */
        ResultActions result = this.mockMvc.perform(
                post("/buyProduct")
                        .header("account_token", "account_token")
                        .content(objectMapper.writeValueAsString(productViewDto))
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );
        /* then */
        result.andExpect(status().isAccepted())
                .andDo(document("buyProduct is success",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("orderId").type(JsonFieldType.NUMBER).description("주문PK").ignored(),
                                fieldWithPath("productId").type(JsonFieldType.NUMBER).description("상품아이디"),
                                fieldWithPath("qty").type(JsonFieldType.NUMBER).description("수량")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태코드"),
                                fieldWithPath("msg").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("responseData").type(JsonFieldType.ARRAY).description("응답데이터").optional(),
                                fieldWithPath("_links.getProductInfo.href").type(JsonFieldType.STRING).description("상품정보").optional(),
                                fieldWithPath("_links.getProductInfo.templated").type(JsonFieldType.BOOLEAN).ignored(),
                                fieldWithPath("_links.self.href").type(JsonFieldType.STRING).description("구매 URI").optional(),
                                fieldWithPath("_links.cancelProduct.href").type(JsonFieldType.STRING).description("취소 URI").optional()
                        )
                ));
    }


    @Test
    @WithMockUser
    public void cancelProduct() throws Exception {
        /* given */

        ProductViewDto productViewDto = ProductViewDto.builder()
                .productId(1)
                .qty(7)
                .build();

        /* when */
        ResultActions result = this.mockMvc.perform(
                post("/cancelProduct")
                        .header("account_token", "account_token")
                        .content(objectMapper.writeValueAsString(productViewDto))
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );
        /* then */
        result.andExpect(status().isAccepted())
                .andDo(document("cancelProduct is success",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("orderId").type(JsonFieldType.NUMBER).description("상품아이디").ignored(),
                                fieldWithPath("productId").type(JsonFieldType.NUMBER).description("상품아이디"),
                                fieldWithPath("qty").type(JsonFieldType.NUMBER).description("수량")
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태코드"),
                                fieldWithPath("msg").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("responseData").type(JsonFieldType.ARRAY).description("응답데이터").optional(),
                                fieldWithPath("_links.getProductInfo.href").type(JsonFieldType.STRING).description("상품정보").optional(),
                                fieldWithPath("_links.getProductInfo.templated").type(JsonFieldType.BOOLEAN).ignored(),
                                fieldWithPath("_links.buyProduct.href").type(JsonFieldType.STRING).description("구매 URI").optional(),
                                fieldWithPath("_links.self.href").type(JsonFieldType.STRING).description("취소 URI").optional()
                        )
                ));
    }

    @Test
    @WithMockUser
    public void sellProductList() throws Exception {

        /* when */
        ResultActions result = this.mockMvc.perform(
                get("/sellProductList")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        /* then */
        result.andExpect(status().isAccepted())
                .andDo(document("sellProductList is success",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.STRING).description("상태코드"),
                                fieldWithPath("msg").type(JsonFieldType.STRING).description("메시지"),
                                fieldWithPath("responseData").type(JsonFieldType.ARRAY).description("응답데이터").optional(),
                                fieldWithPath("_links.self.href").type(JsonFieldType.STRING).description("판매상품정보").optional(),
                                fieldWithPath("_links.cancelProduct.href").type(JsonFieldType.STRING).description("취소정보").optional(),
                                fieldWithPath("_links.getProductInfo.href").type(JsonFieldType.STRING).description("상품정보").optional(),
                                fieldWithPath("_links.buyProduct.href").type(JsonFieldType.STRING).description("구매 URI").optional()
                        )
                ));
    }
}