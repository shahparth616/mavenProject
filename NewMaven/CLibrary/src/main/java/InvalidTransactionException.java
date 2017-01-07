import java.sql.SQLException;

/**
 * Created by emperor on 12/30/16.
 */
public class InvalidTransactionException extends Throwable {

    public InvalidTransactionException(String s, SQLException sql){
        super(s,sql);
    }
}
