package no.hiof.leventen.actionbar.Firebasehandler;

import java.util.List;

import no.hiof.leventen.actionbar.Chat.Conversation;

public interface ConversationCallback{
    void didRecieve(List<Conversation> conversations);
}
