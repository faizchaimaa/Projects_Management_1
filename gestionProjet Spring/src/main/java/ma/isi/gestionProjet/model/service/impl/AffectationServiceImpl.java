package ma.isi.gestionProjet.model.service.impl;

 

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.isi.gestionProjet.bean.Affectation;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
import ma.isi.gestionProjet.bean.Tache;
import ma.isi.gestionProjet.model.dao.AffectationDao;
import ma.isi.gestionProjet.model.dao.CollaborateurDao;
import ma.isi.gestionProjet.model.dao.CompetenceCollaborateurDao;
import ma.isi.gestionProjet.model.service.AffectationService;
import ma.isi.gestionProjet.model.service.CollaborateurService;
import ma.isi.gestionProjet.model.service.CompetenceCollaborateurService;
import ma.isi.gestionProjet.model.service.CompetenceService;
import ma.isi.gestionProjet.model.service.TacheService;

 

@Service
public class AffectationServiceImpl implements AffectationService {
	 
	@Autowired //INJECTION DES DEPENDANCES PAR TYPE
	private AffectationDao affectationDao;
	@Autowired
	private TacheService tacheService;
	@Autowired
	private CollaborateurService collaborateurService;
	@Autowired
	private AffectationService affectationService ;
	//Sauvegarder affectation
	
	@Override
	public int save(Affectation affectation) {
		 String matricule=affectation.getCollaborateur().getMatricule();
		 String intitule=affectation.getTache().getIntitule();
		 int estdisponible=affectation.getCollaborateur().getEstdisponible();
		 Affectation a =findByTacheIntituleAndCollaborateurMatricule(intitule, matricule);
		 if( a != null) {
	            return -1;
		 }   else if(estdisponible==0) {
			 return -2;
			 
		 } else {
	        if(collaborateurService.findByMatricule(matricule)==null) {
        		collaborateurService.save(affectation.getCollaborateur());
        	}
        	if(tacheService.findByIntitule(intitule)==null) {
        		tacheService.saveTache(affectation.getTache());
        	}
        	 Tache t=tacheService.findByIntitule(intitule);
        	 Collaborateur coll=collaborateurService.findByMatricule(matricule);
        	 affectation.setTache(t);
        	 affectation.setCollaborateur(coll); 
        	 affectationDao.save(affectation );
        	 return 1;
	 
	}
	} 
	@Override
	public void delete(Long id) {
   affectationDao.deleteById(id);
	}
	@Override
	public Affectation findByTacheIntituleAndCollaborateurMatricule(String intitule, String matricule) {
		return affectationDao.findByTacheIntituleAndCollaborateurMatricule(intitule, matricule);
	}
	@Override
	public Affectation findById(Long id) {
		// TODO Auto-generated method stub
		return affectationDao.getOne(id);
	}
	@Override
	public Affectation findAffectationByCollaborateurMatricule(String matricule) {
		return affectationDao.findAffectationByCollaborateurMatricule(matricule);
	}
	@Override
	public Affectation findAffectationByTacheIntitule(String intitule) {
		return affectationDao.findAffectationByTacheIntitule(intitule);
	}
	@Override
	public Collection<Tache> findByCollaborateurMatricule(String matricule) {
		// TODO Auto-generated method stub
		return affectationDao.findByCollaborateurMatricule(matricule);
	}
	@Override
	public Collection<Collaborateur> findByTacheIntitule(String intitule) {
		// TODO Auto-generated method stub
		return affectationDao.findByTacheIntitule(intitule);
	}
	@Override
	public Collection<Affectation> findAll() {
		// TODO Auto-generated method stub
		return affectationDao.findAll();
	}
	public AffectationDao getAffectationDao() {
		return affectationDao;
	}
	public void setAffectationDao(AffectationDao affectationDao) {
		this.affectationDao = affectationDao;
	}
	public TacheService getTacheService() {
		return tacheService;
	}
	public void setTacheService(TacheService tacheService) {
		this.tacheService = tacheService;
	}
	public CollaborateurService getCollaborateurService() {
		return collaborateurService;
	}
	public void setCollaborateurService(CollaborateurService collaborateurService) {
		this.collaborateurService = collaborateurService;
	}
	public AffectationService getAffectationService() {
		return affectationService;
	}
	public void setAffectationService(AffectationService affectationService) {
		this.affectationService = affectationService;
	}
	

	 

	
	
}
