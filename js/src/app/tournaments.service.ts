import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
declare module 'uuid';
import { v4 as uuidv4 } from 'uuid';


@Injectable({
  providedIn: 'root'
})
export class TournamentsService {
  private getTournamentsURL = "http://localhost:8083/api/tournaments";
  private deleteTournamentURL = "http://localhost:8083/api/tournaments";
  private createTournamentURL =  "http://localhost:8083/api/tournaments"
  constructor(private http: HttpClient) { }

  getTournaments(): Observable<any> {
    return this.http.get(this.getTournamentsURL);
  }

  deleteTournament(tournamentId: string): Observable<any> {
    const url = `${this.deleteTournamentURL}/${tournamentId}`;
    return this.http.delete(url);
  }

  createTournament(createdName: string, createdRequiredRank: number): Observable<any>
  {
    const url = `${this.createTournamentURL}/${uuidv4()}`;
    const requestData = {
      name: createdName,
      requiredRank: createdRequiredRank
    };

    return this.http.put(url, requestData, { headers: { 'Content-Type': 'application/json' } });
  }
}
