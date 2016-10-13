import javax.naming.InsufficientResourcesException;

public class Operations {

    public static void main(String[] args) throws InsufficientResourcesException, InterruptedException {
        final BankAccount a = new BankAccount(1000);
        final BankAccount b = new BankAccount(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    transfer(a, b, 500);
                    System.out.println("Transfer successful");
                } catch (InsufficientResourcesException e) {
                    System.out.println("Transfer Blocked");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        transfer(b, a, 300);
        System.out.println("Transfer successful");
    }

    public static void transfer(BankAccount account1, BankAccount account2, int amount)
            throws InsufficientResourcesException, InterruptedException {
        if (account1.getBalance() < amount)
            throw new InsufficientResourcesException();

        synchronized (account1) {
            Thread.sleep(1000);
            synchronized (account2) {
            }
            account1.withdraw(amount);
            account2.deposit(amount);
        }
    }

}
