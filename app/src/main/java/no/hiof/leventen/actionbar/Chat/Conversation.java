package no.hiof.leventen.actionbar.Chat;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conversation implements Parcelable {
    private int id;
    private String otherUserEmail;
    private String otherUserName;
    private List<Message> conversationList;

    public Conversation(int id, String otherUserEmail, String otherUserName) {
        this.id = id;
        this.otherUserEmail = otherUserEmail;
        this.otherUserName = otherUserName;
        this.conversationList = new ArrayList<Message>();
    }

    public Conversation(Parcel in) {
        id = in.readInt();
        otherUserEmail = in.readString();
        otherUserName = in.readString();
    }

    public static final Creator<Conversation> CREATOR = new Creator<Conversation>() {
        @Override
        public Conversation createFromParcel(Parcel in) {
            return new Conversation(in);
        }

        @Override
        public Conversation[] newArray(int size) {
            return new Conversation[size];
        }
    };

    public void addMessage(Message message) {
        conversationList.add(0, message);
    }

    public Message getMessage(int i) {
        return conversationList.get(i);
    }

    public Message getLastMessage() {
        return conversationList.get(0);
    }

    public void removeMessage(Message message) {
        for (int i = 0; i < conversationList.size(); i++) {
            if (conversationList.get(i) == message)
                conversationList.remove(i);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOtherUserEmail() { return otherUserEmail; }

    public void setOtherUserEmail(String otherUserEmail) { this.otherUserEmail = otherUserEmail; }

    public String getOtherUserName() { return otherUserName; }

    public void setOtherUserName(String otherUserName) { this.otherUserName = otherUserName; }

    public List<Message> getConversationMessages() {
        return conversationList;
    }

    public void setConversationMessages(ArrayList<Message> conversationList) { this.conversationList = conversationList; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(otherUserEmail);
        parcel.writeString(otherUserName);
    }
}
