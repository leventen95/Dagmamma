package no.hiof.leventen.actionbar.Chat;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Conversation implements Parcelable {
    private String id, otherUser;
    private ArrayList<Message> conversation;

    public Conversation(String id, String otherUser) {
        this.id = id;
        this.otherUser = otherUser;
        this.conversation = new ArrayList<Message>();
    }

    public Conversation(Parcel in) {
      //  this();
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        id = in.readString();
        otherUser = in.readString();

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(otherUser);
    }
}
