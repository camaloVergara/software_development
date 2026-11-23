package org.example.portfolio2x;
import java.util.ArrayList;

public class Controller {

    Controller(View view, Model model){
        //------------- Listeners -------------
        //register the student's name
        view.textName.setOnAction(e->{
            String user = view.textName.getText();
            if(user.isBlank())
                view.alert("You must enter a student name");
            else if(!model.isStudCreated(user)){
                model.addStudent(user);
                view.confirmation("Student registered");
            }
        });

        //sign up for an activity
        view.signUp.setOnAction(e-> {     //registering
            String user = view.textName.getText();
            String activity = view.comboAct.getValue();
            if (user.isBlank())
                view.alert("You must enter a student name");
            else if(!model.isStudCreated(user)){
                model.addStudent(user);
            }
            else if(activity == null || activity.isBlank()){
                view.alert("You must select an activity");  //if activity is null or not selected
            }
            else if((model.isSignUp(user, activity))){
                view.alert("Student already registered");
            }
            else{
                model.addActivity(user, activity);  //then we can register the activity
                view.regisAct.setText(activity);
                view.confirmation("Successfully joined the activity");
            }
        });

        //show the groups by activity
        view.showGroup.setOnAction(e->{
            String getGroup = view.groupAct.getValue(); //the selected activity
            ArrayList<String> seeGroups = model.showGroups(getGroup);
            view.groups.getItems().setAll(seeGroups);
            String see = String.join("\n", seeGroups);
            view.viewGroup.setText(see);
            //viewGroup.setText(String.valueOf(seeGroups)); //old
        });

        //create a new project group
        view.createGroup.setOnAction(e->{
            String user = view.textName.getText();
            String getGroup = view.groupAct.getValue(); //the selected activity
            String titleProj = view.title.getText();
            String nameSup = view.supervisor.getText();
            if(user.isBlank()){
                view.alert("You must enter a student name");
            }
            else if(getGroup == null || getGroup.isBlank()){    //if an activity is empty or not selected
                view.alert("You must select an activity");
            }
            else if(titleProj.isBlank()){
                view.alert("You must enter a title for the project");
            }
            else if(nameSup.isBlank()) {
                view.alert("You must enter a supervisor for the project");
            }
            else{
                model.addNewGroup(user, titleProj, nameSup, getGroup); //register new group
                view.confirmation("Group created successfully");
            }
        });

        //join existing group
        view.join.setOnAction(e->{
            String user = view.textName.getText();
            String activity = view.groupAct.getValue();
            String joinGroup = view.groups.getValue();
            if(user.isBlank()){
                view.alert("You must enter a student name");
            }
            else if(activity == null || activity.isBlank()){    //if an activity is empty or not selected
                view.alert("You must select an activity");
            }
            else if(joinGroup == null || joinGroup.isBlank()){  //if a group from combobox is empty or not selected
                view.alert("You must select a group");
            }
            else if(!model.isSignUp(user, activity)){
                view.alert("Student not signed up for activity");
            }
            else if(model.isMember(activity, user)){
                view.alert("Student already member of a group");
            }
            else{
                model.addMember(user, joinGroup);
                view.confirmation("Successfully joined the group");
            }
        });
    }
}
