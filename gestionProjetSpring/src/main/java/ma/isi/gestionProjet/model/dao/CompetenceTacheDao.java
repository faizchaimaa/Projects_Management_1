package ma.isi.gestionProjet.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ma.isi.gestionProjet.bean.CompetenceTache;






@Repository
public interface CompetenceTacheDao extends JpaRepository<CompetenceTache,Long> {

	public CompetenceTache findByCompetenceTitreAndTacheIntitule(String titre,String intitule);
}
