package ltwrld.shared.web;

public sealed interface Response
        permits Response.Success, Response.Fail
{
    record Success<T>(
            T data
    ) implements Response { }

    record Fail(
            String errorCode,
            String message
    ) implements Response {}
}
