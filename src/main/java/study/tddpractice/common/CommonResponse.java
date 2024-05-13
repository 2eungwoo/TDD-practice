package study.tddpractice.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter // 이거 없으면 406 에러남...
public class CommonResponse<T> {
    private final HttpStatus statusCode;
    private final String message;
    private final T data;
}
