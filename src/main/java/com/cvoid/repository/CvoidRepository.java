package com.cvoid.repository;

import com.cvoid.entity.CvoidInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CvoidRepository extends JpaRepository<CvoidInfo, Long>, JpaSpecificationExecutor {
}
