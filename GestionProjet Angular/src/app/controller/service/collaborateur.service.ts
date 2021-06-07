import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Collaborateur} from "../model/collaborateur.model";
import {Projet} from "../model/projet.model";
import {CompetenceCollaborateur} from "../model/competence-collaborateur.model";
import {Affectation} from "../model/affectation.model";

@Injectable({
  providedIn: 'root'
})
export class CollaborateurService {

  constructor(public http:HttpClient) { }
  getCollaborateurs()
  {
    return this.http.get("http://localhost:8091/manageproject/collaborateurs/");
  }
  ajouteCollaborateur(collaborateur:Collaborateur)
  {
    return this.http.post<number>("http://localhost:8091/manageproject/collaborateurs/",collaborateur);
  }
  ajouteCompetenceCollaborateur(competenceCollaborateur:CompetenceCollaborateur)
  {
    return this.http.post<number>("http://localhost:8091/manageproject/competencecollaborateurs/",competenceCollaborateur);
  }
  getCompCollabByTitreMatricule(matricule:any,titre:any)
  {
    return this.http.get("http://localhost:8091/manageproject/competencecollaborateurs/titre/"+titre+"/matricule/"+matricule);
  }
  deleteCompCollabById(id:any)
  {
    return console.log(this.http.delete("http://localhost:8091/manageproject/competencecollaborateurs/del/"+id).subscribe(data => {
    }));
  }
  getCollaborateur(matricule:number)
  {
    return this.http.get("http://localhost:8091/manageproject/collaborateurs/matricule/"+matricule);
  }
  getTachesCollab(matricule:number)
  {
    return this.http.get("http://localhost:8091/manageproject/affectations/matriculetotache/"+matricule);
  }
  getCompetences(matricule:number)
  {
    return this.http.get("http://localhost:8091/manageproject/competencecollaborateurs/matricule/"+matricule);
  }
  deleteCollab(matricule:number)
  {
    console.log(this.http.delete<number>("http://localhost:8091/manageproject/collaborateurs/del/"+matricule).subscribe(data => {
    }));
  }
  UpdateCollab(collaborateur:Collaborateur)
  {
    return this.http.post<number>("http://localhost:8091/manageproject/collaborateurs/up",collaborateur);
  }
  getCollaborateursAaffecter(idTache:any)
  {
    return this.http.get("http://localhost:8091/manageproject/tache/CollabsAaffecterById/"+idTache);
  }
  ajouteAffectation(affectation:Affectation)
  {
    return this.http.post<number>("http://localhost:8091/manageproject/affectations/",affectation);
  }
  deleteAffectation(id:any)
  {
    return this.http.delete<number>("http://localhost:8091/manageproject/affectations/del/"+id).subscribe(data =>
    {
      console.log(data);
    });
  }
  getAffectationByMatriculeIntitule(matricule:any,intitule:any)
  {
    return this.http.get("http://localhost:8091/manageproject/affectations/intitule/"+intitule+"/matricule/"+matricule);
  }
  getCollaborateursAffectesTache(intitule: string)
  {
      return this.http.get("http://localhost:8091/manageproject/affectations/intituletocollab/"+intitule);
  }
}
