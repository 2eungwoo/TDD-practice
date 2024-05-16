package study.tddpractice.builder;

public class CompanyWithEssentialField {
    private final String name;
    private final String address;
    private final String description;

    // @AllArgsConstructor가 필요한 이유
    protected CompanyWithEssentialField(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

    // 필수 필드 강제하는 법
    // 필수 필드 강제 아니라면 그냥 return new CompanyBuilder(); 일텐데 name 필드는 강제해준 모습
    public static CompanyBuilder builder(String name){
        return new CompanyBuilder()
                .name(name);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    // @Builder 애들
    public static class CompanyBuilder {
        private String name;
        private String address;
        private String description;

        protected CompanyBuilder() {}

        public CompanyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CompanyBuilder address(String address) {
            this.address = address;
            return this;
        }

        public CompanyBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CompanyWithEssentialField build() {
            return new CompanyWithEssentialField(name, address, description);
        }
    }
}