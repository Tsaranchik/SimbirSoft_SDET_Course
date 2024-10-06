package lesson_1;

abstract class WarehouseItem {
    public final String itemID;
    protected int quantity;
    private static int totalItems = 0;

    public WarehouseItem(String itemID, int quantity) {
        this.itemID = itemID;
        this.quantity = quantity;
        totalItems += quantity;
    }

    public abstract void replenish(int amount);
    public abstract void shipItem(int amount);

    public static int getTotalItems() {
        return totalItems;
    }

    protected static void updateTotalItems(int amount) {
        totalItems += amount;
    }
}
