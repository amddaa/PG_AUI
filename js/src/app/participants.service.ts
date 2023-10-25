import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {v4 as uuidv4} from "uuid";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ParticipantsService {
  private getTournamentParticipantsURL_1 = "http://localhost:8083/api/tournaments/"
  private getTournamentParticipantsURL_2 = "/participants"
  private getParticipantURL = "http://localhost:8083/api/participants"
  private deleteParticipantURL =  "http://localhost:8083/api/participants"
  private createParticipantURL = "http://localhost:8083/api/participants"
  private updateParticipantURL = "http://localhost:8083/api/participants"
  constructor(private http: HttpClient) { }

  getTournamentParticipants(tournamentID: string): Observable<any>{
    const url = `${this.getTournamentParticipantsURL_1}${tournamentID}${this.getTournamentParticipantsURL_2}`;
    return this.http.get(url);
  }

  getParticipantByID(participantID: string): Observable<any> {
    const url = `${this.getParticipantURL}/${participantID}`;
    return this.http.get(url);
  }

  deleteParticipant(participantID: string) {
    const url = `${this.deleteParticipantURL}/${participantID}`;
    return this.http.delete(url);
  }

  putParticipant(createdTournamentID: string, createdSurname: string, createdRank: number)
  {
    const url = `${this.createParticipantURL}/${uuidv4()}`;
    const requestData = {
      surname: createdSurname,
      rank: createdRank,
      tournament: createdTournamentID
    };

    return this.http.put(url, requestData, { headers: { 'Content-Type': 'application/json' } });
  }

  updateParticipant(participantData: any, participantID: any)
  {
    const url = `${this.updateParticipantURL}/${participantID}`;
    return this.http.patch(url, participantData,{ headers: { 'Content-Type': 'application/json' } })
  }
}
