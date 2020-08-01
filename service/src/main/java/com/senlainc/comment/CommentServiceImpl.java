package com.senlainc.comment;

import com.senlainc.entity.Announcement;
import com.senlainc.entity.Comment;
import com.senlainc.repository.announcement.AnnouncementRepository;
import com.senlainc.repository.comment.CommentRepository;
import com.senlainc.exceptions.RecordNotFoundException;
import com.senlainc.transfer.dto.CommentDTO;
import com.senlainc.transfer.mapper.CommentMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = Logger.getLogger(CommentServiceImpl.class);

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<CommentDTO> getComments(long announcementId) {
        List<CommentDTO> commentDTOS = commentMapper.toDTO(commentRepository.getComments(announcementId).orElseThrow(() ->
                new RecordNotFoundException("Comments don't exist for announcement id: " + announcementId)));
        logger.debug("Comments for announcement id: " + announcementId + " >>> " + commentDTOS);
        return commentDTOS;
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO, long annoumcementId) {
        Announcement announcement = announcementRepository.getAnnouncement(annoumcementId).orElseThrow(() ->
                new RecordNotFoundException("Announcement with id:" + annoumcementId + " doesn't exist"));
        commentDTO.setAnnouncementId(annoumcementId);
        commentDTO.setUserId((announcement.getUser().getId()));
        Comment comment = commentMapper.toEntity(commentDTO);
        Calendar calendar = Calendar.getInstance();
        comment.setPublicationDate(calendar.getTime());

        Comment newComment = commentRepository.addComment(comment).get();

        logger.debug("Add new comment: " + newComment);

        return commentMapper.toDTO(newComment);
    }

}
