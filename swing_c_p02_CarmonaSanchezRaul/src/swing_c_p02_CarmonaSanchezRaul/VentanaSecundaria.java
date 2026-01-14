/* 
* Proyecto: swing_c_p02_CarmonaSanchezRaul 
* Paquete:  swing_c_p02_CarmonaSanchezRaul 
* Archivo:  VentanaSecundaria.java 
* Autor/a:  Raul Carmona Sanchez 
* Fecha:    19 nov 2025 14:13:18 
* 
* Descripción: 
*   [Resumen del propósito del archivo/clase.] 
* 
* Licencia: 
*   [Condiciones de uso/licencia.] 
*/
package swing_c_p02_CarmonaSanchezRaul;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// TODO: Auto-generated Javadoc
/**
 * Ventana secundaria para el alta de nuevos pisos. Implementa validaciones,
 * accesibilidad y diseño responsiv.
 */
public class VentanaSecundaria extends JDialog {

	/** El texto del nombre. */
	// Variables del arrendador
	private JTextField textoNombre;

	/** El texto de apellidos. */
	private JTextField textoApellidos;

	/** EL texto de dni. */
	private JTextField textoDni;

	/** El texto del telefono. */
	private JTextField textoTelefono;

	/** El texto de la direccion. */
	// Variables del inmuebele
	private JTextField textoDireccion;

	/** El combobox de provincia. */
	private JComboBox<String> comboProvincia;

	/** El spinner fecha alta. */
	private JSpinner spinnerFechaAlta;

	/** El spinner de fecha fin. */
	private JSpinner spinnerFechaFin;

	/** El spinner huespedes. */
	private JSpinner spinnerHuespedes;

	/** El spinner dormitorios. */
	private JSpinner spinnerDormitorios;

	/** EL spinner baños. */
	private JSpinner spinnerBaños;

	/** El spinner camas. */
	private JSpinner spinnerCamas;

	/** El combobox tipo cama. */
	private JComboBox<String> comboTipoCama;

	/** El check ninos. */
	private JCheckBox checkNinos;

	/** El spinner edad niños. */
	private JSpinner spinnerEdadNiños;

	/** El texto tipo cama niño. */
	private JTextField textoTipoCamaNiño;

	/** El label precio minimo. */
	private JLabel labelPrecioMinimo;

	/** El area resumen de arrendador. */
	// Texto de el resumen
	private JTextArea areaResumenArrendador;

	/** El area resumen de inmueble. */
	private JTextArea areaResumenInmueble;

	/** El slider del estado. */
	// Componente no usado en la prectica
	private JSlider sliderEstado;

	/** El label del valor del estado. */
	private JLabel labelValorEstado;

	/** El panel contenedor central. */
	// Contenedor central para los paneles
	private JPanel panelContenedorCentral;

	/**
	 * Constructor de la ventana secundaria. Configura el tamaño para ocupar el área
	 * útil de la pantalla.
	 * 
	 * @param parent Ventana padre (JFrame)
	 * @param modal  Si bloquea la interacción con la padre
	 */
	public VentanaSecundaria(JFrame parent, boolean modal) {
		super(parent, "Alta pisos", modal);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// Obtener área útil de la pantalla (sin tapar barra de tareas)
		Rectangle pantallaUtil = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.setBounds(pantallaUtil);
		this.setLocation(0, 0);

		iniciarComponentes();
	}

	/**
	 * Inicializa y organiza todos los componentes de la interfaz.
	 */
	private void iniciarComponentes() {
		// Panel principal con BorderLayout y margen
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setContentPane(mainPanel);

		// Contenedor central con BoxLayout vertical (apila paneles)
		panelContenedorCentral = new JPanel();
		panelContenedorCentral.setLayout(new BoxLayout(panelContenedorCentral, BoxLayout.Y_AXIS));

		// ScrollPane para permitir desplazamiento si el contenido excede la pantalla
		JScrollPane jScrollPane = new JScrollPane(panelContenedorCentral);
		mainPanel.add(jScrollPane, BorderLayout.CENTER);

		// Añadir los distintos paneles
		agregarPanelCabecera();
		agregarPanelDatosArrendador();
		panelAgregarDatosDelInmueble();
		agregarPanelResumen();
		agregarPanelBotones();
	}

