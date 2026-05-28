package kr.ai.janus.common.exception;

import kr.ai.janus.auth.oauth.UnsupportedProviderException;
import kr.ai.janus.auth.oauth.kakao.KakaoAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(KakaoAuthException.class)
    public ResponseEntity<ErrorResponse> handleKakaoAuth(KakaoAuthException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("KAKAO_AUTH_FAILED", e.getMessage()));
    }

    @ExceptionHandler(UnsupportedProviderException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedProvider(UnsupportedProviderException e) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("UNSUPPORTED_PROVIDER", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        if (e.getRootCause() instanceof UnsupportedProviderException cause) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("UNSUPPORTED_PROVIDER", cause.getMessage()));
        }
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("BAD_REQUEST", "잘못된 요청 파라미터입니다"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .orElse("잘못된 요청입니다");
        return ResponseEntity.badRequest()
                .body(new ErrorResponse("VALIDATION_FAILED", message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception e) {
        log.error("Unexpected server error", e);
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse("INTERNAL_ERROR", "서버 오류가 발생했습니다"));
    }
}
