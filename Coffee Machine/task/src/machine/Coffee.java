package machine;

enum Coffee {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    private final int requiredWater;
    private final int requiredMilk;
    private final int requiredCoffeeBeans;
    private final int price;

    Coffee(int requiredWater, int requiredMilk, int requiredCoffeeBeans, int price) {
        this.requiredWater = requiredWater;
        this.requiredMilk = requiredMilk;
        this.requiredCoffeeBeans = requiredCoffeeBeans;
        this.price = price;
    }

    public int getRequiredWater() {
        return requiredWater;
    }

    public int getRequiredMilk() {
        return requiredMilk;
    }

    public int getRequiredCoffeeBeans() {
        return requiredCoffeeBeans;
    }

    public int getPrice() {
        return price;
    }

}