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
public class Competence  implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 80)
	private String titre;
	@Column(length = 100)
	private String description;
	
	
	/*@OneToMany (mappedBy = "competence")
	private Collection <CompetenceTache>competencetaches;
	
	@OneToMany (mappedBy = "competence")
	private Collection <CompetenceCollaborateur>competencescollaborateur;*/
	
}
