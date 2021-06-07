package ma.isi.gestionProjet.model.dao;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
 


@Repository
public interface CompetenceCollaborateurDao extends JpaRepository<CompetenceCollaborateur, Long> {
	public CompetenceCollaborateur findCompetenceCollaborateurByCompetenceTitre(String titre);
	public CompetenceCollaborateur findCompetenceCollaborateurByCollaborateurMatricule(String matricule);
	public CompetenceCollaborateur findByCompetenceTitreAndCollaborateurMatricule(String titre,String matricule);
    @Query("select distinct m.competence from CompetenceCollaborateur m where m.collaborateur IN (select collaborateur from Collaborateur collaborateur WHERE collaborateur.matricule=?1)")
    public Collection<Competence> findByCollaborateurMatricule(String matricule);
    @Query("select distinct m.collaborateur from CompetenceCollaborateur m where m.competence IN (select competence from Competence competence WHERE competence.titre=?1)")
    public Collection<Collaborateur> findByCompetenceTitre(String titre);
  	 
}
 