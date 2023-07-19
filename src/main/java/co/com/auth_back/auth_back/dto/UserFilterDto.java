package co.com.auth_back.auth_back.dto;

public class UserFilterDto {
    private String name;
    private String role;
    private String active;

    public UserFilterDto() {
        this.name = "";
        this.role = "";
        this.active = "";
    }
}
