package com.cvoid.repository;

import com.cvoid.entity.CvoidInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CvoidRepository extends JpaRepository<CvoidInfo, Long>, JpaSpecificationExecutor {

    public List<CvoidInfo> findAll();
    public List<CvoidInfo> findByCountryRegionContains(String countryName);
    public List<CvoidInfo> findByProvinceStateContains(String stateName);

}
