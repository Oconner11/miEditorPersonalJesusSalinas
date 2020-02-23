package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.rtf.RTFEditorKit;


public class VistaEditor extends JFrame {
	/**
	 * 
	 */
	// Declaración de todos los componentes del editor
	private static final long serialVersionUID = 1L;
	JPanel panelGeneral;
	JTextPane hojaTexto;
	JMenuBar barraDeMenu;
	JToolBar barraDeHerramientas;
	JMenu menuArchivo, menuEstilo, menuParrafo, menuColor, menuInformacion, menuLenguaje, menuSkin;
	JToggleButton btNegrita, btSubrayado, btCursiva, btEspanya, btReinoUnido, btJustificado, btCentrado,
			btAlineadoIzquierda, btAlineadoDerecha;
	JButton btAbrir,btNuevo, btGuardar, btSalir, btImagen;
	JMenuItem itemEspanyol, itemIngles;
	JMenuItem itemCentrado, itemAlineadoDerecha, itemAlineadoIzquierda, itemJustificado, itemNegrita, itemCursiva,
			itemSubrayado, itemInformacion, itemColor, cortar, copiar, pegar, popNegrita, popCursiva, popSubrayado,
			popCentrado, popAlineadoDerecha, popAlineadoIzquierda, popJustificado, itemAbrir, itemNuevo, itemGuardar,
			itemSalir;
	JComboBox boxTamanyoLetra, boxFuentes;
	JLabel labelFuente, labelTmanyoFuente;
	ButtonGroup grupoParrafo, grupoIdiomas;
	JScrollPane scroll;
	boolean compruebaNegrita = false;
	boolean compruebaCursiva = false;
	boolean compruebaSubrayado = false;
	boolean compruebaIzquierda = false;
	boolean compruebaDerecha = false;
	boolean compruebaCentrado = false;
	boolean compruebaJustificado = false;

	// Constructor de la clase
	public VistaEditor() {
		initComponent();

	}

	// Lo que hacemos en el método es crear e inicializar todos los componentes, 	
	// y añadirles funcionalidad a traves de los listeners, apoyandose en los métodos que hemos creado

