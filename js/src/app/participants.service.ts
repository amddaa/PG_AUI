import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ParticipantsService {
  private getTournamentParticipantsURL_1 = "http://localhost:8083/api/tournaments/"
  private getTournamentParticipantsURL_2 = "/participants"
  constructor(private http: HttpClient) { }

  getTournamentParticipants(tournamentID: any) {
    const url = `${this.getTournamentParticipantsURL_1}${tournamentID}${this.getTournamentParticipantsURL_2}`;
    console.log(url)
    return this.http.get(url);
  }
}
