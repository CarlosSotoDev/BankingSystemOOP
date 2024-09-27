import java.math.BigDecimal;

public class SavingsAccount extends BankAccount {
    private BigDecimal insterestRate;


    public SavingsAccount(String accountNumber, BigDecimal balance, String accountHolder, BigDecimal interestRate) {
        super(accountNumber, balance, accountHolder);
        this.insterestRate = interestRate;
    }

    //Method
    //Apply Interest Method
    public void applyInterest() {
        BigDecimal interest = getBalance().multiply(insterestRate);
        deposit(interest);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        BigDecimal minimumBalance = BigDecimal.valueOf(500);

        if(getBalance().subtract(amount).compareTo(minimumBalance) >= 0) {
            super.withdraw(amount);
        }else{
            System.out.println("El retiro no es permitido: el balance no puede bajar de $500.");
        }
    }

    //GettersSetters
    public BigDecimal getInterestRate() {
        return insterestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.insterestRate = interestRate;
    }
}
