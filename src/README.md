
# Sistema de Cuentas Bancarias

Este proyecto implementa un sistema básico de cuentas bancarias utilizando los principios de la Programación Orientada a Objetos (POO) en Java. El sistema incluye operaciones como depósitos, retiros, y la aplicación de intereses, además de demostrar conceptos clave como herencia, polimorfismo y encapsulación.

## Características

- **Depósitos y Retiros**: Permite realizar depósitos y retiros de dinero en cuentas bancarias.
- **Aplicación de Intereses**: Las cuentas de ahorros (`SavingsAccount`) pueden aplicar una tasa de interés al balance.
- **Polimorfismo**: Demuestra cómo el polimorfismo permite que una subclase sobrescriba métodos de la clase base.
- **Encapsulación**: Protege los datos sensibles utilizando modificadores de acceso y métodos públicos.
- **Interfaz `BankServices`**: Define los métodos abstractos para las operaciones bancarias comunes.

## Estructura del Proyecto

El proyecto consta de las siguientes clases e interfaz:

- **`BankServices`**: Interfaz que define los métodos abstractos para operaciones bancarias.
- **`BankAccount`**: Clase base que implementa la interfaz `BankServices` y representa una cuenta bancaria básica.
- **`SavingsAccount`**: Subclase de `BankAccount` que incluye una tasa de interés y sobrescribe el método `withdraw` para garantizar un balance mínimo.
- **`Main`**: Clase con el método `main` que sirve como punto de entrada para ejecutar y probar el sistema.

## Clases e Interfaces

### 1. Interfaz `BankServices`

Define los métodos abstractos que deben implementar las clases que representen servicios bancarios.

```java
import java.math.BigDecimal;

public interface BankServices {
    /**
     * Deposita una cantidad específica en la cuenta.
     *
     * @param amount La cantidad a depositar.
     */
    void deposit(BigDecimal amount);

    /**
     * Retira una cantidad específica de la cuenta.
     *
     * @param amount La cantidad a retirar.
     */
    void withdraw(BigDecimal amount);

    /**
     * Obtiene el balance actual de la cuenta.
     *
     * @return El balance de la cuenta.
     */
    BigDecimal getBalance();
}
```

### 2. Clase `BankAccount`

Implementa la interfaz `BankServices` y representa una cuenta bancaria básica con operaciones de depósito y retiro.

```java
import java.math.BigDecimal;

public class BankAccount implements BankServices {
    private String accountNumber;
    private BigDecimal balance;
    private String accountHolder;

    /**
     * Constructor para BankAccount.
     *
     * @param accountNumber  El número de cuenta.
     * @param balance         El balance inicial de la cuenta.
     * @param accountHolder   El titular de la cuenta.
     */
    public BankAccount(String accountNumber, BigDecimal balance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = balance; // Inicializa el balance con el valor pasado
        this.accountHolder = accountHolder;
    }

    /**
     * Deposita una cantidad específica en la cuenta.
     *
     * @param amount La cantidad a depositar.
     */
    @Override
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) { // Verifica que el monto sea positivo
            this.balance = balance.add(amount);
            System.out.println("Depósito exitoso de: " + amount);
        } else {
            System.out.println("El monto de depósito debe ser positivo.");
        }
    }

    /**
     * Retira una cantidad específica de la cuenta si el balance es suficiente.
     *
     * @param amount La cantidad a retirar.
     */
    @Override
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) { // Verifica que el monto sea positivo
            if (amount.compareTo(this.balance) <= 0) { // Verifica si hay suficiente balance
                this.balance = balance.subtract(amount);
                System.out.println("Retiro exitoso de: " + amount);
            } else {
                System.out.println("Saldo insuficiente");
            }
        } else {
            System.out.println("El monto de retiro debe ser positivo.");
        }
    }

    // Getters y Setters

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Obtiene el balance actual de la cuenta.
     *
     * @return El balance de la cuenta.
     */
    @Override
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
```

### 3. Clase `SavingsAccount`

Hereda de `BankAccount` y añade una tasa de interés. Además, sobrescribe el método `withdraw` para garantizar que el balance no baje de $500.

```java
import java.math.BigDecimal;

public class SavingsAccount extends BankAccount {
    private BigDecimal interestRate;

    /**
     * Constructor para SavingsAccount.
     *
     * @param accountNumber  El número de cuenta.
     * @param balance         El balance inicial de la cuenta.
     * @param accountHolder   El titular de la cuenta.
     * @param interestRate    La tasa de interés.
     */
    public SavingsAccount(String accountNumber, BigDecimal balance, String accountHolder, BigDecimal interestRate) {
        super(accountNumber, balance, accountHolder);
        this.interestRate = interestRate;
    }

    /**
     * Aplica el interés al balance actual de la cuenta.
     */
    public void applyInterest() {
        BigDecimal interest = getBalance().multiply(interestRate); // Calcula el interés
        deposit(interest); // Agrega el interés al balance
        System.out.println("Interés aplicado: " + interest);
    }

    /**
     * Sobrescribe el método withdraw para asegurar que el balance no baje de $500.
     *
     * @param amount La cantidad a retirar.
     */
    @Override
    public void withdraw(BigDecimal amount) {
        BigDecimal minimumBalance = BigDecimal.valueOf(500); // Balance mínimo permitido

        if (getBalance().subtract(amount).compareTo(minimumBalance) >= 0) {
            super.withdraw(amount); // Realiza el retiro si es válido
        } else {
            System.out.println("El retiro no es permitido: el balance no puede bajar de $500.");
        }
    }

    // Getters y Setters

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
```

### 4. Clase `Main`

Clase principal para probar las funcionalidades de las clases `BankAccount` y `SavingsAccount`.

