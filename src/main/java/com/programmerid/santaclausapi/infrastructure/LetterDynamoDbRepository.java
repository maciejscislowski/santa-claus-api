package com.programmerid.santaclausapi.infrastructure;

import com.programmerid.santaclausapi.domain.Letter;
import com.programmerid.santaclausapi.domain.LetterReadRepository;
import com.programmerid.santaclausapi.domain.LetterUuid;
import com.programmerid.santaclausapi.domain.LetterWriteRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

interface LetterDynamoDbRepository extends PagingAndSortingRepository<Letter, LetterUuid>,
        LetterWriteRepository, LetterReadRepository {

    Optional<Letter> findByLetterUuid(UUID letterUuid);
}