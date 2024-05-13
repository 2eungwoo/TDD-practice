package study.tddpractice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.tddpractice.domain.Member;
import study.tddpractice.domain.MemberRepository;
import study.tddpractice.dto.MemberDto;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto.Response saveMember(MemberDto.Request request){

        Member savedMember = memberRepository.save(request.toEntity());

        MemberDto.Response savedMemberResponseDto = new MemberDto.Response(savedMember);
        return savedMemberResponseDto;
    }

    @Transactional(readOnly = true)
    public MemberDto.Response findMemberById(Long memberId){
        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(()-> new NoSuchElementException("유저없음"));

        MemberDto.Response memberResponseDto = new MemberDto.Response(foundMember);
        return memberResponseDto;
    }
}
