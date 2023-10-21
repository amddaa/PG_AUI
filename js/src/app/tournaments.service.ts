import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TournamentsService {
  private getTournamentsURL = "http://localhost:8083/api/tournaments";
  private deleteTournamentURL = "http://localhost:8083/api/tournaments";
  constructor(private http: HttpClient) { }

  getTournaments(): Observable<any> {
    return this.http.get(this.getTournamentsURL);
  }

  deleteTournament(tournamentId: string): Observable<any> {
    const url = `${this.deleteTournamentURL}/${tournamentId}`;
    return this.http.delete(url);
  }
}
