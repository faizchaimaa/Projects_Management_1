package ma.isi.gestionProjet.model.service;

import java.util.Collection;

import ma.isi.gestionProjet.bean.Projet;

public interface ProjetService {
	
	public int save(Projet projet);
	
	public int update(Projet projet);
	 
	//public int delete(int numprojet);
	 
	public int deleteByNumprojet(int numprojet);
	 
	public Projet findById(Long id);
	
	public Collection<Projet> findAll();
	
	 
	 
    public Projet findByNumprojet(int numprojet);
    
	public Projet findByIntitule(String intitule);
	
	//public void save(Projet projet);
	//public List<Projet>findAll();
}
