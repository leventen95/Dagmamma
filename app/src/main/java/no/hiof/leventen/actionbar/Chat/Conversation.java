package no.hiof.leventen.actionbar.Chat;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Conversation {
    RecyclerView recyclerView;
    private ArrayList<Message> dialogList;
    String to;
    private String conversationId;

    public Conversation() {
        dialogList = new ArrayList<Message>();
    }
    public void addMessage(Message message) {
        dialogList.add(message);
    }

    public void removeMessage(Message message) {
        for (int i = 0; i < dialogList.size(); i++) {
            if (dialogList.get(i) == message)
                dialogList.remove(i);
        }
    }

    public ArrayList<Message> getDialogList() {
        return dialogList;
    }

    public void setDialogList(ArrayList<Message> dialogList) {
        this.dialogList = dialogList;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
