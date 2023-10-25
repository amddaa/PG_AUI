import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {ParticipantsService} from "../participants.service";

@Component({
  selector: 'app-add-participant',
  templateUrl: './add-participant.component.html',
  styleUrls: ['./add-participant.component.css']
})
export class AddParticipantComponent {
  private tournamentID: any;
  constructor(private route: ActivatedRoute, private router: Router, private participantService: ParticipantsService) {
    this.route.params.subscribe(params => {
      this.tournamentID = params['id'];
    });
  }

  onSubmit(tournamentForm: NgForm) {
    if (tournamentForm.valid) {
      const createdSurname = tournamentForm.value.surname;
      const createdRank = tournamentForm.value.rank;

      this.participantService.putParticipant(this.tournamentID, createdSurname, createdRank)
        .subscribe(
          () => {
            tournamentForm.resetForm();
            this.router.navigate(['/tournament-details', this.tournamentID]);
          }
        );
    }
  }
}
