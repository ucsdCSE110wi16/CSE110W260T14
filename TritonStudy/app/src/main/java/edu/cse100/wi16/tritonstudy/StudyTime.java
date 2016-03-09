package edu.cse100.wi16.tritonstudy;

/**
 * Created by john on 3/7/16.
 */
public class StudyTime {
    
    private String day;
    private String hourStart;
    private String hourEnd;
    private String minuteStart;
    private String minuteEnd;
    private String location;
    private String course;

    public String getDay() {return day;}
    public void setDay(String day) {this.day= day;}

    public String getHourStart() {return hourStart;}
    public void setHourStart(String hourStart) {this.hourStart = hourStart;}

    public String getHourEnd() {return hourEnd;}
    public void setHourEnd(String hourEnd) {this.hourEnd= hourEnd;}

    public String getMinuteStart() {return minuteStart;}
    public void setMinuteStart(String minuteStart) {this.minuteStart= minuteStart;}

    public String getMinuteEnd() {return minuteEnd;}
    public void setMinuteEnd(String minuteEnd) {this.minuteEnd= minuteEnd;}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location= location;}

    public String getCourse() {return course;}
    public void setCourse(String course) {this.course= course;}

    public StudyTime(
            String day,
            String hour_start,
            String hour_end,
            String minute,
            String minute_end,
            String location,
            String course){

        this.day = day;
        this.hourStart = hour_start;
        this.hourEnd = hour_end;
        this.minuteStart = minute;
        this.minuteEnd = minute_end;
        this.location = location;
        this.course = course;

    }

    public StudyTime(){

    }


}
