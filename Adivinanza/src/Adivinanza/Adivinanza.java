package Adivinanza;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Random;

public class Adivinanza {

    public static void main(String[] args) {
        Random random = new Random();

        try (Scanner sc = new Scanner(System.in)) {
            String[] adivinanzas = {
                "¿Cuál es el país más grande del mundo por superficie?",
                "¿Quién dijo que fue la 'mano de Dios'?",
                "¿En qué lenguaje de programación está escrito este código?",
                "¿Cuánto es 2 + 2 * 2?",
                "¿Cómo te llamás?"
            };

            String[] answers = {
                "rusia",
                "maradona",
                "java",
                "6",
                "ainur"
            };

            int score = 0;
            boolean continueGame = true;
            HashSet<Integer> usedIndexes = new HashSet<>();

            System.out.println("¡Bienvenido al juego de adivinanzas!");

            while (continueGame) {
                if (usedIndexes.size() == adivinanzas.length) {
                    System.out.println("¡Se acabaron las adivinanzas!");
                    break;
                }
                int index;
                do {
                    index = random.nextInt(adivinanzas.length);
                } while (usedIndexes.contains(index));
                
                String answer;
                
                do {
                	System.out.println("\nAdivinanza:");
                	System.out.println(adivinanzas[index]);

                	System.out.print("Tu respuesta: ");
                	answer = sc.nextLine().trim();

                	if (answer.isEmpty()) {
                		System.out.println("Respuesta vacía. Intentalo de nuevo");
                	}
                } while (answer.isEmpty());
                usedIndexes.add(index);
                
                if (
                    answer.equalsIgnoreCase(answers[index]) ||
                    (index == 3 && answer.equalsIgnoreCase("seis"))
                ) {
                    System.out.println("¡Correcto!");
                    score++;
                } else {
                    System.out.println("Incorrecto. La respuesta correcta era: " + answers[index]);
                }

                System.out.println("Puntos actuales: " + score);

                if (usedIndexes.size() < adivinanzas.length) {
                    System.out.print("¿Querés jugar otra ronda? (si/no): ");
                    String resp = sc.nextLine().trim().toLowerCase();
                    continueGame = resp.equals("si");
                } else {
                	continueGame = false;
                }
            }

            System.out.println("\nPuntaje final: " + score);
            System.out.println("¡Gracias por jugar!");
        }
    }
}
