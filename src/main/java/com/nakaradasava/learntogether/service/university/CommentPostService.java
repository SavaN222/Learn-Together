package com.nakaradasava.learntogether.service.university;

import com.nakaradasava.learntogether.entity.studyfield.CommentStudy;
import com.nakaradasava.learntogether.entity.university.CommentPost;
import com.nakaradasava.learntogether.repository.studyfield.CommentStudyRepository;
import com.nakaradasava.learntogether.repository.university.CommentPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentPostService {

    private CommentPostRepository commentPostRepository;

    @Autowired
    public CommentPostService(CommentPostRepository commentPostRepository) {
        this.commentPostRepository = commentPostRepository;
    }

    public void saveComment(CommentPost commentPost) {
        commentPostRepository.save(commentPost);
    }

    public void deleteCommentById(int id) {
        commentPostRepository.deleteById(id);
    }

    public CommentPost findCommentById(int id) {
      return commentPostRepository.findById(id).get();
    }
}
