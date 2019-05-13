package com.idbsa.system.persistence.jpa;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.idbsa.system.persistence.jpa.BaseEntity.BaseEntity;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "group_leader")
@ToString
@EqualsAndHashCode
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupLeader extends BaseEntity {

    @Column(name="name")
    private String name;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @JoinColumn(name = "group_id")
    @ManyToOne
    @JsonBackReference
    private Group group;

    @JoinColumn(name = "scout_qualification_id")
    @ManyToOne
    private RankBadge scoutQualification;

    @Column(name = "date_of_joining")
    private String dateOfJoining;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "cnic")
    private String cnic;

    @JoinColumn(name = "rank_id")
    @ManyToOne
    private Rank leaderRank;

    @Column(name = "is_active")
    private boolean isActive;

    @JoinColumn(name = "leader_qualification_id")
    @ManyToOne
    private LeaderBadge leaderQualification;

    @Column(name = "leader_qualification_cert_no")
    private Integer leaderQualificationCertNumber;

    @Column(name = "gr_number")
    private Integer leaderGrNumber;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "age")
    private Integer age;

    public static boolean validateCnic(String cnic) {
        if (StringUtils.hasLength(cnic) &&
                (cnic.matches("^[0-9]{5}-[0-9]{7}-[0-9]{1}$") || //NIC
                        cnic.matches("^[0-9]{6}-[0-9]{6}-[0-9]{1}$")  //NICOP
//                        this.cnic.matches("^E[C,c,b,B]-[0-9]{11}$") ||
//                        this.cnic.matches("^E[C,c,b,B][0-9]{11}$") ||//Afghan Citizen
//                        this.cnic.matches("^[0-9]{5}-[0-9]{2}$") ||
//                        this.cnic.matches("^GL-[0-9]{12}$") ||
//                        this.cnic.matches("^GL[0-9]{12}$")
                )) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean validateName(String fullName) {
        if (!fullName.equals("")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean validateMobile(String mobileNumber) {
        if (mobileNumber.matches("^(\\d){4}-(\\d){7}$") || mobileNumber.equals("")
            || mobileNumber.matches("^(\\d){11}$") || mobileNumber.matches("^(\\d){10}$") ||
                mobileNumber.matches("^(\\d){3}(\\s){1}(\\d){7}$")) {
                    return true;
        } else {
            return false;
        }
    }


    public static Integer calculateAgeByFormat(String dateOfBirth) {

        //format should be dd/mm/yyyy
        String[] ddmmyyyFormat = dateOfBirth.split("/");
        LocalDate birthDate = LocalDate.of(Integer.parseInt(ddmmyyyFormat[2]), Integer.parseInt(ddmmyyyFormat[1]),
                Integer.parseInt(ddmmyyyFormat[0]));
        int age = calculateAge(birthDate);
        return age;
    }

    public static int calculateAge(LocalDate birthDate) {
        if ((birthDate != null)) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

}
