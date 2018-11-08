package no.hiof.leventen.actionbar;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Conversation {
    RecyclerView recyclerView;
    private ArrayList<ChatMessage> dialogList;
    String to;
    private String conversationId;

    public Conversation() {
        dialogList = new ArrayList<ChatMessage>();
    }
    public void addMessage(ChatMessage message) {
        dialogList.add(message);
    }

    public void removeMessage(ChatMessage message) {
        for (int i = 0; i < dialogList.size(); i++) {
            if (dialogList.get(i) == message)
                dialogList.remove(i);
        }
    }

    public ArrayList<ChatMessage> getDialogList() {
        return dialogList;
    }

    public void setDialogList(ArrayList<ChatMessage> dialogList) {
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