```java
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // Crear un objeto SavingsAccount con un balance inicial de 1000 y una tasa de interés del 5%
        SavingsAccount savingsAccount = new SavingsAccount("123456789", new BigDecimal("1000"), "Carlos Soto", new BigDecimal("0.05"));

        // Imprimir el balance inicial
        System.out.println("Balance inicial: " + savingsAccount.getBalance());

        // Depositar dinero usando el método de BankAccount
        savingsAccount.deposit(new BigDecimal("500"));
        System.out.println("Balance después del depósito: " + savingsAccount.getBalance());

        // Retirar dinero usando el método sobrescrito en SavingsAccount
        savingsAccount.withdraw(new BigDecimal("400"));
        System.out.println("Balance después del retiro de 400: " + savingsAccount.getBalance());

        // Intentar retirar 1200 (debe fallar porque dejaría el balance por debajo de 500)
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
```

## Requisitos

Para ejecutar este proyecto necesitas:

- **Java 8** o superior
- Un IDE o entorno de desarrollo que soporte proyectos Java (Eclipse, IntelliJ IDEA, NetBeans, etc.)
- **Git** para clonar el repositorio (opcional)

## Instalación

1. **Clonar el Repositorio**

   Clona este repositorio en tu máquina local usando Git:

   ```bash
   git clone https://github.com/tu-usuario/sistema-cuentas-bancarias.git
   ```

2. **Abrir el Proyecto**

   Abre el proyecto en tu IDE preferido.

3. **Compilar y Ejecutar**

   Compila y ejecuta la clase `Main.java` para probar las funcionalidades del sistema de cuentas bancarias.

## Uso

El método `main` en la clase `Main` demuestra cómo interactuar con las clases `BankAccount` y `SavingsAccount`. A continuación, se detalla el flujo de operaciones realizadas:

1. **Crear una Cuenta de Ahorros**

   ```java
   SavingsAccount savingsAccount = new SavingsAccount("123456789", new BigDecimal("1000"), "Carlos Soto", new BigDecimal("0.05"));
   ```

    - **Número de Cuenta**: `"123456789"`
    - **Balance Inicial**: `1000`
    - **Titular**: `"Carlos Soto"`
    - **Tasa de Interés**: `5%` (`0.05`)

2. **Depositar Dinero**

   ```java
   savingsAccount.deposit(new BigDecimal("500"));
   ```

   Deposita `500` en la cuenta.

3. **Retirar Dinero**

   ```java
   savingsAccount.withdraw(new BigDecimal("400"));
   ```

   Retira `400` de la cuenta. Como el balance después del retiro es `1100`, la operación es exitosa.

4. **Intentar Retirar una Cantidad Excesiva**

   ```java
   savingsAccount.withdraw(new BigDecimal("1200"));
   ```

   Intenta retirar `1200`, lo cual fallará porque dejaría el balance por debajo de `500`.

5. **Aplicar Interés**

   ```java
   savingsAccount.applyInterest();
   ```

   Aplica el interés del `5%` al balance actual (`1100`), aumentando el balance a `1155`.

6. **Demostrar Polimorfismo**

   ```java
   BankAccount account = savingsAccount;
   account.withdraw(new BigDecimal("100"));
   ```

   Utiliza una referencia de tipo `BankAccount` para llamar al método `withdraw`, demostrando que se ejecuta el método sobrescrito en `SavingsAccount`.

7. **Encapsulación**

   ```java
   // savingsAccount.balance; // Esto causaría un error de compilación porque el campo es privado
   ```

   Intentar acceder directamente al campo `balance` desde fuera de la clase resultará en un error de compilación, demostrando cómo la encapsulación protege los datos.

## Ejemplo de Salida

Al ejecutar la clase `Main`, la salida en la consola será la siguiente:

```
Balance inicial: 1000
Depósito exitoso de: 500
Balance después del depósito: 1500
Retiro exitoso de: 400
Balance después del retiro de 400: 1100
El retiro no es permitido: el balance no puede bajar de $500.
Balance después del intento de retiro de 1200: 1100
Interés aplicado: 55.00
Balance después de aplicar interés: 1155.00
Retiro exitoso de: 100
Balance después del retiro de 100 (usando referencia polimórfica): 1055.00
```

## Conceptos Clave

### Herencia

La clase `SavingsAccount` hereda de `BankAccount`, reutilizando y extendiendo sus funcionalidades. Esto permite que `SavingsAccount` tenga todas las propiedades y métodos de `BankAccount`, además de sus propias características específicas, como la tasa de interés.

### Polimorfismo

El polimorfismo se demuestra cuando una referencia de tipo `BankAccount` apunta a una instancia de `SavingsAccount`. Al llamar al método `withdraw` a través de la referencia de tipo base, se ejecuta el método sobrescrito en la subclase `SavingsAccount`.

```java
BankAccount account = savingsAccount;
account.withdraw(new BigDecimal("100")); // Llama al método sobrescrito en SavingsAccount
```

### Encapsulación

La encapsulación se logra mediante el uso de modificadores de acceso (`private`) en los campos de las clases, lo que impide el acceso directo desde fuera de la clase. En su lugar, se proporcionan métodos públicos (`getters` y `setters`) para interactuar con estos campos de manera controlada.

```java
// Campo privado
private BigDecimal balance;

// Método público para obtener el balance
public BigDecimal getBalance() {
    return balance;
}

// Método público para establecer el balance
public void setBalance(BigDecimal balance) {
    this.balance = balance;
}
```

Esto asegura que cualquier modificación al balance se realice a través de métodos que validan y controlan los cambios, manteniendo la integridad del estado del objeto.



¡Gracias por utilizar y contribuir a este proyecto!