	/**
	 * Método auxiliar para configurar accesibilidad y usabilidad en una sola línea.
	 * Aplica: Mnemónico (Alt+Letra), LabelFor (Lectores pantalla) y Tooltip.
	 * 
	 * @param label    Etiqueta descriptiva
	 * @param comp     Componente de entrada (TextField, Spinner, etc.)
	 * @param mnemonic Carácter para el atajo de teclado
	 * @param tooltip  Texto de ayuda emergente
	 */
	public static void configurarAccesibilidad(JLabel label, JComponent comp, char mnemonic, String tooltip) {
		label.setDisplayedMnemonic(mnemonic);
		label.setLabelFor(comp);
		comp.setToolTipText(tooltip);
	}

	/**
	 * Crea y añade el panel de cabecera con estilo corporativo.
	 */
	private void agregarPanelCabecera() {
		JPanel panelCabecera = new JPanel();
		panelCabecera.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		panelCabecera.setBackground(new Color(230, 245, 255)); // Fondo azul claro

		// Borde compuesto: Línea azul fuerte + Margen interno
		panelCabecera
				.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 51, 153), 2),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		JLabel labelIcono = new JLabel();
		try {
			ImageIcon imagenOriginal = new ImageIcon(getClass().getResource("/recursos/iconoEmpresa.png"));
			Image imagenEscalada = imagenOriginal.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);

			labelIcono.setIcon(new ImageIcon(imagenEscalada));
		} catch (Exception e) {
			System.err.println("No se encontró el iconoEmpresa.png");
		}

		JLabel labelTitulo = new JLabel("Apartamentos Andarax");
		labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
		labelTitulo.setForeground(new Color(0, 51, 153)); // Texto azul fuerte

		panelCabecera.add(labelTitulo);
		panelCabecera.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		panelCabecera.add(labelIcono);

		panelContenedorCentral.add(panelCabecera);
		panelContenedorCentral.add(Box.createVerticalStrut(20)); // Separador vertical
	}

	/**
	 * Crea y añade el formulario de datos del arrendador con validaciones.
	 */
	private void agregarPanelDatosArrendador() {
		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		panelDatos.setBorder(BorderFactory.createTitledBorder("Datos del Arrendador"));

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Nombre
		JLabel labelNombre = new JLabel("Nombre:");
		textoNombre = new JTextField(20);
		configurarAccesibilidad(labelNombre, textoNombre, 'N', "Introduce el nombre del arrendador");

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelDatos.add(labelNombre, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		panelDatos.add(textoNombre, gbc);

		// Apellidos
		JLabel labelApellidos = new JLabel("Apellidos:");
		textoApellidos = new JTextField(20);
		configurarAccesibilidad(labelApellidos, textoApellidos, 'A', "Introduce los apellidos");

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		panelDatos.add(labelApellidos, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		panelDatos.add(textoApellidos, gbc);

		// DNI
		JLabel labelDni = new JLabel("DNI:");
		textoDni = new JTextField(10);
		configurarAccesibilidad(labelDni, textoDni, 'D', "Formato de 8 números y una letra (ej: 12345678Z)");

		textoDni.setInputVerifier(new ExpresionRegularInputVerifier("^\\d{8}[A-Za-z]$",
				"El DNI debe tener 8 números y una letra (Ej: 77384378J)"));

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		panelDatos.add(labelDni, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		panelDatos.add(textoDni, gbc);

		JLabel labelTelefono = new JLabel("Teléfono:");
		textoTelefono = new JTextField(10);
		configurarAccesibilidad(labelTelefono, textoTelefono, 'T', "Introduce 9 dígitos numéricos");

		textoTelefono.setInputVerifier(
				new ExpresionRegularInputVerifier("^\\d{9}$", "El teléfono debe tener 9 dígitos numéricos"));

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0.0;
		panelDatos.add(labelTelefono, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 1.0;
		panelDatos.add(textoTelefono, gbc);

		panelDatos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

		panelContenedorCentral.add(panelDatos);
		panelContenedorCentral.add(Box.createVerticalStrut(10));
	}

	/**
	 * Crea y añade el panel con los datos del inmueble, incluyendo fechas,
	 * configuración de camas, una sección de niños, galería de imágenes y precio
	 * minimo.
	 */
	public void panelAgregarDatosDelInmueble() {
		JPanel panelDatosInmueble = new JPanel();
		panelDatosInmueble.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		panelDatosInmueble.setBorder(BorderFactory.createTitledBorder("Datos del Inmueble"));

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Dirección
		JLabel labelDireccion = new JLabel("Dirección:");
		textoDireccion = new JTextField(20);
		configurarAccesibilidad(labelDireccion, textoDireccion, 'S', "Introduce la dirección del inmueble");

		gbc.gridx = 0;
		gbc.gridy = 0;
		panelDatosInmueble.add(labelDireccion, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		panelDatosInmueble.add(textoDireccion, gbc);

		// Provincia
		JLabel labelProvincia = new JLabel("Provincias");
		comboProvincia = new JComboBox<>(new String[] { "Álava", "Albacete", "Alicante", "Almería", "Asturias", "Ávila",
				"Badajoz", "Barcelona", "Burgos", "Cáceres", "Cádiz", "Cantabria", "Castellón", "Ciudad Real",
				"Córdoba", "Cuenca", "Gerona", "Granada", "Guadalajara", "Guipúzcoa", "Huelva", "Huesca",
				"Islas Baleares", "Jaén", "La Coruña", "La Rioja", "Las Palmas", "León", "Lérida", "Lugo", "Madrid",
				"Málaga", "Murcia", "Navarra", "Orense", "Palencia", "Pontevedra", "Salamanca",
				"Santa Cruz de Tenerife", "Segovia", "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo", "Valencia",
				"Valladolid", "Vizcaya", "Zamora", "Zaragoza" });
		configurarAccesibilidad(labelProvincia, comboProvincia, 'P', "Selecciona la provincia del inmueble.");

		gbc.gridx = 0;
		gbc.gridy = 1;
		panelDatosInmueble.add(labelProvincia, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		panelDatosInmueble.add(comboProvincia, gbc);

		// Fecha de alta
		JLabel labelFechaAlta = new JLabel("Fecha de alta");
		spinnerFechaAlta = new JSpinner(new SpinnerDateModel());
		JSpinner.DateEditor editorAlta = new JSpinner.DateEditor(spinnerFechaAlta, "dd/MM/yyyy");
		spinnerFechaAlta.setEditor(editorAlta);
		configurarAccesibilidad(labelFechaAlta, spinnerFechaAlta, 'F', "Fecha de inicio del alquiler");

		gbc.gridx = 0;
		gbc.gridy = 3;
		panelDatosInmueble.add(labelFechaAlta, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelDatosInmueble.add(spinnerFechaAlta, gbc);

		// Fecha final de disponibilidad
		JLabel labelFechaFin = new JLabel("Fecha de final de disponibilidad");
		Date fechaHoy = new Date();
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fechaHoy);
		calendario.add(Calendar.YEAR, 1);
		Date sumaAño = calendario.getTime();

		SpinnerDateModel modeloFin = new SpinnerDateModel(sumaAño, fechaHoy, null, Calendar.DAY_OF_MONTH);
		spinnerFechaFin = new JSpinner(modeloFin);
		JSpinner.DateEditor editorFin = new JSpinner.DateEditor(spinnerFechaFin, "dd/MM/yyyy");
		spinnerFechaFin.setEditor(editorFin);
		configurarAccesibilidad(labelFechaFin, spinnerFechaFin, 'L', "Fecha fin (mínimo 1 año)");

		spinnerFechaAlta.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				Date nuevaFecha = (Date) spinnerFechaAlta.getValue();
				Calendar c = Calendar.getInstance();
				c.setTime(nuevaFecha);
				c.add(Calendar.YEAR, 1);
				spinnerFechaFin.setValue(c.getTime());
				((SpinnerDateModel) spinnerFechaFin.getModel()).setStart(nuevaFecha);
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 4;
		panelDatosInmueble.add(labelFechaFin, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panelDatosInmueble.add(spinnerFechaFin, gbc);

		JLabel labelNumeroHuespedes = new JLabel("Numero de huespedes");
		spinnerHuespedes = new JSpinner(new SpinnerNumberModel(0, 0, 8, 1));
		configurarAccesibilidad(labelNumeroHuespedes, spinnerHuespedes, 'H', "Máximo de huéspedes");

		gbc.gridx = 0;
		gbc.gridy = 5;
		panelDatosInmueble.add(labelNumeroHuespedes, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		panelDatosInmueble.add(spinnerHuespedes, gbc);

		JLabel labelNumeroDormitorios = new JLabel("Numero de dormitorios");
		spinnerDormitorios = new JSpinner(new SpinnerNumberModel(0, 0, 4, 1));
		configurarAccesibilidad(labelNumeroDormitorios, spinnerDormitorios, 'O', "Cantidad de dormitorios");

		gbc.gridx = 0;
		gbc.gridy = 6;
		panelDatosInmueble.add(labelNumeroDormitorios, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		panelDatosInmueble.add(spinnerDormitorios, gbc);

		JLabel labelNumeroBaños = new JLabel("Numero de baños ");
		spinnerBaños = new JSpinner(new SpinnerNumberModel(0, 0, 3, 1));
		configurarAccesibilidad(labelNumeroBaños, spinnerBaños, 'B', "Baños completos (afecta precio)");

		spinnerBaños.addChangeListener(e -> recalcularPrecio());

		gbc.gridx = 0;
		gbc.gridy = 7;
		panelDatosInmueble.add(labelNumeroBaños, gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		panelDatosInmueble.add(spinnerBaños, gbc);

		JLabel labelNumeroCamas = new JLabel("Numero de camas");
		spinnerCamas = new JSpinner(new SpinnerNumberModel(0, 0, 4, 1));
		configurarAccesibilidad(labelNumeroCamas, spinnerCamas, 'M', "Camas totales (afecta precio)");

		spinnerCamas.addChangeListener(e -> recalcularPrecio());

		gbc.gridx = 0;
		gbc.gridy = 8;
		panelDatosInmueble.add(labelNumeroCamas, gbc);
		gbc.gridx = 1;
		gbc.gridy = 8;
		panelDatosInmueble.add(spinnerCamas, gbc);

		JPanel panelTipoCama = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelTipoCama.setVisible(false);

		JLabel labelTipoCama = new JLabel(" Tipo de cama: ");
		String[] tiposCama = { "Simple", "Doble", "Sofá cama" };
		comboTipoCama = new JComboBox<>(tiposCama);
		configurarAccesibilidad(labelTipoCama, comboTipoCama, 'T', "Tipo de cama (afecta precio)");

		comboTipoCama.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				recalcularPrecio();
			}
		});

		panelTipoCama.add(labelTipoCama);
		panelTipoCama.add(comboTipoCama);

		gbc.gridx = 2;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		panelDatosInmueble.add(panelTipoCama, gbc);

		spinnerCamas.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int numero = (int) spinnerCamas.getValue();
				boolean mostrar = (numero > 0);
				if (panelTipoCama.isVisible() != mostrar) {
					panelTipoCama.setVisible(mostrar);
					panelDatosInmueble.revalidate();
					panelDatosInmueble.repaint();
				}
				recalcularPrecio();
			}
		});

		// Niños
		checkNinos = new JCheckBox("¿Niños?");
		configurarAccesibilidad(new JLabel(), checkNinos, '?', "Marcar si viajan niños");

		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		panelDatosInmueble.add(checkNinos, gbc);

		JPanel panelNinosDetalle = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelNinosDetalle.setVisible(false);

		JLabel lblEdad = new JLabel("Edad:");
		spinnerEdadNiños = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1)); // Edad 0-10
		textoTipoCamaNiño = new JTextField(15);
		textoTipoCamaNiño.setEditable(false);
		textoTipoCamaNiño.setText("Cuna");

		configurarAccesibilidad(lblEdad, spinnerEdadNiños, 'E', "Edad del niño (0-10 años)");

		panelNinosDetalle.add(lblEdad);
		panelNinosDetalle.add(spinnerEdadNiños);
		panelNinosDetalle.add(new JLabel("Asignado: "));
		panelNinosDetalle.add(textoTipoCamaNiño);

		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.gridwidth = 3;
		panelDatosInmueble.add(panelNinosDetalle, gbc);

		checkNinos.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				boolean seleccionado = (e.getStateChange() == ItemEvent.SELECTED);
				panelNinosDetalle.setVisible(seleccionado);
				panelDatosInmueble.revalidate();
				panelDatosInmueble.repaint();
				recalcularPrecio();
			}
		});

		spinnerEdadNiños.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int edad = (int) spinnerEdadNiños.getValue();
				if (edad <= 3) {
					textoTipoCamaNiño.setText("Cuna");
				} else {
					textoTipoCamaNiño.setText("Cama supletoria pequeña");
				}
			}
		});

		// Galeria de los pisos
		JPanel panelImagenes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panelImagenes.setBorder(BorderFactory.createTitledBorder("Galería"));
		for (int i = 1; i <= 3; i++) {
			try {
				ImageIcon icono = new ImageIcon(getClass().getResource("/recursos/piso" + i + ".png"));
				Image imgagenEscalada = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				JLabel labelImgagen = new JLabel(new ImageIcon(imgagenEscalada));
				labelImgagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				panelImagenes.add(labelImgagen);
			} catch (Exception e) {
				JLabel labelError = new JLabel("[Sin imagen " + i + "]");
				labelError.setPreferredSize(new Dimension(100, 100));
				labelError.setBorder(BorderFactory.createLineBorder(Color.RED));
				labelError.setHorizontalAlignment(JLabel.CENTER);
				panelImagenes.add(labelError);
			}
		}

		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 4;
		panelDatosInmueble.add(panelImagenes, gbc);

		// Precio
		JPanel panelPrecio = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel labelPrecio = new JLabel("Precio Mínimo Calculado: ");
		labelPrecio.setFont(new Font("Arial", Font.BOLD, 14));

		labelPrecioMinimo = new JLabel("0 €");
		labelPrecioMinimo.setFont(new Font("Arial", Font.BOLD, 18));
		labelPrecioMinimo.setForeground(new Color(0, 100, 0));

		panelPrecio.add(labelPrecio);
		panelPrecio.add(labelPrecioMinimo);

		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 4;
		panelDatosInmueble.add(panelPrecio, gbc);

		panelContenedorCentral.add(panelDatosInmueble);
		panelContenedorCentral.add(Box.createVerticalStrut(10));
		recalcularPrecio();

		// Estado del inmueble
		JLabel labelSlider = new JLabel("Estado de conservación:");
		sliderEstado = new javax.swing.JSlider(javax.swing.JSlider.HORIZONTAL, 0, 10, 5);
		sliderEstado = new javax.swing.JSlider(javax.swing.JSlider.HORIZONTAL, 0, 10, 5);

		sliderEstado.setMajorTickSpacing(2);
		sliderEstado.setMinorTickSpacing(1);
		sliderEstado.setPaintTicks(true);
		sliderEstado.setPaintLabels(true);

		sliderEstado.setToolTipText("Desliza para puntuar el estado del piso: 0 a 10");

		labelValorEstado = new JLabel("Normal (5)");
		labelValorEstado.setFont(new Font("Arial", Font.ITALIC, 12));
		labelValorEstado.setForeground(Color.BLUE);

		sliderEstado.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int valor = sliderEstado.getValue();
				String descripcion = "";

				if (valor < 3) {
					descripcion = "Malo";
				} else if (valor < 7) {
					descripcion = "Normal";
				} else if (valor < 9) {
					descripcion = "Muy bueno";
				} else {
					descripcion = "Excelente";
				}

				labelValorEstado.setText(descripcion + " (" + valor + ")");
			}
		});

		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.gridwidth = 1;
		panelDatosInmueble.add(labelSlider, gbc);

		gbc.gridx = 1;
		gbc.gridy = 12;
		gbc.weightx = 1.0;
		panelDatosInmueble.add(sliderEstado, gbc);

		gbc.gridx = 2;
		gbc.gridy = 12;
		gbc.weightx = 0.0;
		gbc.gridwidth = 2;
		panelDatosInmueble.add(labelValorEstado, gbc);
	}

	/**
	 * Calcula el precio mínimo basado en la configuración actual.
	 */
	private void recalcularPrecio() {
		double precio = 0.0;

		int numeroDeCamas = (int) spinnerCamas.getValue();
		String tipo = (String) comboTipoCama.getSelectedItem();

		if (tipo != null) {
			if (tipo.equals("Doble")) {
				precio += numeroDeCamas * 20;
			} else {
				precio += numeroDeCamas * 15;
			}
		}

		int numeroBaños = (int) spinnerBaños.getValue();
		precio += numeroBaños * 25;

		if (checkNinos.isSelected()) {
			precio += 12;
		}

		labelPrecioMinimo.setText(String.format("%.2f €", precio));

	}

	/**
	 * PANEL 5: RESUMEN (JTABBEDPANE) Muestra dos fichas con el resumen de datos
	 * introducidos. Se actualiza dinámicamente al cambiar de pestaña.
	 */
	private void agregarPanelResumen() {
		JPanel panelResumen = new JPanel(new BorderLayout());
		panelResumen.setBorder(BorderFactory.createTitledBorder("Resumen de Datos"));

		JTabbedPane tabbedPaneResumen = new JTabbedPane();

		areaResumenArrendador = new JTextArea(10, 30);
		areaResumenArrendador.setEditable(false); // Solo lectura
		areaResumenArrendador.setFont(new Font("Monospaced", Font.PLAIN, 13)); // Fuente monoespaciada para alinear
		areaResumenArrendador.setMargin(new Insets(10, 10, 10, 10)); // Margen interno

		JScrollPane scrollArrendador = new JScrollPane(areaResumenArrendador);
		tabbedPaneResumen.addTab("Arrendador", scrollArrendador); // Añadimos la pestaña

		areaResumenInmueble = new JTextArea(10, 30);
		areaResumenInmueble.setEditable(false);
		areaResumenInmueble.setFont(new Font("Monospaced", Font.PLAIN, 13));
		areaResumenInmueble.setMargin(new Insets(10, 10, 10, 10));

		JScrollPane scrollInmueble = new JScrollPane(areaResumenInmueble);
		tabbedPaneResumen.addTab("Inmueble", scrollInmueble);

		tabbedPaneResumen.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				actualizarTextosResumen();
			}
		});

		panelResumen.add(tabbedPaneResumen, BorderLayout.CENTER);

		panelResumen.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));

		panelContenedorCentral.add(panelResumen);
		panelContenedorCentral.add(Box.createVerticalStrut(10));
	}

	/**
	 * Método auxiliar que recoge los datos de los campos y los formatea en los
	 * JTextArea del resumen.
	 */
	private void actualizarTextosResumen() {
		// Resumen del arrendador
		StringBuilder sbArr = new StringBuilder();
		sbArr.append("DATOS DEL ARRENDADOR\n");
		sbArr.append("---------------------\n\n");
		sbArr.append(String.format("%-15s: %s\n", "Nombre", textoNombre.getText()));
		sbArr.append(String.format("%-15s: %s\n", "Apellidos", textoApellidos.getText()));
		sbArr.append(String.format("%-15s: %s\n", "DNI", textoDni.getText()));
		sbArr.append(String.format("%-15s: %s\n", "Teléfono", textoTelefono.getText()));

		areaResumenArrendador.setText(sbArr.toString());

		// Resumen del inmueble
		StringBuilder sbInm = new StringBuilder();
		sbInm.append("DATOS DEL INMUEBLE\n");
		sbInm.append("---------------------\n\n");
		sbInm.append(String.format("%-20s: %s\n", "Dirección", textoDireccion.getText()));
		sbInm.append(String.format("%-20s: %s\n", "Provincia", comboProvincia.getSelectedItem()));

		// Formatear fechas
		JSpinner.DateEditor editorAlta = (JSpinner.DateEditor) spinnerFechaAlta.getEditor();
		String fechaAltaStr = editorAlta.getFormat().format(spinnerFechaAlta.getValue());
		sbInm.append(String.format("%-20s: %s\n", "Fecha de alta", fechaAltaStr));

		JSpinner.DateEditor editorFin = (JSpinner.DateEditor) spinnerFechaFin.getEditor();
		String fechaFinStr = editorFin.getFormat().format(spinnerFechaFin.getValue());
		sbInm.append(String.format("%-20s: %s\n", "Fecha fin de disponivilidad", fechaFinStr));

		sbInm.append("\nCAPACIDAD Y EXTRAS\n");
		sbInm.append("------------------\n");
		sbInm.append(String.format("%-20s: %d\n", "Huéspedes", spinnerHuespedes.getValue()));
		sbInm.append(String.format("%-20s: %d\n", "Habitaciones", spinnerDormitorios.getValue()));
		sbInm.append(String.format("%-20s: %d\n", "Baños", spinnerBaños.getValue()));
		sbInm.append(String.format("%-20s: %d\n", "Camas", spinnerCamas.getValue()));

		if (comboTipoCama.isVisible()) {
			sbInm.append(String.format("%-20s: %s\n", "Tipo Cama", comboTipoCama.getSelectedItem()));
		}

		if (checkNinos.isSelected()) {
			sbInm.append(String.format("%-20s: %s (%d años)\n", "Niños", textoTipoCamaNiño.getText(),
					spinnerEdadNiños.getValue()));
		} else {
			sbInm.append(String.format("%-20s: %s\n", "Niños", "No"));
		}

		sbInm.append("\n==================\n");
		sbInm.append(String.format("%-20s: %s", "PRECIO MÍNIMO", labelPrecioMinimo.getText()));

		areaResumenInmueble.setText(sbInm.toString());
	}

	/**
	 * Botones de acción Imprimir (vuelca a resumen), Nuevo (limpia) y Guardar
	 * (default).
	 */
	private void agregarPanelBotones() {
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

		// Boton de imprimir
		JButton botonImprimir = new JButton("Imprimir a documento");
		configurarAccesibilidad(new JLabel(), botonImprimir, 'I', "Vuelca los datos al panel de resumen");

		try {
			ImageIcon iconoImprimir = new ImageIcon(getClass().getResource("/recursos/imprimir.png"));
			Image imgagen = iconoImprimir.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			botonImprimir.setIcon(new ImageIcon(imgagen));
		} catch (Exception e) {
			/* Sin icono */ }

		botonImprimir.addActionListener(e -> {
			if (validarCamposObligatorios()) {
				actualizarTextosResumen();
				JOptionPane.showMessageDialog(this, "Datos volcados al resumen correctamente.", "Imprimir",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		// Boton nuevo
		JButton btnNuevo = new JButton("Nuevo");
		configurarAccesibilidad(new JLabel(), btnNuevo, 'N', "Limpia el formulario para un nuevo registro");

		try {
			ImageIcon iconNuevo = new ImageIcon(getClass().getResource("/recursos/nuevo.png"));
			Image img = iconNuevo.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnNuevo.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			/* Sin icono */ }

		btnNuevo.addActionListener(e -> limpiarFormulario());

		// Boton de guardar
		JButton btnGuardar = new JButton("Guardar");
		configurarAccesibilidad(new JLabel(), btnGuardar, 'G', "Guarda el registro actual");

		try {
			ImageIcon iconGuardar = new ImageIcon(getClass().getResource("/recursos/guardar.png"));
			Image img = iconGuardar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
			btnGuardar.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			/* Sin icono */ }

		btnGuardar.addActionListener(e -> {
			if (validarCamposObligatorios()) {
				JOptionPane.showMessageDialog(this, "Registro Guardado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		this.getRootPane().setDefaultButton(btnGuardar);

		panelBotones.add(botonImprimir);
		panelBotones.add(btnNuevo);
		panelBotones.add(btnGuardar);

		panelContenedorCentral.add(panelBotones);
		panelContenedorCentral.add(Box.createVerticalStrut(10));
	}

	/**
	 * Valida que los campos obligatorios tengan datos y cumplan el formato. Muestra
	 * mensaje de error si falla.
	 *
	 * @return true, if successful
	 */
	private boolean validarCamposObligatorios() {
		StringBuilder errores = new StringBuilder();

		if (textoNombre.getText().trim().isEmpty()) {
			errores.append("Nombre del arrendador vacío.\n");
		}
		if (textoDni.getText().trim().isEmpty()) {
			errores.append("DNI vacío.\n");
		}
		if (textoDireccion.getText().trim().isEmpty()) {
			errores.append("Dirección del inmueble vacía.\n");
		}

		if (textoDni.getBorder() instanceof javax.swing.border.LineBorder) {
			Color colorBorde = ((javax.swing.border.LineBorder) textoDni.getBorder()).getLineColor();
			if (colorBorde.equals(Color.RED)) {
				errores.append("El formato del DNI es incorrecto.\n");
			}
		}

		if (textoTelefono.getBorder() instanceof javax.swing.border.LineBorder) {
			Color colorBorde = ((javax.swing.border.LineBorder) textoTelefono.getBorder()).getLineColor();
			if (colorBorde.equals(Color.RED)) {
				errores.append("El formato del Teléfono es incorrecto.\n");
			}
		}

		if (errores.length() > 0) {
			JOptionPane.showMessageDialog(this, "Por favor, corrija los siguientes errores:\n" + errores.toString(),
					"Error de Validación", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Restablece todos los campos a su valor por defecto.
	 */
	private void limpiarFormulario() {
		textoNombre.setText("");
		textoApellidos.setText("");
		textoDni.setText("");
		textoTelefono.setText("");
		textoDireccion.setText("");

		comboProvincia.setSelectedIndex(0);
		if (comboTipoCama != null) {
			comboTipoCama.setSelectedIndex(0);
		}

		Date hoy = new Date();
		spinnerFechaAlta.setValue(hoy);

		spinnerHuespedes.setValue(0);
		spinnerDormitorios.setValue(0);
		spinnerBaños.setValue(0);
		spinnerCamas.setValue(0);

		checkNinos.setSelected(false);
		spinnerEdadNiños.setValue(0);

		textoDni.setBorder(UIManager.getBorder("TextField.border"));
		textoTelefono.setBorder(UIManager.getBorder("TextField.border"));

		textoNombre.requestFocus();
	}

	// String regexEmail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	/**
	 * Clase auxiliar para las expresiones regulares en las comprobaciones.
	 */
	class ExpresionRegularInputVerifier extends InputVerifier {

		/** The expresion regular. */
		private final String expresionRegular;

		/** The mensaje error. */
		private final String mensajeError;

		/**
		 * Instantiates a new expresion regular input verifier.
		 *
		 * @param expresionRegular the expresion regular
		 * @param mensajeError     the mensaje error
		 */
		public ExpresionRegularInputVerifier(String expresionRegular, String mensajeError) {
			this.expresionRegular = expresionRegular;
			this.mensajeError = mensajeError;
		}

		/**
		 * Verify.
		 *
		 * @param input the input
		 * @return true, if successful
		 */
		@Override
		public boolean verify(JComponent input) {
			JTextField tf = (JTextField) input;
			String texto = tf.getText().trim();

			if (texto.isEmpty()) {
				return true;
			}

			return texto.matches(expresionRegular);
		}

		/**
		 * Should yield focus.
		 *
		 * @param input the input
		 * @return true, if successful
		 */
		@Override
		public boolean shouldYieldFocus(JComponent input) {
			boolean valido = verify(input);

			if (valido) {

				input.setBorder(UIManager.getBorder("TextField.border"));
			} else {
				input.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				JOptionPane.showMessageDialog(input, mensajeError, "Error de formato", JOptionPane.WARNING_MESSAGE);
			}

			return true;
		}
	}
}