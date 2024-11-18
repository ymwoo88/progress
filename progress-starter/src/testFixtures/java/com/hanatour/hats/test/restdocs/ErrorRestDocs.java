package com.hanatour.hats.test.restdocs;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;

import java.util.List;

public class ErrorRestDocs extends DefaultRestDocs {

    public static List<FieldDescriptor> defaultErrorResponseFieldsDescriptor() {

        return List.of(
                PayloadDocumentation.fieldWithPath("type").type(JsonFieldType.STRING).description("타입"),
                PayloadDocumentation.fieldWithPath("title").type(JsonFieldType.STRING).description("오류 이름"),
                PayloadDocumentation.fieldWithPath("status").type(JsonFieldType.NUMBER).description("상태 코드"),
                PayloadDocumentation.fieldWithPath("detail").type(JsonFieldType.STRING).description("상세 설명"),
                PayloadDocumentation.fieldWithPath("instance").type(JsonFieldType.STRING).description("호출 경로")
        );
    }

}
