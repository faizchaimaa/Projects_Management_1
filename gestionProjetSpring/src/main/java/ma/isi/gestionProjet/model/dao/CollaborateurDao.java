package ma.isi.gestionProjet.model.dao;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.isi.gestionProjet.bean.Collaborateur;




@Repository
public interface CollaborateurDao extends JpaRepository<Collaborateur, Long>{

	 public Collaborateur findByMatricule(String matricule);
	 public Collaborateur findByNomAndPrenom(String nom,String prenom);
	 public Collection <Collaborateur> findByNomContaining(String infix);
	 public Collection <Collaborateur> findByEstdisponible(int estdisponible);
}

 