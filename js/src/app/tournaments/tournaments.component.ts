import { Component, OnInit } from '@angular/core';
import {TournamentsService} from "../tournaments.service";

@Component({
  selector: 'app-tournaments',
  templateUrl: './tournaments.component.html',
  styleUrls: ['./tournaments.component.css']
})
export class TournamentsComponent implements OnInit {
  tournaments: any[] = [];

  constructor(private tournamentService: TournamentsService) {}

  ngOnInit() {
    this.tournamentService.getTournaments().subscribe((data: any) => {
      this.tournaments = data.tournaments;
    });
  }

  removeTournament(tournamentId: string, index: number): void {
    this.tournamentService.deleteTournament(tournamentId).subscribe(() => {
      this.tournaments.splice(index, 1); // Usuń element z listy po sukcesie usunięcia
    });
  }
}
