package com.healthcare_service.controller;

import com.healthcare_service.entity.Clinic;
import com.healthcare_service.entity.Doctor;
import com.healthcare_service.service.clinic.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class ClinicController {
    private final ClinicService clinicService;

    @GetMapping("/clinic")
    public ResponseEntity<List<Clinic>> findAllClinics(){
        var clinics = clinicService.findAllClinics();
        return new ResponseEntity<>(clinics, HttpStatus.OK);
    }

    @GetMapping("/clinic/{id}")
    public ResponseEntity<Clinic> findClinicById(@PathVariable UUID id){
        var clinic = clinicService.findClinicById(id);
        return new ResponseEntity<>(clinic,HttpStatus.OK);
    }

    @GetMapping("/clinic/{id}/doctors")
    public ResponseEntity<List<Doctor>> findDoctorsByClinicId(@PathVariable UUID id){
        var doctors = clinicService.findDoctorsByClinicId(id);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}
