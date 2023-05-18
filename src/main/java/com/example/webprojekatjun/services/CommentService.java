package com.example.webprojekatjun.services;

import com.example.webprojekatjun.entities.Comment;
import com.example.webprojekatjun.repositories.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {
    @Inject
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment) {
        return commentRepository.addComment(comment);
    }

    public List<Comment> allComments() {
        return commentRepository.allComments();
    }

    public List<Comment> allCommentsForNews(Integer vest_id) {
        return commentRepository.allCommentsForNews(vest_id);
    }

    public Comment findComment(Integer id) {
        return commentRepository.findComment(id);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteComment(id);
    }
}
