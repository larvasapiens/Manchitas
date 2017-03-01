package CA;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Frame que contiene todos los componentes.
 *
 * @author Allan
 */
public class FramePrincipal extends JFrame {

    CA automata;
    JPanel pAgrupaTextF;
    JPanel pAgrupaButton;
    JButton bPobAleatoria;
    JButton bProcesarAutomata;
    JButton bReset;
    JLabel lRegla;
    JLabel lVecinos;
    JTextField tfRegla;
    JTextField tfVecinos;

    public FramePrincipal() {
        inicComponentes();
        inicEventos();
        confComponentes();
        confFrame();
        adicComponentes();
    }

    /**
     * Inicializacion de los componentes del Frame Principal.
     */
    private void inicComponentes() {
        //--------Panels---------//
        automata = new CA();
        pAgrupaTextF = new JPanel();
        pAgrupaButton = new JPanel();
        //--------Botones---------//
        bPobAleatoria = new JButton("Poblacion Aleatoria");
        bProcesarAutomata = new JButton("Procesar Automata");
        bReset = new JButton("Reset");
        //--------Labeks---------//
        lRegla = new JLabel("Regla:");
        lVecinos = new JLabel("Cant. Vecinos:");
        //--------TextFields---------//
        tfRegla = new JTextField();
        tfVecinos = new JTextField("3");
    }

    /**
     * Agrega los Eventos de los Componentes del JFrame.
     */
    private void inicEventos() {
        //--------Botones---------//
        bPobAleatoria.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    automata.poblacionAleatoria();
                } catch (Exception e) {
                    System.out.println("En boton Procesar Automata, " + e);
                }
            }
        });
        bProcesarAutomata.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    automata.setNumVecinos(Byte.parseByte(tfVecinos.getText()));
                    boolean reglaValida = automata.setRegla(Short.parseShort(tfRegla.getText()));
                    if (reglaValida) {
                        automata.procesarAutomataFrio();
                    } else {
                        JOptionPane.showMessageDialog(automata,
                                "Por favor introduzca una Regla Valida\n Entre 0 y 2^(2^(Cant. Vecinos))",
                                "Error"
                                        + " de Formato",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(automata,
                            "Al parecer a olvidado introducir un dato, o tiene el formato incorrecto",
                            "Error de Formato",
                            JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    System.out.println("En boton Procesar Automata, " + e);
                }
            }
        });
        bReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                automata.resetPoblacion();
            }
        });
        //--------TextFields---------//
        tfRegla.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bProcesarAutomata.doClick();
            }
        });
        tfVecinos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bProcesarAutomata.doClick();
            }
        });
    }

    /**
     * Configura el aspecto de los Componentes del Frame Principal.
     */
    private void confComponentes() {
        //--------Panels---------//
        pAgrupaTextF.setLayout(new GridLayout(2, 2, 5, 3));
        pAgrupaButton.setLayout(new GridLayout(2, 1, 0, 3));
        //--------TextFields---------//
        tfRegla.setPreferredSize(new Dimension(50, 25));
        tfVecinos.setPreferredSize(new Dimension(50, 25));
    }

    /**
     * Configura el aspecto del Frame Principal.
     */
    private void confFrame() {
        setTitle("Automata Celular de 1D y 2 Estados");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
    }

    /**
     * Adiciona los componentes al Frame Principal.
     */
    private void adicComponentes() {
        add(automata);
        pAgrupaButton.add(bPobAleatoria);
        pAgrupaButton.add(bReset);
        pAgrupaTextF.add(lRegla);
        pAgrupaTextF.add(tfRegla);
        pAgrupaTextF.add(lVecinos);
        pAgrupaTextF.add(tfVecinos);
        add(pAgrupaButton);
        add(pAgrupaTextF);
        add(bProcesarAutomata);
    }

    public static void main(String args[]) {
        //Previene bugs de Swing
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                FramePrincipal frame = new FramePrincipal();
                frame.setVisible(true);
            }
        });
    }
}
