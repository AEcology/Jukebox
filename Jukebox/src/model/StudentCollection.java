package model;

import java.util.ArrayList;

public class StudentCollection {

	ArrayList<Student> studentCollection;
	
	public StudentCollection(){
		studentCollection = new ArrayList<Student>();
	}
	
	//For adding a student who does not exist
	public void addStudent(String ID, String password){
		studentCollection.add(new Student(ID, password));
	}
	
	//Returns requested student, returns null if not found 
	public Student getStudent(String ID, String password) {
		
		for(Student eachStudent: studentCollection){
			if(eachStudent.getID() == ID && eachStudent.getPassword() == password)
				return eachStudent;
		}
		return null;
	}

	public void clearSongPlaysToday() {
		for(Student eachStudent: studentCollection){
			eachStudent.clearSongPlaysToday();
		}
	}
}
