package no.hiof.leventen.actionbar.Firebasehandler;

import java.util.List;

import no.hiof.leventen.actionbar.Chat.Message;

public interface ChatCallback{
    void didRecieve(List<Message> messages);
    void onUpdate(Message newMessage);
}
