import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Competence} from "../controller/model/competence.model";
import {CompetenceService} from "../controller/service/competence.service";

@Component({
  selector: 'app-competences',
  templateUrl: './competences.component.html',
  styleUrls: ['./competences.component.css']
})
export class CompetencesComponent implements OnInit {

  comp:Competence =new Competence();
  compForEdit:Competence =new Competence();
  PageCompetences:any=[];
  compAsupprimer: any;

  // pour la rechercher
  TitreArechercher: any="";


  EtatAjout:number=0;
  @ViewChild('CloseDelete') CloseDelete: ElementRef;
  @ViewChild('CloseAdd') CloseAdd: ElementRef;
  @ViewChild('CloseUpdate') CloseUpdate: ElementRef;

  constructor(public http:HttpClient,public competenceService:CompetenceService,public router:Router) { }

  ngOnInit(): void
  {
    this.competenceService.getCompetences().subscribe(data=>{
      this.PageCompetences=data;
    })
  }

  supprimerCompetence() {
    this.competenceService.DeleteCompetence(this.compAsupprimer);
    this.CloseDelete.nativeElement.click();
    this.PageCompetences.splice(this.PageCompetences.indexOf(this.comp),1);
  }
  // cette méthode permet de vérifier que les champs ne sont ps vide
  VerificationInput(co:any)
  {
    if(co.titre=="" || co.description=="")
    {
      return 0
    }else return 1;
  }
  close(){this.CloseAdd.nativeElement.click();}
  ajouterCompetence()
  {
      if (this.VerificationInput(this.comp))
      {
        this.competenceService.ajouteCompetence(this.comp).subscribe(data => {
          if(data==1)
          {
            this.PageCompetences.push(this.comp);
            this.close();
          }else {
            this.EtatAjout=2;
          }
        });
      }else
        this.EtatAjout=3;
  }

  ModifierCompetence()
  {
    this.competenceService.UpdateCompetence(this.compForEdit);
    this.CloseUpdate.nativeElement.click();
  }

  chercherParTitre()
  {
    if(this.TitreArechercher!=""){
      this.competenceService.getCompetenceByTitre(this.TitreArechercher).subscribe(data=>
      {
        console.log(data);
        if(data!==null)
        {
          this.PageCompetences=[];
          this.PageCompetences.push(data);

        }else this.PageCompetences=[];
      });
    }else {
      this.competenceService.getCompetences().subscribe(data=>
      {
        this.PageCompetences=data;
      });
    }
  }
}
