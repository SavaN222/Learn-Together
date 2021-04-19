package com.nakaradasava.learntogether.entity.student;

import com.nakaradasava.learntogether.entity.studyfield.StudyField;
import com.nakaradasava.learntogether.entity.university.University;
import com.nakaradasava.learntogether.entity.university.UniversityPost;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "description")
    private String description;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    private University university;

    @ManyToOne
    @JoinColumn(name = "study_field_id", referencedColumnName = "id")
    private StudyField studyField;

    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "student")
    @OrderBy("id DESC")
    private List<StudentPost> posts;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    public void setStudyField(StudyField studyField) {
        this.studyField = studyField;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<StudentPost> getPosts() {
        return posts;
    }

    public void setPosts(List<StudentPost> posts) {
        this.posts = posts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
