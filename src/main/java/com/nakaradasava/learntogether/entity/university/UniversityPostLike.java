package com.nakaradasava.learntogether.entity.university;

import com.nakaradasava.learntogether.entity.dry.ForeignStudent;
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
@Table(name = "like_post")
public class UniversityPostLike extends ForeignStudent {

    @ManyToOne()
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private UniversityPost universityPost;
}
