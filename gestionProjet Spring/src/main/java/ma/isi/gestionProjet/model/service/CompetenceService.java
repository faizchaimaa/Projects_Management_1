package ma.isi.gestionProjet.model.service;

import java.util.Collection;

import ma.isi.gestionProjet.bean.Competence;
 

public interface CompetenceService {
	
	public int save(Competence competence);
    public int update(Competence competence);
	public int delete(String titre);
	public Competence findById(Long id);
	public Collection<Competence> findAll();
	public Competence findByTitre(String titre);
	
	 
	 
}
