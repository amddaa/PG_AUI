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
  private getTournamentByIDURL = "http://localhost:8083/api/tournaments";
  private deleteTournamentURL = "http://localhost:8083/api/tournaments";
  private createTournamentURL =  "http://localhost:8083/api/tournaments";
  private updateTournamentURL = "http://localhost:8083/api/tournaments";
  constructor(private http: HttpClient) { }

  getTournaments(): Observable<any> {
    return this.http.get(this.getTournamentsURL);
  }

  getTournamentByID(tournamentID: any): Observable<any> {
    const url = `${this.getTournamentByIDURL}/${tournamentID}`;
    return this.http.get(url);
  }

  deleteTournament(tournamentID: string): Observable<any> {
    const url = `${this.deleteTournamentURL}/${tournamentID}`;
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

  updateTournament(tournamentData: any, tournamentID: any)
  {
    const url = `${this.updateTournamentURL}/${tournamentID}`;
    return this.http.patch(url, tournamentData,{ headers: { 'Content-Type': 'application/json' } })
  }
}
