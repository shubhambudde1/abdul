package com.example.message.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    // Create a new message
    @PostMapping
    public String sendMessage(@RequestBody Message message) {
        messageRepository.save(message);
        return "Message sent: " + message.getContent();
    }

    // Read all messages
    @GetMapping
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    // Read a message by ID
    @GetMapping("/{id}")
    public Optional<Message> getMessageById(@PathVariable Long id) {
        return messageRepository.findById(id);
    }

    // Update a message by ID
    @PutMapping("/{id}")
    public String updateMessage(@PathVariable Long id, @RequestBody Message updatedMessage) {
        Optional<Message> existingMessage = messageRepository.findById(id);

        if (existingMessage.isPresent()) {
            Message message = existingMessage.get();
            message.setContent(updatedMessage.getContent());
            messageRepository.save(message);
            return "Message updated: " + message.getContent();
        } else {
            return "Message with ID " + id + " not found!";
        }
    }

    // Delete a message by ID
    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable Long id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
            return "Message with ID " + id + " deleted!";
        } else {
            return "Message with ID " + id + " not found!";
        }
    }
}
