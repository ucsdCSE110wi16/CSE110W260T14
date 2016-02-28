package edu.cse100.wi16.tritonstudy;

/**
 * Created by kshtz on 1/28/2016.
 */
public class Student {
    String name;
    String studentId;
    String email;
    String bio;
    String major;
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
        this.password= password;
    }

    public Student(){

    }
}
