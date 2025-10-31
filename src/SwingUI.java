
import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

public class SwingUI {

    public static void mostrar(int[] referencias, int quadros, int fifo, int lru, int clock, int optimal) {
        JFrame frame = new JFrame("Simulador de Substituição de Páginas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 360);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(0,1,6,6));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        panel.add(new JLabel("Sequência: " + Arrays.toString(referencias)));
        panel.add(new JLabel("Quadros: " + quadros));
        panel.add(new JLabel("Método 1 (FIFO): " + fifo + " faltas de página"));
        panel.add(new JLabel("Método 2 (LRU): " + lru + " faltas de página"));
        panel.add(new JLabel("Método 3 (Relógio): " + clock + " faltas de página"));
        panel.add(new JLabel("Método 4 (Ótimo): " + optimal + " faltas de página"));

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}
