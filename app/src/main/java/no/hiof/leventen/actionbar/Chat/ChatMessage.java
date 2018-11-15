package no.hiof.leventen.actionbar.Chat;

import java.util.Date;

import no.hiof.leventen.actionbar.Person;

public class ChatMessage {
    private String messageText;
    private String otherUserEmail;
    private Date date;

    public ChatMessage(String messageText, String otherUserEmail, Date date) {
        this.messageText = messageText;
        this.otherUserEmail = otherUserEmail;
        this.date = date;
    }

    public ChatMessage() {

    }

    public String getMessageText() { return messageText; }

    public void setMessageText(String message) { this.messageText = message; }

    public String getOtherUserEmail() {
        return otherUserEmail;
    }

    public void setUser(String otherUserEmail) {
        this.otherUserEmail = otherUserEmail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
