package study.tddpractice.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    REGISTER_SUCCESS(200, "M001", "멤버 가입 성공."),
    MEMBER_INFO_SUCCESS(200, "M005", "멤버 정보 조회 성공");

    private final int status;
    private final String code;
    private final String message;
}