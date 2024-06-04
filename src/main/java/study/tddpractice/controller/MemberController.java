package study.tddpractice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.tddpractice.dto.MemberDto;
import study.tddpractice.global.response.ResponseCode;
import study.tddpractice.global.response.ResultResponse;
import study.tddpractice.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<?> saveMember(@RequestBody MemberDto.Request request){

        MemberDto.Response savedMemberResponseDto = memberService.saveMember(request);

        log.info("member Id = {}", savedMemberResponseDto.getMemberId());
        log.info("member name = {}", savedMemberResponseDto.getMemberName());

        ResultResponse response = ResultResponse.of(ResponseCode.REGISTER_SUCCESS, savedMemberResponseDto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable Long memberId){

        MemberDto.Response memberResponseDto = memberService.findMemberById(memberId);

        ResultResponse response = ResultResponse.of(ResponseCode.MEMBER_INFO_SUCCESS,memberResponseDto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
