package kr.ai.janus.common.exception;

public record ErrorResponse(
        String code,
        String message
) {}
