package edu.cse100.wi16.tritonstudy;

/**
 * Created by kshtz on 1/28/2016.
 */
public class Student {
    String name;
    String email;
    String major;
    String password;
    String phoneNumber;
    String bio;

    public String getBio() {return bio;}

    public void setBio(String bio) {this.bio = bio;}


    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major= major;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber;}


    public Student(){

    }
}
