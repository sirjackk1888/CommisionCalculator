 /* This program contains the controlling code for the Application class.
 * Coded by: Kelly Ward
 */
package commissioncontroller; //establishes the package

public class CommissionController {

    public static void main(String[] args) {
        //Begin Application
        CommissionApplication finalSalary = new CommissionApplication();

        //Get user's sales total
        finalSalary.getInput();
        //Calculates the additional percentages based on annual sales.
        finalSalary.commissionCalc();
        finalSalary.displayList();
        finalSalary.displayTable();
    }
}
