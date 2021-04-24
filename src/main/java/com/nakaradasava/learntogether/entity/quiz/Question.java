package com.nakaradasava.learntogether.entity.quiz;

import com.nakaradasava.learntogether.entity.dry.IdColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quiz_question")
public class Question extends IdColumn {

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
}
