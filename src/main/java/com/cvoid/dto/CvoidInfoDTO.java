package com.cvoid.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonPropertyOrder({
        "FIPS",
        "Admin2",
        "Province_State",
        "Country_Region",
        "Last_Update",
        "Lat",
        "Long_",
        "Confirmed",
        "Deaths",
        "Recovered",
        "Active",
        "Combined_Key",
        "Incident_Rate",
        "Case_Fatality_Ratio"
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CvoidInfoDTO {

    @JsonProperty("FIPS")
    private String fips;
    @JsonProperty("Admin2")
    private String admin2;
    @JsonProperty("Province_State")
    private String provinceState;
    @JsonProperty("Country_Region")
    private String countryRegion;
    @JsonProperty("Last_Update")
    private String lastUpdate;
    @JsonProperty("Lat")
    private String lat;
    @JsonProperty("Long_")
    private String lng;
    @JsonProperty("Confirmed")
    private String confirmed;
    @JsonProperty("Deaths")
    private String deaths;
    @JsonProperty("Recovered")
    private String recovered;
    @JsonProperty("Active")
    private String Active;
    @JsonProperty("Combined_Key")
    private String  combinedKey;
    @JsonProperty("Incident_Rate")
    private String incidentRate;
    @JsonProperty("Case_Fatality_Ratio")
    private String caseFatalityRatio;
}
