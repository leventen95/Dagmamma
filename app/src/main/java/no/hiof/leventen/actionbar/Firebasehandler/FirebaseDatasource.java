package no.hiof.leventen.actionbar.Firebasehandler;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import no.hiof.leventen.actionbar.Chat.Message;
import no.hiof.leventen.actionbar.Chat.Conversation;
import no.hiof.leventen.actionbar.Classes.UserType;
import no.hiof.leventen.actionbar.Person;

public class FirebaseDatasource {

    private DatabaseReference dbRef;

    public FirebaseDatasource() {
        this.dbRef = FirebaseDatabase.getInstance().getReference();;
    }

    public void getUser(String uid, final boolean isThisUser){
        DatabaseReference selfRef = dbRef.child("users").child("dagmamma").child(uid);

        selfRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //    public Person(String name, String email, String firebaseUid, String profilBeskrivelse, String userType, String by, String fDato, boolean isThisUser) {

                UserType type = UserType.DAGMAMMA;
                Person person = new Person(dataSnapshot.child("name").getValue().toString(),
                        dataSnapshot.child("email").getValue().toString(),
                        dataSnapshot.getKey(),
                        dataSnapshot.child("beskrivelse").getValue().toString(),
                        type.toString(),
                        dataSnapshot.child("by").getValue().toString(),
                        dataSnapshot.child("fdato").getValue().toString(),
                        isThisUser);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void changeUserDetails(Person user, Person newUserData, DidCompleteCallback callback){

        Task<Void> nameRef = dbRef.child("users").child(newUserData.getEmail()).child("username").setValue(newUserData.getName());
        //Task<Void> ageRef = dbRef.child("users").child(newUserData.getEmail()).child("age").setValue(newUserData.getAge());
        //Task<Void> tlfRef = dbRef.child("users").child(newUserData.getEmail()).child("tlfnr").setValue(newUserData.getTlfNr());
        user = newUserData;

        callback.didComplete(true);
    }

    public void getAllUsers(UserType searchParam, final DidGetUsersCallBack callBack){
        final ArrayList<Person> users = new ArrayList<>();

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
                    users.add(person);
                }
                callBack.didRecieve(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getChatMessages(String chatId, final ChatCallback callback){
        DatabaseReference chatRef = dbRef.child("chat").child(chatId);
        DatabaseReference messages = chatRef.child("messages");

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                //Message newMessage = new Message();


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


    public void createConversation(String uid){

        DatabaseReference chatRef = dbRef.child("Chat").push();
        String key = chatRef.getKey();

        DatabaseReference convRef = dbRef.child("Chat").child(key);

        convRef.child("uid1").setValue(uid);
        convRef.child("uid2").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());

        saveConversationForUsers(uid,key);

    }

    public void sendMessage(String convId, String message, String date){

        DatabaseReference convRef = dbRef.child("Chat").child(convId);
        DatabaseReference messagesRef = convRef.child("messages");

        DatabaseReference curMessage = messagesRef.push();
        curMessage.child("message").setValue(message);
        curMessage.child("fromName").setValue(Person.getCurrentUser().getName());
        curMessage.child("fromUid").setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());
        curMessage.child("date").setValue(date);

    }

    public void saveConversationForUsers(String uid, String convId){

        DatabaseReference usersRef = dbRef.child("users").child("dagmamma").child(uid);
        DatabaseReference selfRef = dbRef.child("users").child("dagmamma").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        usersRef.child("conversations").push().setValue(convId);
        selfRef.child("conversations").push().setValue(convId);


    }

    public void uploadImage(String uid, Uri imageUri){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        storageRef.child("profileImages");

        storageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        });
    }

    public void getImage(String uid){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("profileImages").child(uid);
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {

            }
        });
    }

    public void getMessagesStream(String convId){

        DatabaseReference convRef = dbRef.child("Chat").child(convId);

        convRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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

    }

/*  HAR ENDRET int getChatConversationIds() til List<Conversation> getConversations slik at jeg kan sende inn en
    conversationId fra DialogFragment til ChatFragment i stedet for hele Conversation-objektet.
    public void getConversations(Person thisUser, ConversationCallback callback){
        DatabaseReference chatRef = dbRef.child("chat");
        final List<Conversation> convList = new ArrayList<>();

        if(thisUser.getChatConversations() == null || thisUser.getChatConversations().size() == 0){
            return;
        }

        for(String conversationId : thisUser.getChatConversations()){

            DatabaseReference conversation = chatRef.child(conversationId);

            conversation.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Conversation conv;

                        if(dataSnapshot.child("uid1").getValue().toString() == FirebaseAuth.getInstance().getCurrentUser().getUid()) {
                            conv = new Conversation(Integer.parseInt(dataSnapshot.getKey()), dataSnapshot.child("uid2").getValue().toString());
                            convList.add(conv);
                        }

                        if(dataSnapshot.child("uid2").getValue().toString() == FirebaseAuth.getInstance().getCurrentUser().getUid()) {
                            conv = new Conversation(Integer.parseInt(dataSnapshot.getKey()), dataSnapshot.child("uid1").getValue().toString());
                            convList.add(conv);
                        }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            callback.didRecieve(convList);
        }

    }*/

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
