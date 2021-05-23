package com.cvoid.entity;

import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;


import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cvoid_info",
        indexes = {
                @Index(name = "idx_id", columnList = "id", unique = true),
        })
public class CvoidInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fips;
    private String admin2;
    private String provinceState;
    private String countryRegion;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    private Double lat;
    private Double lng;

    private Long confirmed;
    private Long deaths;
    private Long recovered;
    private Long active;
    private String  combinedKey;
    private Double incidentRate;
    private Double caseFatalityRatio;
}
