/**
 * Created by emperor on 12/29/16.
 */
public interface BankingOperations {

    boolean deposit(int accountId, double amount) throws InvalidTransactionException, BusinessException;
    boolean withdraw(int accountId, double amount) throws BusinessException, InvalidTransactionException;
    boolean transferFund(int fromAccountId, int toAccountId, double amount) throws BusinessException, InvalidTransactionException;
}
