package study.tddpractice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.tddpractice.common.CommonResponse;
import study.tddpractice.dto.MemberDto;
import study.tddpractice.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public CommonResponse<?> saveMember(@RequestBody MemberDto.Request request){

        MemberDto.Response savedMemberResponseDto = memberService.saveMember(request);

        log.info("member Id = {}", savedMemberResponseDto.getMemberId());
        log.info("member name = {}", savedMemberResponseDto.getMemberName());

        return CommonResponse.builder()
                .statusCode(HttpStatus.CREATED)
                .message(" 멤버 저장 성공 ")
                .data(savedMemberResponseDto)
                .build();
    }

    @GetMapping("/members/{memberId}")
    public CommonResponse<?> getMember(@PathVariable Long memberId){

        MemberDto.Response member = memberService.findMemberById(memberId);

        return CommonResponse.builder()
                .statusCode(HttpStatus.OK)
                .message(" 멤버 조회 by ID 성공 ")
                .data(member)
                .build();
    }
}
