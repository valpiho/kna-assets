package com.pibox.knaassets.company;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "company_primary_contact")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PrimaryContact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @OneToOne(mappedBy = "primaryContact")
    private Company company;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;
}
