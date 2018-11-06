package no.hiof.leventen.actionbar;

import java.util.ArrayList;
import java.util.List;

public class Person {
    String name;
    int age;
    int photoId;
    private int id;
    private String email;
    private String tlfNr;

    public Person(String name, int age, int photoId, int id, String email, String tlfNr) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
        this.id = id;
        this.email = email;
        this.tlfNr = tlfNr;
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
            Person current = new Person(name[i],age[i],pictures[i],i,"minkulemeial","49149858");
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
    public int getId(){
        return id;
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
    public void setId(int id){
        this.id = id;
    }

}
