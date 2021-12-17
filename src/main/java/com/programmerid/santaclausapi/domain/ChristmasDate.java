package com.programmerid.santaclausapi.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.programmerid.santaclausapi.domain.converters.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamoDBDocument
public class ChristmasDate {

    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    private LocalDate value;
}
