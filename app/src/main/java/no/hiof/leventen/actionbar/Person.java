package no.hiof.leventen.actionbar;

import java.util.ArrayList;
import java.util.List;

public class Person {
    String name;
    int age;
    int photoId;
    private int id;


    public Person(String name, int age, int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
    }
    public Person(){

    }


    public static List<Person> getData(){

        List<Person> personer = new ArrayList<>();

        int [] pictures = {
            R.drawable.scarletjohansson,
            R.drawable.emmawatson,
            R.drawable.keanureeves,
            R.drawable.sandrabullock
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
            Person current = new Person();
            current.setId(i);
            current.setName(name[i]);
            current.setAge(age[i]);
            current.setPicture(pictures[i]);
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
    public void setId(int imageId){
        this.id = id;
    }

}
