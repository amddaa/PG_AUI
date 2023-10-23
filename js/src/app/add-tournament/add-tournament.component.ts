import { Component } from '@angular/core';
import {TournamentsService} from "../tournaments.service";
import {NgForm} from "@angular/forms";
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-tournament',
  templateUrl: './add-tournament.component.html',
  styleUrls: ['./add-tournament.component.css']
})
export class AddTournamentComponent {
  constructor(private router: Router, private tournamentService: TournamentsService) { }

  onSubmit(tournamentForm: NgForm) {
    if (tournamentForm.valid) {
      const createdName = tournamentForm.value.name;
      const createdRequiredRank = tournamentForm.value.requiredRank;

      this.tournamentService.createTournament(createdName, createdRequiredRank)
        .subscribe(
          () => {
            tournamentForm.resetForm();
            this.router.navigate(['/tournaments']);
          }
        );
    }
  }
}
