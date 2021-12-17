package com.programmerid.santaclausapi.domain;

import java.util.Optional;
import java.util.UUID;

public interface LetterReadRepository {

    Optional<Letter> findByLetterUuid(UUID letterUuid);
}
