/* 
* Proyecto: swing_c_p02_CarmonaSanchezRaul 
* Paquete:  swing_c_p02_CarmonaSanchezRaul 
* Archivo:  VentanaPrincipal.java 
* Autor/a:  Raul Carmona Sanchez 
* Fecha:    19 nov 2025 14:12:56 
* 
* Descripción: 
*   [Resumen del propósito del archivo/clase.] 
* 
* Licencia: 
*   [Condiciones de uso/licencia.] 
*/
package swing_c_p02_CarmonaSanchezRaul;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class VentanaPrincipal.
 */
public class VentanaPrincipal extends JFrame {
	
	/** El miJpanel. */
	private JPanel miJPanel;
	
	/** El boton alta. */
	private JButton botonAlta;
	
	/** El menu. */
	private Menu menu;
	
	/** La pantalla. */
	Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	
	/** El ancho. */
	int ancho = pantalla.width / 2;
	
	/** El alto. */
	int alto = pantalla.height / 2;

	/**
	 * Inicializa la ventana principal.
	 */
	// Ventana Principal
	public VentanaPrincipal() {
		super("Gestion de apartamento Andarax");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400, 300));
		this.setSize(ancho, alto);
		this.setLocationRelativeTo(null);
		iniciarComponentes();
		this.setVisible(true);
	}

	/**
	 * Iniciar componentes.
	 */
	// metodo inciar componentes
	public void iniciarComponentes() {
		miJPanel = new JPanel();
		miJPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));
		botonAlta = new JButton("Alta Pisos");
		botonAlta.setPreferredSize(new Dimension(180, 60));
		botonAlta.addActionListener(e -> abrirAlta());
		miJPanel.add(botonAlta);

		// boton Alta
		ImageIcon iconoAlta = new ImageIcon(getClass().getResource("/recursos/alta.png"));
		Image imagen = iconoAlta.getImage();
		Image imagenEscalada = imagen.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		botonAlta.setIcon(new ImageIcon(imagenEscalada));

		// Boton Baja
		JButton botonBaja = new JButton("Baja pisos");
		botonBaja.setPreferredSize(new Dimension(180, 60));
		ImageIcon iconoBaja = new ImageIcon(getClass().getResource("/recursos/baja.png"));
		Image imagen2 = iconoBaja.getImage();
		Image imagenEscalada2 = imagen2.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		botonBaja.setIcon(new ImageIcon(imagenEscalada2));
		botonBaja.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "No está implementado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
		});
		miJPanel.add(botonBaja);
		this.add(miJPanel);
		menu = new Menu(this);
		this.setJMenuBar(menu);
	}

	/**
	 * Abrir alta.
	 */
	// Metodo para abrir la ventana de altas
	public void abrirAlta() {
		VentanaSecundaria alta = new VentanaSecundaria(this, true);
		alta.setVisible(true);
	}
}