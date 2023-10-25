import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {HomeComponent} from "./home/home.component";
import {TournamentsComponent} from "./tournaments/tournaments.component";
import {AddTournamentComponent} from "./add-tournament/add-tournament.component";
import {EditTournamentComponent} from "./edit-tournament/edit-tournament.component";
import {TournamentDetailsComponent} from "./tournament-details/tournament-details.component";
import {AddParticipantComponent} from "./add-participant/add-participant.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'tournaments', component: TournamentsComponent },
  { path: 'add-tournament', component: AddTournamentComponent},
  { path: 'edit-tournament/:id', component: EditTournamentComponent },
  { path: 'tournament-details/:id', component: TournamentDetailsComponent },
  { path: 'tournament-details/:id/add-participant', component: AddParticipantComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
