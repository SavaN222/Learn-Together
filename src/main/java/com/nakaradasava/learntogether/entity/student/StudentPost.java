package com.nakaradasava.learntogether.entity.student;

import com.nakaradasava.learntogether.entity.dry.ForeignStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student_post")
public class StudentPost extends ForeignStudent {

    @NotEmpty(message = "description is required")
    @Column(name = "description")
    private String description;

    @Column(name = "edited")
    private boolean edited;

    //    @OneToMany(mappedBy = "universityPost", cascade = CascadeType.ALL)
//    private List<UniversityPostLike> likes;


//    @OneToMany(mappedBy = "universityPost", cascade = CascadeType.ALL)
//    private List<UniversityPostComment> comments;
}
