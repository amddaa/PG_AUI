import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TournamentsService} from "../tournaments.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-edit-tournament',
  templateUrl: './edit-tournament.component.html',
  styleUrls: ['./edit-tournament.component.css']
})
export class EditTournamentComponent {
  public tournamentID: any;
  public tournamentData: any = {};
  constructor(private route: ActivatedRoute, private tournamentService: TournamentsService, private router: Router) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.tournamentID = params['id'];
      this.tournamentService.getTournamentByID(this.tournamentID).
      subscribe(data => {
        this.tournamentData.name  = data.name;
        this.tournamentData.requiredRank = data.requiredRank;
      });
    });
  }

  saveChanges() {
    this.tournamentService.updateTournament(this.tournamentData, this.tournamentID)
      .subscribe(response => {
        this.router.navigate(['/tournaments']);
      });

  }
}
