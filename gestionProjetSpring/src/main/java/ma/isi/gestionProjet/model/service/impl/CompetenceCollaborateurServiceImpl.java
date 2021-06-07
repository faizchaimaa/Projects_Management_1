package ma.isi.gestionProjet.model.service.impl;

 

import java.util.Collection;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
import ma.isi.gestionProjet.model.dao.CollaborateurDao;
import ma.isi.gestionProjet.model.dao.CompetenceCollaborateurDao;
import ma.isi.gestionProjet.model.service.CollaborateurService;
import ma.isi.gestionProjet.model.service.CompetenceCollaborateurService;
import ma.isi.gestionProjet.model.service.CompetenceService;

 

@Service
public class CompetenceCollaborateurServiceImpl implements CompetenceCollaborateurService {
	 
	@Autowired
	private CompetenceCollaborateurDao competencecollaborateurDao;
	@Autowired
	private CompetenceService competenceService;
	@Autowired
	private CollaborateurService collaborateurService;
	@Autowired
	private CompetenceCollaborateurService competenceCollaborateurService ;
	//Sauvegarder competencecollaborateur
	
	@Override
	public int save(CompetenceCollaborateur competenceCollaborateur) {
		 String titre=competenceCollaborateur.getCompetence().getTitre();
		 String matricule=competenceCollaborateur.getCollaborateur().getMatricule();
		 CompetenceCollaborateur cc=findByCompetenceTitreAndCollaborateurMatricule(titre, matricule);
		 if( cc != null) {
	            return -1;  
	        }else {
	        	if(collaborateurService.findByMatricule(matricule)==null) {
	        		collaborateurService.save(competenceCollaborateur.getCollaborateur());
	        	}
	        	if(competenceService.findByTitre(titre)==null) {
	        		competenceService.save(competenceCollaborateur.getCompetence());
	        	}
	        	 Competence c=competenceService.findByTitre(titre);
	        	 Collaborateur coll=collaborateurService.findByMatricule(matricule);
	        	 competenceCollaborateur.setCompetence(c);
	        	 competenceCollaborateur.setCollaborateur(coll); 
	        	 competencecollaborateurDao.save(competenceCollaborateur );
	        	 return 1;
	        	
	        }
	}
	
	@Override
	public void delete(Long id) {
		 
		competencecollaborateurDao.deleteById(id);	
		 
		}
	
	@Override
	public CompetenceCollaborateur findCompetenceCollaborateurByCompetenceTitre(String titre) {
		return competencecollaborateurDao.findCompetenceCollaborateurByCompetenceTitre(titre);
	}
	
	@Override
	public CompetenceCollaborateur findCompetenceCollaborateurByCollaborateurMatricule(String matricule) {
		return competencecollaborateurDao.findCompetenceCollaborateurByCollaborateurMatricule(matricule);
	}
	@Override
	public CompetenceCollaborateur findByCompetenceTitreAndCollaborateurMatricule(String titre, String matricule) {
		// TODO Auto-generated method stub
		return competencecollaborateurDao.findByCompetenceTitreAndCollaborateurMatricule(titre, matricule);
	}
	//Trouver  les competences d'un collaborateur
	
	@Override
	public Collection<Competence> findByCollaborateurMatricule(String matricule) {
		return  competencecollaborateurDao.findByCollaborateurMatricule(matricule);
	}
	
	//Trouver  les collaborateur qui ont une competence donnée
	@Override
	public Collection<Collaborateur> findByCompetenceTitre(String titre) {
		return  competencecollaborateurDao.findByCompetenceTitre(titre);
	}

	@Override
	public CompetenceCollaborateur findById(Long id) {
		// TODO Auto-generated method stub
		return competencecollaborateurDao.getOne(id);
	}

	
	@Override
	public Collection<CompetenceCollaborateur> findAll() {
		return competencecollaborateurDao.findAll();
	}

 

	/*@Override
	public Collaborateur findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}*/

	
	
	public CompetenceCollaborateurDao getCompetencecollaborateurDao() {
		return competencecollaborateurDao;
	}



	public void setCompetencecollaborateurDao(CompetenceCollaborateurDao competencecollaborateurDao) {
		this.competencecollaborateurDao = competencecollaborateurDao;
	}


	public CompetenceService getCompetenceService() {
		return competenceService;
	}


	public void setCompetenceService(CompetenceService competenceService) {
		this.competenceService = competenceService;
	}


	public CollaborateurService getCollaborateurService() {
		return collaborateurService;
	}


	public void setCollaborateurService(CollaborateurService collaborateurService) {
		this.collaborateurService = collaborateurService;
	}

	public CompetenceCollaborateurService getCompetenceCollaborateurService() {
		return competenceCollaborateurService;
	}

	public void setCompetenceCollaborateurService(CompetenceCollaborateurService competenceCollaborateurService) {
		this.competenceCollaborateurService = competenceCollaborateurService;
	}

	
}
