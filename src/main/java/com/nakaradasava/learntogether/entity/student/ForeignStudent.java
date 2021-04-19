package com.nakaradasava.learntogether.entity.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Reduce code duplicate for foreign key student in tables which depend on student (like, posts)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class ForeignStudent {
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;
}
