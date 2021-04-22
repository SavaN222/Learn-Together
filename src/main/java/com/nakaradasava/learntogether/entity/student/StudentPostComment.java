package com.nakaradasava.learntogether.entity.student;

import com.nakaradasava.learntogether.entity.dry.ForeignStudent;
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
@Table(name = "student_post_comment")
public class StudentPostComment extends ForeignStudent {

    @NotEmpty(message = "content is required")
    @Column(name = "content")
    private String content;

    @ManyToOne()
    @JoinColumn(name = "student_post_id", referencedColumnName = "id")
    private StudentPost studentPost;

    @Column(name = "edited")
    private boolean edited;
}