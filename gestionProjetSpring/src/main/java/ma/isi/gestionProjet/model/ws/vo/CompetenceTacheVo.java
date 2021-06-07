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
public class CompetenceTacheVo {

    private Long id;
    private TacheVo  tacheVo ;
    private CompetenceVo competenceVo ;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


     

	public TacheVo getTacheVo() {
		return tacheVo;
	}

	public void setTacheVo(TacheVo tacheVo) {
		this.tacheVo = tacheVo;
	}

	public CompetenceVo getCompetenceVo() {
		return competenceVo;
	}

	public void setCompetenceVo(CompetenceVo competenceVo) {
		this.competenceVo = competenceVo;
	}

	 

	@Override
    public String toString() {
        return "CompetenceTacheVo{" + "competenceTacheVo=" + tacheVo.getIntitule() + ", competenceVo=" + competenceVo.getTitre() + '}';
    }
}
