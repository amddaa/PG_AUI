import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from "@angular/forms";

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { TournamentsComponent } from './tournaments/tournaments.component';
import { AddTournamentComponent } from './add-tournament/add-tournament.component';
import { EditTournamentComponent } from './edit-tournament/edit-tournament.component';
import { TournamentDetailsComponent } from './tournament-details/tournament-details.component';
import { AddParticipantComponent } from './add-participant/add-participant.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TournamentsComponent,
    AddTournamentComponent,
    EditTournamentComponent,
    TournamentDetailsComponent,
    AddParticipantComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