	public void initComponent() {
		// Ponemos el nombre de la ventana, el icono y el ponemos fame en pantalla completa.
		setTitle("Mi editor personal Jesús Salinas Molina");
		try {
			ImageIcon img = new ImageIcon("resources/iconoEditor.jpg");
			setIconImage(img.getImage());
		} catch (Exception e) {
			System.out.println("El icono no se ha cargado correctamente.");
		}
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Instanciamos todos los componentes que vamos a usar
		panelGeneral = new JPanel(new BorderLayout());
		hojaTexto = new JTextPane();
		barraDeMenu = new JMenuBar();
		barraDeHerramientas = new JToolBar();
		boxTamanyoLetra = new JComboBox(obtenerTamanyos());
		boxFuentes = new JComboBox(obtenerFuentes());
		labelFuente = new JLabel("Fuente  ");
		labelTmanyoFuente = new JLabel("Tamaño  ");
		grupoParrafo = new ButtonGroup();
		grupoIdiomas = new ButtonGroup();

		// Componentes de la barra de menús (JMenuBar)
		menuArchivo = new JMenu("Archivo");
		menuEstilo = new JMenu("Estilo");
		menuParrafo = new JMenu("Párrafo");
		menuColor = new JMenu("Color");
		menuInformacion = new JMenu("Información");
		menuLenguaje = new JMenu("Lenguaje");
		menuSkin = new JMenu("Skin");

		try {

			// Botones de la barra de herramientas (JToolBar)
			btAbrir = new JButton(new ImageIcon("resources/abrir.png"));
			btNuevo = new JButton(new ImageIcon("resources/nuevo.png"));
			btGuardar = new JButton(new ImageIcon("resources/guardar2.png"));
			btSalir = new JButton(new ImageIcon("resources/salir.png"));
			btAlineadoIzquierda = new JToggleButton(new ImageIcon("resources/alineadoIzquierda.png"));
			btCentrado = new JToggleButton(new ImageIcon("resources/centrado.png"));
			btAlineadoDerecha = new JToggleButton(new ImageIcon("resources/alineadoDerecha.png"));
			btJustificado = new JToggleButton(new ImageIcon("resources/justificado.png"));
			btEspanya = new JToggleButton(new ImageIcon("resources/espanya.png"));
			btReinoUnido = new JToggleButton(new ImageIcon("resources/reinoUnido.png"));
			btNegrita = new JToggleButton(new ImageIcon("resources/negrita.png"));
			btCursiva = new JToggleButton(new ImageIcon("resources/cursiva.png"));
			btSubrayado = new JToggleButton(new ImageIcon("resources/subrayado.png"));
			btImagen = new JButton(new ImageIcon("resources/imagen.png"));

			// Items de cada uno de los componentes de la barra de menús
			itemAbrir = new JMenuItem("Abrir", new ImageIcon("resources/abrir.png"));
			itemNuevo = new JMenuItem("Abrir", new ImageIcon("resources/nuevo.png"));
			itemGuardar = new JMenuItem("Guardar", new ImageIcon("resources/guardar2.png"));
			itemSalir = new JMenuItem("Salir", new ImageIcon("resources/salir.png"));
			itemNegrita = new JMenuItem(" Negrita", new ImageIcon("resources/negrita.png"));
			itemCursiva = new JMenuItem(" Cursiva", new ImageIcon("resources/cursiva.png"));
			itemSubrayado = new JMenuItem(" Subrayado", new ImageIcon("resources/subrayado.png"));
			itemAlineadoIzquierda = new JMenuItem("Izquierda", new ImageIcon("resources/alineadoIzquierda.png"));
			itemCentrado = new JMenuItem("Centrado", new ImageIcon("resources/centrado.png"));
			itemAlineadoDerecha = new JMenuItem("Derecha", new ImageIcon("resources/alineadoDerecha.png"));
			itemJustificado = new JMenuItem("Justificado", new ImageIcon("resources/Justificado.png"));
			itemEspanyol = new JMenuItem("Español", new ImageIcon("resources/espanya.png"));
			itemIngles = new JMenuItem("Inglés", new ImageIcon("resources/reinoUnido.png"));
			itemColor = new JMenuItem("Color");
			itemInformacion = new JMenuItem("Información");

			// Items de la ventana emergente al pulsar el botón derecho
			cortar = new JMenuItem("Cortar", new ImageIcon("resources/cortar.png"));
			copiar = new JMenuItem("Copiar", new ImageIcon("resources/copiar2.png"));
			pegar = new JMenuItem("Pegar", new ImageIcon("resources/pegar.png"));
			popCursiva = new JMenuItem("Cursiva", new ImageIcon("resources/cursiva.png"));
			popNegrita = new JMenuItem("Negrita", new ImageIcon("resources/negrita.png"));
			popSubrayado = new JMenuItem("Subrayado", new ImageIcon("resources/subrayado.png"));
			popAlineadoIzquierda = new JMenuItem(" Izquierda", new ImageIcon("resources/alineadoIzquierda.png"));
			popCentrado = new JMenuItem("Centrado", new ImageIcon("resources/centrado.png"));
			popAlineadoDerecha = new JMenuItem("Derecha", new ImageIcon("resources/alineadoDerecha.png"));
			popJustificado = new JMenuItem("Justificado", new ImageIcon("resources/Justificado.png"));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Alguna de las imágenes no se ha cargado correctamanete. ");
		}

		// Texto explicativo de los componentes
		btNegrita.setToolTipText("Negrita");
		btCursiva.setToolTipText("Cursiva");
		btSubrayado.setToolTipText("Subrayado");
		btAbrir.setToolTipText("Abrir");
		btNuevo.setToolTipText("Nuevo Documento");
		btGuardar.setToolTipText("Guardar");
		btSalir.setToolTipText("Salir");
		btAlineadoIzquierda.setToolTipText("Alineado Izquierda");
		btCentrado.setToolTipText("Texto Centrado");
		btAlineadoDerecha.setToolTipText("Alineado Derecha");
		btJustificado.setToolTipText("Texto Justificado");
		btEspanya.setToolTipText("Idioma Español");
		btReinoUnido.setToolTipText("Idioma Inglés");
		btImagen.setToolTipText("Insertar Imagen");
		btNuevo.setToolTipText("Nuevo Documento");

		// Atajos de teclado para todos los componentes que lo necesitan
		itemAbrir.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemGuardar.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_G, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemSalir.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemNegrita.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_B, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemCursiva.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_I, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemSubrayado.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemAlineadoIzquierda.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemAlineadoDerecha.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_D, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemCentrado.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemJustificado.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_J, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		cortar.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		copiar.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		pegar.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		popNegrita.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_B, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		popCursiva.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_I, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		popSubrayado.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		popAlineadoIzquierda.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_L, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		popAlineadoDerecha.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_D, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		popCentrado.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		popJustificado.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_J, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		itemNuevo.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

		// Escuchadores del editor
		btAbrir.addActionListener(actionListener());
		btNuevo.addActionListener(actionListener());
		btGuardar.addActionListener(actionListener());		
		btSalir.addActionListener(actionListener());
		btNegrita.addActionListener(actionListener());
		btCursiva.addActionListener(actionListener());
		btSubrayado.addActionListener(actionListener());
		btAlineadoIzquierda.addActionListener(actionListener());
		btCentrado.addActionListener(actionListener());
		btAlineadoDerecha.addActionListener(actionListener());
		btJustificado.addActionListener(actionListener());
		btImagen.addActionListener(actionListener());
		btEspanya.addActionListener(actionListener());
		btReinoUnido.addActionListener(actionListener());
		itemAbrir.addActionListener(actionListener());
		itemGuardar.addActionListener(actionListener());
		itemSalir.addActionListener(actionListener());
		itemNegrita.addActionListener(actionListener());
		itemCursiva.addActionListener(actionListener());
		itemAlineadoIzquierda.addActionListener(actionListener());
		itemCentrado.addActionListener(actionListener());
		itemAlineadoDerecha.addActionListener(actionListener());
		itemJustificado.addActionListener(actionListener());
		itemSubrayado.addActionListener(actionListener());
		itemColor.addActionListener(actionListener());
		itemEspanyol.addActionListener(actionListener());
		itemIngles.addActionListener(actionListener());
		itemNuevo.addActionListener(actionListener());
		cortar.addActionListener(actionListener());
		copiar.addActionListener(actionListener());
		pegar.addActionListener(actionListener());
		popNegrita.addActionListener(actionListener());
		popCursiva.addActionListener(actionListener());
		popSubrayado.addActionListener(actionListener());
		popAlineadoIzquierda.addActionListener(actionListener());
		popCentrado.addActionListener(actionListener());
		popAlineadoDerecha.addActionListener(actionListener());
		popJustificado.addActionListener(actionListener());

		// Llmamada al método que genera la ventana emergente de információn
		mostrarInformacion(itemInformacion);

		boxFuentes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = boxFuentes.getSelectedItem().toString();
				new StyledEditorKit.FontFamilyAction("", String.valueOf(nombre)).actionPerformed(e);
				hojaTexto.grabFocus();
			}
		});

		boxTamanyoLetra.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int tamanyo = Integer.parseInt(boxTamanyoLetra.getSelectedItem().toString());
				new StyledEditorKit.FontSizeAction("", Integer.valueOf(tamanyo)).actionPerformed(e);
				hojaTexto.grabFocus();

			}
		});

		// Cambio de skin
		LookAndFeelInfo[] skins = UIManager.getInstalledLookAndFeels();
		for (LookAndFeelInfo a : skins) {
			JMenuItem skin = new JMenuItem(a.getName());
			skin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						UIManager.setLookAndFeel(a.getClassName());
						SwingUtilities.updateComponentTreeUI(panelGeneral);

					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
				}
			});
			// Añadimos los items que se crean en el bucle al menu de Skin
			menuSkin.add(skin);
		}

		// Añadimos a cada menu sus items correspondientes
		barraDeMenu.add(menuArchivo);
		barraDeMenu.add(menuEstilo);
		barraDeMenu.add(menuParrafo);
		barraDeMenu.add(menuColor);
		barraDeMenu.add(menuInformacion);
		barraDeMenu.add(menuLenguaje);
		barraDeMenu.add(menuSkin);
		menuArchivo.add(itemAbrir);
		menuArchivo.add(itemNuevo);
		menuArchivo.add(itemGuardar);
		menuArchivo.add(itemSalir);
		menuEstilo.add(itemNegrita);
		menuEstilo.add(itemCursiva);
		menuEstilo.add(itemSubrayado);
		menuParrafo.add(itemAlineadoIzquierda);
		menuParrafo.add(itemCentrado);
		menuParrafo.add(itemAlineadoDerecha);
		menuParrafo.add(itemJustificado);
		menuColor.add(itemColor);
		menuInformacion.add(itemInformacion);
		menuLenguaje.add(itemEspanyol);
		menuLenguaje.add(itemIngles);
		// Añadimos los botones a la barra de herramientas con algunos separadore para
		// mejorar la apariencia
		barraDeHerramientas.add(btAbrir);
		barraDeHerramientas.add(btGuardar);
		barraDeHerramientas.add(btNuevo);
		barraDeHerramientas.add(btSalir);
		barraDeHerramientas.addSeparator();
		barraDeHerramientas.add(labelFuente);
		barraDeHerramientas.add(boxFuentes);
		barraDeHerramientas.addSeparator();
		barraDeHerramientas.add(labelTmanyoFuente);
		barraDeHerramientas.add(boxTamanyoLetra);
		barraDeHerramientas.addSeparator();
		barraDeHerramientas.add(btNegrita);
		barraDeHerramientas.add(btCursiva);
		barraDeHerramientas.add(btSubrayado);
		barraDeHerramientas.addSeparator();
		barraDeHerramientas.add(btAlineadoIzquierda);
		barraDeHerramientas.add(btCentrado);
		barraDeHerramientas.add(btAlineadoDerecha);
		barraDeHerramientas.add(btJustificado);
		barraDeHerramientas.addSeparator();
		barraDeHerramientas.add(btEspanya);
		barraDeHerramientas.add(btReinoUnido);
		barraDeHerramientas.addSeparator();
		barraDeHerramientas.add(btImagen);
		barraDeHerramientas.addSeparator();

		// Añadimos los botones que necesiten un grupo a su grupo correspondiente
		// Lo que hace el grupo es que sean exclusivos unos de otros.
		grupoParrafo.add(btAlineadoIzquierda);
		grupoParrafo.add(btCentrado);
		grupoParrafo.add(btAlineadoDerecha);
		grupoParrafo.add(btJustificado);
		btAlineadoIzquierda.setSelected(true);
		grupoIdiomas.add(btEspanya);
		grupoIdiomas.add(btReinoUnido);
		btEspanya.setSelected(true);

		// Inicializaciones del editor
		// Ponemos fuente Arial, texto normal y la fuente al 12.
		hojaTexto.setFont(new Font(boxFuentes.getItemAt(3).toString(), NORMAL, 12));
		boxFuentes.setSelectedIndex(3);
		boxFuentes.setMaximumSize(boxFuentes.getPreferredSize());
		boxTamanyoLetra.setSelectedIndex(3);
		boxTamanyoLetra.setMaximumSize(boxTamanyoLetra.getPreferredSize());
		itemEspanyol.setBackground(Color.GRAY);
		itemAlineadoIzquierda.setBackground(Color.GRAY);

		// Añadimos el panel genereal a su lugar corresmpondiente que es arriba
		add(panelGeneral, BorderLayout.NORTH);

		// Añadimos al panel la barra de menus y la de herramientas y le ponemos su
		// ubicación,
		// la barra de menús arriba y la bara de herramientas justo debajo
		panelGeneral.add(barraDeMenu, BorderLayout.NORTH);
		panelGeneral.add(barraDeHerramientas, BorderLayout.SOUTH);

		// Añadimos al JScrollPanel el JText Pane llmado hojaTexto
		scroll = new JScrollPane(hojaTexto);
		scroll.setAutoscrolls(true);
		add(scroll, BorderLayout.CENTER);

		// Ponemos visibles todos los elementos
		barraDeHerramientas.setFloatable(false);
		hojaTexto.setVisible(true);
		barraDeMenu.setVisible(true);
		panelGeneral.setVisible(true);
		setVisible(true);

	}

	// Métodos utilizados en el editor

	// Lo que hace es devolver un array de las fuentes instaladas en el sistema, que
	// son las que vamos a utilizar
	private String[] obtenerFuentes() {
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fuentes = e.getAvailableFontFamilyNames();
		return fuentes;
	}

	// Devuelve un array de enteros con los tamaños de letra que me han parecido
	// oportunos para el editor
	private Integer[] obtenerTamanyos() {
		Integer[] n = new Integer[] { 8, 9, 10, 12, 14, 18, 24, 30, 36, 48, 60, 72, 96 };
		return n;

	}

	// Método para abrir documentos desde el explorador de archivos
	private String abrirDocumentos() {
		String aux = "";
		String texto = "";
		JFileChooser file = new JFileChooser();
		file.showOpenDialog(this);
		File abrir = file.getSelectedFile();

		if (abrir != null) {
			try {
				FileReader archivo = new FileReader(abrir);
				BufferedReader lee = new BufferedReader(archivo);
				while ((aux = lee.readLine()) != null) {
					texto += aux + "\n";
				}

				lee.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return texto;

	}

	// Método para guardar un documento respetando el formato que le hayamos puesto
	private void guardarConFormato() {
		JFileChooser file = new JFileChooser();
		if (file.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			String nombreDocumento = file.getSelectedFile().getAbsolutePath();
			StyledDocument styledDocument = (StyledDocument) hojaTexto.getDocument();
			RTFEditorKit kit = new RTFEditorKit();
			BufferedOutputStream bufferedOutputStream;
			try {
				bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(nombreDocumento));
				kit.write(bufferedOutputStream, styledDocument, styledDocument.getStartPosition().getOffset(),
						styledDocument.getLength());
				bufferedOutputStream.flush();
				bufferedOutputStream.close();

			} catch (IOException | BadLocationException e) {
				e.printStackTrace();
			}
		}
	}

	// Método para cargar imagenes en el JTextPane, llamado hojaTexto
	private void cargarImagenes() {
		JFileChooser fileChooserchooser = new JFileChooser();
		int valorFileChooser = fileChooserchooser.showOpenDialog(this);
		if (valorFileChooser == JFileChooser.APPROVE_OPTION) {
			File imagenSeleccionada = fileChooserchooser.getSelectedFile();
			hojaTexto.insertIcon(new ImageIcon(imagenSeleccionada.getAbsolutePath()));
		}

	}

	// Genera una ventana emergente con información mediante un JOptionPane
	private void mostrarInformacion(JMenuItem item) {
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Versión 1.0 \n" + "Autor: Jesús Salinas Molina", "Informacion",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
		});

	}

	// El método genera una ventana emergente para salir de la aplicación
	private void ventanaSalir() {
		Object[] opciones = { "Salir", "Cancelar" };

		int n = JOptionPane.showOptionDialog(this, "¿Desea Salir?", "", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);

		if (n == 0) {
			System.exit(0);

		}

	}

	// Genera una ventana emergente al pulsar el boton derecho del raton dónde
	// pulsemos dentro del JTextPane
	private void mostrarMenuBotonDerecho(JTextPane j) {
		JPopupMenu popMenu = new JPopupMenu();
		popMenu.add(cortar);
		popMenu.add(copiar);
		popMenu.add(pegar);
		popMenu.addSeparator();
		popMenu.add(popNegrita);
		popMenu.add(popCursiva);
		popMenu.add(popSubrayado);
		popMenu.addSeparator();
		popMenu.add(popAlineadoIzquierda);
		popMenu.add(popCentrado);
		popMenu.add(popAlineadoDerecha);
		popMenu.add(popJustificado);

		j.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == e.BUTTON3) {
					popMenu.show(j, e.getX(), e.getY());
				}
			}
		});
		j.add(popMenu);

	}

	// Método para la coherencia de la interfaz y casi toda la funcionalidad del
	// editor

	private ActionListener actionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Comprueba si se ha pulsado alguno de los componentes asociados al alineamieto
				// izquiedo del párrafo,
				// para marcar en el caso de que pulsemos por primera vez o desmarcar si ya
				// hemos pulsado,
				// además de aprotarle la funcionalidad necesaria para que cambie el formato de
				// párrafo.
				if (e.getSource() == btAlineadoIzquierda || e.getSource() == itemAlineadoIzquierda
						|| e.getSource() == popAlineadoIzquierda) {
					new StyledEditorKit.AlignmentAction(hojaTexto.getText(), 0).actionPerformed(e);
					if (!compruebaIzquierda) {
						compruebaIzquierda = true;
						btAlineadoIzquierda.setSelected(true);
						itemAlineadoIzquierda.setBackground(Color.GRAY);
						popAlineadoIzquierda.setBackground(Color.GRAY);
						itemAlineadoDerecha.setBackground(Color.decode("#EEEEEE"));
						popAlineadoDerecha.setBackground(Color.decode("#EEEEEE"));
						compruebaDerecha = false;
						itemCentrado.setBackground(Color.decode("#EEEEEE"));
						popCentrado.setBackground(Color.decode("#EEEEEE"));
						compruebaCentrado = false;
						itemJustificado.setBackground(Color.decode("#EEEEEE"));
						popJustificado.setBackground(Color.decode("#EEEEEE"));
						compruebaJustificado = false;
					} else {
						itemAlineadoIzquierda.setBackground(Color.decode("#EEEEEE"));
						popAlineadoIzquierda.setBackground(Color.decode("#EEEEEE"));
						compruebaIzquierda = false;
					}

				}
				// Comprueba si se ha pulsado alguno de los componentes asociados al alineamieto
				// centrado,
				// para marcar en el caso de que pulsemos por primera vez o desmarcar si ya
				// hemos pulsado,
				// además de aprotarle la funcionalidad necesaria para que cambie el formato de
				// párrafo.
				else if (e.getSource() == btCentrado || e.getSource() == itemCentrado || e.getSource() == popCentrado) {
					new StyledEditorKit.AlignmentAction(hojaTexto.getText(), 1).actionPerformed(e);
					if (!compruebaCentrado) {
						compruebaCentrado = true;
						btCentrado.setSelected(true);
						itemCentrado.setBackground(Color.GRAY);
						popCentrado.setBackground(Color.GRAY);
						itemAlineadoIzquierda.setBackground(Color.decode("#EEEEEE"));
						popAlineadoIzquierda.setBackground(Color.decode("#EEEEEE"));
						compruebaIzquierda = false;
						itemAlineadoDerecha.setBackground(Color.decode("#EEEEEE"));
						popAlineadoDerecha.setBackground(Color.decode("#EEEEEE"));
						compruebaDerecha = false;
						itemJustificado.setBackground(Color.decode("#EEEEEE"));
						popJustificado.setBackground(Color.decode("#EEEEEE"));
						compruebaJustificado = false;

					} else {
						itemCentrado.setBackground(Color.decode("#EEEEEE"));
						popCentrado.setBackground(Color.decode("#EEEEEE"));
						compruebaCentrado = false;

					}
				}

				// Comprueba si se ha pulsado alguno de los componentes asociados al alineamieto
				// derecho del párrafo,
				// para marcar en el caso de que pulsemos por primera vez o desmarcar si ya
				// hemos pulsado,
				// además de aprotarle la funcionalidad necesaria para que cambie el formato de
				// párrafo.
				else if (e.getSource() == btAlineadoDerecha || e.getSource() == itemAlineadoDerecha
						|| e.getSource() == popAlineadoDerecha) {
					new StyledEditorKit.AlignmentAction(hojaTexto.getText(), 2).actionPerformed(e);
					if (!compruebaDerecha) {
						compruebaDerecha = true;
						btAlineadoDerecha.setSelected(true);
						itemAlineadoDerecha.setBackground(Color.GRAY);
						popAlineadoDerecha.setBackground(Color.GRAY);
						itemAlineadoIzquierda.setBackground(Color.decode("#EEEEEE"));
						popAlineadoIzquierda.setBackground(Color.decode("#EEEEEE"));
						compruebaIzquierda = false;
						itemCentrado.setBackground(Color.decode("#EEEEEE"));
						popCentrado.setBackground(Color.decode("#EEEEEE"));
						compruebaCentrado = false;
						itemJustificado.setBackground(Color.decode("#EEEEEE"));
						popJustificado.setBackground(Color.decode("#EEEEEE"));
						compruebaJustificado = false;

					} else {
						itemAlineadoDerecha.setBackground(Color.decode("#EEEEEE"));
						popAlineadoDerecha.setBackground(Color.decode("#EEEEEE"));
						compruebaDerecha = false;

					}
				}
				// Comprueba si se ha pulsado alguno de los componentes asociados al alineamieto
				// justificado del párrafo,
				// para marcar en el caso de que pulsemos por primera vez o desmarcar si ya
				// hemos pulsado,
				// además de aprotarle la funcionalidad necesaria para que cambie el formato de
				// párrafo.
				else if (e.getSource() == btJustificado || e.getSource() == itemJustificado
						|| e.getSource() == popJustificado) {
					new StyledEditorKit.AlignmentAction(hojaTexto.getText(), 3).actionPerformed(e);
					if (!compruebaJustificado) {
						compruebaJustificado = true;
						btJustificado.setSelected(true);
						itemJustificado.setBackground(Color.GRAY);
						popJustificado.setBackground(Color.GRAY);
						itemAlineadoIzquierda.setBackground(Color.decode("#EEEEEE"));
						popAlineadoIzquierda.setBackground(Color.decode("#EEEEEE"));
						compruebaIzquierda = false;
						itemAlineadoDerecha.setBackground(Color.decode("#EEEEEE"));
						popAlineadoDerecha.setBackground(Color.decode("#EEEEEE"));
						compruebaDerecha = false;
						itemCentrado.setBackground(Color.decode("#EEEEEE"));
						popCentrado.setBackground(Color.decode("#EEEEEE"));
						compruebaCentrado = false;

					} else {
						itemJustificado.setBackground(Color.decode("#EEEEEE"));
						popJustificado.setBackground(Color.decode("#EEEEEE"));
						compruebaJustificado = false;
					}

				}
				// Comprueba si se ha pulsado alguno de los componentes asociados a la negrita,
				// para marcar en el caso de que pulsemos por primera vez o desmarcar si ya
				// hemos pulsado,
				// además de aprotarle la funcionalidad necesaria para que cambie el estilo.
				else if (e.getSource() == btNegrita || e.getSource() == itemNegrita || e.getSource() == popNegrita) {
					new StyledEditorKit.BoldAction().actionPerformed(e);
					if (!compruebaNegrita) {
						itemNegrita.setBackground(Color.GRAY);
						popNegrita.setBackground(Color.GRAY);
						btNegrita.setSelected(true);
						compruebaNegrita = true;
					} else {
						itemNegrita.setBackground(Color.decode("#EEEEEE"));
						popNegrita.setBackground(Color.decode("#EEEEEE"));
						btNegrita.setSelected(false);
						compruebaNegrita = false;
					}

				}
				// Comprueba si se ha pulsado alguno de los componentes asociados a la cursiva,
				// para marcar en el caso de que pulsemos por primera vez o desmarcar si ya
				// hemos pulsado,
				// además de aprotarle la funcionalidad necesaria para que cambie el estilo.
				else if (e.getSource() == btCursiva || e.getSource() == itemCursiva || e.getSource() == popCursiva) {
					new StyledEditorKit.ItalicAction().actionPerformed(e);
					if (!compruebaCursiva) {

						itemCursiva.setBackground(Color.GRAY);
						popCursiva.setBackground(Color.GRAY);
						btCursiva.setSelected(true);
						compruebaCursiva = true;
					} else {
						itemCursiva.setBackground(Color.decode("#EEEEEE"));
						popCursiva.setBackground(Color.decode("#EEEEEE"));
						btCursiva.setSelected(false);
						compruebaCursiva = false;
					}
				}
				// Comprueba si se ha pulsado alguno de los componentes asociados al subrayado,
				// para marcar en el caso de que pulsemos por primera vez o desmarcar si ya
				// hemos pulsado,
				// además de aprotarle la funcionalidad necesaria para que cambie el estilo.
				else if (e.getSource() == btSubrayado || e.getSource() == itemSubrayado
						|| e.getSource() == popSubrayado) {
					new StyledEditorKit.UnderlineAction().actionPerformed(e);
					if (!compruebaSubrayado) {
						itemSubrayado.setBackground(Color.GRAY);
						popSubrayado.setBackground(Color.GRAY);
						btSubrayado.setSelected(true);
						compruebaSubrayado = true;
					} else {
						itemSubrayado.setBackground(Color.decode("#EEEEEE"));
						popSubrayado.setBackground(Color.decode("#EEEEEE"));
						btSubrayado.setSelected(false);
						compruebaSubrayado = false;
					}
				}
				// Acción para cortar texto disponible a traves del menú emergente que se genera
				// al pulsar el botón derecho
				// o con la combinación de teclas CRTL+X
				else if (e.getSource() == cortar) {
					new DefaultEditorKit.CutAction().actionPerformed(e);

				}
				// Acción para cortar texto disponible a traves del menú emergente que se genera
				// al pulsar el botón derecho
				// o con la combinación de teclas CRTL+C.
				else if (e.getSource() == copiar) {
					new DefaultEditorKit.CopyAction().actionPerformed(e);
				}
				// Acción para cortar texto disponible a traves del menú emergente que se genera
				// al pulsar el botón derecho
				// o con la combinación de teclas CRTL+V
				else if (e.getSource() == pegar) {
					new DefaultEditorKit.PasteAction().actionPerformed(e);

				}
				// En el caso de que se pulse el botón de abrir o el item abrir, se llama al
				// método abrir documentos
				// que retorna un String y se le pasa al panel del texto.
				else if (e.getSource() == btAbrir || e.getSource() == itemAbrir) {
					hojaTexto.setText(abrirDocumentos());
				}
				// En el caso de que se pulse el botón de guardar o el item guardar, se llama al
				// método guardar documentos.
				else if (e.getSource() == btGuardar || e.getSource() == itemGuardar) {
					guardarConFormato();
				}
				// En el caso de que vayamos a salir del editor mediante el botón de salir,
				// apararecerá una ventana preguntando si se desea salir.
				else if (e.getSource() == btSalir || e.getSource() == itemSalir) {
					ventanaSalir();
				}
				// Cambio de color de la letra del texto mediante un panel de elección de color
				else if (e.getSource() == itemColor) {
					JColorChooser colores = new JColorChooser();
					Color color = colores.showDialog(null, "Seleccione un color", Color.black);
					new StyledEditorKit.ForegroundAction("Color", color).actionPerformed(e);
				}
				// Llama al método que gestiona la carga de imágenes
				else if (e.getSource() == btImagen) {
					cargarImagenes();
				}
				// Abre un nuevo edito
				else if (e.getSource() == itemNuevo||e.getSource() == btNuevo) {
					new VistaEditor().setVisible(true);
					;
				}
				// Cambio de idioma al español apoyandose en el ficharo de propiedades
				// Etiquetas.properties
				else if (e.getSource() == btEspanya || e.getSource() == itemEspanyol) {
					itemEspanyol.setBackground(Color.GRAY);
					itemIngles.setBackground(Color.decode("#EEEEEE"));
					btAbrir.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_abrir"));
					btGuardar.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_guardar"));
					btNuevo.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_nuevo"));
					btSalir.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_salir"));
					btAlineadoIzquierda.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_alineado_izquierda"));
					btCentrado.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_centrado"));
					btAlineadoDerecha.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_alineado_derecha"));
					btJustificado.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_justificado"));
					btNegrita.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_negrita"));
					btCursiva.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_cursiva"));
					btSubrayado.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_subrayado"));
					btImagen.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_imagen"));
					btEspanya.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_espanya"));
					btReinoUnido.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_boton_reinounido"));
					itemAbrir.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_abrir"));
					itemNuevo.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_nuevo"));
					itemGuardar.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_guardar"));
					itemSalir.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_salir"));
					itemNegrita.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_negrita"));
					itemCursiva.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_cursiva"));
					itemAlineadoIzquierda.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_alineado_izquierda"));
					itemCentrado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_centrado"));
					itemAlineadoDerecha.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_alineado_derecha"));
					itemJustificado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_justificado"));
					itemSubrayado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_subrayado"));
					itemColor.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_color"));
					itemInformacion.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_informacion"));
					itemEspanyol.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_espanya"));
					itemIngles.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_reinounido"));
					labelFuente.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_label_fuente"));
					labelTmanyoFuente.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_label_tamanyo"));
					menuArchivo.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_menu_archivo"));
					menuEstilo.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_menu_estilo"));
					menuParrafo.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_menu_parrafo"));
					menuColor.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_menu_color"));
					menuInformacion.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_menu_infomacion"));
					menuLenguaje.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_menu_lenguaje"));
					cortar.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_popmenu_cortar"));
					copiar.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_popmenu_copiar"));
					pegar.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_popmenu_pegar"));
					popNegrita.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_popmenu_negrita"));
					popCursiva.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_popmenu_cursiva"));
					popSubrayado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_popmenu_subrayado"));
					popAlineadoIzquierda.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_popmenu_alineado_izquierda"));
					popCentrado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_popmenu_centrado"));
					popAlineadoDerecha.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_item_alineado_derecha"));
					popJustificado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas").getString("etiqueta_popmenu_justificado"));

				}
				// Cambio de idioma al idima inglés apoyandose en el ficharo de propiedades
				// Etiquetas_en.properties
				else if (e.getSource() == btReinoUnido || e.getSource() == itemIngles) {
					itemIngles.setBackground(Color.GRAY);
					itemEspanyol.setBackground(Color.decode("#EEEEEE"));
					btAbrir.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_abrir"));
					btGuardar.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_guardar"));
					btNuevo.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_nuevo"));
					btSalir.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_salir"));
					btAlineadoIzquierda.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_alineado_izquierda"));
					btCentrado.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_centrado"));
					btAlineadoDerecha.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_alineado_derecha"));
					btJustificado.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_justificado"));
					btNegrita.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_negrita"));
					btCursiva.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_cursiva"));
					btSubrayado.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_subrayado"));
					btImagen.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_imagen"));
					btEspanya.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_espanya"));
					btReinoUnido.setToolTipText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_boton_reinounido"));
					itemAbrir.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_abrir"));
					itemNuevo.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_nuevo"));
					itemGuardar.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_guardar"));
					itemSalir.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_salir"));
					itemNegrita.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_negrita"));
					itemCursiva.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_cursiva"));
					itemAlineadoIzquierda.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_alineado_izquierda"));
					itemCentrado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_centrado"));
					itemAlineadoDerecha.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_alineado_derecha"));
					itemJustificado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_justificado"));
					itemSubrayado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_subrayado"));
					itemColor.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_color"));
					itemInformacion.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_informacion"));
					itemEspanyol.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_espanya"));
					itemIngles.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_reinounido"));				
					labelFuente.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_label_fuente"));
					labelTmanyoFuente.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_label_tamanyo"));
					menuArchivo.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_menu_archivo"));
					menuEstilo.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_menu_estilo"));
					menuParrafo.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_menu_parrafo"));
					menuColor.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_menu_color"));
					menuInformacion.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_menu_infomacion"));
					menuLenguaje.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_menu_lenguaje"));
					cortar.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_popmenu_cortar"));
					copiar.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_popmenu_copiar"));
					pegar.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_popmenu_pegar"));
					popNegrita.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_popmenu_negrita"));
					popCursiva.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_popmenu_cursiva"));
					popSubrayado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_popmenu_subrayado"));
					popAlineadoIzquierda.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_popmenu_alineado_izquierda"));
					popCentrado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_popmenu_centrado"));
					popAlineadoDerecha.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_item_alineado_derecha"));
					popJustificado.setText(ResourceBundle.getBundle("idiomaProperties.Etiquetas_en").getString("etiqueta_popmenu_justificado"));

				}

			}
		};

	}

}