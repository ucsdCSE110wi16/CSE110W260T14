package edu.cse100.wi16.tritonstudy;

/**
 * Created by john on 3/7/16.
 */
public class StudyTime {


    private String day;
    private int hourStart;
    private int hourEnd;
    private int minuteStart;
    private int minuteEnd;
    private String location;
    private String course;

    public String getDay() {return day;}
    public void setDay(String name) {this.day= day;}

    public int getHourStart() {return hourStart;}
    public void setHourStart(int name) {this.hourStart= hourStart;}

    public int getHourEnd() {return hourEnd;}
    public void setHourEnd(int hourEnd) {this.hourEnd= hourEnd;}

    public int getMinuteStart() {return minuteStart;}
    public void setMinuteStart(int minuteStart) {this.minuteStart= minuteStart;}

    public int getMinuteEnd() {return minuteEnd;}
    public void setMinuteEnd(int minuteEnd) {this.minuteEnd= minuteEnd;}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location= location;}

    public String getCourse() {return course;}
    public void setCourse(String course) {this.course= course;}


}