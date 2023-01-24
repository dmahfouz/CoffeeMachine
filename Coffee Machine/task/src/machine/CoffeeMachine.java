package machine;

import java.sql.SQLOutput;
import java.util.Scanner;
import machine.Coffee.*;


public class CoffeeMachine {

    private static int money = 550;
    private static int water = 400;
    private static int milk = 540;
    private static int coffeeBeans = 120;
    private static int disposableCups = 9;


    public static void main(String[] args) {
        promptUser();

    }

    private static void promptUser() {
        String action = "";
        Scanner scanner = new Scanner(System.in);

        while (!action.equals("exit")) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.next();
            run(action);
        }

        scanner.close();
    }

    private static void run(String action) {
        switch (action) {
            case "buy" -> buy();
            case "fill" -> fill();
            case "take" -> take();
            case "remaining" -> showInventory();
            case "exit" -> {
                return;
            }
            default -> System.out.printf("Invalid option: %s. Please try again.%n", action);
        }
    }

    private static void buy() {
        String selection = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
            What do you want to buy: \
            1 - espresso, \
            2 - latte, \
            3 - cappuccino, \
            back - to main menu:""");

        selection = scanner.next();

        switch (selection) {
            case "1" -> makeEspresso();
            case "2" -> makeLatte();
            case "3" -> makeCappuccino();
            case "back" -> {
                // go back to previous menu
                return;
            }
            default -> {
                // if invalid choice is given, re-run buy menu
                System.out.printf("Invalid selection: %s. Please try again.%n", selection);
                buy();
            }
        }
    }

    private static void makeTransaction(int price, int... qty) {
        System.out.println("I have enough resources, making you a coffee!");
        money += price;
        disposableCups--;
        water -= qty[0];
        milk -= qty[1];
        coffeeBeans -= qty[2];
    }

    private static boolean checkResources(int... qty) {
        return disposableCups > 0 && water >= qty[0] && milk >= qty[1] && coffeeBeans >= qty[2];
    }

    private static void showInsufficientResources(int... qty) {
        String sb =
                (disposableCups == 0 ? "disposable cups, " : "") +
                (water < qty[0] ? "water, " : "") +
                (milk < qty[1] ? "milk, " : "") +
                (coffeeBeans < qty[2] ? "coffee beans, " : "");

        String insufficientResources = sb
                .trim()
                .replaceAll(",$", "")
                .replaceAll(",$", " or ");

        System.out.printf("Sorry, not enough %s!%n", insufficientResources);
    }

    private static void showInsufficientResources(Coffee coffee) {
        String sb = (disposableCups == 0 ? "disposable cups, " : "") +
                (water < coffee.getRequiredWater() ? "water, " : "") +
                (milk < coffee.getRequiredMilk() ? "milk, " : "") +
                (coffeeBeans < coffee.getRequiredCoffeeBeans() ? "coffee beans, " : "");

        String insufficientResources = sb
                .trim()
                .replaceAll(",$", "")
                .replaceAll(",$", " or ");

        System.out.printf("Sorry, not enough %s!%n", insufficientResources);

    }

    private static void makeCoffee(Coffee coffee) {
        if(checkResources(coffee)) {
            makeTransaction(coffee);
        } else {
            showInsufficientResources(coffee);
        }

    }

    private static void makeTransaction(Coffee coffee) {
        System.out.println("I have enough resources, making you a coffee!");
        disposableCups--;
        money += coffee.getPrice();
        water -= coffee.getRequiredWater();
        milk -= coffee.getRequiredMilk();
        coffeeBeans -= coffee.getRequiredCoffeeBeans();
    }


    private static boolean checkResources(Coffee coffee) {
        return disposableCups > 0 &&
                water >= coffee.getRequiredWater() &&
                milk >= coffee.getRequiredMilk() &&
                coffeeBeans >= coffee.getRequiredCoffeeBeans();
    }

    private static void makeEspresso() {
        makeCoffee(Coffee.ESPRESSO);
    }

    private static void makeLatte() {
        makeCoffee(Coffee.LATTE);
    }

    private static void makeCappuccino() {
        makeCoffee(Coffee.CAPPUCCINO);
    }

    private static void fill() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeans += scanner.nextInt();

        System.out.println("Write how many disposable cups you want to add:");
        disposableCups += scanner.nextInt();

        scanner.close();

    }

    private static void take() {
        int withdrawn = money;
        money = 0;
        System.out.printf("I gave you $%d\n\n", withdrawn);

    }

    private static void showInventory() {
        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money%n%n""",
                water, milk, coffeeBeans, disposableCups, money);

    }

}
