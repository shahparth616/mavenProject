import java.sql.SQLException;

/**
 * Created by emperor on 12/29/16.
 */
public interface AccountOperations {

    boolean addAccount(AccountDetails accountDetails) throws SQLException;
    void addAccounts() throws SQLException, ClassNotFoundException;
    boolean removeAccount(int accountNumber) throws SQLException;
    boolean updateAccount(AccountDetails accountDetails) throws SQLException;
    void displayAll() throws SQLException;



}
