/* 
* Proyecto: swing_c_p02_CarmonaSanchezRaul 
* Paquete:  swing_c_p02_CarmonaSanchezRaul 
* Archivo:  Menu.java 
* Autor/a:  Raul Carmona Sanchez 
* Fecha:    25 nov 2025 8:29:17 
* 
* Descripción: 
*   [Resumen del propósito del archivo/clase.] 
* 
* Licencia: 
*   [Condiciones de uso/licencia.] 
*/
package swing_c_p02_CarmonaSanchezRaul;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

// TODO: Auto-generated Javadoc
/**
 * The Class Menu.
 */
public class Menu extends JMenuBar {

	/** El mensaje de inofrmacion. */
	String mensaje = "Apartamentos Andarax\n" + "Versión: 1.0\n" + "Autor: Raul Carmona Sanchez\n"+"Telefono de ayuda: 9505566777";
	
	/**
	 * Instantiates a new menu.
	 *
	 * @param ventana the ventana
	 */
	public Menu(VentanaPrincipal ventana) {

		JMenu archivo = new JMenu("Archivo");
        archivo.setMnemonic(KeyEvent.VK_A);
        JMenuItem salir = new JMenuItem("Salir");
        salir.setMnemonic(KeyEvent.VK_S); 
        salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        salir.addActionListener(e -> System.exit(0));

        archivo.add(salir);

        JMenu registro = new JMenu("Registro");
        registro.setMnemonic(KeyEvent.VK_R); 
        JMenuItem alta = new JMenuItem("Alta Pisos");
        alta.setMnemonic(KeyEvent.VK_A); 
        alta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

        JMenuItem baja = new JMenuItem("Baja Pisos");
        baja.setMnemonic(KeyEvent.VK_B); 
        baja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));

        registro.add(alta);
        registro.add(baja);

        JMenu ayuda = new JMenu("Ayuda");
        ayuda.setMnemonic(KeyEvent.VK_Y); 
        
        JMenuItem acercaDe = new JMenuItem("Acerca de...");
        acercaDe.setMnemonic(KeyEvent.VK_C);
        acercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        acercaDe.addActionListener(e -> JOptionPane.showMessageDialog(ventana, mensaje));

        ayuda.add(acercaDe);
        
        this.add(archivo);
        this.add(registro);
        this.add(ayuda);
	}
}
