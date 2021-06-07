package ma.isi.gestionProjet.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity 
@Data @ToString @NoArgsConstructor  @AllArgsConstructor

public class Tache implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int volumehoraire;
	private int nombrecollaborateurs;
	@Column(length = 85)
	private String intitule;
	@Temporal(TemporalType.DATE)
	private Date datedebut;
	@Temporal(TemporalType.DATE)
	private Date datefin;	
	@ManyToOne
	private Projet projet;
	
	/*OneToMany (mappedBy = "tache")
	private Collection <CompetenceTache>competencetaches;
	
	@OneToMany (mappedBy = "tache")
	private Collection <Affectation>affectations;*/
}
