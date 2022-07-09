package com.healthcare_service.service.opinion;

import com.healthcare_service.DTO.opinion.OpinionDTO;
import com.healthcare_service.DTO.opinion.OpinionInputDTO;
import com.healthcare_service.entity.Opinion;
import com.healthcare_service.repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OpinionService {
    private final OpinionRepository opinionRepository;
    private final OpinionValidator opinionValidator;
    private final OpinionMapper opinionMapper;
    private final ModelMapper modelMapper;

    public UUID createOpinion(OpinionInputDTO opinionInputDTO){
        opinionValidator.validateOpinionInput(opinionInputDTO);
        var opinion = opinionMapper.mapInputToEntity(opinionInputDTO);
        opinionRepository.save(opinion);
        return opinion.getId();
    }
    public void deleteOpinionById(UUID opinionId){
        opinionRepository.deleteById(opinionId);
    }

    public List<OpinionDTO> getAllOpinions(){
        var opinions = opinionRepository.findAll();
        return opinions.stream()
                .map(opinion -> modelMapper.map(opinion,OpinionDTO.class))
                .collect(Collectors.toList());
    }

}
