import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {TournamentsService} from "../tournaments.service";
import {ParticipantsService} from "../participants.service";

@Component({
  selector: 'app-participant-details',
  templateUrl: './participant-details.component.html',
  styleUrls: ['./participant-details.component.css']
})
export class ParticipantDetailsComponent {
  public tournamentID: any;
  public participantID: any;
  public participantData: any = {};
  constructor(private route: ActivatedRoute, private tournamentsService: TournamentsService,
              private participantsService: ParticipantsService) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.tournamentID = params['tournamentID'];
      this.participantID = params['participantID'];
      this.participantsService.getParticipantByID(this.participantID).subscribe((data) => {
        this.participantData = data;
      });
    });
  }
}
