package com.healthcare_service.service.opinion;

import com.healthcare_service.DTO.opinion.OpinionInputDTO;
import com.healthcare_service.entity.Opinion;
import com.healthcare_service.repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OpinionService {
    private final OpinionRepository opinionRepository;
    private final OpinionValidator opinionValidator;
    private final OpinionMapper opinionMapper;

    public UUID createOpinion(OpinionInputDTO opinionInputDTO){
        opinionValidator.validateOpinionInput(opinionInputDTO);
        var opinion = opinionMapper.mapInputToEntity(opinionInputDTO);
        opinionRepository.save(opinion);
        return opinion.getId();
    }
    public void deleteOpinionById(UUID opinionId){
        opinionRepository.deleteById(opinionId);
    }
    public List<Opinion> getAllOpinions(){
        return opinionRepository.findAll();
    }

}
