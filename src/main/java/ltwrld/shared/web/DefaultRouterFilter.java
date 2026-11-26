package ltwrld.shared.web;

import ltwrld.shared.exception.ValidationException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.*;

import java.time.Instant;

/**
 * router処理の共通filter
 * responseの共通形式への変換と有効性エラー処理
 */
public class DefaultRouterFilter
    implements HandlerFilterFunction<ServerResponse, ServerResponse> {
    /**
     * Handlerの実行結果を処理し必要に応じて共通response形式に変換
     * @param request　Http request
     * @param next 次に実行するhandler
     * @return 共通形式に変換されたresponse or 元のresponse
     * @throws Exception handlerの実行中に例外が発生した場合
     */
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

    /**
     * 共通response形式へ変換をskipするかどうか判定
     * @param body　response body
     * @param response handlerのresponse
     * @return 変換をskipする場合true
     */
    private boolean shouldSkipWrapping(Object body, ServerResponse response) {
        if (body instanceof Response || body instanceof Resource) {
            return true;
        }

        var contentType = response.headers().getContentType();

        return contentType != null && !contentType.isCompatibleWith(MediaType.APPLICATION_JSON);
    }
}
