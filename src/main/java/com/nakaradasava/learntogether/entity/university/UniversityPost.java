package com.nakaradasava.learntogether.entity.university;

import com.nakaradasava.learntogether.entity.dry.ForeignStudent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post_university")
public class UniversityPost extends ForeignStudent {

    @NotEmpty(message = "description is required")
    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "university_id", referencedColumnName = "id")
    private University university;

    @OneToMany(mappedBy = "universityPost", cascade = CascadeType.ALL)
    private Set<UniversityPostLike> likes;

    @Column(name = "edited")
    private boolean edited;

    @OneToMany(mappedBy = "universityPost", cascade = CascadeType.ALL)
    private List<UniversityPostComment> comments;
}