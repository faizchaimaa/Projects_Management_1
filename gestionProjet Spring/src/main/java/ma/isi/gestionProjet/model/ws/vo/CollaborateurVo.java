/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.isi.gestionProjet.model.ws.vo;



/**
 *
 * @author user
 */
public class CollaborateurVo {
    private Long id;
	private String nom; 
	private String prenom; 
	private String matricule;
	private String estdisponible;
	 
    
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
 
	public String getEstdisponible() {
		return estdisponible;
	}


	public void setEstdisponible(String estdisponible) {
		this.estdisponible = estdisponible;
	}

	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	/*public String getEstdisponible() {
		return estdisponible;
	}


	public void setEstdisponible(String estdisponible) {
		this.estdisponible = estdisponible;
	}*/


	@Override
    public String toString() {
        return "CollaborateurVo{"+ "nom=" + nom + '}';
    }
    
}
