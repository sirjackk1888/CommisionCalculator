/*
 * This program calculates the annual sales 
 * compensation for multiple salespersons.
 *
 * Coded by: Kelly B Ward
 */
package commissioncontroller;       // establishes the controlling package

import java.util.Scanner;           //imports the scanner module
import java.util.ArrayList;         //imports the ArrayList object
import java.text.DecimalFormat;     //established a format for decimal numbers
import java.util.Collections;       //assists in collecting information from Arrays

public class CommissionApplication {    //class identifier

    final double baseSalary = 35000,                //
                commissionPercentage = .15,         //This block establishes
                acceleratedPercentage = .1625,      //unchanging variables
                tableIncrease = 5000,               //throughout the program.
                tableMaxPercentage = 1.5,           //
                salesTarget = 120000,               //
                salesMinimum = salesTarget * .80;   //

    double  tableMax,                               //              
            salesDeficiency,                        //This block establishes
            salesPersonSelection,                   //variables that change
            tableStep,                              //depending on user input
            potentialCompensation,                  //
            salesMax = 0;                           //

    int     iterationMax,                           //
            maxLocation,                            //This block establishes
            iteration = 0,                          //variables that control
            calcIteration = 0,                      //counting throughout the
            displayIteration = 0,                   //program
            tableIteration = 0;                     //

    String anotherSalesperson = "y",                //This block sets string
            anotherChart = "y",                     //variables for user input
            topSeller;                              //

    ArrayList<String> salesName = new ArrayList<>();        // This block creates
    ArrayList<Double> salesNumber = new ArrayList<>();      // ArrayLists for
    ArrayList<Double> salesCommission = new ArrayList<>();  // user input values

    DecimalFormat newFormat = new DecimalFormat("$#,###.00");   //sets the decimal format for monetary values

    public void getInput() {            // input class to ask for sales information
        Scanner keyboard = new Scanner(System.in);      //establishes the keyboard scanner
        System.out.println("Welcome to the annual compensation calculator");        //welcome message

        do {        //do-while loop that collects salesperson's name and sales amount
            do {        //do=while loop that assigns input to ArrayLists 
                System.out.println("Please enter the name of salesperson number " + (iteration + 1));
                salesName.add(keyboard.next());     //prompts and stores salesperson's name
                System.out.println("Please enter a numerical value for this year's sales of " + salesName.get((int) iteration));
                while (!keyboard.hasNextDouble()) {     //This block validates the user input as numeric for sales value
                    System.out.println("I'm Sorry, that is not a number");
                    System.out.println("Please enter a numerical value for this year's sales of " + salesName.get((int) iteration));
                    keyboard.next();
                }
                salesNumber.add(keyboard.nextDouble());     //prompts and stores salesperson's annual sales (with validation)
            } while (salesNumber.get((int) iteration) <= 0);            //closes the loop after numeric values are assigned
            System.out.println("Would you like to enter another salesperson? y/n");     //prompts the user to input additional salesperson's information
            anotherSalesperson = keyboard.next();
            iteration = iteration + 1;      //upgrades the current iteration count for the do-while loop
            iterationMax = iteration;       // counts the iterations and stores as max
        } while (anotherSalesperson.equals("y"));       //accepts user input to return to beginning of loop or exit
        salesMax = Collections.max(salesNumber);            //this block searches for the highest sales value
        maxLocation = salesNumber.indexOf(salesMax);        //and stores it and the seller's name, along with it's
        topSeller = salesName.get((int) maxLocation);       //along with it's respective location
    }

    public void commissionCalc() {          //this class determines the commision for each sales value
        salesNumber.stream().map((_item) -> {
            //starts a for loop to evaluate each element of the sales value array
            if (salesTarget <= salesNumber.get((int) calcIteration)) {      //looks for sales values that meet the sales target
                salesCommission.add(baseSalary + (salesNumber.get((int) calcIteration) * acceleratedPercentage));       //sets sales commission array with commission for this value
            } else {
                if (salesMinimum <= salesNumber.get((int) calcIteration)) {     //looks for values that did not meet the target, but do meet the minimum
                    salesCommission.add(baseSalary + (salesNumber.get((int) calcIteration) * commissionPercentage));        //sets sales commission array with commission for this value
                } else {
                    if (salesMinimum > salesNumber.get((int) calcIteration)); {      // finds those values that do not meet the minimum or target values
                    salesCommission.add(baseSalary);                //sets sales commission array with commission for this value
                    }
                }
            }
            return _item;
        }).forEach((_item) -> {
            calcIteration = calcIteration + 1;      //counts the iteration for the "for" loop
        });
    }

