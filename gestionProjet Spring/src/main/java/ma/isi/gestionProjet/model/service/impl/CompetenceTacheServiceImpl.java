package ma.isi.gestionProjet.model.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
import ma.isi.gestionProjet.bean.CompetenceTache;
import ma.isi.gestionProjet.bean.Tache;
import ma.isi.gestionProjet.model.dao.CompetenceTacheDao;
import ma.isi.gestionProjet.model.service.CompetenceService;
import ma.isi.gestionProjet.model.service.CompetenceTacheService;
import ma.isi.gestionProjet.model.service.TacheService;

 

@Service
public class CompetenceTacheServiceImpl implements CompetenceTacheService {
	 
	@Autowired
	private CompetenceTacheDao competenceTacheDao;
	@Autowired 
	private TacheService tacheService;
	@Autowired
	private CompetenceService competenceService;
	@Autowired
	private CompetenceTacheService competenceTacheService ;
	

	@Override
	public int save(CompetenceTache competenceTache) {
		 String titre=competenceTache.getCompetence().getTitre();
		 String intitule=competenceTache.getTache().getIntitule();
		 CompetenceTache  ct=findByCompetenceTitreAndTacheIntitule(titre, intitule);
		 if( ct != null) {
	            return -1;
		 }else {
	        	if(tacheService.findByIntitule(intitule)==null) {
	        		tacheService.saveTache(competenceTache.getTache());
	        	}
	        	if(competenceService.findByTitre(titre)==null) {
	        		competenceService.save(competenceTache.getCompetence());
	        	}
	        	Competence c=competenceService.findByTitre(titre);
	        	Tache t=tacheService.findByIntitule(intitule);
	        	competenceTache.setCompetence(c);
	        	competenceTache.setTache(t);
	        	competenceTacheDao.save(competenceTache);
	        	return 1;
	        	
	}
		 }
	
	
	@Override
	public void delete(Long id) {
		 
		 competenceTacheDao.deleteById(id);
	}
	
	@Override
	public Collection<CompetenceTache> findByTache(Long id) 
	{
		Collection <CompetenceTache> listCompTache=new ArrayList<>();
		Collection <CompetenceTache> listAllComp=competenceTacheDao.findAll();
		for (CompetenceTache competenceTache : listAllComp) 
		{
			if(competenceTache.getTache().getId()==id)
				listCompTache.add(competenceTache);
		}
		return listCompTache;
	}
	
	@Override
	public CompetenceTache findByCompetenceTitreAndTacheIntitule(String titre, String intitule) {
		
		return competenceTacheDao.findByCompetenceTitreAndTacheIntitule(titre, intitule);
	}

	@Override
	public Collection<CompetenceTache> findAll() {
		// TODO Auto-generated method stub
		return  competenceTacheDao.findAll();
	}

	/*@Override
	public CompetenceTache findByMatricule(String matricule) {
		// TODO Auto-generated method stub
		return competenceTacheDao.findByMatricule(matricule);
	}*/



	@Override
	public CompetenceTache findById(Long id) {
		// TODO Auto-generated method stub
		return competenceTacheDao.getOne(id);
	}


	public TacheService getTacheService() {
		return tacheService;
	}

	public void setTacheService(TacheService tacheService) {
		this.tacheService = tacheService;
	}

	public CompetenceService getCompetenceService() {
		return competenceService;
	}

	public void setCompetenceService(CompetenceService competenceService) {
		this.competenceService = competenceService;
	}

	public CompetenceTacheDao getCompetenceTacheDao() {
		return competenceTacheDao;
	}

	public void setCompetenceTacheDao(CompetenceTacheDao competenceTacheDao) {
		this.competenceTacheDao = competenceTacheDao;
	}


	public CompetenceTacheService getCompetenceTacheService() {
		return competenceTacheService;
	}


	public void setCompetenceTacheService(CompetenceTacheService competenceTacheService) {
		this.competenceTacheService = competenceTacheService;
	}


	
}
