package ma.isi.gestionProjet.model.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.isi.gestionProjet.bean.Competence;



@Repository
public interface CompetenceDao extends JpaRepository<Competence, Long> {

	public Competence findByTitre(String titre);
}

 