    public void displayList() {     // this class displays the top seller information and shows each salesperson thier distance from first place
        salesNumber.stream().map((_item) -> {
            //establishes a for loop to search for the top seller
            if (salesMax == salesNumber.get((int) displayIteration)) {      //identifies and displays a message for the top seller, (you are welcome for the beer)
                System.out.println("Congratulations " + topSeller + " you are the current sales leader with "
                        + newFormat.format(salesNumber.get((int) displayIteration)) + " in sales, and "
                        + newFormat.format(salesCommission.get((int) displayIteration)) + " in compensation. You are also responsible for "
                        + "the first round of beer!");
            } else {
                if (salesMax > salesNumber.get((int) displayIteration)) {                       //identifies values that missed the top sales position and 
                    salesDeficiency = salesMax - salesNumber.get((int) displayIteration);       //displays a message stating how far off from top sdales value the seller was.
                    System.out.println(salesName.get((int) displayIteration) + ", you are " + newFormat.format(salesDeficiency)
                            + " behind the sales leader with " + newFormat.format(salesNumber.get((int) displayIteration))
                            + " in sales and " + newFormat.format(salesCommission.get((int) displayIteration)) + " in compensation.");
                }
            }
            return _item;
        }).forEach((_item) -> {
            displayIteration = displayIteration + 1;        //counts the iteration for the for loop
        });
    }

    public void displayTable() {        //this class shows a table for potential earnings
        do {        // this do-while loop gives the user the ability to view multiple potential earnings tables
            for (int i = 0; i < salesNumber.size(); i++) {      //this loop shows the names of each salesman and displays with a numerical value
                System.out.println(salesName.get(i) + " " + (i + 1));
            }
            Scanner keyboard = new Scanner(System.in);      //sets a keyboard scanner to wait for user input
            System.out.println("Please enter the number coresponding to the "        //prompts the user for a selection from the salesperson name table above
                    + "salesperson you would like to view potential commission for ");
            salesPersonSelection = (keyboard.nextInt() - 1);        //sets the selection variable from user input
            tableMax = (salesNumber.get((int) salesPersonSelection) * tableMaxPercentage);      // sets the table maximum value
            tableStep = salesNumber.get((int) salesPersonSelection);        //establishes the incriments the table will use
            System.out.println("Potential compensation chart for: "         //shows the name of the selected salesperson
                                + salesName.get((int) salesPersonSelection));
            System.out.println();       //blank line for user comfort
            System.out.print("Total sales ");       // table column header
            System.out.println("\t" + "Total compensation");        //table column header
            System.out.println();       //blank line for user comfort
            System.out.print(newFormat.format(salesNumber.get((int) salesPersonSelection)));        //sets the first row based on current sales
            System.out.println("\t" + newFormat.format(salesCommission.get((int) salesPersonSelection)));       //sets the first row based on current commission

            do {        //fills the table
                tableStep = tableStep + tableIncrease;      //establishes the incriments the table will use
                System.out.print(newFormat.format(tableStep));      //shows the next line of sales values
                if (tableStep < salesMinimum) {             //this block looks for values that do 
                    potentialCompensation = baseSalary;     //not meet minimum sales for commission
                }
                if (tableStep >= salesMinimum && tableStep < salesTarget) {                     //this block looks for values that 
                    potentialCompensation = baseSalary + (tableStep * commissionPercentage);    //fall between the minimum and target sales values
                }
                if (tableStep >= salesTarget) {                                                 //this block looks for values that do
                    potentialCompensation = baseSalary + (tableStep * acceleratedPercentage);   //eceed the sales target value
                }
                System.out.println("\t" + newFormat.format(potentialCompensation));     //fills in the compensation side of the table
            } while (tableStep < tableMax);         //ensures the table stops at the given max value
            System.out.println("Would you like to see another potential commission chart? y/n");        //prompts the user to view additrional tables
            anotherChart = keyboard.next();
        } while (anotherChart.equals("y"));     //loops to the table beginning based on user input
        System.out.println("Thank you for using the commission calculator, good luck next year!");      //program end, send off message
    }
}
