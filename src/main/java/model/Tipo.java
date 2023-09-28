package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity  //Designas
@Table(name = "tb_tipos")  //El nombre de la tabla de la bd
@Data  //Para los Get y set y los Constructores 
public class Tipo {
	
	
		@Id     //Definimos quien es el Id
		private int idtipo;
		private String descripcion;
		

	 
}
