package com.senlainc.comment;

import com.senlainc.transfer.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getComments(long announcementId);

    CommentDTO addComment(CommentDTO comment, long announcementId);
}
