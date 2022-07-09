package com.healthcare_service.controller;

import com.healthcare_service.service.visit.VisitService;
import com.healthcare_service.DTO.visit.BookedVisitInputDTO;
import com.healthcare_service.DTO.visit.BookVisitDTO;
import com.healthcare_service.DTO.visit.NotBookedVisitInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class VisitController {
    private final VisitService visitService;

    @PostMapping("/notBookedVisit")
    public ResponseEntity<UUID> addNotBookedVisit(@RequestBody NotBookedVisitInputDTO notBookedVisitInputDTO){
        UUID visitId = visitService.addNotBookedVisit(notBookedVisitInputDTO);
        return new ResponseEntity<>(visitId, HttpStatus.CREATED);
    }

    @PostMapping("/bookedVisit")
    public ResponseEntity<UUID> addBookedVisit(@RequestBody BookedVisitInputDTO bookedVisitInputDTO){
        UUID visitId = visitService.addBookedVisit(bookedVisitInputDTO);
        return new ResponseEntity<>(visitId, HttpStatus.CREATED);
    }

    @PutMapping("/visit/book")
    public ResponseEntity<Void> bookVisit(@RequestBody BookVisitDTO bookVisitDTO){
        visitService.bookVisit(bookVisitDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("visit/{id}/cancel")
    public ResponseEntity<Void> cancelVisit(@PathVariable UUID id){
        visitService.cancelVisit(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/visit/{id}")
    public ResponseEntity<Void> deleteVisitById(@PathVariable UUID id){
        visitService.deleteVisitById(id);
        return ResponseEntity.ok().build();
    }
}
