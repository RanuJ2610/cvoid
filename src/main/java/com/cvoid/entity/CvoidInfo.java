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
    @Column(name = "Last_Update", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    private double lat;
    private double lng;

    private long confirmed;
    private long deaths;
    private long recovered;
    private long Active;
    private String  combinedKey;
    private double incidentRate;
    private double caseFatalityRatio;
}
