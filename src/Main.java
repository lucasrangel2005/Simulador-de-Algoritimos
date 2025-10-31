import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Simulador de Algoritmos de Substituição de Páginas ===");
        System.out.println("Informe a sequência de páginas separadas por espaço (ex: 7 0 1 2 0 3 0 4 2 3 0 3 2):");
        String linha = sc.nextLine().trim();

        int[] referencias;
        if (linha.isEmpty()) {
            referencias = new int[]{7,0,1,2,0,3,0,4,2,3,0,3,2};
            System.out.println("Nenhuma sequência informada. Usando sequência padrão: " + Arrays.toString(referencias));
        } else {
            String[] partes = linha.split("\\s+");
            referencias = new int[partes.length];
            for (int i = 0; i < partes.length; i++) {
                referencias[i] = Integer.parseInt(partes[i]);
            }
        }

        System.out.println("Informe a quantidade de molduras (quadros) de memória (ex: 3 ou 4):");
        String q = sc.nextLine().trim();
        int quadros;
        if (q.isEmpty()) {
            quadros = 3;
            System.out.println("Nenhum valor informado. Usando 3 quadros.");
        } else {
            quadros = Integer.parseInt(q);
        }

        PageReplacementSimulator simulator = new PageReplacementSimulator(referencias, quadros);

        int fifoFaults = simulator.runFIFO();
        int lruFaults = simulator.runLRU();
        int clockFaults = simulator.runClock();
        int optimalFaults = simulator.runOptimal();

        System.out.println("\n=== RESULTADOS ===");
        System.out.println("Método 1 (FIFO)   - " + fifoFaults + " faltas de página");
        System.out.println("Método 2 (LRU)    - " + lruFaults + " faltas de página");
        System.out.println("Método 3 (Relógio)- " + clockFaults + " faltas de página");
        System.out.println("Método 4 (Ótimo)  - " + optimalFaults + " faltas de página");

        sc.close();
    }
}

