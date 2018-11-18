package no.hiof.leventen.actionbar.Test;

public class Users {
    public String name, image, beskrivelse;

    public Users(String name, String image, String beskrivelse) {
        this.name = name;
        this.image = image;
        this.beskrivelse = beskrivelse;
    }
    public Users(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return beskrivelse;
    }

    public void setDescription(String description) {
        this.beskrivelse = description;
    }

    }

