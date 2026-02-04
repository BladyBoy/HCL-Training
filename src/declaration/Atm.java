package declaration;

public abstract class Atm {

    public abstract boolean login(long accountNumber, int pin);
    public abstract void logout();

    public abstract void deposit(int amount);
    public abstract void withdrawal(int amount);
    public abstract void checkBalance();
    public abstract void transactions();
}
