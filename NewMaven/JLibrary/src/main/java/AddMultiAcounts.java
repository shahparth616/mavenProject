import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emperor on 1/3/17.
 */
public class AddMultiAcounts extends Thread {
    AccountOperationsImp a = new AccountOperationsImp();
    Connection connection;

    public AddMultiAcounts() throws SQLException, ClassNotFoundException {
        connection = a.connectDatabase();

    }


    public void run()
    {
        List<AccountDetails> a = new ArrayList<>();

        for(int i = 0; i<100; i++)
        {
            AccountDetails a1 = new AccountDetails((i*3) + 100,"Pa" +i , 500*i);
            a.add(a1);
        }
        int recordsInserted = 0;
        String query = "insert into sys.account (id, name, amount) values ( ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            for(AccountDetails accountDetails : a)
            {
                preparedStatement.setInt(1, accountDetails.getAccountNumber());
                preparedStatement.setString(2,accountDetails.getAccountHolderName());
                preparedStatement.setDouble(3, accountDetails.getAmount());

                preparedStatement.addBatch();
                int response[] = preparedStatement.executeBatch();

                for(int x : response)
                {
                    recordsInserted += x;
                }
                System.out.println(recordsInserted+ " new accounts are added into the System!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void addAccounts(){
        Thread t = new Thread();
        t.start();
    }


}
