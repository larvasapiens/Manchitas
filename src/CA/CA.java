package CA;

import java.awt.GridLayout;
import javax.swing.JPanel;
/**
 * Clase que implementa un Automata Celular, representado como un JPanel
 * que tiene una grilla de Celulas.
 * @author Sebastian Narvaez
 */
public class CA extends JPanel {
    /**
     * Cantidad de individuos del Automata Celular.
     */
    public static final short CANT_INDIVIDUOS = 100;
    /**
     * Cantidad de generaciones que tiene en cuenta el Automata Celular.
     */
    public static final short CANT_TIEMPO = 100;
    /**
     * Cantidad de estados que puede tomar cada Celula de Automata.
     */
    public static final short CANT_ESTADOS = 2;
    private Celula poblacionVsTiempo[][];
    //private boolean[] transformaciones;
    private byte numVecinos;
    
    public CA() {
        //numVecinos = 3;
        poblacionVsTiempo = new Celula[CANT_TIEMPO][CANT_INDIVIDUOS];
        setSize(CANT_INDIVIDUOS * Celula.TAMANO_PX,CANT_INDIVIDUOS * Celula.TAMANO_PX);
        setLayout(new GridLayout(100,100));
        inicializarGrilla();
    }
    
    /**
     * Inicializa el Automata Celular con todas las celulas muertas.
     */
    private void inicializarGrilla() {
        for(short i = 0; i < CANT_TIEMPO; i++) {
            for(short j = 0; j < CANT_INDIVIDUOS; j++) {
                poblacionVsTiempo[i][j] = new Celula();
                if(i != 0) {
                    poblacionVsTiempo[i][j].setEnabled(false);
                }
                add(poblacionVsTiempo[i][j]);
            }
        }
    }
    
    /**
     * Genera de manera pseudoaleatoria una poblacion inicial de celulas.
     */
    public void poblacionAleatoria() {
        for(byte i = 0; i < CANT_INDIVIDUOS; i++) {
            byte desicion = (byte)Math.floor((Math.random() * CANT_ESTADOS));
            poblacionVsTiempo[0][i].setViva(desicion == 1);
        }
    }
    
    
    public void resetPoblacion() {
        for (short i = 0; i < CANT_INDIVIDUOS; i++) {
            poblacionVsTiempo[0][i].setViva(false);
        }
    }

    /**
     * Comunica a las celulas el estado de sus vecinas para que
     * averiguen su proximo estado. (Se asume una frontera fria).
     */     
    public void procesarAutomataFrio() {
        byte vecinosLaterales = (byte)Math.floor(numVecinos/2);
        boolean[] vecindarioActual = new boolean[numVecinos];
        //System.out.println("Se tienen " + vecinosLaterales + " vecinos laterales");
        for(byte i = 1; i < CANT_TIEMPO; i++) {
            for(byte j = 0; j < CANT_INDIVIDUOS; j++) {
                //System.out.println("Para el Tiempo " + i + ", Individuo " + j + ":  ");
                byte indexVecino = 0;
                for(byte k = (byte)(j - vecinosLaterales); k <= (j + vecinosLaterales); k++) {
                    if(k >= 0 && k < CANT_INDIVIDUOS) {
                        vecindarioActual[indexVecino] = poblacionVsTiempo[i-1][k].isViva();
                        //System.out.println("\tVecino " + k + ": " + poblacionVsTiempo[i-1][k].isViva());
                    } else {
                        //System.out.println("\tVecino de la frontera");
                        vecindarioActual[indexVecino] = false;
                    }
                    indexVecino++;
                }
                poblacionVsTiempo[i][j].setViva(poblacionVsTiempo[i-1][j].siguienteEstado(vecindarioActual));
                //System.out.println("Por lo tanto, el resultado es: " + poblacionVsTiempo[i][j].getEstado() + "(Pos " + transformacion + " del arreglo)\n");
            }
        }
    }
    
    /**
     * Cambia el tamanio del vecindario con el que se va a trabajar
     * @param numVecinos 
     */
    public void setNumVecinos(byte numVecinos) {
        this.numVecinos = numVecinos;
    }
    
    /**
     * Cambia la regla actual bajo la que se rige el Automata Celular. Recuerde
     * que la regla no puede ser mayor a la cantidad de Estados elevado a la
     * cantidad de Vecinadarios posibles. 
     * @param regla La nueva regla.
     * @return true si el cambio fue hecho satisfactoriamente, false si
     * la regla introducida no es valida.
     */
    public boolean setRegla(short regla) {
        short vecindariosPosibles = (short) Math.pow(CANT_ESTADOS, numVecinos);
        boolean transformaciones[] = new boolean[vecindariosPosibles];
        
        if(regla < 0 || regla >= Math.pow(CANT_ESTADOS, vecindariosPosibles)) {
            System.err.println("Regla invalida");
            return false;
        } else {
            //for(short i = (short)(vecindariosPosibles - 1); i >= 0; i--) {
            for(short i = 0; i <= (short)(vecindariosPosibles - 1); i++) {
                transformaciones[i] = (regla % 2) == 1;
                System.out.print((regla % 2) + " ");
                regla /= 2;
            }
            Celula.setReglaBinario(transformaciones);
        }
        System.out.print("\n");
        
        return true; 
    }
}
