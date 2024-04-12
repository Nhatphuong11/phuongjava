package com.ra.dto.request;

import com.ra.dto.validate.PasswordConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserLoginDTO {
    @NotEmpty(message = "không được rỗng")
    @Email(message = "Sai dinh dạng email")
    private String email;

    @PasswordConstraint
    private String password;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserLoginDTO() {
    }

    public UserLoginDTO(String email, String password, Integer userId ) {
        this.email = email;
        this.password = password;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
