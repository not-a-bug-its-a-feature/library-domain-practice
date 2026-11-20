package ltwrld.shared.web;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.*;

public class DefaultRouterFilter
    implements HandlerFilterFunction<ServerResponse, ServerResponse> {
    @Override
    public ServerResponse filter(
            ServerRequest request,
            HandlerFunction<ServerResponse> next
    ) throws Exception {
        ServerResponse response = next.handle(request);

        if (response instanceof EntityResponse<?> entityResponse) {
            Object body = entityResponse.entity();

            if (shouldSkipWrapping(body, response)) {
                return response;
            }

            return ServerResponse.from(response)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new Response.Success<>(body));

        } else if (response.statusCode().is2xxSuccessful()) {
            return ServerResponse.from(response)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new Response.Success<>(null));
        }

        return response;
    }

    private boolean shouldSkipWrapping(Object body, ServerResponse response) {
        if (body instanceof Response || body instanceof Resource) {
            return true;
        }

        var contentType = response.headers().getContentType();

        return contentType != null && !contentType.isCompatibleWith(MediaType.APPLICATION_JSON);
    }
}
