package com.pibox.knaassets.company;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "company_addresses")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Company company;

    @Column(name = "street_address_first_line")
    private String streetAddressFirstLine;

    @Column(name = "street_address_second_line")
    private String streetAddressSecondLine;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;
}
