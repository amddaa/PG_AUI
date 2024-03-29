import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TournamentsService} from "../tournaments.service";
import {ParticipantsService} from "../participants.service";

@Component({
  selector: 'app-tournament-details',
  templateUrl: './tournament-details.component.html',
  styleUrls: ['./tournament-details.component.css']
})
export class TournamentDetailsComponent {
  public tournamentID: any;
  public tournamentData: any = {};
  public participantsList: any = {};
  constructor(private route: ActivatedRoute, private tournamentsService: TournamentsService,
              private participantsService: ParticipantsService, private router: Router) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.tournamentID = params['id'];
      this.tournamentsService.getTournamentByID(this.tournamentID).subscribe((data) => {
        this.tournamentData = data;
      });
      this.participantsService.getTournamentParticipants(this.tournamentID).subscribe((data) => {
        this.participantsList = data;
      });
    });
  }

  deleteParticipant(participantID: string, index: number) {
    this.participantsService.deleteParticipant(participantID)
      .subscribe(data => {
        this.participantsList.participants.splice(index, 1);
      });
  }

  editParticipant(participantID: string) {
    this.router.navigate(['/tournament-details', this.tournamentID, 'edit-participant', participantID]);
  }

  detailsParticipant(participantID: string) {
    this.router.navigate(['/tournament-details', this.tournamentID, 'participant-details', participantID]);
  }
}
