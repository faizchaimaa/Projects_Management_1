package ma.isi.gestionProjet.model.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.isi.gestionProjet.bean.Projet;
import ma.isi.gestionProjet.bean.Tache;

@Repository
public interface TacheDao extends JpaRepository<Tache, Long>  {

	 public Tache findByIntitule(String intitule);
	 public Tache findByProjetNumprojet(int numprojet);
     public int deleteByProjetNumprojet(int numprojet);
	 
	 public Collection<Tache>findByProjet(Projet projet);
}
 
 