package kr.ai.janus.auth.dto;

public record AuthResponse(
        String token,
        Long userId,
        String nickname
) {}
