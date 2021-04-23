package com.nakaradasava.learntogether.entity.student;

import com.nakaradasava.learntogether.entity.dry.Comment;
import com.nakaradasava.learntogether.entity.dry.ForeignStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student_post_comment")
public class StudentPostComment extends Comment {

    @ManyToOne()
    @JoinColumn(name = "student_post_id", referencedColumnName = "id")
    private StudentPost studentPost;

}