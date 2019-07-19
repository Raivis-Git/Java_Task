import java.util.*;

public class Employee {
    // ArrayList of employees
    ArrayList<Employee> employees = new ArrayList<Employee>();
    //Values from the task
    private String firstName;
    private String lastName;
    private int salary;
    private int bonus;
    //Additional values
    final int taxAllowance = 100;
    final int LOWER_LIMIT = 500;
    final int UPPER_LIMIT = 4000;
    private int taxableSalary;
    //Constructor with 4 values
    public Employee(String firstName, String lastName, int salary, int bonus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.bonus = bonus;
        this.taxableSalary=this.salary+this.bonus-this.taxAllowance;
    }
    //Default contructor
    public Employee() {
    }
    //Method for calculating tax
    public int getTax(){
        int tax=0;
        if(this.salary<this.LOWER_LIMIT){
            tax=this.taxableSalary/100*10;
        }else if(this.salary<this.UPPER_LIMIT){
            tax=this.taxableSalary/100*22;
        }else{
            tax=this.UPPER_LIMIT/100*22 + (this.taxableSalary-this.UPPER_LIMIT)/100*40;
        }
        return tax;
    }
    //Method for updating employees salary
    public static ArrayList<Employee> updateSalary(List<Employee> emp , int salaryRangeFrom, int salaryRangeTo, double changeMultiplier){
        final int lessThan = 3000;
        double salaryIncrease;

        ArrayList<Employee> filtered = new ArrayList<Employee>();
        for(Employee e : emp){

            if( (e.getSalary()>salaryRangeFrom && e.getSalary()<salaryRangeTo) && e.getTaxableSalary()<lessThan){

                salaryIncrease = e.getSalary() * changeMultiplier - e.getSalary();

                e.setSalary((int)checkTheMultiplier(salaryIncrease, Double.valueOf(e.getTax()), e.getSalary(), changeMultiplier));
                filtered.add(e);
            }
        }

        return filtered;
    }
    //Additional method for updateSalary  for correcting the changeMultiplier
    private static double checkTheMultiplier(double salaryIncrease, double tax, int salary, double multiplier){

        while(salaryIncrease > tax){
            salaryIncrease= salary*multiplier - salary;
           multiplier -= 0.01;
        }
        return salary+salaryIncrease;
    }
    // GETTERS and SETTERS
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getTaxAllowance() {
        return taxAllowance;
    }

    public int getLOWER_LIMIT() {
        return LOWER_LIMIT;
    }

    public int getUPPER_LIMIT() {
        return UPPER_LIMIT;
    }

    public int getTaxableSalary() {
        return taxableSalary;
    }

    public static void main(String[] args){
        Employee e = new Employee("John","Johnson",2000,500);
        int tax= e.getTax();

        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee("John", "Johnson", 2000, 500));
        employees.add(new Employee("Bob", "Bobson", 4000, 200));
        employees.add(new Employee("Job", "Jobson", 3000, 0));
        for(Employee empl : employees){
            System.out.println("First Name: " + empl.firstName + " Last Name: " + empl.lastName + " Tax: " + empl.getTax() + " Salary: " + empl.salary);
        }
        System.out.println();
        ArrayList<Employee> employeesUpdated = updateSalary(employees, 100, 5000, 2.2);
        for(Employee em : employeesUpdated){
            System.out.println("First Name: " + em.firstName + " Last Name: " + em.lastName + " Tax: " + em.getTax() + " Salary: " + em.salary);
        }
    }
}
