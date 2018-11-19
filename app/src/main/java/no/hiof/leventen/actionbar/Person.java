package no.hiof.leventen.actionbar;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Person {
    String name;
    int photoId;
    private String email;
    private List<String> chatConversations;
    private static Person currentUser;
    private String firebaseUid;
    private String profilBeskrivelse;
    private String userType;
    private String by;
    private String fDato;

    public Person() {
    }

    public Person(String name, String email, String firebaseUid, String profilBeskrivelse, String userType, String by, String fDato, boolean isThisUser) {
        this.name = name;
        this.email = email;
        this.firebaseUid = firebaseUid;
        this.profilBeskrivelse = profilBeskrivelse;
        this.userType = userType;
        this.by = by;
        this.fDato = fDato;
        this.chatConversations = new ArrayList<String>();
        if(isThisUser) {
            this.currentUser = this;
            this.currentUser.setFirebaseUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
            this.currentUser.setProfilBeskrivelse(profilBeskrivelse);
        }else{
            this.setProfilBeskrivelse(profilBeskrivelse);
        }

    }

    public String getfDato() {
        return fDato;
    }

    public void setfDato(String fDato) {
        this.fDato = fDato;
    }

    public String parseFDato(String fDato) {
        if (fDato.length() == 6) {
            String day = fDato.substring(0, Math.min(fDato.length(), 2));
            String month = fDato.substring(2, Math.min(fDato.length(), 2));
            String year = fDato.substring(4, Math.min(fDato.length(), 2));
            return day + "-" + month + "-" + year;
        }
        return null;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getProfilBeskrivelse() {
        return profilBeskrivelse;
    }

    public void setProfilBeskrivelse(String profilBeskrivelse) {
        this.profilBeskrivelse = profilBeskrivelse;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public List<String> getChatConversations() {
        return chatConversations;
    }

    public void setChatConversations(List<String> chatConversations) {
        this.chatConversations = chatConversations;
    }

    public static Person getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Person currentUser) {
        Person.currentUser = currentUser;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<Person> getData(){

        List<Person> personer = new ArrayList<>();

        int [] pictures = {
            R.drawable.scarlettjohannson,
            R.drawable.emmawatson,
            R.drawable.keanureevesnew,
            R.drawable.sandrabullocknew
        };

        String [] name = {
          "Scarlet Johansson",
          "Emma Watson",
          "Keanu Reeves",
          "Sandra Bullock"
        };
        int[] age = {
                22,34,21,45
        };



        return personer;
    }
    public String getName(){
        return name;
    }

    public int getPhotoId(){
        return photoId;
    }

    public void setName(String name){
        this.name=name;
    }
    public void setPicture(int picture){
        this.photoId = picture;
    }
}
