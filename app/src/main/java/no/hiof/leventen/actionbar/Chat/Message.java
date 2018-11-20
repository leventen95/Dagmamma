package no.hiof.leventen.actionbar.Chat;

import java.util.Date;

public class Message {
    private String id;
    private String messageText;
    private String fromUser;
    private String toUser;
    private Date date;

    public Message(String id, String messageText, String fromUser, String toUser, Date date) {
        this.id = id;
        this.messageText = messageText;
        this.fromUser = fromUser;
        this.date = date;
    }
    public Message(String messageText, String fromUser, String toUser, Date date) {
        this.messageText = messageText;
        this.fromUser = fromUser;
        this.date = date;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getMessageText() { return messageText; }

    public void setMessageText(String messageText) { this.messageText = messageText; }

    public String getFromUser() { return fromUser; }

    public void setFromUser(String fromUser) { this.fromUser = fromUser; }

    public String getToUser() { return toUser; }

    public void setToUser(String toUser) { this.toUser = toUser; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }
}
