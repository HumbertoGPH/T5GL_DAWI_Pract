package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_productos") //Nombre segun la bd
@Data
public class Producto {
	@Id
	private String id_prod;  //Definir como llave primaria
	private String des_prod;
	private int stk_prod;
	private double pre_prod;
	private int idcategoria;
	private int est_prod;
	private int idproveedor;
		
	@ManyToOne
	@JoinColumn(name = "idcategoria", insertable = false,  updatable = false)   //Son los que vienen de los demas tablas o entidades guiarse de la bd
	private Categoria objCategoria;
	
	@ManyToOne
	@JoinColumn(name = "idproveedor", insertable = false,  updatable = false)  
	private Proveedor objProveedor;
	
}
