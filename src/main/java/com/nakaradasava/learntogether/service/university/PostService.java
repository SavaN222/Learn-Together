package com.nakaradasava.learntogether.service.university;

import com.nakaradasava.learntogether.entity.university.Post;
import com.nakaradasava.learntogether.repository.university.PostRepository;
import com.nakaradasava.learntogether.repository.university.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }
}
