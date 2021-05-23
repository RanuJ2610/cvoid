package com.cvoid.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class CvoidResposeDTO {

   private String countryName;
   private String stateName;
   private Long active;
   private Long confirmed;
   private Long deaths;
   private Double incidentRate;
   private Double caseFatalityRatio;
}
