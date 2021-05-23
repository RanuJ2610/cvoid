package com.cvoid.service;

import com.cvoid.dto.CvoidInfoDTO;
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
}
