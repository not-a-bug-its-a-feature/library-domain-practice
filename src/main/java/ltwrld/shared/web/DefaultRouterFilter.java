package ltwrld.shared.web;

import ltwrld.shared.exception.DomainException;
import ltwrld.shared.exception.ValidationException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.*;

import java.time.Instant;
import java.util.Map;

public class DefaultRouterFilter
    implements HandlerFilterFunction<ServerResponse, ServerResponse> {
    @Override
    public ServerResponse filter(
            ServerRequest request,
            HandlerFunction<ServerResponse> next
    ) throws Exception {
        try {
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
        } catch (ValidationException e) {
            return ServerResponse
                    .status(HttpStatus.UNPROCESSABLE_CONTENT)
                    .body(new Response.Fail(
                            e.errorCode().code(),
                            e.errorCode().message(),
                            e.errors(),
                            Instant.now()
                    ));
        }
    }

    private boolean shouldSkipWrapping(Object body, ServerResponse response) {
        if (body instanceof Response || body instanceof Resource) {
            return true;
        }

        var contentType = response.headers().getContentType();

        return contentType != null && !contentType.isCompatibleWith(MediaType.APPLICATION_JSON);
    }
}
