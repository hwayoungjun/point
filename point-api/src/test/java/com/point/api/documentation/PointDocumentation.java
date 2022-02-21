package com.point.api.documentation;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class PointDocumentation {

    public static RestDocumentationResultHandler accumulate() {
        return document("point/api/accumulate",
                requestFields(
                        fieldWithPath("userIdx").type(JsonFieldType.NUMBER).description("유저 IDX"),
                        fieldWithPath("amount").type(JsonFieldType.NUMBER).description("적립 금액")
                )
        );
    }
}
