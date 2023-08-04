package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        ScreenRoom screenRoom = new ScreenRoom();
        UI ui = new UI(screenRoom);
    }
}