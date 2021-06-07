package ma.isi.gestionProjet.model.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.isi.gestionProjet.bean.CompetenceTache;
import ma.isi.gestionProjet.bean.Projet;
import ma.isi.gestionProjet.bean.Tache;
import ma.isi.gestionProjet.model.dao.ProjetDao;
import ma.isi.gestionProjet.model.dao.TacheDao;
import ma.isi.gestionProjet.model.service.ProjetService;
import ma.isi.gestionProjet.model.service.TacheService;

@Service
public class ProjetServiceImpl implements ProjetService {
	 
	@Autowired
	private ProjetDao projetDao;
	@Autowired
	private TacheService tacheService;
	@Autowired
	private TacheDao tacheDao;
	 
	@Override
	                                      
	 //Sauvegarder un projet
	
	public int save(Projet projet) {

		Projet p=findByNumprojet(projet.getNumprojet());
		if(p !=null) {
			return -1;
	   }else {
		   projet.setDatefin(CalculeDateFinFinal(projet));
			projetDao.save(projet);
		    tacheService.save(projet, projet.getTaches());
			return 1;
		}
	}
	
	  
	//Mettre à jour un projet
	
	@Override
	public int update(Projet projet) {
        Projet p=findByNumprojet(projet.getNumprojet());
        if(p == null) {
            return -1;
        }
        else {
        	  projet.setDatefin(CalculeDateFinFinal(projet));
            projetDao.save(projet);
            return 1;
        }	 
	}
	
	//Supprimer le projet avec ses taches
	@Override @Transactional
	public int deleteByNumprojet(int numprojet) {
 
		 Projet p =projetDao.findByNumprojet(numprojet);
		 
	 Collection <Tache> Taches=tacheService.findAll();
	 if(Taches.size()==0) {
		 projetDao.deleteByNumprojet(numprojet);
		 return  0;
	 }else {
		 for(Tache tache :Taches) {
			 if(tache.getProjet().getId()==p.getId()) {
				 String intitule=tache.getIntitule(); 
				 tacheService.delete(intitule);
				 }}
	 
		 projetDao.deleteByNumprojet(numprojet);
		 return 1;
	 }
	   
	 
	}
  
	                                     

	//Trover un projet par numprojet
	@Override
	public Projet findByNumprojet(int numprojet) {
		// TODO Auto-generated method stub
		return projetDao.findByNumprojet(numprojet);
	}

	//Trouver ts les projets
	@Override
	public Collection<Projet> findAll() {
		// TODO Auto-generated method stub
		return  projetDao.findAll();
	} 
	
	 //Trouver un projet par intitule
		@Override
		public Projet findByIntitule(String intitule) {
			// TODO Auto-generated method stub
			return projetDao.findByIntitule(intitule);
		}
		

		/*@Override
		public Projet findById(Long id) {
			// TODO Auto-generated method stub
			return projetDao.findById(id);

		}*/
	@Override
	public Projet findById(Long id) {
		// TODO Auto-generated method stub
		return null;
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
		
	// calcule date fin
	public Date CalculeDateFinFinal(Projet projet) 
	{
		return CalculeDateFin(projet.getDatedebut(),CalculerNbrJour(projet.getVolumehoraire(),projet.getNombrecollaborateurs()));
	}
   

	 
		public TacheService getTacheService() {
			return tacheService;
		}

		public void setTacheService(TacheService tacheService) {
			this.tacheService = tacheService;
		}

		public ProjetDao getProjetDao() {
			return projetDao;
		}

		public void setProjetDao(ProjetDao projetDao) {
			this.projetDao = projetDao;
		}

		

		
		
		
		

		 
		

	
}
