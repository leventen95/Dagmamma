package no.hiof.leventen.actionbar.Chat;

import java.util.Date;

public class Message {
    private String messageText;
    private String otherUserEmail;
    private Date date;

    public Message(String messageText, String otherUserEmail, Date date) {
        this.messageText = messageText;
        this.otherUserEmail = otherUserEmail;
        this.date = date;
    }

    public Message() {}

    public String getMessageText() { return messageText; }

    public void setMessageText(String messageText) { this.messageText = messageText; }

    public String getOtherUserEmail() { return otherUserEmail; }

    public void setOtherUserEmail(String otherUserEmail) { this.otherUserEmail = otherUserEmail; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }
}
