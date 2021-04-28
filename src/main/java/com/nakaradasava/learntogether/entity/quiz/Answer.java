package com.nakaradasava.learntogether.entity.quiz;

import com.nakaradasava.learntogether.entity.dry.IdColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer extends IdColumn {

    @Column(name = "text")
    @NotEmpty(message = "cannot be empty")
    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @Column(name = "value")
    private Boolean value;

    public Answer(String text) {
        this.text = text;
    }
}