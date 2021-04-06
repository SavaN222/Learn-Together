package com.nakaradasava.learntogether.entity;

import com.nakaradasava.learntogether.validation.ValidEmail;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RegistrationStudent {

    private Integer id;

    @NotNull(message = "is required")
    @Size(min = 3, message = "minimum length is 3")
    private String username;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String password;

    @ValidEmail
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String email;

    private boolean enabled;

    private String profilePic;

    private String role;

    private College college;

    private StudyField studyField;

    private String gender;
}
