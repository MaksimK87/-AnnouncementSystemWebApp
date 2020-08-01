package com.senlainc.controller;

import com.senlainc.comment.CommentService;
import com.senlainc.transfer.dto.CommentDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class CommentController {

    private static final Logger logger = Logger.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @GetMapping("/announcements/{id}/comments")
    ResponseEntity<List<CommentDTO>> getComments(@PathVariable long id) {
        logger.info("Get comments for announcement id: " + id);
        return ResponseEntity.ok(commentService.getComments(id));
    }

    @PostMapping("/announcements/{id}/comments")
    ResponseEntity<CommentDTO> addComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable long id) {

        return ResponseEntity.ok(commentService.addComment(commentDTO, id));
    }
}
