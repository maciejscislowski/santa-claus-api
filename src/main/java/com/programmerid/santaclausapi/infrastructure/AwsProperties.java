package com.programmerid.santaclausapi.infrastructure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.aws")
class AwsProperties {

    private String accessKey;
    private String secretKey;
    private String region;
}
