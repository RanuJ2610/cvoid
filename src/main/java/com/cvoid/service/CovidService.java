package com.cvoid.service;

import com.cvoid.dto.CvoidInfoDTO;
import com.cvoid.dto.SearchParamDto;
import com.cvoid.entity.CvoidInfo;
import com.cvoid.mapper.CvoidInfoMapper;
import com.cvoid.repository.CvoidRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CovidService {

    private final CvoidRepository cvoidRepository;

    private final CvoidInfoMapper cvoidInfoMapper;

    public void save(List<CvoidInfoDTO> cvoidInfoDTOList) {
        List<CvoidInfo> cvoidInfos = cvoidInfoDTOList.stream().map(cvoidInfoDTO -> cvoidInfoMapper.map(cvoidInfoDTO, CvoidInfo.class)).collect(Collectors.toList());
        cvoidRepository.saveAll(cvoidInfos);
    }

    public List<CvoidInfoDTO> search(SearchParamDto searchParamDto) {
        List<CvoidInfo> cvoidInfos;
        if(searchParamDto.getCountry()!=null) {
            cvoidInfos = cvoidRepository.findByCountryRegionContains(searchParamDto.getCountry());
        } else if(searchParamDto.getCountry()!=null) {
            cvoidInfos = cvoidRepository.findByProvinceStateContains(searchParamDto.getState());
        } else {
            cvoidInfos = cvoidRepository.findAll();
        }
        return cvoidInfos.stream().map(cvoidInfo -> cvoidInfoMapper.map(cvoidInfo, CvoidInfoDTO.class)).collect(Collectors.toList());
    }
}
