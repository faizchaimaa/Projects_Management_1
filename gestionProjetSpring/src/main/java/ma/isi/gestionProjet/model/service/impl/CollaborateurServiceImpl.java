package ma.isi.gestionProjet.model.service.impl;

 

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.isi.gestionProjet.bean.Affectation;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
import ma.isi.gestionProjet.model.dao.AffectationDao;
import ma.isi.gestionProjet.model.dao.CollaborateurDao;
import ma.isi.gestionProjet.model.dao.CompetenceCollaborateurDao;
import ma.isi.gestionProjet.model.service.AffectationService;
import ma.isi.gestionProjet.model.service.CollaborateurService;
import ma.isi.gestionProjet.model.service.CompetenceCollaborateurService;

 

@Service
public class CollaborateurServiceImpl implements CollaborateurService {
	 
	@Autowired
	private CollaborateurDao collaborateurDao; 
	@Autowired
	private CompetenceCollaborateurService competenceCollaborateurService ;
	@Autowired
	private CompetenceCollaborateurDao competencecollaborateurDao ;
	@Autowired 
	private AffectationService affectationService;
	@Autowired
	private AffectationDao affectationDao ;
	 
	@Override
	public int save(Collaborateur collaborateur) {

		Collaborateur p=findByMatricule(collaborateur.getMatricule());
		if(p !=null) {
			return -1;
	   }else {
			collaborateurDao.save(collaborateur);
	 
			return 1;
		}
	}

	 //Supprimer un collaborateur avec ses competence collaborateur 
	@Override
	public int delete(String  matricule) {
		Collaborateur c=findByMatricule(matricule);
		Collection <CompetenceCollaborateur> listComp=competenceCollaborateurService.findAll();
		Collection <Affectation> affectations=affectationService.findAll();
		if(c==null) {
			return -1;
		}else if (listComp.size()==0 && affectations.size()==0) {
			collaborateurDao.delete(c);
			return 0;
		}else {
			for (CompetenceCollaborateur competenceCollaborateur : listComp) 
			{
				if(competenceCollaborateur.getCollaborateur().getId()==c.getId())
					competencecollaborateurDao.delete(competenceCollaborateur);
			}
             for(Affectation affectation :affectations) {
				
				if(affectation.getCollaborateur().getId()==c.getId()) {
				 affectationDao.delete(affectation);
				}
			}
			collaborateurDao.delete(c);
			 return 1;
		}	 
	   }
	
	 
	//Mettre à jour un projet
	@Override
	public int update(Collaborateur collaborateur ) {
		Collaborateur c=findByMatricule(collaborateur.getMatricule());
        if(c == null) {
            return -1;
        }
        else {
            collaborateurDao.save(collaborateur);
            return 1;
        }	 
	}
	//Trouver les collaborateur Disponibles
	@Override
	public Collection<Collaborateur> findByEstdisponible() 
	{
		Collection <Collaborateur> ListCollab=collaborateurDao.findAll();
		Collection <Collaborateur> ListEstDisp =new ArrayList<>();
		for (Collaborateur collaborateur : ListCollab) 
		{
			if(collaborateur.getEstdisponible()==1)
				ListEstDisp.add(collaborateur);
		}
		return ListEstDisp;
	}

	/*@Override
	public Collaborateur findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}*/

	
	@Override
	public Collaborateur findByNomAndPrenom(String nom,String prenom) {
		return collaborateurDao.findByNomAndPrenom(nom, prenom);
	}

	@Override
	public Collection<Collaborateur> findAll() {
		// TODO Auto-generated method stub
		return  collaborateurDao.findAll();
	}

	@Override
	public Collaborateur findByMatricule(String matricule) {
		// TODO Auto-generated method stub
		return collaborateurDao.findByMatricule(matricule);
	}

	@Override
	public Collection<Collaborateur> findByNomContaining(String infix) {
		// TODO Auto-generated method stub
		return collaborateurDao.findByNomContaining(infix) ;
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
	
	public AffectationService getAffectationService() {
		return affectationService;
	}

	public void setAffectationService(AffectationService affectationService) {
		this.affectationService = affectationService;
	}

	public AffectationDao getAffectationDao() {
		return affectationDao;
	}

	public void setAffectationDao(AffectationDao affectationDao) {
		this.affectationDao = affectationDao;
	}

	public CollaborateurDao getCollaborateurDao() {
		return collaborateurDao;
	}

	public void setCollaborateurDao(CollaborateurDao collaborateurDao) {
		this.collaborateurDao = collaborateurDao;
	}


	
	
}
