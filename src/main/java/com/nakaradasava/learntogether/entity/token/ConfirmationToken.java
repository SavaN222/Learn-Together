package com.nakaradasava.learntogether.entity.token;

import com.nakaradasava.learntogether.entity.dry.IdColumn;
import com.nakaradasava.learntogether.entity.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "token")
public class ConfirmationToken extends IdColumn {

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "created_At")
    private LocalDate createdAt;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public ConfirmationToken(Student student) {
        this.createdAt = LocalDate.now();
        this.student = student;
        this.confirmationToken = UUID.randomUUID().toString();
    }
}