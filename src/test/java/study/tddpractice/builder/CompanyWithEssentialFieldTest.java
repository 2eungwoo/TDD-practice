package study.tddpractice.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("필수 필드값이 존재하는 빌더 패턴 테스트")
class CompanyWithEssentialFieldTest {
    private static final String NAME = "이름";
    private static final String ADDRESS = "주소";
    private static final String DESCRIPTION = "설명";


    @DisplayName("company 생성 1")
    @Test
    void test1() {
        // given
        CompanyWithEssentialField companyWithEssentialField = CompanyWithEssentialField.builder(NAME)
                .address(ADDRESS)
                .description(DESCRIPTION)
                .build();

        // then
        assertThat(companyWithEssentialField.getName()).isNotNull();
        assertThat(companyWithEssentialField.getName()).isEqualTo(NAME);
        assertThat(companyWithEssentialField.getAddress()).isEqualTo(ADDRESS);
        assertThat(companyWithEssentialField.getDescription()).isEqualTo(DESCRIPTION);
    }

    @DisplayName("company 생성 2")
    @Test
    void test2(){
        // given
        CompanyWithEssentialField companyWithEssentialField = CompanyWithEssentialField.builder(NAME)
                .address(ADDRESS)
                .build();

        // then
        assertThat(companyWithEssentialField.getName()).isEqualTo(NAME);
        assertThat(companyWithEssentialField.getAddress()).isEqualTo(ADDRESS);
        assertThat(companyWithEssentialField.getDescription()).isNull();

    }

}
