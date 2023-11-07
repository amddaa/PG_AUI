import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
declare module 'uuid';
import { v4 as uuidv4 } from 'uuid';


@Injectable({
  providedIn: 'root'
})
export class TournamentsService {

  constructor(private http: HttpClient) { }

  getTournaments(): Observable<any> {
    return this.http.get('/api/tournaments');
  }

  getTournamentByID(tournamentID: any): Observable<any> {
    return this.http.get('/api/tournaments/' + tournamentID);
  }

  deleteTournament(tournamentID: string): Observable<any> {
    return this.http.delete('/api/tournaments/' + tournamentID);
  }

  createTournament(createdName: string, createdRequiredRank: number): Observable<any>
  {
    const requestData = {
      name: createdName,
      requiredRank: createdRequiredRank
    };

    return this.http.put('/api/tournaments/' + uuidv4(), requestData, { headers: { 'Content-Type': 'application/json' } });
  }

  updateTournament(tournamentData: any, tournamentID: any)
  {
    return this.http.patch('/api/tournaments/' + tournamentID, tournamentData,{ headers: { 'Content-Type': 'application/json' } })
  }
}
