package com.example.studenttrackproject;

public class timetableday {
    private String idsubject;
    private String subjectname;
    private String teachsubject;
    private String classroom;
    private String starttime;
    private String stoptime;

    public timetableday(String idsubject, String subjectname, String teachsubject, String classroom, String starttime, String stoptime) {
        this.idsubject = idsubject;
        this.subjectname = subjectname;
        this.teachsubject = teachsubject;
        this.classroom = classroom;
        this.starttime = starttime;
        this.stoptime = stoptime;
    }

    public String getIdsubject() {
        return idsubject;
    }

    public void setIdsubject(String idsubject) {
        this.idsubject = idsubject;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getTeachsubject() {
        return teachsubject;
    }

    public void setTeachsubject(String teachsubject) {
        this.teachsubject = teachsubject;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStoptime() {
        return stoptime;
    }

    public void setStoptime(String stoptime) {
        this.stoptime = stoptime;
    }
}
