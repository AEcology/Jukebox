package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A collection of Student objects to be used for the Jukebox Class. 
 * @author Jonathan Snavely
 * @author Anthony Rodriguez
 *
 */
public class StudentCollection implements Serializable{

	ArrayList<Student> studentCollection;
	
	public StudentCollection(){
		studentCollection = new ArrayList<Student>();
		addStudent("Ali", "1111");
		addStudent("Chris", "2222");
		addStudent("River", "3333");
		addStudent("Ryan", "4444");	   
	}
	
	//For adding a student who does not exist
	public void addStudent(String ID, String password){
		studentCollection.add(new Student(ID, password));
	}
	
	//Returns requested student, returns null if not found 
	public Student getStudent(String ID, String password) {
		
		for(Student eachStudent: studentCollection){
			if((eachStudent.getID()).equals(ID) && (eachStudent.getPassword()).equals(password)){
				return eachStudent;
			}
		}
		return null;
	}

	//Reset each of the student's play counts
	public void clearSongPlaysToday() {
		for(Student eachStudent: studentCollection){
			eachStudent.clearSongPlaysToday();
		}
	}
}
