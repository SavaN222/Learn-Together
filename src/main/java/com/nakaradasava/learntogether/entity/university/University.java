package com.nakaradasava.learntogether.entity.university;

import com.nakaradasava.learntogether.entity.dry.IdColumn;
import com.nakaradasava.learntogether.entity.student.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "university")
public class University extends IdColumn {

    @Column(name = "full_name")
    @OrderBy
    private String fullName;

    @Column(name = "short_name")
    private String shortName;

    @OneToMany(mappedBy = "university")
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private UniversityCity universityCity;

    @Column(name = "url")
    private String url;

    @OneToMany(mappedBy = "university")
    @OrderBy("id DESC")
    private List<UniversityPost> universityPosts;
}
