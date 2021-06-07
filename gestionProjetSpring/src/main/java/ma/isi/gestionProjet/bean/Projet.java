package ma.isi.gestionProjet.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
 
 


@Entity 
 @ToString @NoArgsConstructor  @AllArgsConstructor
public class Projet implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique =true)
	private int numprojet;
	@Column(length = 80)
	private String intitule;
	@Temporal(TemporalType.DATE)
	private Date datedebut;
	@Temporal(TemporalType.DATE)
	private Date datefin;
	private int volumehoraire;
	private int nombrecollaborateurs;
	 
	@OneToMany (mappedBy = "projet")
	private List <Tache>taches;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumprojet() {
		return numprojet;
	}

	public void setNumprojet(int numprojet) {
		this.numprojet = numprojet;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public int getVolumehoraire() {
		return volumehoraire;
	}

	public void setVolumehoraire(int volumehoraire) {
		this.volumehoraire = volumehoraire;
	}
	
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projet other = (Projet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@JsonIgnore
	public List<Tache> getTaches() {
		return taches;
	}
	@JsonSetter
	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public int getNombrecollaborateurs() {
		return nombrecollaborateurs;
	}

	public void setNombrecollaborateurs(int nombrecollaborateurs) {
		this.nombrecollaborateurs = nombrecollaborateurs;
	}
	 
	
	 
}
