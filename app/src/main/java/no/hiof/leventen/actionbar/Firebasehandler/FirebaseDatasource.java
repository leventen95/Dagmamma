package no.hiof.leventen.actionbar.Firebasehandler;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import no.hiof.leventen.actionbar.Person;

/**
 * Created by Marcus on 06.11.2018.
 */




public class FirebaseDatasource {

    private DatabaseReference dbRef;

    public void getUser(){

        dbRef.getDatabase().getReference();

    }

    public void changeUserDetails(){
        dbRef.getDatabase().getReference();


    }

    public void getChatMessages(){
        dbRef.getDatabase().getReference();
    }

    public void getChatUsers(){
        dbRef.getDatabase().getReference();
    }

    public void createUser(Person person){

        dbRef = FirebaseDatabase.getInstance().getReference();
        Task<Void> nameRef = dbRef.child("users").child(person.getEmail()).child("username").setValue(person.getName());
        Task<Void> ageRef = dbRef.child("users").child(person.getEmail()).child("age").setValue(person.getAge());
        Task<Void> idRef = dbRef.child("users").child(person.getEmail()).child("id").setValue(person.getId());
        Task<Void> tlfRef = dbRef.child("users").child(person.getEmail()).child("tlfnr").setValue(person.getTlfNr());
    }

    public void loginUser(String email, String passord){
        dbRef = FirebaseDatabase.getInstance().getReference();
        final Person person;
        dbRef.child("users").child(email)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

}
