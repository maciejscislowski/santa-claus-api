package com.programmerid.santaclausapi.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@DynamoDBDocument
public class PresentName {

    private final String value;
}
