package com.programmerid.santaclausapi.application;

import com.programmerid.santaclausapi.api.AcceptedConfirmation;
import com.programmerid.santaclausapi.api.LetterToSantaCandidate;
import com.programmerid.santaclausapi.domain.Letter;
import com.programmerid.santaclausapi.domain.LetterReadRepository;
import com.programmerid.santaclausapi.domain.LetterWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LetterStore {

    private final Clock clock;
    private final LetterWriteRepository writeRepository;
    private final LetterReadRepository readRepository;

    public AcceptedConfirmation createLetter(LetterToSantaCandidate letterToSanta) {
        final String letterUuid = writeRepository.save(letterToSanta.toLetter(clock))
                .getLetterUuid().getValue().toString();
        return new AcceptedConfirmation(letterUuid);
    }

    public Letter findLetter(String letterUuid) {
        return readRepository.findByLetterUuid(UUID.fromString(letterUuid))
                .orElseThrow(() -> new RuntimeException("Letter not found"));
    }

}
