package ma.isi.gestionProjet.model.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.isi.gestionProjet.bean.Affectation;
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.bean.CompetenceTache;
import ma.isi.gestionProjet.bean.Projet;
import ma.isi.gestionProjet.bean.Tache;
import ma.isi.gestionProjet.model.dao.AffectationDao;
import ma.isi.gestionProjet.model.dao.CompetenceTacheDao;
import ma.isi.gestionProjet.model.dao.TacheDao;
import ma.isi.gestionProjet.model.service.AffectationService;
import ma.isi.gestionProjet.model.service.CollaborateurService;
import ma.isi.gestionProjet.model.service.CompetenceCollaborateurService;
import ma.isi.gestionProjet.model.service.CompetenceTacheService;
import ma.isi.gestionProjet.model.service.ProjetService;
import ma.isi.gestionProjet.model.service.TacheService;

@Service
public class TacheServiceImpl implements TacheService {
	
	@Autowired
	private ProjetService  projetService ;
	@Autowired 
	private AffectationService affectationService;
	@Autowired 
	private AffectationDao affectationDao;
	@Autowired
	private TacheDao tacheDao;
	@Autowired
	private TacheService tacheService;
	@Autowired
	private CompetenceTacheService competenceTacheService;
	@Autowired 
	private CompetenceTacheDao competenceTacheDao;
	@Autowired
	private CollaborateurService collaborateurService;
	@Autowired
	private CompetenceCollaborateurService competenceCollaborateurService ;
	
	@Override
	public int save(Projet projet, Collection<Tache> taches) {
		 if(taches==null || taches.isEmpty()) {
		 return -1;
		 }else {
			 for(Tache tache :taches) 
			 {
				tache.setProjet(projet); 
				 tacheDao.save(tache);
			 }
			 return 1;
		 }
	}
	//Sauvegarder une Tache
	@Override
	public int saveTache(Tache tache) {
		Projet projet = projetService.findByNumprojet(tache.getProjet().getNumprojet());
		Tache ta =findByIntitule(tache.getIntitule());
		
		if(ta  !=null) {
			return -1;
		}
		else if (projet==null) {
			return -2;
	                            } 
		else {
			tache.setProjet(projet);
			tache.setDatefin(CalculeDateFinFinal(tache));
			tacheDao.save(tache);
			return 1;
		     }
	}
	//Mettre à jour une tache
	 
	public int update(Tache tache) {
	Projet projet = projetService.findByNumprojet(tache.getProjet().getNumprojet());
    Tache t=findByIntitule(tache.getIntitule());
    if(t==null) {
    	return -1;
    }else {
    	tache.setProjet(projet); 
		tache.setDatefin(CalculeDateFinFinal(tache));
		tacheDao.save(tache);
		return 1;	
		}  
    }

	 //Supprimer une tache
	@Override
	public int delete(String intitule) {
		Tache t=findByIntitule(intitule);
		Collection <CompetenceTache> listComptach=competenceTacheService.findAll();
		Collection <Affectation> affectations=affectationService.findAll();
		if(t==null) {
			return -1;}
		else if(listComptach.size()==0 && affectations.size()==0  ) {
			tacheDao.delete(t);
			return 0;
		}else {
			for(CompetenceTache competenceTache :listComptach) {
				
				if(competenceTache.getTache().getId()==t.getId()) {
				competenceTacheDao.delete(competenceTache);
				}
			}for(Affectation affectation :affectations) {
				
				if(affectation.getTache().getId()==t.getId()) {
				 affectationDao.delete(affectation);
				}
			}
			tacheDao.delete(t);
			
			 return 1;
			}
		
		} 
	
	@Override
	public int deleteByProjetNumprojet(int numprojet) {
		 
		return tacheDao.deleteByProjetNumprojet(numprojet);
	}
	
	@Override
	public Tache findByProjetNumprojet(int numprojet) {
		 
		return tacheDao.findByProjetNumprojet(numprojet);
	}

		@Override
		public Collection<Tache> findByProjet(Projet projet) {
			return tacheDao.findByProjet(projet);
		}
		
		@Override
		public Collection<Tache> findAll() {
			return  tacheDao.findAll();
		}

