package no.hiof.leventen.actionbar;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import no.hiof.leventen.actionbar.Chat.Conversation;
import no.hiof.leventen.actionbar.Chat.Message;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class Person {
    String name;
    int photoId;
    private String email;
    private List<Conversation> conversations;
    private static Person currentUser;
    private String firebaseUid;
    private String profilBeskrivelse;
    private String userType;
    private String by;
    private String fDato;

    public Person(String name, String email, String firebaseUid, String profilBeskrivelse, String userType, String by, String fDato, boolean isThisUser) {
        this.name = name;
        this.email = email;
        this.firebaseUid = firebaseUid;
        this.profilBeskrivelse = profilBeskrivelse;
        this.userType = userType;
        this.by = by;
        this.fDato = fDato;
        this.conversations = new ArrayList<Conversation>();
        Conversation con1 = new Conversation(0,"mail@mail.com","Fredrik Kalsberg");
        con1.addMessage(new Message("Hei du! Jeg vil gjerne passe ungen din! Hilsen Fredrik","Fredrik Kalsberg",new Date(81996972)));
        con1.addMessage(new Message("Hei du! Jeg har ingen unger jeg!","it mannen",new Date(81996972)));
        con1.addMessage(new Message("Uff, det var dumt!","Fredrik Kalsberg",new Date(81996972)));
        con1.addMessage(new Message("Men jeg kjenner en person som har unger da!","it mannen",new Date(81996972)));
        con1.addMessage(new Message("Javell? Hvem da?","Fredrik Kalsberg",new Date(81996972)));
        con1.addMessage(new Message("Han heter Petter!","it mannen",new Date()));

        Conversation con2 = new Conversation(1,"hansegutt@haha.lol","Hans Larsson");
        con2.addMessage(new Message("Hei du! Jeg vil gjerne passe ungen din! Hilsen Hans","Hans Larsson",new Date(81996972)));
        con2.addMessage(new Message("Hei du! Jeg har ingen unger jeg!","it mannen",new Date(81996972)));
        con2.addMessage(new Message("Uff, det var dumt!","Hans Larsson",new Date(81996972)));
        con2.addMessage(new Message("Men jeg kjenner en person som har unger da!","it mannen",new Date(81996972)));
        con2.addMessage(new Message("Javell? Hvem da?","Hans Larsson",new Date(81996972)));

        this.conversations.add(con1);
        this.conversations.add(con2);

        if(isThisUser) {
            this.currentUser = this;
            this.currentUser.setFirebaseUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
            this.currentUser.setProfilBeskrivelse(profilBeskrivelse);
        }else{
            this.setProfilBeskrivelse(profilBeskrivelse);
        }

    }
    public Person() {

    }

    public String getfDato() {
        return fDato;
    }

    public void setfDato(String fDato) {
        this.fDato = fDato;
    }

    public static String parseFDato(String fDato) {
        if (fDato.length() == 6) {
            String day = fDato.substring(0, Math.min(fDato.length(), 2));
            String month = fDato.substring(2, Math.min(fDato.length(), 2));
            String year = fDato.substring(4, Math.min(fDato.length(), 2));
            return day + "-" + month + "-" + year;
        }

        return "41";
    }
    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
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

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
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
