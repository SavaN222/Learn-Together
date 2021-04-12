package com.nakaradasava.learntogether.entity.studyfield;

import com.nakaradasava.learntogether.entity.student.Student;
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
@Table(name = "question_study")
public class QuestionStudy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "title is required")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "description is required")
    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "study_field_id", referencedColumnName = "id")
    private StudyField studyField;

    @OneToMany(mappedBy = "questionStudy", cascade = CascadeType.ALL)
    private List<CommentStudy> comments;

    @OneToMany(mappedBy = "questionStudy", cascade = CascadeType.ALL)
    private List<LikeStudy> likes;

    @Column(name = "edited")
    private boolean edited;
}
