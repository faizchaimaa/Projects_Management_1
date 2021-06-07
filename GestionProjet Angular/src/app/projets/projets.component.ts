import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProjetService} from "../controller/service/projet.service";
import {Router} from "@angular/router";
import {Projet} from "../controller/model/projet.model";
import { ViewChild, ElementRef} from '@angular/core';
@Component({
  selector: 'app-projets',
  templateUrl: './projets.component.html',
  styleUrls: ['./projets.component.css']
})

export class ProjetsComponent implements OnInit {


  prjt:Projet =new Projet();
  Pageprojets:any=[];
  prjtAsupprimer: any;

  //pour la recherche
  numProjetArechercher:any="";
  IntituleArechercher: any="";
  TypeRecherche="numProjet";

  // pour la pagination
  i:number=1;
  PageprojetsPag:any=[];
  pageIndex:any=[];

  nbrPage:number;
  EtatAjout:number=0;
  @ViewChild('CloseDelete') CloseDelete: ElementRef;
  @ViewChild('CloseAdd') CloseAdd: ElementRef;
  @ViewChild('CloseUpdate') CloseUpdate: ElementRef;

  constructor(public http:HttpClient,public projetService:ProjetService,public router:Router) { }

  ngOnInit(): void
  {
    this.projetService.getProjets().subscribe(data=>{
      console.log(data);
      this.Pageprojets=data;
      this.goToPage();
    })
  }

  goToPage()
  {
    if(this.Pageprojets.length%5==0)
      this.nbrPage=this.Pageprojets.length/5;
    else this.nbrPage=(this.Pageprojets.length/5)+1;
    for (var _j=1;_j<=this.nbrPage;_j++)
    {
      if(_j==1 && this.pageIndex.length!=0)
        this.pageIndex=[];
      this.pageIndex.push(_j);
    }
    for (var _i=(this.i-1)*5;_i<((this.i-1)*5)+5;_i++)
    {
      if(_i==(this.i-1)*5 && this.PageprojetsPag.length!=0)
        this.PageprojetsPag=[];
      if (this.Pageprojets[_i]!=null)
        this.PageprojetsPag.push(this.Pageprojets[_i]);
    }
  }
  supprimerProjet(numpProjet: any) {
    this.projetService.DeleteProjet(numpProjet);
    this.CloseDelete.nativeElement.click();
    this.Pageprojets.splice(this.Pageprojets.indexOf(this.prjt),1);
    this.goToPage();
  }
  // cette méthode permet de vérifier que les champs ne sont ps vide
  VerificationInput(pr:any)
  {
    if(pr.intitule=="" || pr.numprojet==null || pr.volumehoraire==null)
    {
      return 0
    }else return 1;
  }
  close(){this.CloseAdd.nativeElement.click();}
  ajouterProjet()
  {
      if(this.VerificationInput(this.prjt))
      {
        this.projetService.ajouteProjet(this.prjt).subscribe(data => {
          if(data==1)
          {
            //this.EtatAjout=1;
            this.Pageprojets.push(this.prjt);
            this.close();
            this.goToPage();
            window.location.reload();
            //setTimeout("alert('lajout à été avec succée ')",500);
          }else {
            this.EtatAjout=2;
          }
        });
      }else{
        this.EtatAjout=3;
      }
  }
  // window.location.reload();
  // var snd = new Audio('/alarm.mp3');
  // snd.play();

  ModifierProjet()
  {
    this.projetService.UpdateProjet(this.prjt);
    this.CloseUpdate.nativeElement.click();
  }

  redirectInf(id:number) {
    this.router.navigate((['projets/inf',id]));
  }
  Pagination(index:number)
  {
    this.i=index;
    //this.PageprojetsPag=null;
    //this.pageIndex=null;
    this.goToPage();
  }
  chercherParNumProjet()
  {
    if(this.numProjetArechercher!=""){
      this.projetService.getProjet(this.numProjetArechercher).subscribe(data=>
      {
        console.log(data);
        if(data!==null)
        {
          this.Pageprojets=[];
          this.Pageprojets.push(data);
          this.goToPage();
        }else this.Pageprojets=[];
      });
    }else {
      this.projetService.getProjets().subscribe(data=>
      {
        this.Pageprojets=data;
        this.goToPage();
      });
    }
  }
  chercherParIntitule() {
    if(this.IntituleArechercher!=""){
      this.projetService.getProjetByIntitule(this.IntituleArechercher).subscribe(data=>
      {
        console.log(data);
        if(data!==null)
        {
          this.Pageprojets=[];
          this.Pageprojets.push(data);
          this.goToPage();
        }else this.Pageprojets=[];
      });
    }else {
      this.projetService.getProjets().subscribe(data=>
      {
        this.Pageprojets=data;
        this.goToPage();
      });
    }
  }
}
