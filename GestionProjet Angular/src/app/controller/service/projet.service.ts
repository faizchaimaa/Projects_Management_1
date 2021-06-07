import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Projet} from "../model/projet.model";
import {Tache} from "../model/tache.model";
import {CompetenceTache} from "../model/competence-tache.model";

@Injectable({
  providedIn: 'root'
})
export class ProjetService {

  constructor(public http:HttpClient) { }
  getProjets()
  {
    return this.http.get("http://localhost:8091/manageproject/projets/");
  }
  getTaches()
  {
    return this.http.get("http://localhost:8091/manageproject/tache/");
  }
  ajouteProjet(projet:Projet)
  {
    return  this.http.post<number>("http://localhost:8091/manageproject/projets/",projet);
  }
  DeleteProjet(numProjet:number)
  {
    console.log(this.http.delete<number>("http://localhost:8091/manageproject/projets/delete/"+numProjet).subscribe(data => {
    }));
  }
  UpdateProjet(projet:Projet)
  {
    console.log(this.http.post<number>("http://localhost:8091/manageproject/projets/up",projet).subscribe(data => {
    }));
  }
  getProjet(idProjet: any) {
      return this.http.get("http://localhost:8091/manageproject/projets/numprojet/"+idProjet);
  }
  getProjetByIntitule(intitule: any) {
    return this.http.get("http://localhost:8091/manageproject/projets/intitule/"+intitule);
  }

  ajouteTache(tache:Tache) {
      return this.http.post<number>("http://localhost:8091/manageproject/tache/",tache);
    }
  DeleteTache(intitule:string)
  {
    console.log(this.http.delete<number>("http://localhost:8091/manageproject/tache/del/"+intitule).subscribe(data => {
    }));
  }
  UpdateTache(tache:Tache)
  {
    console.log(this.http.post<number>("http://localhost:8091/manageproject/tache/up/",tache).subscribe(data => {
    }));
  }

  ajouteCompetenceTache(TachCom: CompetenceTache)
  {
    return this.http.post<number>("http://localhost:8091/manageproject/competencetaches/",TachCom);
  }

    getTacheByIntitule(IntituleArechercher: any)
    {
      return this.http.get("http://localhost:8091/manageproject/tache/intitule/"+IntituleArechercher);
    }

    getCompetencesTache(id: any) {
      return this.http.get("http://localhost:8091/manageproject/competencetaches/findByTache/"+id);
    }

  deleteCompTacheById(id: any)
  {
    console.log(this.http.delete("http://localhost:8091/manageproject/competencetaches/del/"+id).subscribe(data => {
    }));
  }
}
