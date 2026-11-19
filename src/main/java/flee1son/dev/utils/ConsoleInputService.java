package flee1son.dev.utils;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleInputService {

    private final Scanner scanner;

    public ConsoleInputService(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getIntFromUser(String message){
        System.out.print(message);
        int value = scanner.nextInt();
        scanner.nextLine();

        return value;
    }

    public double getDoubleFromUser(String message){
        System.out.print(message);
        double value = scanner.nextDouble();
        scanner.nextLine();

        return value;
    }

    public String getStringFromUser(String message){
        System.out.print(message);
        String value = scanner.nextLine().trim();

        return value;
    }


}
