package pg.participant.controller.impl;

import pg.participant.controller.api.ParticipantController;
import pg.participant.dto.GetParticipantResponse;
import pg.participant.dto.GetParticipantsResponse;
import pg.participant.dto.PatchParticipantRequest;
import pg.participant.dto.PutParticipantRequest;
import pg.participant.entity.Participant;
import pg.participant.function.ParticipantToResponseFunction;
import pg.participant.function.ParticipantsToResponseFunction;
import pg.participant.function.RequestToParticipantFunction;
import pg.participant.function.RequestToParticipantPatchFunction;
import pg.participant.service.api.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pg.tournament.entity.Tournament;
import pg.tournament.service.api.TournamentService;

import java.util.Optional;
import java.util.UUID;

@RestController
public class ParticipantDefaultController implements ParticipantController {
    private final ParticipantService participantService;
    private final TournamentService tournamentService;
    private final ParticipantToResponseFunction participantToResponse;
    private final ParticipantsToResponseFunction participantsToResponse;
    private final RequestToParticipantFunction requestToParticipant;
    private final RequestToParticipantPatchFunction requestToParticipantPatch;

    @Autowired
    public ParticipantDefaultController(
            ParticipantService participantService,
            TournamentService tournamentService,
            ParticipantToResponseFunction participantToResponse,
            ParticipantsToResponseFunction participantsToResponse,
            RequestToParticipantFunction requestToParticipant,
            RequestToParticipantPatchFunction requestToParticipantPatch
    ){
        this.participantService = participantService;
        this.tournamentService = tournamentService;
        this.participantsToResponse = participantsToResponse;
        this.participantToResponse = participantToResponse;
        this.requestToParticipant = requestToParticipant;
        this.requestToParticipantPatch = requestToParticipantPatch;
    }

    @Override
    public GetParticipantsResponse getParticipants() {
        return participantsToResponse.apply(participantService.findAll());
    }

    @Override
    public GetParticipantResponse getParticipant(UUID id) {
        return participantService.find(id)
                .map(participantToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetParticipantsResponse getTournamentParticipants(UUID tournamentId) {
        return participantService.findAllByTournament(tournamentId)
                .map(participantsToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void patchParticipant(UUID id, PatchParticipantRequest request) {
        Optional<Participant> existing = participantService.find(id);
        if(existing.isPresent())
        {
            if(request.getRank()==0) request.setRank(existing.get().getRank());
            if(request.getSurname()==null) request.setSurname(existing.get().getSurname());
            if(request.getTournament()==null) request.setTournament(existing.get().getTournament() != null ? existing.get().getTournament().getId() : null);
            else {
                Optional<Tournament> tournament = tournamentService.find(request.getTournament());
                if (tournament.isPresent()) {
                    request.setTournament(tournament.get().getId());
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
            }
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        participantService.update(requestToParticipantPatch.apply(id, request));
    }

    @Override
    public void putParticipant(UUID id, PutParticipantRequest request) {
        participantService.create(requestToParticipant.apply(id, request));
    }

    @Override
    public void deleteParticipant(UUID id) {
        participantService.find(id)
                .ifPresentOrElse(
                        participant -> participantService.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
}
