package com.nakaradasava.learntogether.entity.university;

import com.nakaradasava.learntogether.entity.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comment_post")
public class UniversityPostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "content is required")
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private UniversityPost universityPost;

    @Column(name = "edited")
    private boolean edited;
}
