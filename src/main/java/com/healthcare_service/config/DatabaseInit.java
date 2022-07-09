package com.healthcare_service.config;

import com.healthcare_service.entity.Address;
import com.healthcare_service.entity.Admin;
import com.healthcare_service.repository.AdminRepository;
import com.healthcare_service.entity.Client;
import com.healthcare_service.repository.ClientRepository;
import com.healthcare_service.entity.Clinic;
import com.healthcare_service.repository.ClinicRepository;
import com.healthcare_service.entity.Doctor;
import com.healthcare_service.repository.DoctorRepository;
import com.healthcare_service.entity.Hospital;
import com.healthcare_service.repository.HospitalRepository;
import com.healthcare_service.entity.role.ERole;
import com.healthcare_service.entity.role.Role;
import com.healthcare_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Component
public class DatabaseInit {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    ClinicRepository clinicRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ClientRepository clientRepository;

    @PostConstruct
    public void init() {
        //role
        if(roleRepository.count() == 0){

            roleRepository.save(new Role(ERole.ADMIN));
            roleRepository.save(new Role(ERole.DOCTOR));
            roleRepository.save(new Role(ERole.CLIENT));
        }


        //administrator
        if(adminRepository.count()==0){
            var rolesList = new ArrayList<Role>(){{add(new Role(ERole.ADMIN));}};

            var admin = Admin.builder()
                    .id(UUID.randomUUID())
                    .username("admin")
                    .password(new BCryptPasswordEncoder().encode("admin"))
                    .role(new Role(ERole.ADMIN)).build();


            adminRepository.save(admin);

        }

        //szpitale
        var hospital1 = Hospital.builder()
                .id(UUID.randomUUID())
                .name("Szpital im. Jana Pawła II")
                .address(Address.builder().city("Białystok").roadName("Kopernika").buildingNumber("43c").build())
                .build();
        var hospital2 = Hospital.builder()
                .id(UUID.randomUUID())
                .name("Szpital im. Zbigniewa Religi")
                .address(Address.builder().city("Warszawa").roadName("Długa").buildingNumber("21").build())
                .build();
        var hospital3 = Hospital.builder()
                .id(UUID.randomUUID())
                .name("Szpital główny w Krakowie")
                .address(Address.builder().city("Kraków").roadName("Ostra").buildingNumber("420").build())
                .build();


        //kliniki
        var clinic11 = Clinic.builder()
                .id(UUID.randomUUID())
                .clinicType("Okulistyczna")
                .hospital(hospital1)
                .build();
        var clinic12 = Clinic.builder()
                .id(UUID.randomUUID())
                .clinicType("Dermatologiczna")
                .hospital(hospital1)
                .build();
        var clinic21 = Clinic.builder()
                .id(UUID.randomUUID())
                .clinicType("Ginekologiczna")
                .hospital(hospital2)
                .build();
        var clinic22 = Clinic.builder()
                .id(UUID.randomUUID())
                .clinicType("Gastroenterologiczna")
                .hospital(hospital2)
                .build();
        var clinic31 = Clinic.builder()
                .id(UUID.randomUUID())
                .clinicType("Neurologiczna")
                .hospital(hospital3)
                .build();
        var clinic32 = Clinic.builder()
                .id(UUID.randomUUID())
                .clinicType("Alergologiczna")
                .hospital(hospital3)
                .build();

        if(hospitalRepository.count() == 0){
            //szpitale
            hospitalRepository.save(hospital1);
            hospitalRepository.save(hospital2);
            hospitalRepository.save(hospital3);

            //kliniki
            clinicRepository.save(clinic11);
            clinicRepository.save(clinic12);
            clinicRepository.save(clinic21);
            clinicRepository.save(clinic22);
            clinicRepository.save(clinic31);
            clinicRepository.save(clinic32);
        }

        //doktorzy

        if(doctorRepository.count()==0){
            var rolesList = new ArrayList<Role>(){{add(new Role(ERole.DOCTOR));}};

            var doctor1VisitTypes = new HashMap<String,Integer>();
            doctor1VisitTypes.put("Konsultacja",150);
            doctor1VisitTypes.put("Elektroencefalografia",600);
            doctor1VisitTypes.put("Migrena - leczenie toksyną botulinową",2150);
            doctor1VisitTypes.put("Rezonans magnetyczny",1500);
            doctor1VisitTypes.put("Tomografia komputerowa",450);



            var doctor1 = Doctor.builder()
                    .id(UUID.randomUUID())
                    .username("lekarz")
                    .emailAddress("lekarz@op.pl")
                    .password(new BCryptPasswordEncoder().encode("lekarz"))
                    .firstName("Paweł")
                    .lastName("Nowacki")
                    .pesel("96052698386")
                    .degree("lek. med.")
                    .clinic(clinicRepository.findByClinicType(clinic31.getClinicType()))
                    .roomNumber(420)
                    .aboutMe("xyz")
                    .myExpirience("xyz")
                    .myEducation("xyz")
                    .visitTypes(doctor1VisitTypes)
                    .specialisation("NEUROLOG")
                    .role(new Role(ERole.DOCTOR))
                    .build();

            var doctor2VisitTypes = new HashMap<String,Integer>();
            doctor2VisitTypes.put("Konsultacja",150);
            doctor2VisitTypes.put("Badanie dna oka",150);
            doctor2VisitTypes.put("Badanie optometryczne",100);
            doctor2VisitTypes.put("USG gałek ocznych",120);
            doctor2VisitTypes.put("Badanie pola widzenia",150);

            var doctor2 = Doctor.builder()
                    .id(UUID.randomUUID())
                    .username("lekarz2")
                    .emailAddress("lekarz2@op.pl")
                    .password(new BCryptPasswordEncoder().encode("lekarz2"))
                    .firstName("Jan")
                    .lastName("Kowalski")
                    .pesel("63052484141")
                    .degree("dr n. med.")
                    .clinic(clinicRepository.findByClinicType(clinic11.getClinicType()))
                    .roomNumber(21)
                    .aboutMe("Super lekarz")
                    .myExpirience("Jakies doświadczenie")
                    .myEducation("Jakaś szkoła")
                    .visitTypes(doctor2VisitTypes)
                    .specialisation("OKULISTA")
                    .role(new Role(ERole.DOCTOR))
                    .build();

            var doctor3VisitTypes = new HashMap<String,Integer>();
            doctor3VisitTypes.put("Konsultacja",150);
            doctor3VisitTypes.put("Usuwanie zmian skórnych",250);
            doctor3VisitTypes.put("Laserowe usuwanie rozstępów",550);
            doctor3VisitTypes.put("Laserowe usuwanie blizn",300);

            var doctor3 = Doctor.builder()
                    .id(UUID.randomUUID())
                    .username("lekarz3")
                    .emailAddress("lekarz3@op.pl")
                    .password(new BCryptPasswordEncoder().encode("lekarz3"))
                    .firstName("Piotr")
                    .lastName("Kowalski")
                    .pesel("54110989997")
                    .degree("dr hab n. med.")
                    .clinic(clinicRepository.findByClinicType(clinic12.getClinicType()))
                    .roomNumber(21)
                    .aboutMe("xyz")
                    .myExpirience("xyz")
                    .myEducation("xyz")
                    .visitTypes(doctor3VisitTypes)
                    .specialisation("DERMATOLOG")
                    .role(new Role(ERole.DOCTOR))
                    .build();

            var doctor4VisitTypes = new HashMap<String,Integer>();
            doctor4VisitTypes.put("Konsultacja",175);
            doctor4VisitTypes.put("USG ginekologiczne",200);
            doctor4VisitTypes.put("Cytologia",170);
            doctor4VisitTypes.put("Konsultacja położnicza + USG",250);

            var doctor4 = Doctor.builder()
                    .id(UUID.randomUUID())
                    .username("lekarz4")
                    .emailAddress("lekarz4@op.pl")
                    .password(new BCryptPasswordEncoder().encode("lekarz4"))
                    .firstName("Anna")
                    .lastName("Kowalska")
                    .pesel("72031263264")
                    .degree("dr n. med.")
                    .clinic(clinicRepository.findByClinicType(clinic21.getClinicType()))
                    .roomNumber(55)
                    .aboutMe("xyz")
                    .myExpirience("xyz")
                    .myEducation("xyz")
                    .visitTypes(doctor4VisitTypes)
                    .specialisation("GINEKOLOG")
                    .role(new Role(ERole.DOCTOR))
                    .build();

            var doctor5VisitTypes = new HashMap<String,Integer>();
            doctor5VisitTypes.put("Konsultacja",200);
            doctor5VisitTypes.put("USG",250);
            doctor5VisitTypes.put("Gastroskopia",300);

            var doctor5 = Doctor.builder()
                    .id(UUID.randomUUID())
                    .username("lekarz5")
                    .emailAddress("lekarz5@op.pl")
                    .password(new BCryptPasswordEncoder().encode("lekarz5"))
                    .firstName("Justyna")
                    .lastName("Wiśniewska")
                    .pesel("64071635967")
                    .degree("dr hab n. med.")
                    .clinic(clinicRepository.findByClinicType(clinic22.getClinicType()))
                    .roomNumber(2)
                    .aboutMe("xyz")
                    .myExpirience("xyz")
                    .myEducation("xyz")
                    .visitTypes(doctor5VisitTypes)
                    .specialisation("GASTROLOG")
                    .role(new Role(ERole.DOCTOR))
                    .build();

            var doctor6VisitTypes = new HashMap<String,Integer>();
            doctor6VisitTypes.put("Konsultacja",120);
            doctor6VisitTypes.put("Testy skórne",250);
            var doctor6 = Doctor.builder()
                    .id(UUID.randomUUID())
                    .username("lekarz6")
                    .emailAddress("lekarz6@op.pl")
                    .password(new BCryptPasswordEncoder().encode("lekarz6"))
                    .firstName("Kamil")
                    .lastName("Lewandowski")
                    .pesel("72121645239")
                    .degree("lek. med.")
                    .clinic(clinicRepository.findByClinicType(clinic32.getClinicType()))
                    .roomNumber(12)
                    .aboutMe("xyz")
                    .myExpirience("xyz")
                    .myEducation("xyz")
                    .visitTypes(doctor6VisitTypes)
                    .specialisation("ALERGOLOG")
                    .role(new Role(ERole.DOCTOR))
                    .build();

            doctorRepository.save(doctor1);
            doctorRepository.save(doctor2);
            doctorRepository.save(doctor3);
            doctorRepository.save(doctor4);
            doctorRepository.save(doctor5);
            doctorRepository.save(doctor6);
        }

        if(clientRepository.count()==0){
            var rolesList = new ArrayList<Role>(){{add(new Role(ERole.CLIENT));}};

            var client1 = Client.builder()
                    .id(UUID.randomUUID())
                    .username("pacjent")
                    .emailAddress("pacjent@op.pl")
                    .password(new BCryptPasswordEncoder().encode("pacjent"))
                    .firstName("Mariusz")
                    .lastName("Pudzianowski")
                    .pesel("52120947617")
                    .address(Address.builder()
                            .city("Warszawa")
                            .roadName("JakaśUlica")
                            .buildingNumber("15")
                            .localNumber("42")
                            .build())
                    .role(new Role(ERole.CLIENT))
                    .build();

            var client2 = Client.builder()
                    .id(UUID.randomUUID())
                    .username("pacjent2")
                    .emailAddress("pacjent2@op.pl")
                    .password(new BCryptPasswordEncoder().encode("pacjent2"))
                    .firstName("Adam")
                    .lastName("Małysz")
                    .pesel("73040555331")
                    .address(Address.builder()
                            .city("Kraków")
                            .roadName("Sadowa")
                            .buildingNumber("10")
                            .localNumber("2")
                            .build())
                    .role(new Role(ERole.CLIENT))
                    .build();

            clientRepository.save(client1);
            clientRepository.save(client2);
        }
    }
}
