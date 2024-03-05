package bank;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    @Test
    @DisplayName("El metodo withdraw debe devolver false si el amount es mayor que el balance")
    public void withdraw_amountGreaterThanBalance_returnFalse(){
        BankAccount ba = new BankAccount(100);
        assertFalse(ba.withdraw(150));
    }

    @Test
    @DisplayName("El metodo withdraw debe devolver true si el amount es menor que el balance")
    public void withdraw_amountLowerThanBalance_returnTrue(){
        BankAccount ba = new BankAccount(100);
        assertTrue(ba.withdraw(50));
    }

    @Test
    @DisplayName("El metodo deposit debe tener un amount positivo")
    public void deposit_amountNegative_throwException(){
        BankAccount ba = new BankAccount(100);
        assertThrows(IllegalArgumentException.class, () -> ba.deposit(-1));
    }

    @Test
    @DisplayName("El metodo deposit debe depositar correctamente el dinero")
    public void deposit_amountPositive_returnTrue(){
        BankAccount ba = new BankAccount(100);
        int bal = ba.getBalance();
        ba.deposit(10);
        assertEquals(ba.getBalance(),bal+10);
    }

    @Test
    @DisplayName("El metodo payment debe calcular correctamente el pago")
    public void payment_amountInterestNpaymentsPositive_returnTrue(){
        BankAccount ba = new BankAccount(100);
        int amount = 15;
        int npayments = 10;
        double interest = 0.07;
        assertEquals(ba.payment(amount, interest, npayments), amount*(interest*Math.pow((1+interest), npayments)/(Math.pow((1+interest), npayments)-1)));
    }

    @Test
    @DisplayName("El metodo pending debe devolver el amount si month es igual a 0")
    public void pending_monthEqualsZero_returnAmount(){
        BankAccount ba = new BankAccount(100);
        int amount = 10;
        assertEquals(ba.pending(10, 0, 0, 0),amount);
    }

    @Test
    @DisplayName("El metodo pending debe devolver la cantidad pendiente correctamente")
    public void pending_monthGreaterThanZero_returnPending(){
        BankAccount ba = new BankAccount(100);
        double amount = 10;
        double inte = 0.07;
        int npayments = 12;
        int month = 1;
        double ant = amount - (amount*(inte*Math.pow((1+inte), npayments)/(Math.pow((1+inte), npayments)-1)) - inte*amount);
        assertEquals(ba.pending(amount, inte, npayments, month),ant);
    }
}
