package main.java.ru.inforion.trash;

import java.util.Scanner;


public class CryptoController {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CryptoModel cryptoModel;
        String input;

        System.out.println("Исходный текст: ");
        input = sc.nextLine().toLowerCase();
        cryptoModel = new CryptoModel(input);

        while (true) {
            input = sc.nextLine();
            if (input.equals("--")) {
                System.out.println("Откат");
                System.out.println(cryptoModel.revert());
            } else if (input.length() == 3 && input.charAt(1) == ' ') {
                System.out.println(String.format("Замена %s на %s", input.charAt(0), input.charAt(2)));
                System.out.println(cryptoModel.decrypt(input.charAt(0), input.toUpperCase().charAt(2)));
            } else if (input.equals("stop")) {
                System.out.println(cryptoModel.stop());
                System.exit(0);
            } else {
                System.out.println("Ошибка I/O, попробуй еще раз");
            }
        }
    }
}
