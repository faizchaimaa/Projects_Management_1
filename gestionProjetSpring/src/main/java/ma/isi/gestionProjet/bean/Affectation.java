package ma.isi.gestionProjet.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Data @ToString  @NoArgsConstructor @AllArgsConstructor 
 
public class Affectation implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private int volumehoraire;
	
	@ManyToOne
	private Tache tache;
	
	@ManyToOne
	private Collaborateur collaborateur;
}
