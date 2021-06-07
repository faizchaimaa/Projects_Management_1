import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';


import { AppRoutingModule } from './app.routing';
import { ComponentsModule } from './components/components.module';

import { AppComponent } from './app.component';

import { DashboardComponent } from './dashboard/dashboard.component';
import { ProjetsComponent } from './projets/projets.component';
import { CollaborateursComponent } from './collaborateurs/collaborateurs.component';
import { CollaborateurDetailComponent } from './collaborateurs/collaborateur-detail/collaborateur-detail.component';
import { ProjetDetailComponent } from './projets/projet-detail/projet-detail.component';
import { CompetencesComponent } from './competences/competences.component';

import {
  AgmCoreModule
} from '@agm/core';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import {HttpClientModule} from "@angular/common/http";
import {ProjetService} from "./controller/service/projet.service";
import {CollaborateurService} from "./controller/service/collaborateur.service";
import {CompetenceService} from "./controller/service/competence.service";

@NgModule({
  imports: [
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule,
    AgmCoreModule.forRoot({
      apiKey: 'YOUR_GOOGLE_MAPS_API_KEY'
    })
  ],
  declarations: [
    AppComponent,
    AdminLayoutComponent,
  ],
  providers: [ProjetService,CollaborateurService,CompetenceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
