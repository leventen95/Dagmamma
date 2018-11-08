package no.hiof.leventen.actionbar;

import java.util.ArrayList;
import java.util.List;

public class Person {
    String name;
    int age;
    int photoId;
    private String email;
    private String tlfNr;
    private String passord;
    private List<String> chatConversations;
    private static Person currentUser;

    public Person(String name, int age, int photoId, String email, String tlfNr, String passord) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
        this.email = email;
        this.tlfNr = tlfNr;
        this.passord = passord;
        this.chatConversations = new ArrayList<>();
        this.currentUser = this;
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

    public String getTlfNr() {
        return tlfNr;
    }

    public void setTlfNr(String tlfNr) {
        this.tlfNr = tlfNr;
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

        for (int i=0; i<pictures.length; i++){
            Person current = new Person(name[i],age[i],pictures[i],"minkulemeial","49149858","passord123");
            personer.add(current);
        }

        return personer;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public int getPhotoId(){
        return photoId;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPicture(int picture){
        this.photoId = picture;
    }

    public String getPassord() { return passord; }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}
