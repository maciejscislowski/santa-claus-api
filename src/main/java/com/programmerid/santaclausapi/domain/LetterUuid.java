package com.programmerid.santaclausapi.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamoDBDocument
public class LetterUuid {

    @DynamoDBHashKey(attributeName = "id")
    private UUID value;

}
