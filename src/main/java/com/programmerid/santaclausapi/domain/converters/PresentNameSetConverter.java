package com.programmerid.santaclausapi.domain.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.programmerid.santaclausapi.domain.PresentName;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Objects.nonNull;

public class PresentNameSetConverter implements DynamoDBTypeConverter<Set<String>, Set<PresentName>> {

    @Override
    public Set<String> convert(Set<PresentName> object) {
        Set<String> result = newHashSet();
        if (nonNull(object)) {
            object.forEach(name -> result.add(name.getValue()));
        }
        return result;
    }

    @Override
    public Set<PresentName> unconvert(Set<String> object) {
        Set<PresentName> result = newHashSet();
        if (nonNull(object)) {
            object.forEach(rawName -> result.add(new PresentName(rawName)));
        }
        return result;
    }
}