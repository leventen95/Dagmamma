package no.hiof.leventen.actionbar;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Dialog {
    RecyclerView recyclerView;
    private ArrayList<ChatMessage> dialogList;

    public Dialog() {
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
}
