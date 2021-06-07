package ma.isi.gestionProjet.model.service;

import java.util.Collection;
import java.util.Date;

import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Projet;
import ma.isi.gestionProjet.bean.Tache;

public interface TacheService {
	
	public int save(Projet projet,Collection<Tache>taches);
	
    public int update(Tache tache);
	public Date CalculeDateFinFinal(Tache tache) ;
	public int delete(String intitule);
	
	public int deleteByProjetNumprojet(int numprojet);
	public Tache findByProjetNumprojet(int numprojet);
	public Collection<Tache>findByProjet(Projet projet);
	
	public int saveTache(Tache tache);
	
	public Collection<Tache> findAll();
	
	public Tache findByIntitule(String intitule);
	//public Projet findByIntitule(String intitule);
	//public void save(Projet projet);
	//public List<Projet>findAll();
	public Collection<Collaborateur> CollabAaffecter(Long id);
}