		@Override
		public Tache findByIntitule(String intitule) {
			return tacheDao.findByIntitule(intitule);
		}

	 
	public  long days(Date start, Date end){
	    //Ignore argument check

	    Calendar c1 = Calendar.getInstance();
	    c1.setTime(start);
	    int w1 = c1.get(Calendar.DAY_OF_WEEK);
	    c1.add(Calendar.DAY_OF_WEEK, -w1);

	    Calendar c2 = Calendar.getInstance();
	    c2.setTime(end);
	    int w2 = c2.get(Calendar.DAY_OF_WEEK);
	    c2.add(Calendar.DAY_OF_WEEK, -w2);

	    //end Saturday to start Saturday 
	    long days = (c2.getTimeInMillis()-c1.getTimeInMillis())/(1000*60*60*24);
	    long daysWithoutWeekendDays = days-(days*2/7);

	    // Adjust days to add on (w2) and days to subtract (w1) so that Saturday
	    // and Sunday are not included
	    if (w1 == Calendar.SUNDAY && w2 != Calendar.SATURDAY) {
	        w1 = Calendar.MONDAY;
	    } else if (w1 == Calendar.SATURDAY && w2 != Calendar.SUNDAY) {
	        w1 = Calendar.FRIDAY;
	    } 

	    if (w2 == Calendar.SUNDAY) {
	        w2 = Calendar.MONDAY;
	    } else if (w2 == Calendar.SATURDAY) {
	       w2 = Calendar.FRIDAY;
	    }

	    return daysWithoutWeekendDays-w1+w2;
	}
	public  int CalculerNbrJour(int volumeH,int nb) {
				int nbjour;
				int volumeHoraire0=volumeH/nb;
			    if(volumeHoraire0%8==0) {
			    	 nbjour=volumeHoraire0/8;
			    	System.out.println(nbjour);
			    }else {
			    	 nbjour=volumeHoraire0/8+1;
			    	System.out.println(nbjour);
			    }
			   
			   return nbjour;
			   
			  }
	public Date CalculeDateFin(Date datedebut,int nbj) 
		{
			Date dateFin1=(Date) datedebut.clone();
			dateFin1.setHours(dateFin1.getHours()+24*nbj);
			int nbWorkDays=(int)days(datedebut,dateFin1);
			if(nbj-nbWorkDays==1) 
			{
				dateFin1.setHours(dateFin1.getHours()+24*2);
			}else {
						dateFin1.setHours(dateFin1.getHours()+24*(nbj-nbWorkDays));
			}
			return dateFin1;

		}
	
	public int Condition1(Collection<Long> A,Collection<Long> B)
	{
		if(A.size()==0)return 0;
		if(B.containsAll(A)) 
		{
			return 1;
		}else 
			return 0;
	}
	
	// calcule date fin
	public Date CalculeDateFinFinal(Tache tache) 
	{
		return CalculeDateFin(tache.getDatedebut(),CalculerNbrJour(tache.getVolumehoraire(),tache.getNombrecollaborateurs()));
	}
	@Override
	public Collection<Collaborateur> CollabAaffecter(Long id) 
	{
		
		Collection <Long> A= new ArrayList<>();
		Collection <Long> B= new ArrayList<>();
		
		Collection <Collaborateur> ListFinal=new ArrayList<>();
				
		Collection <Collaborateur> listCollabDisponible=collaborateurService.findByEstdisponible();
		Collection <CompetenceTache> ListCompetencesTache=competenceTacheService.findByTache(id);
		Collection <Competence> ListCompetencesCollab;
			
		
		for (CompetenceTache ct : ListCompetencesTache) 
		{
			A.add(ct.getCompetence().getId());
		}
		for (Collaborateur c : listCollabDisponible) 
		{
			ListCompetencesCollab=competenceCollaborateurService.findByCollaborateurMatricule(c.getMatricule());
			for (Competence comp : ListCompetencesCollab) 
			{
				B.add(comp.getId());
			}
			if(Condition1(A,B)==1)
			{
				ListFinal.add(c);
			}
		}
		return ListFinal;
	}
	
	public ProjetService getProjetService() {
		return projetService;
	}

	public void setProjetService(ProjetService projetService) {
		this.projetService = projetService;
	}

	public TacheDao getTacheDao() {
		return tacheDao;
	}

	public void setTacheDao(TacheDao tacheDao) {
		this.tacheDao = tacheDao;
	}

	public CompetenceTacheDao getCompetenceTacheDao() {
		return competenceTacheDao;
	}
	public void setCompetenceTacheDao(CompetenceTacheDao competenceTacheDao) {
		this.competenceTacheDao = competenceTacheDao;
	}
	

	public AffectationDao getAffectationDao() {
		return affectationDao;
	}
	public void setAffectationDao(AffectationDao affectationDao) {
		this.affectationDao = affectationDao;
	}
	
	
	
	
	
	
}


