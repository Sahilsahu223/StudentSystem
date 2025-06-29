package com.sahilsahu.StudentSystem.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(int id){
        super("Could not found the user with id "+ id);
    }
}
