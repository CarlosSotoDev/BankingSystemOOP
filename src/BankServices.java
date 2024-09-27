import java.math.BigDecimal;

public interface BankServices {
    abstract void deposit(BigDecimal amount);

    abstract void withdraw(BigDecimal amount);

    BigDecimal getBalance();
}
