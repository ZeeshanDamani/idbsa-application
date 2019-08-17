package com.idbsa.system.persistence.jpa;


import com.idbsa.system.persistence.jpa.BaseEntity.BaseEntity;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
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
    private Group group;

    @Column(name = "date_of_joining")
    private Long dateOfJoining;

    @Column(name = "date_of_birth")
    private Long dateOfBirth;

    @Column(name = "cnic")
    private String cnic;

    @JoinColumn(name = "rank_id")
    @ManyToOne
    private Rank rank;

    @Column(name = "is_active")
    private boolean isActive;

    @JoinColumn(name = "leader_qualification_id")
    @ManyToOne
    private LeaderBadge leaderQualification;

    @Column(name = "leader_qualification_cert_no")
    private String leaderQualificationCertNumber;

    @Column(name = "gr_number")
    private String grNumber;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "created_at")
    private Long creationTime;

    @Column(name = "updated_at")
    private Long updatedTime;

    @Column(name = "age")
    private Integer age;

    @Column(name="nic_image_url")
    private String nicImageUrl;

    @Column(name="leader_image_url")
    private String imageUrl;

    @Column(name="leader_qualification_image_url")
    private String leaderQualificationImageUrl;

    @Column(name="leader_academic_qualification")
    private String leaderAcademicQualification;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="blood_group")
    private String bloodGroup;

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


    public  Integer calculateAgeByFormat() {

        //format should be dd/mm/yyyy
        Date dob = new Date(this.dateOfBirth);
        LocalDate birthDate = dob.toLocalDate();
        this.age = calculateAge(birthDate);
        return age;
    }

    public  int calculateAge(LocalDate birthDate) {
        if ((birthDate != null)) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

}
