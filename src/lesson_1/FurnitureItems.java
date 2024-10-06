package lesson_1;

import java.util.Random;

public class FurnitureItems extends WarehouseItem implements iTrackable, iShippable {
    private double weight;
    private String dimensions;

    private int x;
    private int y;
    private int angle = 0;
    private static Random random = new Random();

    private static final int DISTANCE_TO_CLIENT = random.nextInt(50);
    private static final double BASE_DELIVERY_RATE = 5.5;

    public FurnitureItems(String itemID, int quantity, double weight, String dimensions) {
        super(itemID, quantity);
        this.weight = weight;
        this.dimensions = dimensions;

        this.x = random.nextInt(100);
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
    public double calculateShippingCost(int amount) {
        double totalCost = 0.0;

        for (int i = 1; i < amount; ++i) {
            double deliveryCostForItem = (weight * BASE_DELIVERY_RATE) * DISTANCE_TO_CLIENT;
            totalCost += deliveryCostForItem;

            // Скидка
            if (i % 3 == 0) {
                totalCost -= deliveryCostForItem * 0.1;
            }
        }

        return totalCost;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void move() {
        angle = (angle + 10) % 360;
        x = (int) (40 + 15 * Math.cos(Math.toRadians(angle)));
        y = (int) (40 + 15 * Math.sin(Math.toRadians(angle)));

        x += random.nextInt(3) - 1;
        y += random.nextInt(3) - 1;
        System.out.println("Furniture item moved to new position: (" + getX() + ", " + getY() + ")");
    }

    public double getWeight() {
        return weight;
    }

    public String getDimensions() {
        return dimensions;
    }
}
