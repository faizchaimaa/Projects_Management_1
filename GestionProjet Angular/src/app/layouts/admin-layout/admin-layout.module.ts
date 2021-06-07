import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../dashboard/dashboard.component';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatRippleModule} from '@angular/material/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSelectModule} from '@angular/material/select';
import {ProjetsComponent} from "../../projets/projets.component";
import {HttpClientModule} from "@angular/common/http";
import {CollaborateursComponent} from "../../collaborateurs/collaborateurs.component";
import {CollaborateurDetailComponent} from "../../collaborateurs/collaborateur-detail/collaborateur-detail.component";
import {ProjetDetailComponent} from "../../projets/projet-detail/projet-detail.component";
import {CompetencesComponent} from "../../competences/competences.component";
import {AffectationComponent} from "../../affectation/affectation.component";

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatRippleModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatTooltipModule,
    HttpClientModule,
  ],
  declarations: [
    DashboardComponent,
    ProjetsComponent,
    CollaborateursComponent,
    CollaborateurDetailComponent,
    ProjetDetailComponent,
    CompetencesComponent,
      AffectationComponent,
  ]
})

export class AdminLayoutModule {}
