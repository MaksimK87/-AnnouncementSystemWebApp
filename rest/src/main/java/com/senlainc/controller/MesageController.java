package com.senlainc.controller;

import com.senlainc.message.MessageService;
import com.senlainc.transfer.dto.MessageDTO;
import com.senlainc.transfer.dto.UserDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class MesageController {

    private static final Logger logger = Logger.getLogger(MesageController.class);

    @Autowired
    MessageService messageService;

    @GetMapping("messages/{userIdTo}/from")
    ResponseEntity<List<UserDTO>> getIncomingUserList(@PathVariable long userIdTo) {
        logger.info("Get all users with messages to user id: " + userIdTo);

        return ResponseEntity.ok(messageService.getIncomingUserList(userIdTo));
    }

    @GetMapping("messages/{userIdTo}/{idFrom}")
    ResponseEntity<List<MessageDTO>> getMessages(@PathVariable long userIdTo, @PathVariable long idFrom) {
        logger.info("Get all messages from user id: " + idFrom + " to user id " + userIdTo);
        messageService.getAllMessages(userIdTo, idFrom);
        return ResponseEntity.ok(messageService.getAllMessages(userIdTo, idFrom));
    }

    @PostMapping("messages/{userIdTo}/{idFrom}")
    ResponseEntity<MessageDTO> sendMessage(@PathVariable long userIdTo, @PathVariable Long idFrom,
                                           @Valid @RequestBody MessageDTO messageDTO) {

        messageDTO.setUserIdFrom(idFrom);
        messageDTO.setUserIdTo(userIdTo);
        logger.info("Send message: " + messageDTO);
        messageDTO = messageService.getMessage(messageService.addMessage(messageDTO));


        return ResponseEntity.ok(messageDTO);
    }

    @DeleteMapping("messages/{id}")
    ResponseEntity deleteMessage(@PathVariable long id) {
        logger.info("Delete message with id: " + id);
        messageService.deleteMessage(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
