package gauss;

import java.util.ArrayList;
import javax.swing.JTextArea;
import simplex.Gauss;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Modelo {

    Gauss gauss = new Gauss();

    Modelo() {
    }

    void resolverMatriz(JTextArea txtArea, ArrayList<Double[]> matriz, int numVariables, int numEcuaciones) {
        double[] maximos = new double[numEcuaciones];
        txtArea.setText("Matriz: \n");
        imprirmir(txtArea, matriz);

        for (int i = 0; i < numEcuaciones; i++) {
            txtArea.setText(txtArea.getText() + (i + 1) + ") Se transforma en \n");
            gauss.gauss(matriz, numVariables, numEcuaciones);
            imprirmir(txtArea, matriz);
        }

        for (int i = 1; i <= numEcuaciones; i++) {
            for (int j = 1; j <= numVariables; j++) {
                if (matriz.get(i)[j] == 1.0){
                    int a = matriz.get(0).length - 1;
                    if (i == numEcuaciones) {
                        maximos[j - 1] = matriz.get(i-1)[a];
                    } else{
                        maximos[j - 1] = matriz.get(i)[a];
                    }
                }
            }
        }

        txtArea.setText(txtArea.getText() + "Para maximiar se necesitan los siguientes valores: ");
        for (int i = 1; i <= numVariables; i++) {
            txtArea.setText(txtArea.getText() + "X" + i + " = " + maximos[i - 1] + " ");
        }
        txtArea.setText(txtArea.getText() + " y conseguir un maximo de " + matriz.get(0)[1+numVariables*2]);
    }

    void imprirmir(JTextArea txtArea, ArrayList<Double[]> matriz) {
        String t = txtArea.getText();
        for (int j = 0; j < matriz.get(0).length; j++) {
            if (j == 0) {
                t = t + "Z\t";
            } else if (j < matriz.get(0).length - 1) {
                if (j < (matriz.get(0).length) / 2) {
                    t = t + "X" + (j) + "\t";
                } else {
                    int a = j - (matriz.get(0).length / 2) + 1;
                    t = t + "S" + a + "\t";
                }

            } else {
                t = t + "Igual\n";
            }
        }

        for (int i = 0; i < matriz.size(); i++) {
            for (int j = 0; j < matriz.get(0).length; j++) {
                double valor = Math.round(matriz.get(i)[j] * 100.0) / 100.0;
                t = t + Double.toString(valor) + "\t";
            }
            t = t + "\n";
        }
        t = t + "------------------------------------------------------------------------------------\n";
        txtArea.setText(t);
    }
}
