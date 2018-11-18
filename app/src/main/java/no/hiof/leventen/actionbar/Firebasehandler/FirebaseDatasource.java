package no.hiof.leventen.actionbar.Firebasehandler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import no.hiof.leventen.actionbar.Chat.Message;
import no.hiof.leventen.actionbar.Chat.Conversation;
import no.hiof.leventen.actionbar.Classes.UserType;
import no.hiof.leventen.actionbar.Person;

interface DidCompleteCallback{
    void didComplete(boolean didComplete);
}
interface ConversationCallback{
    void didRecieve(List<Conversation> conversations);
}

interface ChatCallback{
    void didRecieve(List<Message> messages);
    void onUpdate(Message newMessage);
}

public class FirebaseDatasource {

    private DatabaseReference dbRef;

    public FirebaseDatasource() {
        this.dbRef = FirebaseDatabase.getInstance().getReference();;
    }

    public void getUser(){

    }

    public void changeUserDetails(Person user, Person newUserData, DidCompleteCallback callback){

        Task<Void> nameRef = dbRef.child("users").child(newUserData.getEmail()).child("username").setValue(newUserData.getName());
        //Task<Void> ageRef = dbRef.child("users").child(newUserData.getEmail()).child("age").setValue(newUserData.getAge());
        //Task<Void> tlfRef = dbRef.child("users").child(newUserData.getEmail()).child("tlfnr").setValue(newUserData.getTlfNr());
        user = newUserData;

        callback.didComplete(true);
    }

    public List<Person> getAllUsers(UserType searchParam){
        ArrayList<Person> users = new ArrayList<>();

        DatabaseReference usersRef = dbRef.child("users").child(searchParam.toString().toLowerCase());

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Person person = new Person(
                            snapshot.child("name").getValue().toString(),
                            snapshot.child("email").getValue().toString(),
                            snapshot.getKey(),
                            snapshot.child("beskrivelse").getValue().toString(),
                            "",
                            snapshot.child("by").getValue().toString(),
                            snapshot.child("fdato").getValue().toString(),
                            false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return users;
    }

    public void getChatMessages(String chatId, final ChatCallback callback){
        DatabaseReference chatRef = dbRef.child("chat").child(chatId);
        DatabaseReference messages = chatRef.child("messages");

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Message newMessage = new Message();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //Hent user 1 og 2 fra chatref
        //hent alle meldinger

    }

    public void getConversations(final Person thisUser, final ConversationCallback callback){
        DatabaseReference chatRef = dbRef.child("chat");
        final List<Conversation> convList = new ArrayList<>();

        for(final String conversationId: thisUser.getChatConversations()){
            final DatabaseReference conversation = chatRef.child(conversationId);

            conversation.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Conversation conversationItem = new Conversation();
                    conversationItem.setConversationId(conversationId);
                    conversationItem.setTo(dataSnapshot.child("user1").getValue().toString());

                    convList.add(conversationItem);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            callback.didRecieve(convList);
        }

    }

    public void addExtraInfoForUser(Person person, String userUid,String userType, DidCreateUserCallback callback){

        dbRef = FirebaseDatabase.getInstance().getReference().child("users").child(userType).child(userUid);
        Task<Void> nameRef = dbRef.child("name").setValue(person.getName());
        Task<Void> beskrivelse = dbRef.child("beskrivelse").setValue(person.getProfilBeskrivelse());
        Task<Void> by = dbRef.child("by").setValue(person.getBy());
        Task<Void> fdato = dbRef.child("fdato").setValue(person.getfDato());
        Task<Void> email = dbRef.child("email").setValue(person.getEmail());


        callback.didCreateUser(true);
    }



}
