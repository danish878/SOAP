package org.danny.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.danny.messenger.database.Database;
import org.danny.messenger.model.Message;

public class MessageService {

    private Map<Long, Message> messages = Database.getMessages();

    public MessageService() {
        messages.put(1L, new Message(1L, "Hello World", "Danny"));
        messages.put(2L, new Message(2L, "Hello Danny", "Danny"));
    }

    public List<Message> getAllMessages() {
        return new ArrayList<Message>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year) {
        // add implementation
        return null;
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        // add implementation
        return null;
    }

    public Message getMessage(long id) {
        return messages.get(id);
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        return messages.remove(id);
    }

}
