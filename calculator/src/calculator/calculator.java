package calculator;

import java.util.Scanner;

public class calculator {

    // Búsqueda de la posición correcta del operador (teniendo en cuenta el signo del primer número) (Поиск корректной позиции оператора (с учётом знака у первого числа))
    static int findOperatorPosition(String input) {
    	int start;

    	if (input.charAt(0) == '-') {
    	    start = 1;
    	} else {
    	    start = 0;
    	}

        for (int i = start; i < input.length(); i++) {
            char c = input.charAt(i);
            if ("+-*/".indexOf(c) != -1) {
                String left = input.substring(0, i);
                String right = input.substring(i + 1);
                try {
                    Double.parseDouble(left);
                    Double.parseDouble(right);
                    return i;  // Operador encontrado, ambas partes son números (оператор найден, обе части — числа)
                } catch (Exception e) {
                    return -1; // Números no válidos (невалидные числа)
                }
            }
        }

        return -1; // Operador no encontrado (оператор не найден)
    }

    // Verificación de que hay exactamente un operador (después de un posible signo menos) (Проверка, что есть ровно один оператор (после возможного минуса))
    static boolean findOnlyOneOperator(String input) {
        int count = 0;
        int start;

    	if (input.charAt(0) == '-') {
    	    start = 1;
    	} else {
    	    start = 0;
    	}

        for (int i = start; i < input.length(); i++) {
            if ("+-*/".indexOf(input.charAt(i)) != -1) {
                count++;
            }
        }

        return count == 1;
    }

    // Extraemos dos números de la expresión según la posición del operador (Извлекаем два числа из выражения по позиции оператора)
    static double[] findNumbers(String input, int operatorPosition) {
        try {
            String left = input.substring(0, operatorPosition);
            String right = input.substring(operatorPosition + 1);
            double num1 = Double.parseDouble(left);
            double num2 = Double.parseDouble(right);
            return new double[]{num1, num2};
        } catch (Exception e) {
            return null;
        }
    }

    // Calculador
    static Double calculate(double a, double b, char operator) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) {
                    System.out.println("Error: división por cero");
                    return null;
                }
                return a / b;
            default:
                System.out.println("Error: operador desconocido");
                return null;
        }
    }

    // Repetir pregunta
    static boolean askRepeat(Scanner scanner) {
        String response;
        do {
            System.out.print("Querés realizar otra operación? (yes/no): ");
            response = scanner.nextLine().trim().toLowerCase();
        } while (!response.equals("yes") && !response.equals("no"));
        return response.equals("yes");
    }

    // Main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean Calculating = true;

        System.out.println("Bienvenido a la calculadora");

        while (Calculating) {
            System.out.print("\nngresá una expresión (por ejemplo: -2.5*4): ");
            String input = scanner.nextLine().replaceAll("\\s", "");

            if (input.length() < 3) {
                System.out.println("Error: la expresión es demasiado corta");
                continue;
            }
            
            int operatorPosition = findOperatorPosition(input);
            if (operatorPosition == -1 || operatorPosition == input.length() - 1) {
                System.out.println("Error: la expresión no es válida o contiene valores no numéricos");
                continue;
            }
            
            if (!findOnlyOneOperator(input)) {
                System.out.println("Error: debe haber exactamente una operación");
                continue;
            }

            char operator = input.charAt(operatorPosition);
            double[] numbers = findNumbers(input, operatorPosition);

            if (numbers == null) {
                System.out.println("Error: no se pueden convertir los números");
                continue;
            }

            Double result = calculate(numbers[0], numbers[1], operator);
            if (result != null) {
                System.out.printf("Resultado: %.3f\n", result);
            }

            Calculating = askRepeat(scanner);
        }

        System.out.println("\n¡Gracias por usar la calculadora!");
    }
}
