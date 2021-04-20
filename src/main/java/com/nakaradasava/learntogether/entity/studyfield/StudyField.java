package com.nakaradasava.learntogether.entity.studyfield;

import com.nakaradasava.learntogether.entity.dry.IdColumn;
import com.nakaradasava.learntogether.entity.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "study_field")
public class StudyField extends IdColumn {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "studyField")
    private Set<Student> students;

    @OneToMany(mappedBy = "studyField")
    private List<QuestionStudy> questions;

}