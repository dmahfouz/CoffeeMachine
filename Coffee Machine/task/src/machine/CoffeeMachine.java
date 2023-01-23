package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private final static int WATER_PER_CUP = 200;
    private final static int MILK_PER_CUP = 50;
    private final static int BEANS_PER_CUP = 15;




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Write how many ml of water the coffee machine has:");
        int water = sc.nextInt();

        System.out.println("Write how many ml of milk the coffee machine has:");
        int milk = sc.nextInt();

        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int coffeeBeans = sc.nextInt();

        System.out.println("Write how many cups of coffee you will need:");
        int cupsOfCoffeeNeeded = sc.nextInt();

        canEnoughCoffeesBeMade(
                water,
                milk,
                coffeeBeans,
                cupsOfCoffeeNeeded
        );


    }

    public static void canEnoughCoffeesBeMade(
            int water,
            int milk,
            int coffeeBeans,
            int numCupsNeeded
    ) {
        int availableCups = calculateAvailableCups(water, milk, coffeeBeans);

        System.out.println(availableCups == numCupsNeeded ?
                "Yes, I can make that amount of coffee" :
                availableCups >= numCupsNeeded ?
                        String.format("Yes, I can make that amount of coffee (and even %d more than that", (availableCups - numCupsNeeded)) :
                        String.format("No, I can only make %d cup(s) of coffee", availableCups)
        );

    }

    public static int calculateAvailableCups(int water, int milk, int coffeeBeans) {

        int numCupsOfWater = water / WATER_PER_CUP;
        int numCupsOfMilk = milk / MILK_PER_CUP;
        int numCupsOfBeans = coffeeBeans / BEANS_PER_CUP;

        return Math.min(numCupsOfWater, Math.min(numCupsOfMilk, numCupsOfBeans));

    }




}
