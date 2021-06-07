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
public class CompetenceCollaborateurVo {

    private Long id;
    private CollaborateurVo  collaborateurVo ;
    private CompetenceVo competenceVo ;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public CollaborateurVo getCollaborateurVo() {
		return collaborateurVo;
	}

	public void setCollaborateurVo(CollaborateurVo collaborateurVo) {
		this.collaborateurVo = collaborateurVo;
	}

	public CompetenceVo getCompetenceVo() {
		return competenceVo;
	}

	public void setCompetenceVo(CompetenceVo competenceVo) {
		this.competenceVo = competenceVo;
	}

	 

	@Override
    public String toString() {
        return "CompetenceCollaborateurVo{" + "competenceCollaborateurVoVo=" + collaborateurVo.getMatricule() + ", competenceVo=" + competenceVo.getTitre() + '}';
    }
}
