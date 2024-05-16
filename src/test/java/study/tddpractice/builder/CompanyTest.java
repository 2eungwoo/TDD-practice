package study.tddpractice.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("일반적인 빌더 패턴 테스트")
class CompanyTest {
    private static final String NAME = "이름";
    private static final String ADDRESS = "주소";
    private static final String DESCRIPTION = "설명";


    @DisplayName("company 생성 1")
    @Test
    void test1() {
        // given
        Company company = Company.builder()
                .name(NAME)
                .address(ADDRESS)
                .description(DESCRIPTION)
                .build();

        // then
        assertThat(company.getName()).isEqualTo(NAME);
        assertThat(company.getAddress()).isEqualTo(ADDRESS);
        assertThat(company.getDescription()).isEqualTo(DESCRIPTION);
    }

    @DisplayName("company 생성 2 : address만 지정")
    @Test
    void test2(){
        // given
        Company company = Company.builder()
                .address(ADDRESS)
                .build();

        // then
        assertThat(company.getName()).isNull();
        assertThat(company.getAddress()).isEqualTo(ADDRESS);
        assertThat(company.getDescription()).isNull();

    }

}
