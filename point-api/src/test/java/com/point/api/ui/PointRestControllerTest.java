package com.point.api.ui;

import com.point.api.application.PointService;
import com.point.api.documentation.PointDocumentation;
import com.point.api.domain.Transaction;
import com.point.api.domain.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PointRestController.class)
@ExtendWith(RestDocumentationExtension.class)
public class PointRestControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private PointService pointService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void accumulate() throws Exception {
        final Transaction transaction = Transaction.builder()
                .type(TransactionType.ACCUMULATION)
                .amount(1000)
                .build();
        given(pointService.accumulate(any())).willReturn(transaction);

        mockMvc.perform(post("/api/accumulate")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userIdx\":1, \"amount\":1000}"))
                .andExpect(status().isOk())
                .andDo(PointDocumentation.accumulate());
    }
}
