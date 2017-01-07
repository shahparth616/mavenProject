import java.sql.*;

/**
 * Created by emperor on 12/29/16.
 */
public class AccountOperationsImp implements AccountOperations {

    Connection connection;

    public AccountOperationsImp() throws SQLException, ClassNotFoundException {
        connection = connectDatabase();
    }

    @Override
    public boolean addAccount(AccountDetails accountDetails) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sys.account (id, name, amount) VALUES (?, ? , ?)");
        preparedStatement.setInt(1, accountDetails.getAccountNumber());
        preparedStatement.setString(2,accountDetails.getAccountHolderName());
        preparedStatement.setDouble(3, accountDetails.getAmount());
        boolean success = preparedStatement.execute();
        return success;
    }

    @Override
    public void addAccounts() throws SQLException, ClassNotFoundException {
        AddMultiAcounts a = new AddMultiAcounts();
        a.addAccounts();
    }

    @Override
    public boolean removeAccount(int accountNumber) throws SQLException {
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate("DELETE from sys.account where id = " + accountNumber);
        if(result > 0)
            return true;

        return false;
    }

    @Override
    public boolean updateAccount(AccountDetails accountDetails) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("update sys.account set name = ? , amount = ? where id = ?");

        preparedStatement.setString(1,accountDetails.getAccountHolderName());
        preparedStatement.setDouble(2,accountDetails.getAmount());
        preparedStatement.setInt(3,accountDetails.getAccountNumber());
        int success = preparedStatement.executeUpdate();

        if(success>0)
        return true;

        return false;
    }

    @Override
    public void displayAll() throws SQLException
    {
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from sys.account");
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1)+ " " +
            resultSet.getString(2) + " " +
            resultSet.getDouble(3));
        }

    }

    public Connection connectDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys","root","root");

        return connection;

    }
}
