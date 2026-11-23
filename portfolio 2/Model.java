package org.example.portfolio2x;
import java.util.ArrayList;

public class Model {
    MyDB db = new MyDB();
    Model(){
        db.cmd("PRAGMA foreign_keys = ON");
        db.cmd("create table if not exists student "+ "(name text primary key);");
        db.cmd("create table if not exists studyActivity"+ "(name text primary key);");
        db.cmd("create table if not exists projectGroup "+ "(title text primary key, supervisor text, activity text references studyActivity(name));");
        db.cmd("create table if not exists groupMember "+ "(nameStud text references student(name), title text references projectGroup(title), primary key(nameStud, title));");
        db.cmd("create table if not exists signUp"+ "(nameStud text references student(name), nameAct text references studyActivity(name), primary key(nameStud, nameAct));");
        db.cmd("insert or ignore into studyActivity(name)"+ " values('Basic Project 1'), ('Basic Project 2'), ('Basic Project 3'), ('Project Module 1'), ('Project Module 2'), ('Bachelor Project');");
    }
    //get project groups by activity for student
    boolean isMember(String activity, String name){
        ArrayList<String>list = db.query("select g.title from groupMember as g inner join projectGroup as p on g.title = p.title where p.activity = '"+ activity +"' and g.nameStud = '"+ name +"';", "title");
        if(list.isEmpty())
            return false;
        return true;
    }
    //ask if student is signed uo for an activity
    boolean isSignUp(String stud, String act){
        ArrayList<String> matchName = db.query("select nameAct from signUp where nameStud = '"+ stud +"' AND nameAct = '"+ act +"';", "nameAct");
        if(matchName.isEmpty())
            return false;
        return true;
    }
    //register activity
    void addActivity(String name, String act){
        db.cmd("insert into signUp(nameStud, nameAct) values('"+ name +"','"+ act +"');");
    }
    //return list with project groups by activity
    ArrayList<String> showGroups(String act){
        return db.query("select title from projectGroup where activity = '"+ act +"';", "title");
    }
    //register a new group and member
    void addNewGroup(String nameStud, String title, String supervisor, String activity){
        db.cmd("insert into projectGroup(title, supervisor, activity) values('"+ title +"', '"+ supervisor +"', '"+ activity +"');");
        db.cmd("insert into groupMember(nameStud, title) values('"+ nameStud +"', '"+ title +"');");
    }
    //register a new member to project group
    void addMember(String stud, String title){
        db.cmd("insert into groupMember(nameStud, title) values('"+ stud +"', '"+ title +"');");
    }
    //check if student's name exist
    boolean isStudCreated(String name) {
        ArrayList<String> names = db.query("select name from student where name = '" + name + "';", "name");
        if (names.isEmpty())
            return false;
        return true;
    }
    //register student's name
    void addStudent(String student){
        db.cmd("insert into student(name) values('"+ student +"');");
    }
    //return the list of activities
    ArrayList<String> showAct(){
        return db.query("select name from studyActivity;", "name");
    }

}
