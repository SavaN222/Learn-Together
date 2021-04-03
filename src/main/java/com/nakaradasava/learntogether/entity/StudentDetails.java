package com.nakaradasava.learntogether.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class StudentDetails implements UserDetails {

    private Student student;

    public StudentDetails(Student student) {
        this.student = student;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> student.getRole());
    }

    @Override
    public String getPassword() {
        return student.getPassword();
    }

    /**
     * Because Spring Security and authorization mail is returned!
     * @return student mail
     */
    @Override
    public String getUsername() {
        return student.getEmail();
    }

    public String getName() {
        return student.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return student.isEnabled();
    }
}
