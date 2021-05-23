package com.cvoid.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SearchParamDto {

    private String country;
    private String state;
}
