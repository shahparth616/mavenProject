import java.util.Scanner;

/**
 * Created by emperor on 12/29/16.
 */
public class AccountDetails {

    private int accountNumber;
    private String accountHolderName;
    private double amount;

    public AccountDetails(){}

    public AccountDetails(int accountNumber, String accountHolderName, double amount) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.amount = amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "accountNumber=" + accountNumber +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", amount=" + amount +
                '}';
    }


    public AccountDetails readAccountDetails()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the Account Number: ");
        int id = s.nextInt();
        System.out.println("Enter the Account Holder Name: ");
        String name = s.next();
        System.out.println("Enter the Amount: ");
        double amount = s.nextDouble();
        AccountDetails a = new AccountDetails(id, name, amount);
        return a;
    }
}
