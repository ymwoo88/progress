package com.woo.progress.config.security.config;


import com.woo.progress.config.security.handlers.ErrorResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.AnnotatedType;

//@Configuration
//@OpenAPIDefinition(
//        info = @Info(
//                contact = @Contact(
//                        name = "Mossaab",
//                        email = "frifita1@gmail.com"
//                ),
//                title = "Spring Security 6+ APP",
//                description = "Spring Boot 3+ Spring Security 6+ example",
//                version = "0.0.1-SNAPSHOT"
//        ),
//        servers = {
//                @Server(
//                        description = "Development",
//                        url = "http://localhost:8086"
//
//                )
//        },
//        security = {
//                @SecurityRequirement(
//                        name = "bearerAuth"
//                )
//        }
//)
//@SecurityScheme(
//        name = "bearerAuth",
//        description = "JWT auth description",
//        scheme = "bearer",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        in = SecuritySchemeIn.HEADER
//)
public class OpenAPIConfiguration {
//    @Bean
//    public OpenApiCustomizer schemaCustomizer() {
//        ResolvedSchema resolvedSchema = ModelConverters.getInstance()
//                .resolveAsResolvedSchema(new AnnotatedType(ErrorResponse.class));
//        return openApi -> openApi
//                .schema(resolvedSchema.schema.getName(), resolvedSchema.schema);
//    }
}
