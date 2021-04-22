package com.nakaradasava.learntogether.entity.student;

import com.nakaradasava.learntogether.entity.dry.ForeignStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student_like_post")
public class StudentPostLike extends ForeignStudent {

    @ManyToOne()
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private StudentPost studentPost;
}