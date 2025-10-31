import java.util.*;

public class PageReplacementSimulator {

    private final int[] referencias;
    private final int quadros;

    public PageReplacementSimulator(int[] referencias, int quadros) {
        this.referencias = referencias;
        this.quadros = quadros;
    }

    public int runFIFO() {
        Set<Integer> memoria = new HashSet<>();
        Queue<Integer> fila = new LinkedList<>();
        int faltas = 0;

        for (int pagina : referencias) {
            if (!memoria.contains(pagina)) {
                if (memoria.size() == quadros) {
                    int removida = fila.poll();
                    memoria.remove(removida);
                }
                memoria.add(pagina);
                fila.add(pagina);
                faltas++;
            }
        }
        return faltas;
    }

    public int runLRU() {
        List<Integer> memoria = new ArrayList<>(quadros);
        Map<Integer, Integer> ultimoUso = new HashMap<>();
        int faltas = 0;

        for (int i = 0; i < referencias.length; i++) {
            int pagina = referencias[i];

            if (memoria.contains(pagina)) {
                ultimoUso.put(pagina, i);
            } else {
                if (memoria.size() < quadros) {
                    memoria.add(pagina);
                } else {
                    int lruPagina = encontrarLRU(memoria, ultimoUso);
                    memoria.remove((Integer) lruPagina);
                    ultimoUso.remove(lruPagina);
                    memoria.add(pagina);
                }
                ultimoUso.put(pagina, i);
                faltas++;
            }
        }
        return faltas;
    }

    private int encontrarLRU(List<Integer> memoria, Map<Integer, Integer> ultimoUso) {
        int lruPagina = memoria.get(0);
        int lruTempo = ultimoUso.getOrDefault(lruPagina, -1);

        for (int pagina : memoria) {
            int tempo = ultimoUso.getOrDefault(pagina, -1);
            if (tempo < lruTempo) {
                lruTempo = tempo;
                lruPagina = pagina;
            }
        }
        return lruPagina;
    }

    public int runClock() {
        int faltas = 0;
        int[] molduras = new int[quadros];
        boolean[] bitsUso = new boolean[quadros];
        Arrays.fill(molduras, -1);
        int ponteiro = 0;

        for (int pagina : referencias) {
            boolean hit = false;

            for (int i = 0; i < quadros; i++) {
                if (molduras[i] == pagina) {
                    bitsUso[i] = true;
                    hit = true;
                    break;
                }
            }

            if (!hit) {
                while (true) {
                    if (molduras[ponteiro] == -1) {
                        molduras[ponteiro] = pagina;
                        bitsUso[ponteiro] = true;
                        ponteiro = (ponteiro + 1) % quadros;
                        break;
                    } else if (!bitsUso[ponteiro]) {
                        molduras[ponteiro] = pagina;
                        bitsUso[ponteiro] = true;
                        ponteiro = (ponteiro + 1) % quadros;
                        break;
                    } else {
                        bitsUso[ponteiro] = false;
                        ponteiro = (ponteiro + 1) % quadros;
                    }
                }
                faltas++;
            }
        }

        return faltas;
    }

    public int runOptimal() {
        List<Integer> memoria = new ArrayList<>(quadros);
        int faltas = 0;

        for (int i = 0; i < referencias.length; i++) {
            int pagina = referencias[i];

            if (memoria.contains(pagina)) {
                // hit
            } else {
                if (memoria.size() < quadros) {
                    memoria.add(pagina);
                    faltas++;
                } else {
                    int indiceParaRemover = escolherPaginaParaRemover(memoria, referencias, i + 1);
                    memoria.set(indiceParaRemover, pagina);
                    faltas++;
                }
            }
        }

        return faltas;
    }

    private int escolherPaginaParaRemover(List<Integer> memoria, int[] refs, int inicioBusca) {
        int indiceRemover = -1;
        int maisLonge = -1;

        for (int i = 0; i < memoria.size(); i++) {
            int pagina = memoria.get(i);
            int proximoUso = encontrarProximoUso(pagina, refs, inicioBusca);

            if (proximoUso == -1) {
                return i;
            }

            if (proximoUso > maisLonge) {
                maisLonge = proximoUso;
                indiceRemover = i;
            }
        }

        return indiceRemover;
    }

    private int encontrarProximoUso(int pagina, int[] refs, int inicio) {
        for (int i = inicio; i < refs.length; i++) {
            if (refs[i] == pagina) {
                return i;
            }
        }
        return -1;
    }
}
