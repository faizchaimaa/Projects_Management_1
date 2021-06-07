package ma.isi.gestionProjet.model.service;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import ma.isi.gestionProjet.bean.Collaborateur;


public interface CollaborateurService {
	
	 
	public int save(Collaborateur collaborateur  );
	public int update(Collaborateur collaborateur);
    public int delete(String matricule);
	public Collaborateur findByMatricule(String matricule);
	public Collaborateur findByNomAndPrenom(String nom,String prenom); 
	public Collection <Collaborateur> findByNomContaining(String infix);
    @Query("select distinct m.collaborateur from Collaborateur m where m.competence like '%chaimaa%' ")
	public Collection<Collaborateur>findAll();
	public Collection <Collaborateur> findByEstdisponible();
}

 