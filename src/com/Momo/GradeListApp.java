package com.Momo;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Momo Johnson on 10/11/2016.
 */
public class GradeListApp extends  JFrame  {
    private JPanel rootPane;
    private JTextField txtSoceTotal;
    private JTextField txtScoreInput;
    private JTextField txtScoreCount;
    private JTextField txtAverage;
    private JButton btnSortedScores;
    private JButton btnAddGrade;
    private JButton btnExit;
    private JButton btnClearScore;
    private JTextField txtMaxValue;
    private JTextField txtMinValue;
    private JButton btnUnsortedScores;
    //Initialization of the Grade book class
    private ArrayList<GradeBook> gradeList = new ArrayList<>();

    public GradeListApp() {
        //Setting the title of the calculator app
        super("Grade Calculator Application");
        //Setting of the window width and length
        setPreferredSize(new Dimension(450, 300));
        //Setting the contentPane
        setContentPane(rootPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Setting the default button to be the add button
        rootPane.getRootPane().setDefaultButton(btnAddGrade);
        pack();
        //Setting the visibility of the window
        setVisible(true);
        setLocationRelativeTo(null);
        //A action event listener that exit the grade app
        btnExit.addActionListener(ae ->{
            System.exit(0);
        });
        btnAddGrade.addActionListener( ae ->{
                //Validation of the input being entered of the user
                if (isValidData()) {
                    //If the method above is true, convert the user input to an integer
                    double number = Double.parseDouble(txtScoreInput.getText());
                    //Adding the grade to the GradeBook arraylist
                    gradeList.add(new GradeBook(number));
                    //Setting the grade total using the totalGrades mehtod
                    txtSoceTotal.setText("" + totalGrades(gradeList));
                    //Setting the average of the the txtAverage using the average method
                   ;
                    txtAverage.setText(String.format("%.2f", averageGrade(gradeList)));

                    //Setting the amount of inputs to the txtScoreCount using the countGrades method
                    txtScoreCount.setText(""+ countGrades(gradeList));
                    //Finding the max grade grade using the collection max function
                    double maxNumber = Collections.max(gradeList).gradeNumber;
                    //Finding the min grade using the collection min function
                    double minNumber = Collections.min(gradeList).gradeNumber;
                    //Setting of the max value in the max textField box
                    txtMaxValue.setText( String.format("%.2f", maxNumber));
                    //Setting of the minx grade in the minimum textfield textbox
                    txtMinValue.setText(String.format("%.2f", minNumber));

                }
                //Clearing the txtScoreInput after the user add a value;
                txtScoreInput.setText("");
                //Setting the focus to txtScoreInput textBox
                txtScoreInput.requestFocus();
            }
        );
        //An action that display the grades enter by the user
        btnSortedScores.addActionListener(ae ->{
                //Sorting the grade list to display the grades in ascending order
                ArrayList<GradeBook> sortedGrads = getStudentGrades(gradeList);
            //Displaying of the grades from the Grade book arraylist using the displayGrade method
                displayGrades(sortedGrads);
        });

        //A Action event that clears all textbox when click
        btnClearScore.addActionListener(ae -> {
                //Clearing  of the various textField to an empty string
                txtScoreCount.setText("");
                txtAverage.setText("");
                txtSoceTotal.setText("");
                gradeList.clear();
                txtMaxValue.setText("");
                txtMinValue.setText("");

        });

        //A method that display the unsorted list of the GradeBook grade
        btnUnsortedScores.addActionListener(ae -> {


            displayGrades(gradeList);
        });

    }

    //A method that adds a grade to the Grade Book arraylist
    private void addGrade(ArrayList<GradeBook> scores, int number) {

        scores.add(new GradeBook(number));
    }

    //A method that counts and returns the grade being enter by the user
    private int countGrades(ArrayList<GradeBook> grades) {
        return grades.size();
    }

    //A method that returns the average of grades enter by the user
    private double averageGrade(ArrayList<GradeBook> grades) {
        double sum = 0;
        for (GradeBook grad : grades) {
            sum += grad.getGradeNumber();


        }
        return sum / grades.size();

    }

    //A method that calculates the totals grades enter by the user
    private double totalGrades(ArrayList<GradeBook> grades) {
        int sum = 0;
        for (GradeBook grade : grades) {
            sum += grade.getGradeNumber();
        }
        return sum;
    }

    //A method that checks to see if the user enter a value in the textbox
    private boolean isPresent(JTextField textField, String name) {
        if (textField.getText().equals("")) {
            JOptionPane.showConfirmDialog(rootPane, name
                    + " is a required field", "Entry Error", JOptionPane.ERROR_MESSAGE);
            textField.requestFocus();
            textField.setText("");
            return false;
        }
        return true;
    }

    //A method that checks to see if the string enter by the user can be parse to a number
    private boolean isInteger(JTextField textField, String name) {
        try {
            if (Double.parseDouble(textField.getText()) > 0) {


                return true;

            } else {
                JOptionPane.showConfirmDialog(rootPane, name
                        + " must be an decimal above 0", "Entry Errror", JOptionPane.ERROR_MESSAGE);
                textField.setText("");
                textField.requestFocus();
                return false;
            }
        } catch (NumberFormatException ext) {
            JOptionPane.showConfirmDialog(rootPane, name
                    + " shouldn't be letters", "Entry Error", JOptionPane.ERROR_MESSAGE);
            textField.setText("");
            textField.requestFocus();
            return false;

        }
    }

private void displayGrades(ArrayList<GradeBook> grades) {
    String result = " ";
    ;
    if (gradeList.isEmpty()) {
        JOptionPane.showConfirmDialog(rootPane, "There are no grades in the gradebook", "Gradebook Empty", JOptionPane.YES_NO_OPTION);
    } else {

        for (int i = 0; i < grades.size(); i++) {
            result += grades.get(i).getGradeNumber() + "\n";
        }
        JOptionPane.showConfirmDialog(rootPane, result, "Display Grades", JOptionPane.YES_NO_OPTION);

    }
}



    private ArrayList<GradeBook> getStudentGrades (ArrayList < GradeBook > grades)
    {
        ArrayList<GradeBook> sortedGrades = new ArrayList<>(grades);
        Collections.sort(sortedGrades);
        return sortedGrades;
    }


//A method that checks for valid input enter by the user

    private boolean isValidData() {
        if (!isPresent(txtScoreInput, "Score")) {
            return false;
        }
        if (!isInteger(txtScoreInput, "Score")) {
            return false;
        }


        return true;

    }


}
