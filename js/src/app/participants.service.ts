import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ParticipantsService {
  private getTournamentParticipantsURL_1 = "http://localhost:8083/api/tournaments/"
  private getTournamentParticipantsURL_2 = "/participants"
  private deleteParticipantURL =  "http://localhost:8083/api/participants"
  constructor(private http: HttpClient) { }

  getTournamentParticipants(tournamentID: string) {
    const url = `${this.getTournamentParticipantsURL_1}${tournamentID}${this.getTournamentParticipantsURL_2}`;
    return this.http.get(url);
  }

  deleteParticipant(participantID: string) {
    const url = `${this.deleteParticipantURL}/${participantID}`;
    return this.http.delete(url);
  }
}