package com.programmerid.santaclausapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PresentOrder {

    private PresentOrderUuid orderUuid;
    private PresentName presentName;

}
