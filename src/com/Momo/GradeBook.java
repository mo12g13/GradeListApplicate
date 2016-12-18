package com.Momo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Momo Johnson on 10/13/2016.
 * This class keeps track of the grades enter by the user. It implements the Comparable Interface in order
 * to sort, find the min and maximum value of the GradeBook arraylist
 */
public class GradeBook implements Comparable<GradeBook> {
    ArrayList<GradeBook> grades = new ArrayList<>();
    double gradeNumber;
    public  GradeBook(double number){
        this.gradeNumber = number;

    }


    //Implementation of the comparable
    @Override
    public int compareTo(GradeBook compareNumber) {
        double comparabeGrade = ((GradeBook) compareNumber).getGradeNumber();
        return (int) (this.gradeNumber-comparabeGrade);
    }
    // method that adds a new grade to the gradebook
    public void AddGrade(int grade){
        this.grades.add(new GradeBook(grade));
    }
    // A method that gets the grade number for comparision purposes
    public double getGradeNumber() {
        return gradeNumber;
    }

}
