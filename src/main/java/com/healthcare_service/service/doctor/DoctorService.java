package com.healthcare_service.service.doctor;


import com.healthcare_service.DTO.doctor.*;
import com.healthcare_service.entity.Opinion;
import com.healthcare_service.exceptions.NotFoundException;
import com.healthcare_service.entity.Doctor;
import com.healthcare_service.repository.DoctorRepository;
import com.healthcare_service.DTO.doctor.DeleteVisitTypeDTO;
import com.healthcare_service.entity.Visit;
import com.healthcare_service.repository.OpinionRepository;
import com.healthcare_service.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final OpinionRepository opinionRepository;
    private final VisitRepository visitRepository;
    private final DoctorValidator doctorValidator;
    private final DoctorMapper doctorMapper;
    private final DoctorEditor doctorEditor;

    public UUID createDoctor(DoctorInputDTO doctorInputDTO) {
        doctorValidator.validateInput(doctorInputDTO);
        var doctor = doctorMapper.mapInputToEntity(doctorInputDTO);
        doctorRepository.save(doctor);
        return doctor.getId();
    }

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(UUID doctorId) {
        return doctorRepository.findById(doctorId).orElseThrow(()-> new NotFoundException("Nie znaleziono lekarza"));
    }

    public Doctor getDoctorByUsername(String username) {
        return doctorRepository.findByUsername(username);
    }

    public Doctor getDoctorByVisitId(UUID visitId) {
        var visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono wizyty"));
        return doctorRepository.findByVisitsContaining(visit);
    }

    public void editPersonalData(EditDocPersonalDataDTO editDocPersonalDataDTO) {
       doctorEditor.editPersonalData(editDocPersonalDataDTO);
    }

    public void editAboutMe(EditDocAboutmeDTO editDocAboutmeDTO) {
        doctorEditor.editAboutMe(editDocAboutmeDTO);
    }

    public List<Opinion> getOpinionsByDoctorId(UUID doctorId){
        return opinionRepository.findByDoctorId(doctorId);
    }

    public String addVisitType(VisitTypeInputDTO dto) {
      return doctorEditor.addVisistType(dto);
    }

    public void deleteVisitType(DeleteVisitTypeDTO dto) {
        doctorEditor.deleteVisitType(dto);
    }

    public List<Visit> getBookedVisitByDoctorId(UUID id){
        var doctor = doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));

        return doctor.getVisits().stream()
                .filter((visit -> visit.getVisitDate().isAfter(LocalDateTime.now()) && visit.getClient() != null))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Visit> getNotBookedVisitsByDoctorId(UUID doctorId){
        var doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));

        return doctor.getVisits().stream()
                .filter(visit -> visit.getClient()==null && visit.getVisitDate().isAfter(LocalDateTime.now()))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Visit> getBookedVisitHistoryByDoctorId(UUID doctorId){
        var doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));

        return doctor.getVisits().stream()
                .filter(visit -> visit.getClient()!=null && visit.getVisitDate().isBefore(LocalDateTime.now()))
                .sorted()
                .collect(Collectors.toList());
    }

    public void deleteDoctor(UUID doctorId) {
        var doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));
        doctorRepository.delete(doctor);
    }

}
