package ltwrld.member.adapter.in.web;

import ltwrld.shared.web.DefaultRouterFilter;
import ltwrld.shared.web.RouterMarker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class MemberTypeRouter implements RouterMarker {
    @Bean
    public RouterFunction<ServerResponse> memberRoutes(MemberTypeHandler handler) {
        return RouterFunctions.route()
                .path("/api/member",
                        builder -> builder.POST("", handler::createMemberType)
                ).filter(new DefaultRouterFilter()).build();
    }
}
