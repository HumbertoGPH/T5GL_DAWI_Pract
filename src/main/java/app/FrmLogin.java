package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Producto;
import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class FrmLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblUsuario;
	private JLabel lblClave;
	private JButton btnIngresar;
	private JTextField txtUsuario;
	private JTextField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setTitle("LOGIN CIBERTEC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("USUARIO : ");
		lblUsuario.setBounds(55, 48, 81, 13);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(146, 45, 161, 19);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		lblClave = new JLabel("CLAVE : ");
		lblClave.setBounds(55, 98, 81, 13);
		contentPane.add(lblClave);
		
		btnIngresar = new JButton("INGRESAR");
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(340, 59, 132, 39);
		contentPane.add(btnIngresar);
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(146, 95, 161, 19);
		contentPane.add(txtClave);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(e);
		}
	}
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		//Metodo para ingreso
		   registrar();
		   
		   
		   
		   
		   
	}
	void registrar() {
		//S4-DAWI 
		//Login Usuario 
		
		//Para recuperar los datos 
		String usuario =  leerUsuario();  //Son metodos para validar
		String clave =  leerClave();
		
		//Validacion si esta vacio                                //EL || es o  . u  operador or .
		if (usuario ==  null || clave == null ) {    
			return;    
		}
		
		//1. Obtener conexion - llamando a persistence
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion01"); 
		//2. Crear un manejador de entidades 
		EntityManager em = fabrica.createEntityManager();
		
		//Proceso
		
		//Select * from tb_usuarios where idtipo = ?  -->List
		String jpql =  "select u from Usuario u where u.usr_usua = :xusu and u.cla_usua = :xclav ";  //Aqui la sentencia va a cambiar y se pondra el nombre de la Entidad 
		                                                                                             //Aqui se puede poner mayor igual ,menor etc  
		
	
		try {
			Usuario u = em.createQuery(jpql, Usuario.class).
					setParameter("xusu",usuario).
					setParameter("xclav", clave).
					getSingleResult(); //Este metodo es para que solo devuelva un valor y si hay error devuelve excepciones 

			
			
			    //Imprimir  el Listado
				JOptionPane.showMessageDialog(null, "Bienvenido " + u.getNom_usua());  //Para que muestre el nombre usuario
				
				//Abrir la ventana principal 
			
				FrmManteProd manteniento = new FrmManteProd();  //Lo instanciamos 
				manteniento.setVisible(true);  //Se usa para que se muestre 
				dispose();
				
				
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error: Usuario o clave incorrecta");
		}
			
		//Cerramos el manager	
		em.close();
	}

	//Metodos para la vadilacion para seguridad usando REGEX
	private String leerUsuario() {         //admin@ciberfarma.com
		if (!txtUsuario.getText().matches("[A-Za-z0-9]+[@][a-z0-9]+[.][a-z]{2,3}")) {      // El ! es diferente de y el matches es para colocar el patron segun la caja de texto
			JOptionPane.showMessageDialog(null, "Usuario  es  un coreo");  //Mensaje de errror 
			return null; 
		}
		return txtUsuario.getText(); //Si esta bien retornara lo que tiene el txtUsuario
	}
	
	private String leerClave() {   //Para el patron      //super
		if (!txtClave.getText().matches("[A-Za-z0-9]+")) {      // El ! es diferente de y el matches es para colocar el patron segun la caja de texto
			JOptionPane.showMessageDialog(null, "Usuario  es  un coreo");  //Mensaje de errror 
			return null; 
		}
		return txtClave.getText(); //Si esta bien retornara lo que tiene el txtUsuario
	}
	
	
}
