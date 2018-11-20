package no.hiof.leventen.actionbar.Chat;

import java.util.ArrayList;
import java.util.Date;

public class Conversation {
    private String id, otherUser;
    private ArrayList<Message> conversation;

    public Conversation(String id, String otherUser) {
        this.id = id;
        this.otherUser = otherUser;
        this.conversation = new ArrayList<Message>();
    }

    public void addMessage(Message message) {
        conversation.add(message);
    }

    public Message getMessage(int i) {
        return conversation.get(i);
    }

    public Message getLastMessage() {
        // TODO - Mekke ordentlig Dato-referanse som kan sammenliknes, eller bare bruke string og lete i den...
        //Date date = new Date();
        Message msg = null;
        for (int i = 0; i < conversation.size(); i++) {
            // TODO - Hvis datoen i dette objektet er nyere enn date; overskriv tmpMsg.getDate()!
            if(i == conversation.size() -1) {
                msg = conversation.get(i);
            }
        }
        return msg;

    }

    public void removeMessage(Message message) {
        for (int i = 0; i < conversation.size(); i++) {
            if (conversation.get(i) == message)
                conversation.remove(i);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOtherUser() { return otherUser; }

    public void setOtherUser(String otherUser) { this.otherUser = otherUser; }

    public ArrayList<Message> getConversationMessages() {
        return conversation;
    }

    public void setConversationMessages(ArrayList<Message> conversation) { this.conversation = conversation; }
}
