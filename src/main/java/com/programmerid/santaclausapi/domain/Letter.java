package com.programmerid.santaclausapi.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.programmerid.santaclausapi.domain.converters.PresentNameSetConverter;
import lombok.*;

import java.util.Set;

@ToString(of = "letterUuid")
@EqualsAndHashCode(of = "letterUuid")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@DynamoDBTable(tableName = "Letter")
public class Letter {

    @DynamoDBFlattened(attributes = @DynamoDBAttribute(mappedBy = "value", attributeName = "id"))
    @DynamoDBHashKey(attributeName = "id")
    private LetterUuid letterUuid;

    @DynamoDBFlattened(attributes = @DynamoDBAttribute(mappedBy = "value", attributeName = "christmasDate"))
    private ChristmasDate christmasDate;

    @DynamoDBFlattened(attributes = @DynamoDBAttribute(mappedBy = "value", attributeName = "childFirstName"))
    private ChildFirstName childFirstName;

    @DynamoDBTypeConverted(converter = PresentNameSetConverter.class)
    private Set<PresentName> presentNames;
}
