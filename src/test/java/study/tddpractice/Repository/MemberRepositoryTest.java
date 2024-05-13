package study.tddpractice.Repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import study.tddpractice.domain.Member;
import study.tddpractice.domain.MemberRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach

    @Test
    @DisplayName(" 멤버 저장 ")
    void saveMember(){
        // given
        Member newMember = Member.builder()
                .memberName("test new member")
                .build();

        // when
        Member testSaveMember = memberRepository.save(newMember);

        // then
        assertThat("test new member").isEqualTo(testSaveMember.getMemberName());
    }

    @Test
    @DisplayName(" 멤버 조회 by ID ")
    void findMemberById(){
        // given
        Member newMember = Member.builder()
                .memberName("test member")
                .build();

        Member savedMember = memberRepository.save(newMember);

        // when
        Member foundMember = memberRepository.findById(savedMember.getId()).get();

        // then
        assertThat(foundMember)
                .isNotNull()
                .isEqualTo(savedMember);

        assertThat(savedMember.getId()).isEqualTo(foundMember.getId());
        assertThat(savedMember.getMemberName()).isEqualTo(foundMember.getMemberName());
    }


}
