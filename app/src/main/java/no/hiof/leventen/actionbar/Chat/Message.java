package no.hiof.leventen.actionbar.Chat;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Message implements Parcelable {
    private int id;
    private String messageText;
    private String fromUser;
    private String toUser;
    private Date date;

    public Message(String messageText, String fromUser, Date date) {
        this.messageText = messageText;
        this.fromUser = fromUser;
        this.date = date;
    }

    protected Message(Parcel in) {
        messageText = in.readString();
        fromUser = in.readString();
        this.date = date;
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getMessageText() { return messageText; }

    public void setMessageText(String messageText) { this.messageText = messageText; }

    public String getFromUser() { return fromUser; }

    public void setFromUser(String fromUser) { this.fromUser = fromUser; }

    public String getToUser() { return toUser; }

    public void setToUser(String toUser) { this.toUser = toUser; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(messageText);
        parcel.writeString(fromUser);
        parcel.writeString(toUser);
    }
}
