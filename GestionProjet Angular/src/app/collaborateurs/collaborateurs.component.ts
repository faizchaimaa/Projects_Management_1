import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {CollaborateurService} from "../controller/service/collaborateur.service";
import {Collaborateur} from "../controller/model/collaborateur.model";
import {FormControl} from "@angular/forms";
import {CompetenceCollaborateur} from "../controller/model/competence-collaborateur.model";
import {CompetenceService} from "../controller/service/competence.service";

@Component({
  selector: 'app-collaborateurs',
  templateUrl: './collaborateurs.component.html',
  styleUrls: ['./collaborateurs.component.css']
})
export class CollaborateursComponent implements OnInit {

  collab:Collaborateur =new Collaborateur();
  PageCollaborateurs:any=[];
  CollabAsupprimer:any;

  //pour la recherche
  matriculeArechercher:any="";
  TypeRecherche="Mat";

  // ^pour l'ajout des compétence
  CompForms = new FormControl();
  collabComp:CompetenceCollaborateur=new CompetenceCollaborateur();
  listComp:any;


  EtatAjout:number=0;
  @ViewChild('CloseDelete') CloseDelete: ElementRef;
  @ViewChild('CloseAdd') CloseAdd: ElementRef;
  @ViewChild('CloseUpdate') CloseUpdate: ElementRef;

  constructor(public http:HttpClient,public collaborateurService:CollaborateurService,public competenceService: CompetenceService,public router:Router) { }

  ngOnInit(): void
  {
    this.collaborateurService.getCollaborateurs().subscribe(data=>
    {
      if (this.matriculeArechercher=="")
      {
        this.PageCollaborateurs=data;
      }
    });
    this.competenceService.getCompetences().subscribe(data=>{
      this.listComp=data;
    });
  }
  close(){this.CloseAdd.nativeElement.click();}

  // cette méthode permet de vérifier que les champs ne sont ps vide
  VerificationInput(collaborateur:any)
  {
    if(collaborateur.matricule=="" || collaborateur.nom=="" || collaborateur.prenom=="")
    {
      return 0
    }else return 1;
  }
  // cette méthode permet l'ajout d'un collaborateur

  ajouterCollaborateur()
  {
      console.log(this.collab);
      if(this.VerificationInput(this.collab)==1){
        this.collaborateurService.ajouteCollaborateur(this.collab).subscribe(data => {
          if(data==1)
          {
            this.collabComp.collaborateurVo=this.collab;
            for (let  cpt of this.CompForms.value)
            {
              this.collabComp.competenceVo=cpt;
              console.log(this.collabComp);
              this.collaborateurService.ajouteCompetenceCollaborateur(this.collabComp).subscribe(data=>{
                console.log(data);
              });
            }
            this.PageCollaborateurs.push(this.collab);
            this.close();
          }else {
            this.EtatAjout=2;
          }
        });
      }else
        this.EtatAjout=3;
  }
  rederictedInf(id:number)
  {
    this.router.navigate((['collaborateurs/inf',id]));
  }
  supprimerCollab(matricule: number)
  {
    this.collaborateurService.deleteCollab(matricule);
    this.CloseDelete.nativeElement.click();
    this.PageCollaborateurs.splice(this.PageCollaborateurs.indexOf(this.collab),1);
  }
  chercherParMatr()
  {
    if(this.matriculeArechercher!=""){
      this.collaborateurService.getCollaborateur(this.matriculeArechercher).subscribe(data=>
      {
        console.log(data);
        if(data!==null)
        {
          this.PageCollaborateurs=[];
          this.PageCollaborateurs.push(data);
        }else this.PageCollaborateurs=[];
      });
    }else {
      this.collaborateurService.getCollaborateurs().subscribe(data=>
      {
          this.PageCollaborateurs=data;
      });
    }

  }

  chercherParNom() {

  }

  chercherParNomPr() {

  }
}
