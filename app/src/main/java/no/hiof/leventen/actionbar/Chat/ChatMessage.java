package no.hiof.leventen.actionbar.Chat;

import java.util.Date;

public class ChatMessage {
    private String messageText;
    private String messageUser;
    private long messageDate;

    public ChatMessage(String messageText, String messageUser, long messageTime) {
        this.messageText = messageText;
        this.messageUser = messageUser;
        messageTime = new Date().getTime();
    }

    public ChatMessage() {

    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(long messageTime) {
        this.messageDate = messageTime;
    }
}
