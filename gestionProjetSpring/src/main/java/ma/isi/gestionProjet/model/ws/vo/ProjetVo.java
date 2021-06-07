/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.isi.gestionProjet.model.ws.vo;

 
import java.util.Date;
import java.util.List;

 


/**
 *
 * @author user
 */
public class ProjetVo {
    private Long id;
    private List<TacheVo> tacheVos;
    private String numprojet;
	private String intitule;
	private Date datedebut;
	private Date datefin;
	private String volumehoraire;
	private String nombrecollaborateurs;
    
    public String getNombrecollaborateurs() {
		return nombrecollaborateurs;
	}

	public void setNombrecollaborateurs(String nombrecollaborateurs) {
		this.nombrecollaborateurs = nombrecollaborateurs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
  
	 

	 

	public List<TacheVo> getTacheVos() {
		return tacheVos;
	}

	public void setTacheVos(List<TacheVo> tacheVos) {
		this.tacheVos = tacheVos;
	}

	public String getNumprojet() {
		return numprojet;
	}

	public void setNumprojet(String numprojet) {
		this.numprojet = numprojet;
	}

	 

	public String getVolumehoraire() {
		return volumehoraire;
	}

	public void setVolumehoraire(String volumehoraire) {
		this.volumehoraire = volumehoraire;
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

	 

	@Override
    public String toString() {
        return "ProjetVo{"+ "intitule=" + intitule + '}';
    }
    
}
