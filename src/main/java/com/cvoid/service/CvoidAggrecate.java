package com.cvoid.service;

/**
 * Created By techjini on May, 2021
 */
public interface CvoidAggrecate {

    String getCountryName();
    String getStateName();
    Long getActive();
    Long getConfirmed();
    Long getDeaths();
    Double getIncidentRate();
    Double getCaseFatalityRatio();

}
