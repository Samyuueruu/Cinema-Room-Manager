package cinema;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ScreenRoom {
    private int rows;
    private int cols;
    private char[][] seats;
    private int numOfPurchasedTickets = 0;
    protected final int numOfSeats;
    private static final int TICKET_PRICE_FIRST_HALF = 10;
    private static final int TICKET_PRICE_SECOND_HALF = 8;

    private void initSeats(){
        for (char[] row:seats) {
            Arrays.fill(row, 'S');
        }
    }

    public ScreenRoom() {
        setRoomSize();
        this.seats = new char[rows][cols];
        this.numOfSeats = rows * cols;
        this.initSeats();
    }

    public int getTicketPrice(int row){
        if (rows * cols <= 60 || row <= rows / 2) {
            return TICKET_PRICE_FIRST_HALF;
        }
        return TICKET_PRICE_SECOND_HALF;
    }

    public static int getTicketPrice(int rows, int cols, int row){
        if (rows * cols <= 60 || row < rows / 2) {
            return TICKET_PRICE_FIRST_HALF;
        }
        return TICKET_PRICE_SECOND_HALF;
    }

    public int calculatePossibleIncome(){
        if (rows * cols <= 60) {
            return TICKET_PRICE_FIRST_HALF * rows * cols;
        }
        return rows / 2 * cols * TICKET_PRICE_FIRST_HALF + (rows - rows / 2) * cols * TICKET_PRICE_SECOND_HALF; // rows - rows / 2 is equal second half of an odd number
    }

    public int calculateCurrentIncome(){
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.seats[i][j] == 'B') {
                    sum += getTicketPrice(i + 1);
                }
            }
        }
        return sum;
    }

    public void selectASeat(){
        Scanner scanner = new Scanner(System.in);
        int row, seat;

        System.out.println("Enter a row number:");
        row = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        seat = scanner.nextInt();
        System.out.println();

        if (row > this.rows || seat > this.cols) {
            System.out.println("Wrong input!\n");
            selectASeat();
            return;
        } else if (this.seats[row-1][seat-1] == 'B') {
            System.out.println("That ticket has already been purchased!\n");
            selectASeat();
            return;
        }

        System.out.println("Ticket price: $" + getTicketPrice(row));
        System.out.println();
        this.seats[row-1][seat-1] = 'B';
        this.numOfPurchasedTickets++;
        printSeats();
    }

    private void setRoomSize(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        this.rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        this.cols = scanner.nextInt();
        System.out.println();
    }

    public void printSeats(){
        System.out.println("Cinema:");
        System.out.print(" ");

        for (int i = 0; i < cols; i++){
            System.out.print(" " + (i+1));
        }

        System.out.println();

        for (int i = 0; i < rows; i++){
            System.out.print(i+1);
            for (int j = 0; j < cols; j++){
                System.out.print(" " + seats[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getNumOfPurchasedTickets() {
        return numOfPurchasedTickets;
    }

    public float getPercentage(){
        return (float) numOfPurchasedTickets / ((float) numOfSeats / 100);
    }
}
