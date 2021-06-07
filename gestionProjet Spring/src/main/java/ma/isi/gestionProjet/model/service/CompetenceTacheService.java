package ma.isi.gestionProjet.model.service;

import java.util.Collection;

import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
import ma.isi.gestionProjet.bean.CompetenceTache;
 

public interface CompetenceTacheService {
	
	public int save(CompetenceTache competenceTache);
	public void delete(Long id); 
	public CompetenceTache findById(Long id);
	public CompetenceTache findByCompetenceTitreAndTacheIntitule(String titre,String intitule);
	public Collection<CompetenceTache> findAll();
	public Collection<CompetenceTache> findByTache(Long id);
 
	 
}
