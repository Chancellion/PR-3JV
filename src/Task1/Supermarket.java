package Task1;
class Cashier implements Runnable {
    private String cashierName;

    public Cashier(String cashierName) {
        this.cashierName = cashierName;
    }

    @Override
    public void run() {
        System.out.println(cashierName + " починає обслуговування клiєнта.");
        try {
            Thread.sleep((int) (Math.random() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cashierName + " закiнчує обслуговування клsєнта.");
    }
}

public class Supermarket {
    public static void main(String[] args) {
        Thread cashier1 = new Thread(new Cashier("Каса 1"));
        Thread cashier2 = new Thread(new Cashier("Каса 2"));
        Thread cashier3 = new Thread(new Cashier("Каса 3"));

        cashier1.start();
        cashier2.start();
        cashier3.start();
    }
}
