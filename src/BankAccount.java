import java.math.BigDecimal;

public class BankAccount implements BankServices{
    private String accountNumber;
    private BigDecimal balance;
    private String accountHolder;

    public BankAccount(String accountNumber, BigDecimal balance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
        this.accountHolder = accountHolder;
    }

    //Methods
    //Deposit Method
    @Override
    public void deposit(BigDecimal amount) {
        this.balance = balance.add(amount);
    }

    //Withdraw Method
    @Override
    public void withdraw(BigDecimal amount) {
        // Verifica si el monto a retirar es menor o igual al balance actual
        if (amount.compareTo(this.balance) <= 0) {
            this.balance = balance.subtract(amount); // Resta el monto del balance
        } else {
            // Muestra un mensaje si no hay suficientes fondos
            System.out.println("Saldo insuficiente");
        }
    }


    //GettersSetters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

}

