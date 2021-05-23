package com.cvoid.service;

import com.cvoid.dto.CvoidInfoDTO;
import com.cvoid.dto.CvoidResposeDTO;
import com.cvoid.dto.SearchParamDto;
import com.cvoid.entity.CvoidInfo;
import com.cvoid.mapper.CvoidInfoMapper;
import com.cvoid.repository.CvoidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CovidService {

    private final CvoidRepository cvoidRepository;

    private final CvoidInfoMapper cvoidInfoMapper;

    @Value("${cvoid.confirmed.threshold.value}")
    private long confirmedThresholdValue;

    public void save(List<CvoidInfoDTO> cvoidInfoDTOList) {
        List<CvoidInfo> cvoidInfos = cvoidInfoDTOList.stream().map(cvoidInfoDTO -> cvoidInfoMapper.map(cvoidInfoDTO, CvoidInfo.class)).collect(Collectors.toList());
        cvoidRepository.saveAll(cvoidInfos);
    }

    public List<CvoidResposeDTO> search(SearchParamDto searchParamDto) {
        List<CvoidResposeDTO> cvoidResposeDTOS;
        List<CvoidAggrecate> cvoidAggrecates;
        if(searchParamDto.getCountry() != null) {
            cvoidAggrecates = cvoidRepository.findByCountryRegion(searchParamDto.getCountry());
        } else if(searchParamDto.getState() != null) {
            cvoidAggrecates = cvoidRepository.findByProvinceState(searchParamDto.getState());
        } else {
            cvoidAggrecates = cvoidRepository.findAggregativeValues();
        }
        cvoidResposeDTOS = cvoidAggrecates.stream().map(cvoidAggrecate -> CvoidResposeDTO.builder()
                .active(cvoidAggrecate.getActive())
                .confirmed(cvoidAggrecate.getConfirmed())
                .deaths(cvoidAggrecate.getDeaths())
                .caseFatalityRatio(cvoidAggrecate.getCaseFatalityRatio())
                .incidentRate(cvoidAggrecate.getIncidentRate())
                .countryName(cvoidAggrecate.getCountryName())
                .stateName(cvoidAggrecate.getStateName())
                .build()).collect(Collectors.toList());
        return cvoidResposeDTOS;
    }

    public List<CvoidInfoDTO> findContementZones() {
        List<CvoidInfo> cvoidInfos = cvoidRepository.findByActiveGreaterThanEqual(confirmedThresholdValue);
        return cvoidInfos.stream().map(cvoidInfo -> cvoidInfoMapper.map(cvoidInfo, CvoidInfoDTO.class)).collect(Collectors.toList());
    }
}
