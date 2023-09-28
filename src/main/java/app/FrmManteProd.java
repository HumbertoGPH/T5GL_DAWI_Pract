package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//Importamos los modelos
import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	
	private JTextArea txtSalida;
	private JTextField txtCodigo;
	JComboBox cboCategorias;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JLabel lblProveedor;
	private JComboBox cboProveedores;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 216);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 414, 116, 23);
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(90, 70, 109, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);
		
		lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(216, 74, 77, 14);
		contentPane.add(lblProveedor);
		
		cboProveedores = new JComboBox();
		cboProveedores.setBounds(303, 70, 110, 23);
		contentPane.add(cboProveedores);
		
		llenaCombo();
	}

	void llenaCombo() {
		//Llenar combo categorias 
		//1. Obtener conexion - llamando a persistence
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01");  //Nombre del persisten
		//2. Crear un manejador de entidades 
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso
		
		//Select * from tb_usuarios  -->List		
		String jpql =  "select c from Categoria c"; 
		List<Categoria> lstCategorias = em.createQuery(jpql, Categoria.class).getResultList();
		
		//Mostrar el Listado
		cboCategorias.addItem("Seleccione...");
	
		for(Categoria  c : lstCategorias) {
			cboCategorias.addItem(c.getDescripcion());;
			
		}
		
		//Para Llenar combo de los proveedores
		//Select * from tb_usuarios  -->List		
				String jpql2 =  "select pr from Proveedor pr"; //Para los proveedores 
				List<Proveedor> lstProveedores = em.createQuery(jpql2, Proveedor.class).getResultList();
				
				//Mostrar el Listado
				cboProveedores.addItem("Seleccione...");
			
				for(Proveedor pr : lstProveedores) {
					cboProveedores.addItem(pr.getNombre_rs());;
					
		}
		
		
		
		em.close();  //Cerramos
		
	}
	
	void listado() {
	//1. Obtener conexion - llamando a persistence
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01"); //Nombre de la persitencia
	//2. Crear un manejador de entidades 
	EntityManager em = fabrica.createEntityManager();
	
	//Proceso
	
	//Select * from tb_usuarios  -->List		
	String jpql =  "select p from Producto p"; 
	List<Producto> lstProductos = em.createQuery(jpql, Producto.class).getResultList();
	
	//Mostrar el Listado
	txtSalida.setText("Listado de Productos \n");   //Aqui esta el espacio o donde se almacenara
	imprimir("------------------------------------");
	for(Producto  p : lstProductos) {
		imprimir("Codigo...:" + p.getId_prod());
		imprimir("Nombre...:" + p.getDes_prod());
		imprimir("Categoria...:" + p.getObjCategoria().getDescripcion());
		imprimir("Proveedor...:" + p.getObjProveedor().getNombre_rs() + " - " + p.getObjProveedor().getTelefono());  //Aqui si son mas de uno se llama nuevamente
		imprimir("-----------------------------------");
		
	}
	em.close();  //Cerramos
		
	}
	//Para imprimir  o mostrarlo
	void imprimir(String msg) {

		txtSalida.append(msg + "\n");

	}
	
	
	void registrar() {
	//1. Obtener conexion - llamando a persistence
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01"); 
	//2. Crear un manejador de entidades 
	EntityManager em = fabrica.createEntityManager();
	
	//Entrada de datos
	 String id_prod = leerCodigo();  //Es por seguridad
	 String des_prod = txtDescripcion.getText();
	 int stk_prod = Integer.parseInt(txtStock.getText());  //Se parsea porque de un int se debe pasar a texto
	 double pre_prod = Double.parseDouble(leerPrecio());   //Se parsea porque de double se pasa a texto 
	 int idcategoria = cboCategorias.getSelectedIndex();
	 int idproveedor=cboProveedores.getSelectedIndex();
	 int setEst_prod;
	 
	 //Validaciones 
	 if (id_prod ==  null || des_prod == null ||   stk_prod ==  0 || pre_prod == 0.0 ) {     //Falta mejorar
			return;    
		}
	 
	 
	 
	//Proceso
		Producto p = new Producto();  //Creamos el objeto
		
		p.setId_prod(id_prod);   //pasamos todos las entradas
		p.setDes_prod(des_prod);
		p.setStk_prod (stk_prod);
		p.setPre_prod(pre_prod);
		p.setIdcategoria(idcategoria);
		p.setIdproveedor(idproveedor);
		p.setEst_prod(1);  //Lo pone por dafault true = 1 o false = 0 
		
		try {
			em.getTransaction().begin();
			em.persist(p);   //Para grabar necesita al objeto
			em.getTransaction().commit();
			aviso("Registro OK");
		} catch (Exception e) {
			aviso("Ocurrio un error " + e.getMessage());
		}
		
		em.close();  //Cerramos
		
			
		}
	

	//Metodos de  Validacion usando Regex
	private String leerCodigo() {          //P0025
		if (!txtCodigo.getText().matches("[P][0-9]{4}")) {      // El ! es diferente de y el matches es para colocar el patron segun la caja de texto
			JOptionPane.showMessageDialog(null, "Formato de codigo invalido P0000");  //Mensaje de errror 
			return null; 
		}
		return txtCodigo.getText(); //Si esta bien retornara lo que tiene el txtcodigo
	}
	private String leerPrecio() {
		if (!txtPrecio.getText().matches("[0-9]{1,5}[.][0-9]{1,2}")) {      // El ! es diferente de y el matches es para colocar el patron segun la caja de texto
			JOptionPane.showMessageDialog(null, "Monto de Precio Incorrecto ");  //Mensaje de errror 
			return null; 
		}
		return txtPrecio.getText(); //Si esta bien retornara lo que tiene el txt
	}

	void aviso (String msg) {
		JOptionPane.showMessageDialog(this, msg,"Aviso",JOptionPane.INFORMATION_MESSAGE);   //Para crear un aviso cuando creas un producto
	}
		
}
