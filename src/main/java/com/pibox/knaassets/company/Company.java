package com.pibox.knaassets.company;

import com.pibox.knaassets.company.enums.CompanyStatusEnum;
import com.pibox.knaassets.company.enums.VendorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "companies")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "vat_number", unique = true, nullable = false)
    private String vatNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "primary_contact_id", referencedColumnName = "id")
    private PrimaryContact primaryContact;

    @Column(name = "is_vendor")
    private boolean isVendor;

    @Enumerated(EnumType.STRING)
    @Column(name = "vendor_type")
    private VendorType vendorType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "removed_at")
    private Date removedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_status")
    private CompanyStatusEnum currentStatus;

    // TODO: List of Items
}
