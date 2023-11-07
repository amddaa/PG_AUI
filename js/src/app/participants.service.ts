import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {v4 as uuidv4} from "uuid";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ParticipantsService {
  constructor(private http: HttpClient) { }

  getTournamentParticipants(tournamentID: string): Observable<any>{
    return this.http.get('/api/tournaments/' + tournamentID + '/participants');
  }

  getParticipantByID(participantID: string): Observable<any> {
    return this.http.get('/api/participants/' + participantID);
  }

  deleteParticipant(participantID: string) {
    return this.http.delete('/api/participants/' + participantID);
  }

  putParticipant(createdTournamentID: string, createdSurname: string, createdRank: number)
  {
    const requestData = {
      surname: createdSurname,
      rank: createdRank,
      tournament: createdTournamentID
    };

    return this.http.put('/api/participants/' + uuidv4(), requestData, { headers: { 'Content-Type': 'application/json' } });
  }

  updateParticipant(participantData: any, participantID: any)
  {
    return this.http.patch('/api/participants/' + participantID, participantData,{ headers: { 'Content-Type': 'application/json' } })
  }
}
