package no.hiof.leventen.actionbar.Firebasehandler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

    public void getUser(){

        dbRef = FirebaseDatabase.getInstance().getReference();

    }

    public void changeUserDetails(Person user, Person newUserData, DidCompleteCallback callback){
        dbRef = FirebaseDatabase.getInstance().getReference();

        Task<Void> nameRef = dbRef.child("users").child(newUserData.getEmail()).child("username").setValue(newUserData.getName());
        //Task<Void> ageRef = dbRef.child("users").child(newUserData.getEmail()).child("age").setValue(newUserData.getAge());
        //Task<Void> tlfRef = dbRef.child("users").child(newUserData.getEmail()).child("tlfnr").setValue(newUserData.getTlfNr());




        user = newUserData;

        callback.didComplete(true);

    }

    public void getChatMessages(String chatId, final ChatCallback callback){
        dbRef = FirebaseDatabase.getInstance().getReference();
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
        dbRef = FirebaseDatabase.getInstance().getReference();
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

    public void getAllUsers(){

    }

    public void addExtraInfoForUser(Person person, String userUid, DidCreateUserCallback callback){

        dbRef = FirebaseDatabase.getInstance().getReference().child("users").child(userUid);
        Task<Void> nameRef = dbRef.child("name").setValue(person.getName());
        Task<Void> beskrivelse = dbRef.child("beskrivelse").setValue(person.getProfilBeskrivelse());
        Task<Void> by = dbRef.child("by").setValue(person.getBy());
        Task<Void> fdato = dbRef.child("fdato").setValue(person.getfDato());

        callback.didCreateUser(true);
    }



    public void loginUser(String email, final String password, final LoginCallBack loginCallBack){
        dbRef = FirebaseDatabase.getInstance().getReference();

        dbRef.child("users").child(email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String passFromDB = dataSnapshot.child("password").getValue().toString();

                    if(password.equalsIgnoreCase(passFromDB)){
                        String userName = dataSnapshot.child("username").getValue().toString();
                        int age =  Integer.valueOf(dataSnapshot.child("age").getValue().toString());
                        String tlfNr = dataSnapshot.child("tlfnr").getValue().toString();
                        String email = dataSnapshot.getKey();

                        loginCallBack.onLoginBack(null);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    loginCallBack.onLoginBack(null);
                        System.out.println(databaseError.getMessage());
                    }
                });
    }




}
