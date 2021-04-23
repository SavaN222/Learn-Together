package com.nakaradasava.learntogether.entity.dry;

import com.nakaradasava.learntogether.entity.CommentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Comment extends ForeignStudent {

    @NotEmpty(message = "content is required")
    @Size(max = 255, message = "max size is 255")
    @Column(name = "content")
    private String content;

    @Column(name = "edited")
    private boolean edited;

    @Enumerated(EnumType.STRING)
    private CommentStatus status;
}
