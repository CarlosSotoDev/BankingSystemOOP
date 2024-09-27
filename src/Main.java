import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // Crear un objeto SavingsAccount
        //Accountnumber, balance, accountholder e interes
        //No creara el balance de 1000 por que la condicion siempre será que sea BigDecimal.ZERO
        SavingsAccount savingsAccount = new SavingsAccount("123456789", new BigDecimal("1000"), "Carlos Soto", new BigDecimal("0.05"));

        //Saber balance inicial
        System.out.println("El balance inicial es de:" + savingsAccount.getBalance());
        // Depositar dinero usando el método de BankAccount
        savingsAccount.deposit(new BigDecimal("500"));
        System.out.println("Balance después del depósito: " + savingsAccount.getBalance());

        // Intentar retirar 400 (balance será mayor a 500)
        savingsAccount.withdraw(new BigDecimal("400"));
        System.out.println("Balance después del retiro de 400: " + savingsAccount.getBalance());

        // Intentar retirar 1200 (balance bajaría de 500, por lo que será rechazado)
        savingsAccount.withdraw(new BigDecimal("1200"));
        System.out.println("Balance después del intento de retiro de 1200: " + savingsAccount.getBalance());

        // Aplicar interés
        savingsAccount.applyInterest();
        System.out.println("Balance después de aplicar interés: " + savingsAccount.getBalance());

        // Demostrar polimorfismo
        // Usando una referencia de tipo BankAccount para SavingsAccount
        BankAccount account = savingsAccount; // Polimorfismo
        account.withdraw(new BigDecimal("100")); // Llama al método withdraw sobrescrito en SavingsAccount
        System.out.println("Balance después del retiro de 100 (usando referencia polimórfica): " + account.getBalance());

        // Intentar acceder al balance directamente desde fuera de la clase
        // savingsAccount.balance; // Esto causaría un error de compilación porque el campo es privado
        // Comentario explicativo:
        // La encapsulación protege el campo "balance" al hacerlo privado, lo que evita que sea accedido o modificado
        // directamente desde fuera de la clase. Esto garantiza que cualquier modificación del balance se realice a
        // través de los métodos controlados (como deposit y withdraw), asegurando que el estado del objeto siempre sea válido.
    }
}
