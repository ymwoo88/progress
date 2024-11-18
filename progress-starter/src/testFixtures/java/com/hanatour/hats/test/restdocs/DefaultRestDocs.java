package com.hanatour.hats.test.restdocs;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.headers.HeaderDocumentation;

import java.util.List;

public class DefaultRestDocs {

    public static List<HeaderDescriptor> defaultRequestHeadersDescriptor() {

        return List.of(
                HeaderDocumentation.headerWithName(HttpHeaders.CONTENT_TYPE).description("요청 Content-Type 헤더"),
                HeaderDocumentation.headerWithName(HttpHeaders.ACCEPT).description("요청 Accept 헤더")
        );
    }

    public static List<HeaderDescriptor> defaultResponseHeadersDescriptor() {

        return List.of(
                HeaderDocumentation.headerWithName(HttpHeaders.CONTENT_TYPE).description("응답 Content-Type 헤더")
        );
    }

    public static ResourceSnippetParameters resource(String tagName) {
        return ResourceSnippetParameters.builder().tag(tagName).build();
    }
}
