import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Collaborateur} from "../../controller/model/collaborateur.model";
import {CollaborateurService} from "../../controller/service/collaborateur.service";
import {CompetenceService} from "../../controller/service/competence.service";
import {FormControl} from "@angular/forms";
import {CompetenceCollaborateur} from "../../controller/model/competence-collaborateur.model";
import {Competence} from "../../controller/model/competence.model";

@Component({
  selector: 'app-collaborateur-detail',
  templateUrl: './collaborateur-detail.component.html',
  styleUrls: ['./collaborateur-detail.component.css']
})
export class CollaborateurDetailComponent implements OnInit {
  idCollab:any;
  collab:any=new Collaborateur();
  CompForms = new FormControl();
  collabComp:CompetenceCollaborateur=new CompetenceCollaborateur();
  listCompCollab:any=[];
  listCompCollabTemp:any=[];
  listComp:any=[];

  //pour la suppression
    listCompAsupprimer:any=[];
    listCompAajouter:any=[];
    collabCompAsupprimer:any;

  constructor(public activatedRoute:ActivatedRoute, public collaborateurService:CollaborateurService,public competenceService:CompetenceService) { }
  listTacheCollab: any=[];

  // cette fonction permet d'initialiser les données  d'un collaborateur
  ngOnInit(): void {
    this.idCollab=this.activatedRoute.snapshot.params['id'];
    this.collaborateurService.getCollaborateur(this.idCollab).subscribe(data=>{
      this.collab=data;
    });
    this.collaborateurService.getTachesCollab(this.idCollab).subscribe(data=>{
      this.listTacheCollab=data;
    });
    this.competenceService.getCompetences().subscribe(data=>{
      this.listComp=data;
    });
    this.collaborateurService.getCompetences(this.idCollab).subscribe(data=>{
      this.listCompCollab=data;
      for (let c of this.listCompCollab)
      {
        for (let cc of this.listComp)
        {
          if(cc.id==c.id)
            this.listCompCollabTemp.push(cc);
        }
      }
      this.CompForms.setValue(this.listCompCollabTemp);
    });

  }

  ExistInCompsCollab(comp:any)
  {
    for(let c of this.listCompCollabTemp)
    {
      if (comp.id==c.id)
        return 1;
    }
    return 0;
  }
  ExistInCompsAajouter(comp:any)
  {
    for(let c of this.CompForms.value)
    {
      if (comp.id==c.id)
        return 1;
    }
    return 0;
  }
  // cette fonction permet de modifier un collaborateur
  modifierCollab()
  {
    this.listCompAsupprimer=[];
    this.listCompAajouter=[];
    this.collaborateurService.UpdateCollab(this.collab).subscribe(data => {
      if(data==1)
      {

        for (let  cpt of this.CompForms.value)
        {
          if(this.ExistInCompsCollab(cpt)==0) this.listCompAajouter.push(cpt);
        }
        for (let  cptt of this.listCompCollabTemp)
        {
          if(this.ExistInCompsAajouter(cptt)==0) this.listCompAsupprimer.push(cptt);
        }

        this.collabComp.collaborateurVo=this.collab;
        for(let competence of this.listCompAajouter)
        {
          this.collabComp.competenceVo=competence;
          console.log(this.collabComp);
          this.collaborateurService.ajouteCompetenceCollaborateur(this.collabComp).subscribe(data=>{
            console.log(data);
          });
          this.listCompCollab.push(competence);
        }
        for(let competenc of this.listCompAsupprimer)
        {
          this.collaborateurService.getCompCollabByTitreMatricule(this.collab.matricule,competenc.titre).subscribe(data=>{
            this.collabCompAsupprimer=data;
            this.collaborateurService.deleteCompCollabById(this.collabCompAsupprimer.id);
          });
        }
        alert("La modification bien faite");
      }
    });

  }
}
