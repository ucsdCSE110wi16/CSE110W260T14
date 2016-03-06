package edu.cse100.wi16.tritonstudy;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by kshtz on 1/28/2016.
 */
public class Student implements Parcelable {
//    private static final long serialVersionUID = -7060210544600464481L;
    private String name;
    private String email;
    private String major;
    private String password;
    private String phoneNumber;
    private String bio;
    private String class1;
    private String class2;
    private String class3;
    private String class4;

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

    public String getBio() {return bio;}

    public void setBio(String bio) {this.bio = bio;}

    public String getClass1(){return class1;}
    public void setClass1(String class1){this.class1 = class1;}

    public String getClass2(){return class2;}
    public void setClass2(String class2){this.class2 = class2;}

    public String getClass3(){return class3;}
    public void setClass3(String class3){this.class3 = class3;}

    public String getClass4(){return class4;}
    public void setClass4(String class4){this.class4 = class4;}


    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel source) {
            Student student = new Student();
            student.name= source.readString();
            student.email = source.readString();
            student.major= source.readString();
            student.password = source.readString();
            student.phoneNumber= source.readString();
            student.bio = source.readString();

            return student;
        }
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int describeContents() {
        return 0;
    }
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(major);
        parcel.writeString(password);
        parcel.writeString(phoneNumber);
        parcel.writeString(bio);
    }

    public Student(){

    }
}
