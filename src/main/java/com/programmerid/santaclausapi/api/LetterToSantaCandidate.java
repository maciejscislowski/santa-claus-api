package com.programmerid.santaclausapi.api;

import com.programmerid.santaclausapi.domain.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
public class LetterToSantaCandidate {

    @NotBlank
    private final String childFirstName;
    @Size(min = 1)
    private final Set<String> presentNames;

    public Letter toLetter(Clock clock) {
        return Letter.builder()
                .letterUuid(new LetterUuid(UUID.randomUUID()))
                .christmasDate(new ChristmasDate(LocalDate.now(clock)))
                .childFirstName(new ChildFirstName(this.childFirstName))
                .presentNames(this.presentNames.stream().map(PresentName::new).collect(Collectors.toSet()))
                .build();
    }
}
