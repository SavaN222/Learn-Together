package com.nakaradasava.learntogether.entity.studyfield;

import com.nakaradasava.learntogether.entity.student.ForeignStudent;
import com.nakaradasava.learntogether.entity.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "question_study")
public class QuestionStudy extends ForeignStudent {

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

    @ManyToOne
    @JoinColumn(name = "study_field_id", referencedColumnName = "id")
    private StudyField studyField;

    @OneToMany(mappedBy = "questionStudy", cascade = CascadeType.ALL)
    private List<QuestionComment> comments;

    @OneToMany(mappedBy = "questionStudy", cascade = CascadeType.ALL)
    private List<QuestionLike> likes;

    @Column(name = "edited")
    private boolean edited;
}
