package com.cvoid.repository;

import com.cvoid.entity.CvoidInfo;
import com.cvoid.service.CvoidAggrecate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CvoidRepository extends JpaRepository<CvoidInfo, Long>, JpaSpecificationExecutor {

    @Query( value = "select sum(active) as active, sum(confirmed) as confirmed, sum(deaths) as deaths, " +
            "sum(incidentRate) as incidentRate, sum(caseFatalityRatio) as caseFatalityRatio, countryRegion as countryName, provinceState as stateName " +
            "from CvoidInfo group by countryRegion, provinceState")
    public List<CvoidAggrecate> findAggregativeValues();

    @Query( value = "select sum(active) as active, sum(confirmed) as confirmed, sum(deaths) as deaths, " +
            "sum(incidentRate) as incidentRate, sum(caseFatalityRatio) as caseFatalityRatio, countryRegion as countryName " +
            "from CvoidInfo where countryRegion = ?1")
    public List<CvoidAggrecate> findByCountryRegion(String countryName);

    @Query( value = "select sum(active) as active, sum(confirmed) as confirmed, sum(deaths) as deaths, " +
            "sum(incidentRate) as incidentRate, sum(caseFatalityRatio) as caseFatalityRatio, provinceState as stateName " +
            "from CvoidInfo where provinceState = ?1")
    public List<CvoidAggrecate> findByProvinceState(String stateName);

    public List<CvoidInfo> findByActiveGreaterThanEqual(Long confirmedThresholdValue);

}
