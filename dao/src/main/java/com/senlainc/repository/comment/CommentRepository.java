package com.senlainc.repository.comment;

import com.senlainc.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> addComment(Comment comment);

    Optional<List<Comment>> getComments(long announcementId);
}
