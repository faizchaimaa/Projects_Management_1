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
public class AffectationVo {

    private Long id;
    private CollaborateurVo  collaborateurVo ;
    private TacheVo tacheVo ;
    private String volumehoraire;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


     

	public String getVolumehoraire() {
		return volumehoraire;
	}

	public void setVolumehoraire(String volumehoraire) {
		this.volumehoraire = volumehoraire;
	}

	public CollaborateurVo getCollaborateurVo() {
		return collaborateurVo;
	}

	public void setCollaborateurVo(CollaborateurVo collaborateurVo) {
		this.collaborateurVo = collaborateurVo;
	}


	public TacheVo getTacheVo() {
		return tacheVo;
	}

	public void setTacheVo(TacheVo tacheVo) {
		this.tacheVo = tacheVo;
	}

	@Override
    public String toString() {
        return "AffectationVo{" + "affectationVoVo=" + collaborateurVo.getMatricule() + ", tacheVo=" + tacheVo.getIntitule() + '}';
    }
}
