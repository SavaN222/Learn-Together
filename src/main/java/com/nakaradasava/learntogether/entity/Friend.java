package com.nakaradasava.learntogether.entity;

import com.nakaradasava.learntogether.entity.dry.IdColumn;
import com.nakaradasava.learntogether.entity.student.Student;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "friends")
public class Friend extends IdColumn {

    @ManyToOne
    private Student studentLower;

    @ManyToOne
    private Student studentHigher;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Student actionUser;
}