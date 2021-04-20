package com.nakaradasava.learntogether.entity.university;

import com.nakaradasava.learntogether.entity.dry.IdColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "city")
public class UniversityCity extends IdColumn {

    @Column(name = "name")
    private String name;
}