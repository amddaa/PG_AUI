import { Component, OnInit } from '@angular/core';
import {TournamentsService} from "../tournaments.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-tournaments',
  templateUrl: './tournaments.component.html',
  styleUrls: ['./tournaments.component.css']
})
export class TournamentsComponent implements OnInit {
  tournaments: any[] = [];

  constructor(private tournamentService: TournamentsService, private router: Router) {}

  ngOnInit() {
    this.tournamentService.getTournaments().subscribe((data: any) => {
      this.tournaments = data.tournaments;
    });
  }

  removeTournament(tournamentId: string, index: number): void {
    this.tournamentService.deleteTournament(tournamentId)
      .subscribe(data => {
        this.tournaments.splice(index, 1);
    });
  }

  editTournament(categoryID: string) {
    this.router.navigate(['/edit-tournament', categoryID]);
  }
}
