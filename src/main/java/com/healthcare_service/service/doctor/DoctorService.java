package com.healthcare_service.service.doctor;


import com.healthcare_service.DTO.doctor.*;
import com.healthcare_service.DTO.opinion.OpinionDTO;
import com.healthcare_service.DTO.visit.BookedVisitDTO;
import com.healthcare_service.DTO.visit.NotBookedVisitDTO;
import com.healthcare_service.entity.Opinion;
import com.healthcare_service.exceptions.NotFoundException;
import com.healthcare_service.entity.Doctor;
import com.healthcare_service.repository.DoctorRepository;
import com.healthcare_service.DTO.doctor.DeleteVisitTypeDTO;
import com.healthcare_service.entity.Visit;
import com.healthcare_service.repository.OpinionRepository;
import com.healthcare_service.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    public UUID createDoctor(DoctorInputDTO doctorInputDTO) {
        doctorValidator.validateInput(doctorInputDTO);
        var doctor = doctorMapper.mapInputToEntity(doctorInputDTO);
        doctorRepository.save(doctor);
        return doctor.getId();
    }

    public List<DoctorDTO> getDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctor -> modelMapper.map(doctor,DoctorDTO.class))
                .collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(UUID doctorId) {
        var doctor = doctorRepository.findById(doctorId).orElseThrow(()-> new NotFoundException("Nie znaleziono lekarza"));
        return modelMapper.map(doctor,DoctorDTO.class);
    }

    public DoctorDTO getDoctorByUsername(String username) {
        var doctor = doctorRepository.findByUsername(username);
        return modelMapper.map(doctor,DoctorDTO.class);
    }

    public DoctorDTO getDoctorByVisitId(UUID visitId) {
        var visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono wizyty"));
        var doctor = doctorRepository.findByVisitsContaining(visit);
        return modelMapper.map(doctor,DoctorDTO.class);
    }

    public void editPersonalData(EditDocPersonalDataDTO editDocPersonalDataDTO) {
       doctorEditor.editPersonalData(editDocPersonalDataDTO);
    }

    public void editAboutMe(EditDocAboutmeDTO editDocAboutmeDTO) {
        doctorEditor.editAboutMe(editDocAboutmeDTO);
    }

    public List<OpinionDTO> getOpinionsByDoctorId(UUID doctorId){
        var opinions = opinionRepository.findByDoctorId(doctorId);

        return opinions.stream()
                .map(opinion -> modelMapper.map(opinion,OpinionDTO.class))
                .collect(Collectors.toList());
    }

    public String addVisitType(VisitTypeInputDTO dto) {
      return doctorEditor.addVisistType(dto);
    }

    public void deleteVisitType(DeleteVisitTypeDTO dto) {
        doctorEditor.deleteVisitType(dto);
    }

    public List<BookedVisitDTO> getBookedVisitByDoctorId(UUID id){
        var doctor = doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));

        var visits = doctor.getVisits().stream()
                .filter((visit -> visit.getVisitDate().isAfter(LocalDateTime.now()) && visit.getClient() != null))
                .sorted()
                .collect(Collectors.toList());

        return visits.stream()
                .map(visit -> modelMapper.map(visit,BookedVisitDTO.class))
                .collect(Collectors.toList());
    }

    public List<NotBookedVisitDTO> getNotBookedVisitsByDoctorId(UUID doctorId){
        var doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));

        var visits = doctor.getVisits().stream()
                .filter(visit -> visit.getClient()==null && visit.getVisitDate().isAfter(LocalDateTime.now()))
                .sorted()
                .collect(Collectors.toList());

        return visits.stream()
                .map(visit -> modelMapper.map(visit, NotBookedVisitDTO.class))
                .collect(Collectors.toList());
    }

    public List<BookedVisitDTO> getBookedVisitHistoryByDoctorId(UUID doctorId){
        var doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));

        var visits = doctor.getVisits().stream()
                .filter(visit -> visit.getClient()!=null && visit.getVisitDate().isBefore(LocalDateTime.now()))
                .sorted()
                .collect(Collectors.toList());

        return visits.stream()
                .map(visit -> modelMapper.map(visit,BookedVisitDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteDoctor(UUID doctorId) {
        var doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException("Nie znaleziono lekarza"));
        doctorRepository.delete(doctor);
    }

}
