package com.nakaradasava.learntogether.entity.studyfield;

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
@Table(name = "comment_study")
public class QuestionComment extends ForeignStudent {

    @NotEmpty(message = "content is required")
    @Column(name = "content")
    private String content;

    @ManyToOne()
    @JoinColumn(name = "question_study_id", referencedColumnName = "id")
    private QuestionStudy questionStudy;

    @Column(name = "edited")
    private boolean edited;
}
