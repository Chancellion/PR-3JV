package Task2;

class Warehouse {
    private int items;

    public Warehouse(int initialItems) {
        this.items = initialItems;
    }

    public synchronized void addItem() {
        items++;
        System.out.println(Thread.currentThread().getName() + " додав товар. Кiлькiсть товарiв: " + items);
    }

    public synchronized void removeItem() {
        if (items > 0) {
            items--;
            System.out.println(Thread.currentThread().getName() + " видалив товар. Кiлькiсть товарiв: " + items);
        } else {
            System.out.println(Thread.currentThread().getName() + " не може видалити товар. Склад порожнiй.");
        }
    }

    public int getItems() {
        return items;
    }
}

class Cashier implements Runnable {
    private Warehouse warehouse;

    public Cashier(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " починає обслуговування клiєнта.");
        try {
            // Симуляція часу обслуговування клієнта
            Thread.sleep((int) (Math.random() * 5000));
            warehouse.addItem();
            Thread.sleep((int) (Math.random() * 5000));
            warehouse.removeItem();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " закiнчує обслуговування клiєнта.");
    }
}

public class Supermarket {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse(10);

        Thread cashier1 = new Thread(new Cashier(warehouse), "Каса 1");
        Thread cashier2 = new Thread(new Cashier(warehouse), "Каса 2");
        Thread cashier3 = new Thread(new Cashier(warehouse), "Каса 3");

        cashier1.start();
        cashier2.start();
        cashier3.start();
    }
}
