package CA;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que implementa una Celula como un Boton. Puede tener 2 estados representados
 * visualmente con colores (Viva-Blanco / Muerta-Negro).
 * @author Allan
 */
public class Celula extends JButton {
    public static final byte TAMANO_PX = 5;
    private boolean[] estadosVecindario;
    private static boolean[] reglaBinario; //Almacena la regla de transformacion en forma de cadena de bits
    private boolean viva;
 
    public Celula() {
        viva = false;
        setPreferredSize(new Dimension(TAMANO_PX, TAMANO_PX));
        setBackground(Color.WHITE);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setViva(!viva);
            }
        });
    }
    
    /**
     * Computa el siguiente estado basado en los estados de su vecindario.
     * @param estadosVecindario Arreglo de booleanos que almacena los estados
     *                          del vecindario de la celula.
     * @return Su estado en la siguiente unidad de tiempo.
     */
    public boolean siguienteEstado(boolean[] estadosVecindario) {
        short transformacion = 0;
        byte exp = (byte)(estadosVecindario.length - 1);
        for(byte i = 0; i < estadosVecindario.length; i++) {
            if(estadosVecindario[i]) {
                transformacion += (short)Math.pow(2, exp);
            }
            exp -= 1;
        }
        //System.out.println("Index transformacion: " + transformacion);
        return Celula.reglaBinario[/*Celula.reglaBinario.length -*/ transformacion /*- 1*/];
    }
    
    /**
     * Actualiza el color de la celula.
     */
    private void actualizarColor() {
        if(viva) {
            setBackground(Color.BLACK);
        } else {
            setBackground(Color.WHITE);
        }
    }
    
    /**
     * Asigna la regla de transformacion bajo la que se regira la celula.
     * 
     * @param reglaBinario 
     */
    public static void setReglaBinario(boolean[] reglaBinario) {
        Celula.reglaBinario = reglaBinario;
    }

    /**
     * Decide el estado de la Celula (Vive si viva=true, Muere si viva=false)
     * @param viva 
     */
    public void setViva(boolean viva) {
        this.viva = viva;
        actualizarColor();
    }
    
    /**
     * 
     * @return Estado actual de la Celula.
     */
    public boolean isViva() {
        return viva;
    }
}
