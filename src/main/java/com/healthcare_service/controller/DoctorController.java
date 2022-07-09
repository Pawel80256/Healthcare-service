package com.healthcare_service.controller;

import com.healthcare_service.DTO.doctor.*;
import com.healthcare_service.entity.Doctor;
import com.healthcare_service.DTO.doctor.DeleteVisitTypeDTO;
import com.healthcare_service.entity.Opinion;
import com.healthcare_service.entity.Visit;
import com.healthcare_service.service.doctor.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class DoctorController {

    private DoctorService doctorService;

    @PostMapping("doctor")
    public ResponseEntity<UUID> addDoctor(@RequestBody DoctorInputDTO inputDTO){
        var doctorId = doctorService.createDoctor(inputDTO);
        return new ResponseEntity<>(doctorId,HttpStatus.OK);
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<Doctor>> getDoctors(){
        var doctors = doctorService.getDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable UUID doctorId){
        var doctor = doctorService.getDoctorById(doctorId);
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }

    @GetMapping("/doctorByUsername/{doctorUsername}")
    public ResponseEntity<Doctor> getDoctorByUsername(@PathVariable String doctorUsername){
        var doctor = doctorService.getDoctorByUsername(doctorUsername);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/doctor/{id}/opinions")
    public ResponseEntity<List<Opinion>> getOpinionsByDoctorId(@PathVariable UUID id){
        var opinions = doctorService.getOpinionsByDoctorId(id);
        return new ResponseEntity<>(opinions, HttpStatus.OK);
    }

    @GetMapping("/doctorByVisitId")
    public ResponseEntity<Doctor> getDoctorByVisitId(@RequestParam UUID visitId){
        var doctor = doctorService.getDoctorByVisitId(visitId);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("doctor/{doctorId}/notBookedVisits")
    public ResponseEntity<List<Visit>> getNotBookedVisitsByDoctorId(@PathVariable UUID doctorId){
        var notBookedVisits = doctorService.getNotBookedVisitsByDoctorId(doctorId);
        return new ResponseEntity<>(notBookedVisits, HttpStatus.OK);
    }

    @GetMapping("doctor/{id}/bookedVisits")
    public ResponseEntity<List<Visit>> getBookedVisitsByDoctorId(@PathVariable UUID id){
        var bookedVisits = doctorService.getBookedVisitByDoctorId(id);
        return new ResponseEntity<>(bookedVisits, HttpStatus.OK);
    }

    @GetMapping("doctor/{id}/bookedVisitsHistory")
    public ResponseEntity<List<Visit>> getBookedVisitsHistoryByDoctorId(@PathVariable UUID id){
        var bookedVisitsHistory = doctorService.getBookedVisitHistoryByDoctorId(id);
        return new ResponseEntity<>(bookedVisitsHistory,HttpStatus.OK);
    }

    @PutMapping("/doctor/personalData")
    public ResponseEntity<Void> editPersonalData(@RequestBody EditDocPersonalDataDTO editDocPersonalDataDTO){
        doctorService.editPersonalData(editDocPersonalDataDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/doctor/visitType")
    public ResponseEntity<String> addVisitType(@RequestBody VisitTypeInputDTO visitTypeInputDTO){
        var visitType = doctorService.addVisitType(visitTypeInputDTO);
        return new ResponseEntity<>(visitType,HttpStatus.OK);
    }

    @DeleteMapping("/doctor/visitType")
    public ResponseEntity<Void> deleteVisitType(@RequestBody DeleteVisitTypeDTO deleteVisitTypeDTO){
         doctorService.deleteVisitType(deleteVisitTypeDTO);
         return ResponseEntity.ok().build();
    }

    @DeleteMapping("/doctor/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable UUID id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/doctor/aboutMe")
    public ResponseEntity<Void> editAboutMe(@RequestBody EditDocAboutmeDTO editDocAboutmeDTO){
        doctorService.editAboutMe(editDocAboutmeDTO);
        return ResponseEntity.ok().build();
    }


}
