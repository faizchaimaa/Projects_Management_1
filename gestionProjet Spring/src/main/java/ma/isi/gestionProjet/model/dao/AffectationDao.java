package ma.isi.gestionProjet.model.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ma.isi.gestionProjet.bean.Affectation;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Tache;


@Repository

public interface AffectationDao extends JpaRepository<Affectation,Long>{

	public  Affectation findByTacheIntituleAndCollaborateurMatricule(String intitule,String matricule);
	public Affectation findAffectationByCollaborateurMatricule(String matricule);
	public Affectation findAffectationByTacheIntitule(String intitule);	
	@Query("select distinct m.tache from Affectation m where m.collaborateur IN (select collaborateur from Collaborateur collaborateur WHERE collaborateur.matricule=?1)")
	public Collection<Tache> findByCollaborateurMatricule(String matricule);
	@Query("select distinct m.collaborateur from Affectation m where m.tache IN (select tache from Tache tache WHERE tache.intitule=?1)")
    public Collection<Collaborateur> findByTacheIntitule(String intitule);
}
