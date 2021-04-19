package com.nakaradasava.learntogether.entity.studyfield;

import com.nakaradasava.learntogether.entity.dry.ForeignStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "question_study")
public class QuestionStudy extends ForeignStudent {

    @NotEmpty(message = "title is required")
    @Column(name = "title")
    @Size(max = 45, message = "max size is 45")
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
