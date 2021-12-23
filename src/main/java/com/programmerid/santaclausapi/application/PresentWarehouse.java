package com.programmerid.santaclausapi.application;

import com.programmerid.santaclausapi.domain.Letter;
import com.programmerid.santaclausapi.domain.LetterReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PresentWarehouse {

    private final PresentOrderingDepartment presentOrderingDepartment;
    private final PresentWrapOrderingDepartment wrapOrderingDepartment;
    private final LetterReadRepository readRepository;

    public Set<String> orderPresents(String letterUuid) {
        return readRepository.findByLetterUuid(UUID.fromString(letterUuid))
                .map(Letter::getPresentNames)
                .orElseGet(Collections::emptySet)
                .stream()
                .map(presentName -> {
                    wrapOrderingDepartment.orderPresentWrap(presentName.getValue());
                    return presentOrderingDepartment.orderPresent(presentName.getValue());
                })
                .collect(Collectors.toSet());
    }

}
