package cinema;

import java.util.Scanner;

public class UI {

    private ScreenRoom screenRoom;
    private boolean isRunning = true;

    public UI(ScreenRoom screenRoom) {
        Scanner scanner = new Scanner(System.in);
        this.screenRoom = screenRoom;
        while (isRunning) {
            printMenu();
            onInputAction(scanner.nextInt());
        }
    }

    public void printMenu(){
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
    }

    public void printStatistics(){
        System.out.println("Number of purchased tickets: " + screenRoom.getNumOfPurchasedTickets() + "\n" +
                String.format("Percentage: %.2f%%\n", screenRoom.getPercentage()) +
                "Current income: $" + screenRoom.calculateCurrentIncome() + "\n" +
                "Total income: $" + screenRoom.calculatePossibleIncome() + "\n");
    }

    public void onInputAction(int input){
        System.out.println();
        switch (input){
            case 1:
                this.screenRoom.printSeats();
                break;
            case 2:
                this.screenRoom.selectASeat();
                break;
            case 3:
                printStatistics();
                break;
            case 0:
                this.isRunning = false;
                break;
        }
    }
}
