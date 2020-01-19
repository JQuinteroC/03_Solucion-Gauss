package gauss;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Vista extends JFrame {

    JTextField txtNumVar = new JTextField();
    JTextField txtNumIne = new JTextField();
    JLabel lblTitulo = new JLabel("Método Simplex");
    JLabel lblNumVar = new JLabel("Número de variables");
    JLabel lblNumIne = new JLabel("Número de inecuaciones");
    JLabel lblVarZ = new JLabel("Variables de Z:");
    JScrollPane spValores = new JScrollPane();
    JScrollPane spVariables = new JScrollPane();
    JScrollPane spInecuaciones = new JScrollPane();
    JScrollPane spDesarollo = new JScrollPane();
    JButton btnCrearCampos = new JButton("Crear campos de inecuaciones");
    JButton btnMostrarResultados = new JButton("Mostrar resultado");
    JTextField txtEcuaciones [][];
    JTextField txtVariables [];
    JLabel lblNomEcu [];
    JLabel lblIgual[];
    JTextArea txtProceso = new JTextArea();
    
    void mostrar() {
        spValores.setBounds(20, 50, 720, 50);
        lblNumVar.setBounds(10, 10, 140, 25);
        txtNumVar.setBounds(140, 10, 50, 25);
        lblNumIne.setBounds(230, 10, 150, 25);
        txtNumIne.setBounds(380, 10, 50, 25);
        btnCrearCampos.setBounds(480, 8, 220, 30);
        lblVarZ.setBounds(10, 10, 100, 25);
        lblTitulo.setBounds(200, -20, 100, 100);
            
        setSize(780, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Factorial");
    }

    public Vista() throws HeadlessException {
        Container c = getContentPane();

        c.setLayout(null);
        
        // Crear segmento Scroll de Variables
        c.add(spValores);
        c.add(lblTitulo);
        Container frValores = new Container();
        frValores.setLayout(null);
        frValores.setPreferredSize(new Dimension(700, 45));
        spValores.getViewport().add(frValores);
        
        // Ingresar componentes a Scroll de Variables
        frValores.add(lblNumVar);
        frValores.add(txtNumVar);
        frValores.add(lblNumIne);
        frValores.add(txtNumIne);
        frValores.add(btnCrearCampos);
        
        c.add(spVariables);
        c.add(spInecuaciones);
        c.add(spDesarollo);

    }

    void asignaOyentes(Controlador c) {
        btnCrearCampos.addActionListener(c);
        btnMostrarResultados.addActionListener(c);
        
  //      btnCalcularFac.addActionListener(c);
    //    txtNumeroFac.addKeyListener(c);
    }
}
