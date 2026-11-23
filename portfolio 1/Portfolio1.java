import java.util.ArrayList;

public class Portfolio1{
    public static void main(String[] args){
        StudyProgram NatBach = new StudyProgram("Natbach");
        BasicCourse calculus = new BasicCourse("calculus");
        BasicProject project1 = new BasicProject("project1");
        SubjectCourse software = new SubjectCourse("software development");
        SubjectProject mathProject = new SubjectProject("mathematical rainfall-runoff model");
        Student Rodrigo = new Student("Rodrigo", 1, NatBach);
        Student Millaray = new Student("Millaray", 2, NatBach);

        Rodrigo.addActivity(calculus);
        Rodrigo.addActivity(software);
        Rodrigo.addActivity(project1);
        ProjectGroup group2 = new ProjectGroup(Rodrigo, project1, "title", "super");
        Rodrigo.passActivity(project1);
        Millaray.addActivity(project1);

        System.out.println(Rodrigo.basicProjects);
        System.out.println(Rodrigo.subjectProjects);
        System.out.println(Rodrigo.groups);
        Rodrigo.completion();
        group2.addMember(Millaray);
    }


    static abstract class BachelorDegree{
        public String program;
        public int credits = 180;
        public BachelorDegree(String program){
            this.program = program;
        }
        public String toString(){
            return program;
        }
    }

    //study program, example: international bachelor study in natural science
    static class StudyProgram extends BachelorDegree{
        public String name;
        public ArrayList<StudyActivity> activities;
        public StudyProgram(String nameProgram){
            super(nameProgram);
        }
    }

    //Study activity can be a basic or subject specific course/project
    static abstract class StudyActivity{
        public String nameActivity;
        public int credits;
        public StudyActivity(String nameActivity, int credits){
            this.nameActivity = nameActivity;
            this.credits = credits;
        }
        public int getCredits(){
            return credits;
        }
        public String toString(){
            return nameActivity;
        }
    }
    //basic courses, example: calculus
    static abstract class BasicModule extends StudyActivity{
        public BasicModule(String basicCourse, int credits){
            super(basicCourse, credits);
        }
    }
    //subject module courses, example: Software development
    static abstract class SubjectModule extends StudyActivity{
        public SubjectModule(String subjectCourse, int credits){
            super(subjectCourse, credits);
        }
    }
    //basic course object
    static class BasicCourse extends BasicModule{
        public BasicCourse(String course){
            super(course, 5);
        }
    }
    //basic project object
    static class BasicProject extends BasicModule{
        public BasicProject(String project){
            super(project, 15);
        }
    }
    //subject course specific object
    static class SubjectCourse extends SubjectModule{
        public SubjectCourse(String course){
            super(course, 5);
        }
    }
    //subject project object
    static class SubjectProject extends SubjectModule{
        public SubjectProject(String project){
            super(project, 15);
        }
    }
    //student object
    static class Student{
        private int id;
        private String name;
        private StudyProgram bachelor;
        private ArrayList<BasicCourse> basicCourses = new ArrayList<>();
        private ArrayList<SubjectCourse> subjectCourses = new ArrayList<>();
        private ArrayList<BasicProject> basicProjects = new ArrayList<>();
        private ArrayList<SubjectProject> subjectProjects = new ArrayList<>();
        private ArrayList<ProjectGroup> groups = new ArrayList<>();
        private int totalCredits = 0;
        public Student(String name, int id, StudyProgram bachelor){
            this.name = name;
            this.id =  id;
            this.bachelor = bachelor;
        }
        //checks if the student is signed for the activity
        public boolean isSignedUp(StudyActivity project){
            for(StudyActivity a : basicProjects){
                if(a == project) {
                    return true;
                }
            }
            for(StudyActivity a : subjectProjects){
                if(a == project) {
                    return true;
                }
            }
            return false;
        }
        public String toString(){
            return name;
        }
        //adding activities
        public void addActivity(StudyActivity activity){
            switch(activity){
                case BasicCourse course  -> basicCourses.add(course);
                case BasicProject project -> basicProjects.add(project);
                case SubjectCourse course -> subjectCourses.add(course);
                case SubjectProject project -> subjectProjects.add(project);
                default -> throw new IllegalArgumentException("Not an Activity");
            }
        }
        //adding group project
        public void addGroupProject(ProjectGroup group){
            groups.add(group);
        }
        //passing a study activity and adding credits
        public void passActivity(StudyActivity act){
            if(!isSignedUp(act)){
                throw new IllegalArgumentException("student hasn't signed up for activity");
            }
            else{
                totalCredits += act.getCredits();
            }
        }
        //check the completion of the program
        public void completion(){
            if(totalCredits == bachelor.credits)
                IO.println("Bachelor Completed");
            IO.println("Not Completed yet, "+ (bachelor.credits-totalCredits)+" credits missing");

        }
    }

    static class ProjectGroup{
        public ArrayList<Student> members = new ArrayList<>();
        public StudyActivity project;
        public String projectTitle;
        public String supervisor;
        public ProjectGroup(Student member, StudyActivity project, String title, String supervisor){
            if(member == null){
                throw new IllegalArgumentException("Member is null");
            }
            else if(!member.isSignedUp(project)){
                throw new IllegalArgumentException("Member hasn't signed up for activity");
            }
            else if(project == null){
                throw new IllegalArgumentException("Study activity is null");
            }
            else if(title == null || title.isBlank()){
                throw new IllegalArgumentException("title is null or blank");
            }
            else if(supervisor == null || supervisor.isBlank()){
                throw new IllegalArgumentException("Supervisor is null or blank");
            }
            this.members.add(member);
            member.addGroupProject(this);
            this.project = project;
            this.projectTitle = title;
            this.supervisor = supervisor;
        }
        public ArrayList<Student> getMembers(){
            return members;
        }
        public String toString(){
            return projectTitle;
        }
        //to check if all the members of the group are signed up for this project
        public boolean areAllSignedUp(){
            for(int i = 0; i < this.members.size(); i++){
                if(!this.members.get(i).isSignedUp(this.project))
                    return false;
            }
            return true;
        }
        //add member
        public void addMember(Student student){
            if(student.isSignedUp(this.project)){
                members.add(student);
            }
            else{
                throw new IllegalArgumentException("Member hasn't signed up for activity");
            }
        }
    }
}