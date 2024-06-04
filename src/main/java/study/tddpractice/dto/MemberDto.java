package study.tddpractice.dto;

import lombok.*;
import study.tddpractice.domain.Member;


public class MemberDto {

    @Builder
    @AllArgsConstructor
    public static class Request{
        private Long memberId;
        private String memberName;

        public Member toEntity(){
            return Member.builder()
                    .id(memberId)
                    .memberName(memberName)
                    .build();
        }
    }

    @Getter
    public static class Response{
        private Long memberId;
        private String memberName;

        public Response(Member member){
            this.memberId = member.getId();
            this.memberName = member.getMemberName();
        }
    }
}
