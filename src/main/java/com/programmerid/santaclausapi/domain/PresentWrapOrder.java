package com.programmerid.santaclausapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PresentWrapOrder {

    private PresentWrapOrderUuid wrapOrderUuid;
    private PresentName presentName;

}
