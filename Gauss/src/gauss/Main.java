package gauss;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Main {

    public static void main(String[] args) {
        Modelo t = new Modelo();
        Vista f = new Vista();
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(f.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        Controlador u = new Controlador(f, t);
        f.asignaOyentes(u);
        f.mostrar();
    }
}
