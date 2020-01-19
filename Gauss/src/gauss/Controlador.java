package gauss;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Controlador implements ActionListener, KeyListener {

    Vista d;
    Modelo t;
    int numVar;
    int numIne;
    boolean mod; //Indica si en una misma sesión se esta cambiando las variables o ecuaciones

    public Controlador(Vista d, Modelo t) {
        this.d = d;
        this.t = t;
        mod = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Crear campos de inecuaciones".equals(e.getActionCommand())) {
            if (!d.txtNumIne.getText().equals("") && !d.txtNumVar.getText().equals("")) {
                numVar = Integer.parseInt(d.txtNumVar.getText());
                numIne = Integer.parseInt(d.txtNumIne.getText());
                if (numVar < 2) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar mínimo 2 variables");
                    d.txtNumVar.requestFocus();
                } else if (numIne < 1) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar mínimo 1 inecuación");
                    d.txtNumIne.requestFocus();
                } else {
                    // Definir container
                    Container frInecuaciones = new Container();
                    frInecuaciones.setLayout(null);
                    frInecuaciones.setPreferredSize(new Dimension(350, 350));
                    
                    Container frVariables = new Container();
                    frVariables.setLayout(null);
                    frVariables.setPreferredSize(new Dimension(700, 50));
                    
                    // Limpiar 
                    if (mod) {
                        // Elimiar los campos de variables
                        frVariables.removeAll();
                        frVariables.repaint();

                        // Elimiar los campos de Inecuacinoes
                        frInecuaciones.removeAll();
                        frInecuaciones.repaint();
                    }

                    mod = true; 
// <editor-fold defaultstate="collapsed" desc="Región de variables">
                    // Diseño de JScrollPane de variables 
                    d.spVariables.setBounds(20, 110, 720, 55);
                    
                    frVariables.add(d.lblVarZ);
                    
                    // Instancia los JTextfield
                    d.txtVariables = new JTextField[numVar];
                    for (int i = 0; i < numVar; i++) {
                        d.txtVariables[i] = new JTextField();
                    }

                    // Agrega los JTextfield al contenedor
                    for (int i = 0; i < numVar; i++) {
                        frVariables.add(d.txtVariables[i]);
                        d.txtVariables[i].setBounds(100 + (i * 45), 10, 30, 25);
                        frVariables.setPreferredSize(new Dimension(100 + (i * 45) + 35 , 5));
                    }

                    // Al JScrollPane le asigna el contenedor
                    d.spVariables.getViewport().add(frVariables);
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Región de ecuaciones">
                    // Diseño del JScrollPane de ecauciones
                    d.spInecuaciones.setBounds(20, 180, 360, 360);

                    // Instancia los cuadros de texto de las ecuaciones
                    d.txtEcuaciones = new JTextField[numIne][numVar + 1];
                    d.lblNomEcu = new JLabel[numVar+1];
                    d.lblIgual = new JLabel[numIne];
                   
                    for (int i = 0; i < numIne; i++) {
                        for (int j = 0; j < numVar + 1; j++) {
                            d.txtEcuaciones[i][j] = new JTextField();
                            
                            d.lblIgual[i] = new JLabel("=");
                            if (j < numVar) {
                                d.lblNomEcu[j] = new JLabel("X" + (j+1));
                            } else
                            {
                                d.lblNomEcu[j] = new JLabel("Núm.");
                            }
                        }
                    }

                    // Agrega los cuadros de textos al contenedor de ecuaciones
                    for (int i = 0; i < numIne; i++) {
                        for (int j = 0; j < numVar + 1; j++) {
                            frInecuaciones.add(d.lblNomEcu[j]);
                            frInecuaciones.add(d.txtEcuaciones[i][j]);
                            frInecuaciones.add(d.lblIgual[i]);
                            if (j == numVar) {
                                d.txtEcuaciones[i][j].getLocation();
                                d.lblNomEcu[j].setBounds(30 + (j * 45), 10, 30, 25);
                                d.txtEcuaciones[i][j].setBounds(25 + (j * 45), 40 + (i * 30), 30, 25);
                                
                                d.lblIgual[i] .setBounds(5 + (j * 45), 40 + (i * 30), 30, 25);
                                
                                // Redefine el tamaño del contenedor
                                if ((10 + (j * 45)) > 350 || (125 + (i * 30)) > 350) {
                                    frInecuaciones.setPreferredSize(new Dimension(65 + (j * 45), 125 + (i * 30)));
                                }
                            } else { 
                                d.lblNomEcu[j].setBounds(15 + (j * 45), 10, 30, 25);
                                d.txtEcuaciones[i][j].setBounds(10 + (j * 45), 40 + (i * 30), 30, 25);
                            }
                        }
                    }

                    // Ubica al botón Mostrar Resultados
                    d.btnMostrarResultados.setBounds(100, 45, 150, 30);
                    d.btnMostrarResultados.setLocation(frInecuaciones.getPreferredSize().width/2 -75, frInecuaciones.getPreferredSize().height - 40);
                    frInecuaciones.add(d.btnMostrarResultados);
                    
                    // Agrega el contenedor al JScrollPane
                    d.spInecuaciones.getViewport().add(frInecuaciones);
// </editor-fold>
                }
            }
        } else if ("Mostrar resultado".equals(e.getActionCommand())) {
            // Comprueba que los campos necesarios no esten vacios
            boolean mLlena = true;
            for (int i = 0; i < numIne; i++) {
                for (int j = 0; j < numVar + 1; j++) {

                    if ("".equals(d.txtEcuaciones[i][j].getText())) {
                        mLlena = false;
                        break;
                    }
                }
            }

            for(int j = 0; j < numVar; j++){
                if("".equals(d.txtVariables[j].getText())){
                    mLlena = false;
                    break;
                }
            }
            if (mLlena) {
// <editor-fold defaultstate="collapsed" desc="Región de variables">
                ArrayList<Double[]> matriz = new ArrayList();
                Double[] temporal = new Double[2 + numVar * 2];

                // llenar primer renglon
                temporal[0] = 1.0;
                for (int i = 1; i < (2 + numVar * 2); i++) {
                    if (i <= numVar) {
                        temporal[i] = -1 * Double.parseDouble(d.txtVariables[i - 1].getText());
                    } else {
                        temporal[i] = 0.0;
                    }
                }

                matriz.add(temporal);

                // Coeficientes de las ecuaciones
                for (int i = 0; i < numIne; i++) {
                    temporal = new Double[2 + numVar * 2];
                    temporal[0] = 0.0;
                    for (int j = 1; j < (2 + numVar * 2); j++) {
                        if (j <= numVar) {
                            temporal[j] = Double.parseDouble(d.txtEcuaciones[i][(j - 1)].getText());
                        } else if ((j - numVar - 1) == i) {
                            temporal[j] = 1.0;
                        } else if (j == (2 + numVar * 2) - 1) {
                            temporal[j] = Double.parseDouble(d.txtEcuaciones[i][numVar].getText());
                        } else {
                            temporal[j] = 0.0;
                        }
                    }

                    matriz.add(temporal);
// </editor-fold>
                }

                // Diseño del JScrollPane de ecauciones
                d.spDesarollo.setBounds(380, 180, 360, 360);
                
                // Agrega el contenedor al JScrollPane
                d.spDesarollo.getViewport().add(d.txtProceso);
                Modelo log = new Modelo();
                
                log.resolverMatriz(d.txtProceso, matriz, numVar, numIne);
            } else {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (!Character.isDigit(e.getKeyChar())) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
