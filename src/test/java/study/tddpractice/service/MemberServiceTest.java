package study.tddpractice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.tddpractice.domain.Member;
import study.tddpractice.domain.MemberRepository;
import study.tddpractice.dto.MemberDto;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 생성")
    void saveMember() {
        // given
        Member newMember = Member.builder()
                .id(333L)
                .memberName("Tester")
                .build();

        MemberDto.Request request = MemberDto.Request.builder()
                .memberName("Tester")
                .build();

        given(memberRepository.save(any(Member.class))).willReturn(newMember); // mocking

        // when
        MemberDto.Response savedMember = memberService.saveMember(request);

        // then
        assertThat(savedMember.getMemberName()).isEqualTo("Tester");
    }

    @Test
    @DisplayName("회원 조회 by id")
    void findMember() {
        // given
        Long fakeMemberId = 333L;
        Member newMember = Member.builder()
                .id(fakeMemberId)
                .memberName("Tester")
                .build();

        given(memberRepository.findById(fakeMemberId)).willReturn(Optional.ofNullable(newMember)); // mocking

        // when
        MemberDto.Response foundMember = memberService.findMemberById(fakeMemberId);

        // then
        assert newMember != null;
        assertThat(newMember.getId()).isEqualTo(foundMember.getMemberId());
        assertThat(newMember.getMemberName()).isEqualTo(foundMember.getMemberName());

    }
}