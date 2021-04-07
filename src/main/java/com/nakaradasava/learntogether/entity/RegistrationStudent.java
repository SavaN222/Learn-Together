package com.nakaradasava.learntogether.entity;

import com.nakaradasava.learntogether.validation.ValidEmail;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    private University university;

    @NotNull(message = "is required")
    private StudyField studyField;

    @NotNull(message = "is required")
    private String gender;
}
