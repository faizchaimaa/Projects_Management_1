import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Projet} from "../controller/model/projet.model";
import {Tache} from "../controller/model/tache.model";
import {ProjetService} from "../controller/service/projet.service";
import {FormControl} from "@angular/forms";
import {CollaborateurService} from "../controller/service/collaborateur.service";
import {Affectation} from "../controller/model/affectation.model";

@Component({
  selector: 'app-affectation',
  templateUrl: './affectation.component.html',
  styleUrls: ['./affectation.component.css']
})
export class AffectationComponent implements OnInit {

  projet:any=new Projet();
  estActive:any;
  tacheAaffecter:any=new Tache();
  listTache=[];
  Pageprojets:any=[];
  etatAjout: number=1;
  CollabForms =new FormControl();
  ListCollabAaffect :any=[];
  affectation :any=new Affectation();

  AffectationTacheAsupp: any;
  AffectationAsupprimer:any;


  //pour la suppression
  listAffectationAsupprimer:any=[];
  listAffectationAajouter:any=[];
  listAffectationTemp:any=[];
  res:any=[];

  @ViewChild('CloseAdd') CloseAdd: ElementRef;
  private ListCollabTache: any=[];

  constructor(public projetService:ProjetService , public collaborateurService:CollaborateurService) { }
  ngOnInit(): void
  {
    this.projetService.getProjets().subscribe(data=>{
      //console.log(data);
      this.Pageprojets=data;
      this.projet=this.Pageprojets[0];
      this.estActive=this.projet.intitule;
    });
    this.projetService.getTaches().subscribe(data=>{
      for (let c in data)
      {
        if(data[c].projet.id==this.projet.id)
          this.listTache.push(data[c]);
      }
    });

  }

  AfficheListTache(p:Projet)
  {
    this.projet=p;
    this.estActive=p.intitule;
    this.listTache=[];
    this.projetService.getTaches().subscribe(data=>{
      for (let c in data)
      {
        if(data[c].projet.id==this.projet.id)
          this.listTache.push(data[c]);
      }
    });
  }
  estAffecte(intitule:any)
  {
    this.collaborateurService.getCollaborateursAffectesTache(intitule).subscribe(data=> {
      this.res=data;
    });
    if(this.res.length==0)
      return "Affecter";
    else return "Update";
  }

  ExistInCompsCollab(aff:any)
  {
    for(let c of this.listAffectationTemp)
    {
      if (aff.id==c.id)
        return 1;
    }
    return 0;
  }
  ExistInCompsAajouter(aff:any)
  {
    for(let c of this.CollabForms.value)
    {
      if (aff.id==c.id)
        return 1;
    }
    return 0;
  }
  prepareAffactation(t: any)
  {
    this.listAffectationTemp=[];
    this.collaborateurService.getCollaborateursAaffecter(t.id).subscribe(data=>{
      this.ListCollabAaffect=data;
    });
    this.collaborateurService.getCollaborateursAffectesTache(t.intitule).subscribe(data=>{
      this.ListCollabTache=data;
      for (let c of this.ListCollabTache)
      {
        for (let cc of this.ListCollabAaffect)
        {
          if(cc.id==c.id)
            this.listAffectationTemp.push(cc);
        }
      }
      this.CollabForms.setValue(this.listAffectationTemp);
    });
    this.tacheAaffecter=t;

  }
/*
  affecter()
  {
      this.affectation.tacheVo=this.tacheAaffecter;
      //this.affectation.volumehoraire= this.tacheAaffecter.volumehoraire/this.tacheAaffecter.nombrecollaborateurs;
      for(let collab of this.CollabForms.value)
      {
        this.affectation.collaborateurVo=collab;
        // console.log(this.affectation);
        this.collaborateurService.ajouteAffectation(this.affectation).subscribe(data=>{
          console.log(data);
        });
      }
      this.CloseAdd.nativeElement.click();
  }
  */

  UpdateAffectation()
  {
    if(this.CollabForms.value.length==this.tacheAaffecter.nombrecollaborateurs)
    {
      this.listAffectationAsupprimer=[];
      this.listAffectationAajouter=[];
      for (let  cpt of this.CollabForms.value)
      {
        if(this.ExistInCompsCollab(cpt)==0) this.listAffectationAajouter.push(cpt);
      }
      for (let  cptt of this.listAffectationTemp)
      {
        if(this.ExistInCompsAajouter(cptt)==0) this.listAffectationAsupprimer.push(cptt);
      }

      this.affectation.tacheVo=this.tacheAaffecter;
      for(let collab of this.listAffectationAajouter)
      {
        this.affectation.collaborateurVo=collab;
        this.collaborateurService.ajouteAffectation(this.affectation).subscribe(data=>{
          console.log(data);
        });
      }
      for(let collab of this.listAffectationAsupprimer)
      {
        this.collaborateurService.getAffectationByMatriculeIntitule(collab.matricule,this.tacheAaffecter.intitule).subscribe(data=>{
          this.AffectationAsupprimer=data;
          this.collaborateurService.deleteAffectation(this.AffectationAsupprimer.id);
        });
      }
      this.CloseAdd.nativeElement.click();
      this.etatAjout=1;
    }else this.etatAjout=2;
  }
  affcheListCollabsAffectes(t: Tache)
  {
    this.collaborateurService.getCollaborateursAffectesTache(t.intitule).subscribe(data=>{
      this.ListCollabTache=data;
    });
  }
}
