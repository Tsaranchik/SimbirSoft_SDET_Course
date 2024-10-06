package lesson_1;

import java.util.Random;

public class ElectronicItem extends WarehouseItem implements iTrackable {
    private double powerConsumption;
    private int warrantyYears;

    private int x;
    private int y;
    private int angle = 0;
    private final Random random = new Random();

    public ElectronicItem(String itemID, int quantity, double powerConsumption, int warrantyYears) {
        super(itemID, quantity);
        this.powerConsumption = powerConsumption;
        this.warrantyYears = warrantyYears;

        this.x =  random.nextInt(100);
        this.y = random.nextInt(100);
    }

    @Override
    public void replenish(int amount) {
        if (amount > 0) {
            quantity += amount;
            updateTotalItems(amount);
            System.out.println("Stock replenished by " + amount);
        }
        else {
            System.out.println("Nothing to stock!");
        }
    }

    public void shipItem(int amount) {
        if (amount > 0 && amount <= quantity) {
            quantity -= amount;
            updateTotalItems(-amount);
            System.out.println("Shipped " + amount + " units");
        }
        else {
            System.out.println("Not enough stock!");
        }
    }

    @Override
    public int  getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    // Имитация движения
    @Override
    public void move() {
        angle = (angle + 10) % 360;
        x = (int) (50 + 20 * Math.cos(Math.toRadians(angle)));
        y = (int) (50 + 20 * Math.sin(Math.toRadians(angle)));

        x += random.nextInt(3) - 1;
        y += random.nextInt(3) - 1;
        System.out.println("Electronic item moved to new position: (" + getX() + ", " + getY() + ")");
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }
}
