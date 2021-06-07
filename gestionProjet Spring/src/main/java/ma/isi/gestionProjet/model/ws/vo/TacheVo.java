/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.isi.gestionProjet.model.ws.vo;

import java.util.Date;

 

/**
 *
 * @author user
 */
public class TacheVo {
    private Long id;
    private String volumehoraire;
	private String nombrecollaborateurs;
	
    public String getVolumehoraire() {
		return volumehoraire;
	}


	public void setVolumehoraire(String volumehoraire) {
		this.volumehoraire = volumehoraire;
	}


	public String getNombrecollaborateurs() {
		return nombrecollaborateurs;
	}


	public void setNombrecollaborateurs(String nombrecollaborateurs) {
		this.nombrecollaborateurs = nombrecollaborateurs;
	}


	


	private String intitule;
	private Date datedebut;
	private Date datefin;
    private ProjetVo projetVo;
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

 
	public String getIntitule() {
		return intitule;
	}


	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public Date getDatedebut() {
		return datedebut;
	}


	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}


	public Date getDatefin() {
		return datefin;
	}


	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}


	public ProjetVo getProjetVo() {
		return projetVo;
	}


	public void setProjetVo(ProjetVo projetVo) {
		this.projetVo = projetVo;
	}


	@Override
    public String toString() {
        return "TacheVo{"+ "intitule=" + intitule + '}';
    }
    
}
