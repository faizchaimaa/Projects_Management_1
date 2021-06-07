package ma.isi.gestionProjet.model.service.impl;

 

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.isi.gestionProjet.bean.Affectation;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
import ma.isi.gestionProjet.bean.CompetenceTache;
import ma.isi.gestionProjet.model.dao.CompetenceCollaborateurDao;
import ma.isi.gestionProjet.model.dao.CompetenceDao;
import ma.isi.gestionProjet.model.dao.CompetenceTacheDao;
import ma.isi.gestionProjet.model.service.CompetenceCollaborateurService;
import ma.isi.gestionProjet.model.service.CompetenceService;
import ma.isi.gestionProjet.model.service.CompetenceTacheService;

 

@Service
public class CompetenceServiceImpl implements CompetenceService {
	 
	@Autowired
	private CompetenceDao competenceDao;
	@Autowired
	private CompetenceCollaborateurService competenceCollaborateurService ;
	@Autowired
	private CompetenceTacheService competenceTacheService ;
	@Autowired
	private CompetenceTacheDao competenceTacheDao ;
	@Autowired
	private CompetenceCollaborateurDao competencecollaborateurDao ;
	 
	 
	@Override
	public int save(Competence competence) {

		Competence c=findByTitre(competence.getTitre());
		if(c !=null) {
			return -1;
	   }else {
			competenceDao.save(competence);
	 
			return 1;
		}
	}
	 //Supprimer une competence
		@Override
		public int delete(String titre) {
			Competence c=findByTitre(titre);
			Collection <CompetenceCollaborateur> listComp=competenceCollaborateurService.findAll();
			Collection <CompetenceTache> listComptach=competenceTacheService.findAll();	
			
			if(c==null) {
				return -1;
				
			}else if (listComp.size()==0 && listComptach.size()==0) {
				competenceDao.delete(c);
				return 0;
			}
			else {
				
	             for(CompetenceTache competenceTache :listComptach) {
					
					if(competenceTache.getCompetence().getId()==c.getId()) 
					 competenceTacheDao.delete(competenceTache);
					
				}
	             for (CompetenceCollaborateur competenceCollaborateur :listComp) 
					{
						if(competenceCollaborateur.getCollaborateur().getId()==c.getId())
							competencecollaborateurDao.delete(competenceCollaborateur);
					}
				competenceDao.delete(c);
				 return 1;
			}	 
		}
		 
		//Mettre à jour un projet //il faut changer par find by id
		@Override
		public int update(Competence competence ) {
			Competence c=findByTitre(competence.getTitre());
	        if(c == null) {
	            return -1;
	        }
	        else {
	            competenceDao.save(competence);
	            return 1;
	        }	 
		}
		  

	@Override
	public Collection<Competence> findAll() {
		// TODO Auto-generated method stub
		return  competenceDao.findAll();
	}

	@Override
	public Competence findByTitre(String titre) {
		return competenceDao.findByTitre(titre);
	}

	@Override
	public Competence findById(Long id) {
		return null;
	}


	public CompetenceDao getCompetenceDao() {
		return competenceDao;
	}

	public void setCompetenceDao(CompetenceDao competenceDao) {
		this.competenceDao = competenceDao;
	}
	public CompetenceCollaborateurService getCompetenceCollaborateurService() {
		return competenceCollaborateurService;
	}
	public void setCompetenceCollaborateurService(CompetenceCollaborateurService competenceCollaborateurService) {
		this.competenceCollaborateurService = competenceCollaborateurService;
	}
	public CompetenceCollaborateurDao getCompetencecollaborateurDao() {
		return competencecollaborateurDao;
	}
	public void setCompetencecollaborateurDao(CompetenceCollaborateurDao competencecollaborateurDao) {
		this.competencecollaborateurDao = competencecollaborateurDao;
	}
	public CompetenceTacheService getCompetenceTacheService() {
		return competenceTacheService;
	}
	public void setCompetenceTacheService(CompetenceTacheService competenceTacheService) {
		this.competenceTacheService = competenceTacheService;
	}
	public CompetenceTacheDao getCompetenceTacheDao() {
		return competenceTacheDao;
	}
	public void setCompetenceTacheDao(CompetenceTacheDao competenceTacheDao) {
		this.competenceTacheDao = competenceTacheDao;
	}
	
}
