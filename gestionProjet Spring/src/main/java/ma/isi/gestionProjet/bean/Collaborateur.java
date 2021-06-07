package ma.isi.gestionProjet.bean;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity 
@Data @ToString @NoArgsConstructor  @AllArgsConstructor
public class Collaborateur implements Serializable {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(length = 40)
	private String nom;
    @Column(length = 40)
	private String prenom;
    @Column(length = 100)
	private String adresse;
    @Column(length = 40)
	private String email;
	@Column(unique =true)
	private String matricule;
	private int estdisponible;
	
	//@OneToMany (mappedBy = "collaborateur")
	//private Collection <CompetenceCollaborateur>competencescollaborateur;
	
	//@OneToMany (mappedBy = "collaborateur")
	//private Collection <Affectation>affectations;
}
