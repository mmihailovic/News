package com.example.webprojekatjun.repositories;


import com.example.webprojekatjun.entities.Comment;

import java.util.List;

public interface CommentRepository {
    Comment addComment(Comment comment);
    List<Comment> allComments();
    List<Comment> allCommentsForNews(Integer vest_id);
    Comment findComment(Integer id);
    void deleteComment(Integer id);
}
