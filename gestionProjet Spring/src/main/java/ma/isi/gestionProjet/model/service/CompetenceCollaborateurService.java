package ma.isi.gestionProjet.model.service;

import java.util.Collection;

import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
 
 

public interface CompetenceCollaborateurService {
	
	public int save(CompetenceCollaborateur competenceCollaborateur);
	public void delete(Long id);
	public CompetenceCollaborateur findByCompetenceTitreAndCollaborateurMatricule(String titre,String matricule);
	public Collection<Competence> findByCollaborateurMatricule(String matricule);
    public Collection<Collaborateur> findByCompetenceTitre(String titre);
	public CompetenceCollaborateur findById(Long id);
	public CompetenceCollaborateur findCompetenceCollaborateurByCompetenceTitre(String titre);
	public CompetenceCollaborateur findCompetenceCollaborateurByCollaborateurMatricule(String matricule);
	public Collection<CompetenceCollaborateur> findAll();

}
