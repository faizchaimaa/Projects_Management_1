package ma.isi.gestionProjet.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.isi.gestionProjet.bean.Projet;



@Repository 
public interface ProjetDao extends JpaRepository<Projet,Long > {

	 public Projet findByNumprojet(int numprojet);
	 public Projet findByIntitule(String intitule);
	 public int deleteByNumprojet(int numprojet);
}
