import { Routes } from '@angular/router';

import { DashboardComponent } from '../../dashboard/dashboard.component';
import {ProjetsComponent} from "../../projets/projets.component";
import {CollaborateursComponent} from "../../collaborateurs/collaborateurs.component";
import {CollaborateurDetailComponent} from "../../collaborateurs/collaborateur-detail/collaborateur-detail.component";
import {ProjetDetailComponent} from "../../projets/projet-detail/projet-detail.component";
import {CompetencesComponent} from "../../competences/competences.component";
import {AffectationComponent} from "../../affectation/affectation.component";

export const AdminLayoutRoutes: Routes = [
    // {
    //   path: '',
    //   children: [ {
    //     path: 'dashboard',
    //     component: DashboardComponent
    // }]}, {
    // path: '',
    // children: [ {
    //   path: 'userprofile',
    //   component: UserProfileComponent
    // }]
    // }, {
    //   path: '',
    //   children: [ {
    //     path: 'icons',
    //     component: IconsComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'notifications',
    //         component: NotificationsComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'maps',
    //         component: MapsComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'typography',
    //         component: TypographyComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'upgrade',
    //         component: UpgradeComponent
    //     }]
    // }
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'collaborateurs/inf/:id',     component: CollaborateurDetailComponent },
    { path: 'projets/inf/:id',     component: ProjetDetailComponent },
    { path: 'projets',     component: ProjetsComponent },
    { path: 'collaborateurs',     component: CollaborateursComponent },
    { path: 'competences',     component: CompetencesComponent },
    { path: 'affectation',     component: AffectationComponent },


];
