package com.programmerid.santaclausapi.domain.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.nonNull;

public class LocalDateConverter implements DynamoDBTypeConverter<String, LocalDate> {

    @Override
    public String convert(LocalDate object) {
        return nonNull(object) ? object.format(DateTimeFormatter.ISO_DATE) : "";
    }

    @Override
    public LocalDate unconvert(String object) {
        return LocalDate.parse(object, DateTimeFormatter.ISO_DATE);
    }
}