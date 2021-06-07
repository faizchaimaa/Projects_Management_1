import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Projet} from "../../controller/model/projet.model";
import {ProjetService} from "../../controller/service/projet.service";
import {Tache} from "../../controller/model/tache.model";
import {FormControl} from "@angular/forms";
import {CompetenceService} from "../../controller/service/competence.service";
import {CompetenceTache} from "../../controller/model/competence-tache.model";
import {CollaborateurService} from "../../controller/service/collaborateur.service";

@Component({
  selector: 'app-projet-detail',
  templateUrl: './projet-detail.component.html',
  styleUrls: ['./projet-detail.component.css']
})
export class ProjetDetailComponent implements OnInit {
  idProjet:any;
  projet:any=new Projet();
  tache:any=new Tache();
  tacheAsupprimer:any;
  listTache=[];

  // pour la rechercher
  IntituleArechercher: any="";

  //pour afficher les détail d'une tache
  tachePlusInfo:any=new Tache();
  ListCompetencesTache:any=[];

  // pour l'ajout des compétence
  CompForms = new FormControl();
  listComp:any;
  TachCom:CompetenceTache= new CompetenceTache();
  // pour la modification
  CompFormsForEdit= new FormControl();
  listCompTachTemp:any=[];
  //pour la suppression
  listCompAsupprimer:any=[];
  listCompAajouter:any=[];
  tacheCompAsupprimer:any;


  EtatAjout:number=0;
  @ViewChild('CloseDelete') CloseDelete: ElementRef;
  @ViewChild('CloseAdd') CloseAdd: ElementRef;
  @ViewChild('CloseUpdate') CloseUpdate: ElementRef;

  ListEquipeProjet: any=[];
  temp:any=[];



  constructor(public activatedRoute:ActivatedRoute, public projetService:ProjetService,public competenceService:CompetenceService,public collaborateurService:CollaborateurService) { }

  ngOnInit(): void {
    this.idProjet=this.activatedRoute.snapshot.params['id'];
    this.projetService.getProjet(this.idProjet).subscribe(data=>{
      this.projet=data;
    });
    this.projetService.getTaches().subscribe(data=>{
      for (let c in data)
      {
          if(data[c].projet.id==this.projet.id)
          {
            this.listTache.push(data[c]);
            this.collaborateurService.getCollaborateursAffectesTache(data[c].intitule).subscribe(data=>
            {
              this.temp=data;
              for (let coll of this.temp)
              {
                if (!this.ListEquipeProjet.some(col=>col.id === coll.id))
                 this.ListEquipeProjet.push(coll);
              }
            });
          }
      }
    });
    this.competenceService.getCompetences().subscribe(data=>{
      this.listComp=data;
    });

  }
  VerificationInput(tch:any)
  {
    if(tch.intitule=="" || tch.datedebut==null || tch.volumehoraire==null)
    {
      return 0
    }else return 1;
  }
close(){this.CloseAdd.nativeElement.click();}
ajouterTache()
  {
    if(this.VerificationInput(this.tache)==1)
    {
      this.tache.projet.numprojet=this.idProjet;
      this.projetService.ajouteTache(this.tache).subscribe(data => {
        if(data==1)
        {
          this.TachCom.tacheVo=this.tache;
          if(this.CompForms.value.length!=0)
          {
            for (let  cpt of this.CompForms.value)
            {
              this.TachCom.competenceVo=cpt;
              this.projetService.ajouteCompetenceTache(this.TachCom).subscribe(data=>{
                console.log(data);
              });
            }
          }
          this.listTache.push(this.tache);
          this.close();
          window.location.reload();
          //setTimeout("alert('lajout à été avec succée ')",500);
        }else {
          this.EtatAjout=2;
        }
      });
    }else
      this.EtatAjout=3;
  }

  ExistInCompsCollab(comp:any)
  {
    for(let c of this.listCompTachTemp)
    {
      if (comp.id==c.id)
        return 1;
    }
    return 0;
  }
  ExistInCompsAajouter(comp:any)
  {
    for(let c of this.CompFormsForEdit.value)
    {
      if (comp.id==c.id)
        return 1;
    }
    return 0;
  }
  prepareModification(id:any)
  {
    this.projetService.getCompetencesTache(id).subscribe(data=>{
      this.ListCompetencesTache=data;
      for (let c of this.ListCompetencesTache)
      {
        for (let cc of this.listComp)
        {
          if(cc.id==c.competence.id)
            this.listCompTachTemp.push(cc);
        }
      }
      this.CompFormsForEdit.setValue(this.listCompTachTemp);
      //console.log(this.listCompTachTemp);
    });
  }
  modifierTache(id:any)
  {
    this.listCompAsupprimer=[];
    this.listCompAajouter=[];
    this.projetService.UpdateTache(this.tache);
    for (let  cpt of this.CompFormsForEdit.value)
    {
      if(this.ExistInCompsCollab(cpt)==0) this.listCompAajouter.push(cpt);
    }
    for (let  cptt of this.listCompTachTemp)
    {
      if(this.ExistInCompsAajouter(cptt)==0) this.listCompAsupprimer.push(cptt);
    }
    this.TachCom.tacheVo=this.tache;
    if(this.listCompAajouter.length!=0)
    {
      for (let  cpt of this.listCompAajouter)
      {
        this.TachCom.competenceVo=cpt;
        this.projetService.ajouteCompetenceTache(this.TachCom).subscribe(data=>{
          console.log(data);
        });
      }
    }
    for(let competenc of this.listCompAsupprimer)
    {
        for(let compTach of this.ListCompetencesTache)
        {
          if(competenc.id==compTach.competence.id)
           this.projetService.deleteCompTacheById(compTach.id);
        }
    }
    this.CloseUpdate.nativeElement.click();
  }
  supprimerTache() {
    this.projetService.DeleteTache(this.tacheAsupprimer);
    this.CloseDelete.nativeElement.click();
    this.listTache.splice(this.listTache.indexOf(this.tache),1);

  }
  chercherParIntitule()
  {
    if(this.IntituleArechercher!=""){
      this.projetService.getTacheByIntitule(this.IntituleArechercher).subscribe(data=>
      {
        console.log(data);
        if(data!==null)
        {
          this.listTache=[];
          this.listTache.push(data);
        }else this.listTache=[];
      });
    }else {
      this.projetService.getTaches().subscribe(data=>{
        for (let c in data)
        {
          if(data[c].projet.id==this.projet.id)
            this.listTache.push(data[c]);
        }
        //console.log(this.listTache);
      });
    }
  }
  afficheCompetencesTache(id:any)
  {
    this.projetService.getCompetencesTache(id).subscribe(data=>{
      this.ListCompetencesTache=data;
    });
  }
}
