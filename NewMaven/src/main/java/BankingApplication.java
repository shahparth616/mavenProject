import java.sql.SQLException;
import java.util.Scanner;


/**
 * Created by emperor on 12/29/16.
 */
public class BankingApplication {

    AccountDetails accountDetails = new AccountDetails();
    AccountOperations accountOperationsImp = new AccountOperationsImp();
    BankingOperations bankingOperationsImp = new BankingOperationsImp();

    public BankingApplication() throws SQLException, ClassNotFoundException {}

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvalidTransactionException, BusinessException {

        BankingApplication b = new BankingApplication();

        int option;
        do {
            System.out.println("Please insert your option: \n1. Add Account \n2. Add Accounts List \n3. Remove Account " +
                    "\n4. Update Account \n5. Display Account \n6. Deposit to Account \n7. Withdraw from Account \n8. Transfer Fund to Account \n9. Exit");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    b.add();
                    break;

                case 2:
                    b.addList();
                    break;

                case 3:
                    b.remove();
                    break;

                case 4:
                    b.update();
                    break;

                case 5:
                    b.display();
                    break;

                case 6:
                    b.deposit();
                    break;

                case 7:
                    b.withdraw();
                    break;

                case 8:
                    b.transfer();
                    break;

                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }while(option != 0);
    }

    private void add() throws SQLException {
        AccountDetails a;
        a = accountDetails.readAccountDetails();
        boolean success = accountOperationsImp.addAccount(a);
        if(success)
            System.out.println("Account Successfully added! ");
    }

    private void addList() throws SQLException, ClassNotFoundException {
        accountOperationsImp.addAccounts();
    }

    private  void remove() throws SQLException {
        System.out.println("Enter the account Number of the account you want to delete: ");
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        if(accountOperationsImp.removeAccount(a))
            System.out.println("Account deleted is: "+ a);
        else
            System.out.println("Account Number does not Exist!");
    }

    private  void update() throws SQLException{
        AccountDetails a;
        a = accountDetails.readAccountDetails();
        boolean success = accountOperationsImp.updateAccount(a);
        if(success)
            System.out.println("Account updated Successfully!!!");
        else
            System.out.println("Account Number does not Exist!");
    }

    private  void display() throws  SQLException{
        accountOperationsImp.displayAll();
    }

    private  void deposit() throws InvalidTransactionException, BusinessException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Account Number: ");
        int id = s.nextInt();
        System.out.println(("Enter Amount to Deposit: "));
        double amount = s.nextDouble();
        if(bankingOperationsImp.deposit(id, amount)){
            System.out.println("Money deposited Successfully!");
        }
        else
            System.out.println("Deposit Aborted! Please try again!");

    }


    private  void withdraw() throws InvalidTransactionException, BusinessException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Account Number: ");
        int id = s.nextInt();
        System.out.println(("Enter Amount to Deposit: "));
        double amount = s.nextDouble();
        if(bankingOperationsImp.withdraw(id, amount)){
            System.out.println("Money withdrew Successfully!");
        }
        else
            System.out.println("Withdraw Aborted! Please try again!");
    }

    private  void transfer() throws InvalidTransactionException, BusinessException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Account Number FROM which you want to Transfer: ");
        int idF = s.nextInt();
        System.out.println("Enter Account Number TO which you want to Transfer: ");
        int idT = s.nextInt();
        System.out.println(("Enter Amount to Transfer: "));
        double amount = s.nextDouble();
        if(bankingOperationsImp.transferFund(idF, idT, amount)){
            System.out.println("Money Transferred Successfully!");
        }
        else
            System.out.println("transfer Fund Aborted! Please try again!");



    }







}
