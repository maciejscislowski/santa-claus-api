package com.programmerid.santaclausapi.api;

import com.programmerid.santaclausapi.domain.Letter;
import com.programmerid.santaclausapi.domain.PresentName;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Builder(access = AccessLevel.PRIVATE)
@Data
public class LetterToSantaView {

    private final String letterUuid;
    private final String childFirstName;
    private final String christmasDate;
    private final Set<String> presentNames;

    public static LetterToSantaView fromLetter(Letter letter) {
        return LetterToSantaView.builder()
                .letterUuid(letter.getLetterUuid().getValue().toString())
                .childFirstName(letter.getChildFirstName().getValue())
                .christmasDate(DateTimeFormatter.ISO_DATE.format(letter.getChristmasDate().getValue()))
                .presentNames(letter.getPresentNames().stream().map(PresentName::getValue).collect(Collectors.toSet()))
                .build();
    }
}
