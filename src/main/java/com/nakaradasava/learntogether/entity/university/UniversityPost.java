package com.nakaradasava.learntogether.entity.university;

import com.nakaradasava.learntogether.entity.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post_university")
public class UniversityPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    private University university;

    @OneToMany(mappedBy = "universityPost", cascade = CascadeType.ALL)
    private List<UniversityPostLike> likes;

    @Column(name = "edited")
    private boolean edited;

    @OneToMany(mappedBy = "universityPost", cascade = CascadeType.ALL)
    private List<UniversityPostComment> comments;
}
