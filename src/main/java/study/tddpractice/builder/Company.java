package study.tddpractice.builder;

public class Company {
    private final String name;
    private final String address;
    private final String description;

    // @AllArgsConstructor가 필요한 이유
    protected Company(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public static CompanyBuilder builder(){
        return new CompanyBuilder();
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

        public Company build() {
            return new Company(name, address, description);
        }
    }
}