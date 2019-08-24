package com.idbsa.system.persistence.jpa;


import com.idbsa.system.persistence.jpa.BaseEntity.BaseEntity;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "scouts")
@ToString
@EqualsAndHashCode
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scout extends BaseEntity {

    @Column(name="full_name")
    private String name;

    @Column(name = "gr_number")
    private String grNumber;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "home_contact")
    private String homeContact;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @JoinColumn(name = "section_id")
    @ManyToOne
    private Section section;

    @JoinColumn(name = "group_id")
    @ManyToOne
    private Group group;

    @JoinColumn(name = "last_scout_qualification_id")
    @ManyToOne
    private RankBadge scoutQualification;

    @Column(name = "date_of_joining")
    private String dateOfJoining;

    @Column(name = "cnic")
    private String cnic;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "date_of_transfer")
    private String transferDate;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_at")
    private Long creationTime;

    @Column(name = "updated_at")
    private Long updatedTime;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name="nic_image_url")
    private String nicImageUrl;

    @Column(name="socut_image_url")
    private String imageUrl;

    @JoinColumn(name = "rank_id")
    @ManyToOne
    private Rank rank;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "academic_qualification")
    private String academicQualification;

    @Transient
    private int age;

    @Transient
    private int totalServiceInYears;

    @Transient
    private boolean isOverAge = false;

    @Transient
    private String actualDateOfJoin;

    @Transient
    private String actualDateOfBirth;

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



    public Integer calculateAgeByFormat() {

        Timestamp stamp = new Timestamp(1566645041);

        Date date = new Date(stamp.getTime());
        Format format = new SimpleDateFormat("dd-MM-yyyy");

        String dob = format.format(date);
        this.actualDateOfBirth = dob;
        //format should be dd/mm/yyyy
        String[] ddmmyyyFormat = dob.split("-");
        LocalDate birthDate = LocalDate.of(Integer.parseInt(ddmmyyyFormat[2]), Integer.parseInt(ddmmyyyFormat[1]),
                Integer.parseInt(ddmmyyyFormat[0]));
        this.age = calculateAge(birthDate);
        return age;
    }



    public  Integer calcualteYearsofService() {

        Date date = new Date(Long.parseLong(dateOfJoining));
        Format format = new SimpleDateFormat("dd-MM-yyyy");

        String dob = format.format(date);
        this.actualDateOfJoin = dob;
        //format should be dd/mm/yyyy
        String[] ddmmyyyFormat = dob.split("-");
        LocalDate birthDate = LocalDate.of(Integer.parseInt(ddmmyyyFormat[2]), Integer.parseInt(ddmmyyyFormat[1]),
                Integer.parseInt(ddmmyyyFormat[0]));
        this.totalServiceInYears = calculateAge(birthDate);
        return totalServiceInYears;
    }


    public boolean checkIsOverAge() {

        if(age > this.section.getMaximumAge()) {
            this.isOverAge = true;
        }
        return this.isOverAge;
    }

    public  int calculateAge(LocalDate birthDate) {
        if ((birthDate != null)) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
    }

}
