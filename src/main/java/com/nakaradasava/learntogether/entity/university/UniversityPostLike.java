package com.nakaradasava.learntogether.entity.university;

import com.nakaradasava.learntogether.entity.student.ForeignStudent;
import com.nakaradasava.learntogether.entity.student.Student;
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
@Table(name = "like_post")
public class UniversityPostLike extends ForeignStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private UniversityPost universityPost;
}
