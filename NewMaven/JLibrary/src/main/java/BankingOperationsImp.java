import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by emperor on 12/29/16.
 */
public class BankingOperationsImp implements BankingOperations {

    AccountOperationsImp accountOperationsImp = new AccountOperationsImp();


    Connection connection;
    private int accountId;
    private double amount;

    public BankingOperationsImp() throws SQLException, ClassNotFoundException {
        connection = accountOperationsImp.connectDatabase();
    }

    private AccountDetails findAccount(int accountId) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("Select * from sys.account where id = ?");
        preparedStatement.setInt(1,accountId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            AccountDetails a = new AccountDetails(resultSet.getInt(1), resultSet.getString(2) ,resultSet.getDouble(3));
            return a;
        }
        return null;
    }

    @Override
    public boolean deposit(int accountId, double amount) throws InvalidTransactionException, BusinessException {

        try {
            connection.setAutoCommit(false);
            AccountDetails a = findAccount(accountId);
            if (a == null) {
                throw new BusinessException("Account ID does not exist: " + accountId);
            }

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE sys.account SET amount = ? where id = ?");
            preparedStatement.setDouble(1,a.getAmount() + amount);
            preparedStatement.setInt(2,accountId);

            int j = preparedStatement.executeUpdate();
            if(j > 0) {
                connection.commit();
                return true;
            }
            else {
                connection.rollback();
                return false;
            }
        }catch (SQLException s){
            throw new InvalidTransactionException("Deposit aborted" , s);
        }

    }

    @Override
    public boolean withdraw(int accountId, double amount) throws BusinessException, InvalidTransactionException {
        try {
            connection.setAutoCommit(false);
            AccountDetails a = findAccount(accountId);
            if (a == null) {
                throw new BusinessException("Account ID does not exist: " + accountId);
            }

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE sys.account SET amount = ? where id = ?");
            preparedStatement.setDouble(1,a.getAmount() - amount);
            preparedStatement.setInt(2,accountId);

            int j = preparedStatement.executeUpdate();
            if(j > 0) {
                connection.commit();
                return true;
            }
            else {
                connection.rollback();
                return false;
            }
        }catch (SQLException s){
            throw new InvalidTransactionException("Withdraw aborted" , s);
        }
    }

    @Override
    public boolean transferFund(int fromAccountId, int toAccountId, double amount) throws BusinessException, InvalidTransactionException {
        try {
            connection.setAutoCommit(false);
            AccountDetails aFrom = findAccount(fromAccountId);
            AccountDetails aTo = findAccount(toAccountId);
            if (aFrom == null || aTo == null) {
                throw new BusinessException("Account ID does not exist: " + accountId);
            }

            boolean withdraw = withdraw(fromAccountId, amount);
            boolean deposit = deposit(toAccountId,amount);

            if(withdraw == true && deposit == true) {
                connection.commit();
                return true;
            }
            else {
                connection.rollback();
                return false;
            }
        }catch (SQLException s){
            throw new InvalidTransactionException("Transfer Fund aborted" , s);
        }
    }


}
