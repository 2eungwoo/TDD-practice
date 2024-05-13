package study.tddpractice.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import study.tddpractice.domain.Member;
import study.tddpractice.dto.MemberDto;
import study.tddpractice.service.MemberService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName(" 회원 저장 ")
    // https://dd-developer.tistory.com/108
    // https://steady-hello.tistory.com/90
    void saveMember() throws Exception {
        // given
        MemberDto.Request memberJoinRequest = MemberDto.Request.builder()
                .memberId(100L)
                .memberName("TTT")
                .build();

        Member mem = memberJoinRequest.toEntity();
        given(memberService.saveMember(/*memberJoinRequest*/any())).willReturn(new MemberDto.Response(mem));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String memberJson = objectMapper.writeValueAsString(memberJoinRequest);


        System.out.println("============== "+mem.getId());
        System.out.println("============== "+mem.getMemberName());

        System.out.println("============== "+memberJson);


        // when
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/join")
                        .content(memberJson)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(100L))
                .andExpect(jsonPath("$.data.memberName").value("TTT"))
                .andDo(print());

    }

    @Test
    @DisplayName(" 회원 조회 by ID ")
    void findMemberById() throws Exception {
        // given
        Long mockMemberId = 3L;
        Member mem = Member.builder()
                .id(mockMemberId)
                .memberName("TE")
                .build();

        given(memberService.findMemberById(mockMemberId)).willReturn(new MemberDto.Response(mem));

        // when
        Long memberId = 3L;
        final ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/members/{memberId}", memberId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(3))
                .andExpect(jsonPath("$.data.memberName").value("TE"))
                .andDo(print());

    }

}