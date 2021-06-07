package ma.isi.gestionProjet.model.service;

import java.util.Collection;

import ma.isi.gestionProjet.bean.Affectation;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
import ma.isi.gestionProjet.bean.Tache;
 
 

public interface AffectationService {
	
	public int save(Affectation affectation );
	public void delete(Long id);
    public Affectation findByTacheIntituleAndCollaborateurMatricule(String intitule,String matricule);
	public Affectation findAffectationByCollaborateurMatricule(String matricule);
	public Affectation findAffectationByTacheIntitule(String intitule);
    public Affectation findById(Long id);
	public Collection<Affectation>findAll();
    public Collection<Tache> findByCollaborateurMatricule(String matricule);
	public Collection<Collaborateur> findByTacheIntitule(String intitule);
 
 
}
