import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ParticipantsService} from "../participants.service";

@Component({
  selector: 'app-edit-participant',
  templateUrl: './edit-participant.component.html',
  styleUrls: ['./edit-participant.component.css']
})
export class EditParticipantComponent {
  tournamentID: any;
  participantID: any;
  participantData: any = {};

  constructor(private route: ActivatedRoute, private participantService: ParticipantsService,
              private router: Router) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.tournamentID = params['tournamentID'];
      this.participantID = params['participantID'];
    });
    this.participantService.getParticipantByID(this.participantID).
    subscribe(data => {
      this.participantData.surname  = data.surname;
      this.participantData.rank = data.rank;
      this.participantData.tournament = this.tournamentID;
    });
  }

  saveChanges() {
    this.participantService.updateParticipant(this.participantData, this.participantID)
      .subscribe(response => {
        this.router.navigate(['/tournament-details', this.tournamentID]);
      });
  }
}
