package com.healthcare_service.controller;

import com.healthcare_service.DTO.opinion.OpinionInputDTO;
import com.healthcare_service.entity.Opinion;
import com.healthcare_service.service.opinion.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin
public class OpinionController {
    private final OpinionService opinionService;

    @GetMapping("/opinion")
    public ResponseEntity<List<Opinion>> findAllOpinions(){
        var opinions = opinionService.getAllOpinions();
        return new ResponseEntity<>(opinions, HttpStatus.OK);
    }

    @PostMapping("/opinion")
    public ResponseEntity<UUID> addOpinion(@RequestBody OpinionInputDTO opinionInputDTO){
        var opinionId = opinionService.createOpinion(opinionInputDTO);
        return new ResponseEntity<>(opinionId, HttpStatus.CREATED);
    }


    @DeleteMapping("/opinion/{opinionId}")
    public ResponseEntity<Void> deleteOpinionById(@PathVariable UUID opinionId){
        opinionService.deleteOpinionById(opinionId);
        return ResponseEntity.ok().build();
    }

}
