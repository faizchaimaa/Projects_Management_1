import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Competence} from "../model/competence.model";

@Injectable({
  providedIn: 'root'
})
export class CompetenceService {
  constructor(public http:HttpClient) { }
  getCompetences()
  {
    return this.http.get("http://localhost:8091/manageproject/competences/");
  }
  ajouteCompetence(competence:Competence)
  {
    return this.http.post<number>("http://localhost:8091/manageproject/competences/",competence);
  }
  DeleteCompetence(titre:string)
  {
    console.log(this.http.delete<number>("http://localhost:8091/manageproject/competences/del/"+titre).subscribe(data => {
    }));
  }
  UpdateCompetence(competence:Competence)
  {
    console.log(this.http.post<number>("http://localhost:8091/manageproject/competences/up",competence).subscribe(data => {
    }));
  }
  getCompetenceByTitre(titre: any) {
    return this.http.get("http://localhost:8091/manageproject/competences/titre/"+titre);
  }

}
