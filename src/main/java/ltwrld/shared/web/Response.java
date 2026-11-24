package ltwrld.shared.web;

import ltwrld.shared.exception.ErrorCode;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public sealed interface Response
        permits Response.Success, Response.Fail
{
    record Success<T>(
            T data
    ) implements Response { }

    record Fail(
            String code,
            String message,
            Map<String, List<String>> errors,
            Instant timestamp
    ) implements Response {}
}
