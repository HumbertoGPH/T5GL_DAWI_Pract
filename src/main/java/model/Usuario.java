package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity     //Para que se vuelva entidad
@Table(name = "tb_usuarios")    
@Getter                      //Metodos Get y Set 
@Setter
@Data                       //Constructor vacio
@AllArgsConstructor //Constructor con datos
@NoArgsConstructor          //Otra forma de constru vacio 

public class Usuario {
	
	//Atributos               --Deben ser iguales segun la bd 
	@Id
	private int cod_usua;
	private String nom_usua;
	private String ape_usua;
	private String usr_usua;
	private String cla_usua;
	private String fna_usua; 
	private int idtipo;
	private int est_usua;
	
	 @ManyToOne 
	 @JoinColumn(name = "idtipo" , insertable = false, updatable = false) //Esto es para solo una union
	 
	 //Creamos  una varible objeto tipo  Para guardar toda la Informacion = 
	 private Tipo objTipo;
}
