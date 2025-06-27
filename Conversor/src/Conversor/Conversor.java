package Conversor;

import java.util.Scanner;

public class Conversor {

    // Tasas de cambio con respecto al USD (Курсы валют по отношению к USD)

    static boolean askRepeat(Scanner scanner) {
        String answer;
        do {
            System.out.print("¿Querés realizar otra operación? (si/no): ");
            answer = scanner.nextLine().trim().toLowerCase();
        } while (!answer.equals("si") && !answer.equals("no"));
        return answer.equals("si");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido al conversor de monedas");

        boolean continuar = true;
        do {
            System.out.println("Monedas disponibles: USD, EUR, ARG, RUB, GBP");

            
            System.out.print("Ingresá la moneda de origen: ");
            String myMoney = scanner.nextLine().toUpperCase();

            
            System.out.print("Ingresá la moneda de destino: ");
            String wantChange = scanner.nextLine().toUpperCase();

            
            System.out.print("Ingresá la cantidad a convertir: ");
            double total;
            try {
                total = Double.parseDouble(scanner.nextLine());
                if (total <= 0) {
                    System.out.println("Error: el monto debe ser mayor que cero");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Error: valor numérico no válido");
                continue;
            }
            // Si la moneda es la misma (Ввод одинаковой валюты)
            if (myMoney.equals(wantChange)) {
                System.out.printf("Resultado: %.2f %s\n", total, wantChange);
                continuar = askRepeat(scanner);
                continue;
            }

            // Conversión a USD (Перевод в USD)
            double totalInUSD = 0;
            switch (myMoney) {
                case "USD" -> totalInUSD = total;
                case "EUR" -> totalInUSD = total / 0.86;
                case "ARG" -> totalInUSD = total / 1190.0;
                case "RUB" -> totalInUSD = total / 80.0;
                case "GBP" -> totalInUSD = total / 0.735;
                default -> {
                    System.out.println("Error: moneda de origen no válida");
                    continue;
                }
            }

            // Conversión de USD a la moneda deseada (Перевод из USD в нужную валюту)
            double result = 0;
            switch (wantChange) {
                case "USD" -> result = totalInUSD;
                case "EUR" -> result = totalInUSD * 0.90;
                case "ARG" -> result = totalInUSD * 1100.0;
                case "RUB" -> result = totalInUSD * 89.0;
                case "GBP" -> result = totalInUSD * 0.735;
                default -> {
                    System.out.println("Error: moneda de destino no válida");
                    continue;
                }
            }

            System.out.printf("Resultado: %.2f %s\n", result, wantChange);

            continuar = askRepeat(scanner);
        } while (continuar);

        System.out.println("¡Gracias por usar el conversor de monedas!");
    }
}

