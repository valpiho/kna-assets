package com.pibox.knaassets.asset;

import com.pibox.knaassets.asset.enums.AssetQualityConditionEnum;
import com.pibox.knaassets.asset.enums.AssetStatusEnum;
import com.pibox.knaassets.asset.enums.AssetTypeEnum;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "assets")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "serial_number", unique = true, nullable = false)
    private String serialNumber;

    @Column(name = "bar_code", unique = true, nullable = false)
    private Long barCode;

    @Column(name = "title")
    private String title;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "longDescription")
    private String longDescription;

    @Column(name = "notes")
    private String notes;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "manufacturer")
    private String manufacturer;   // TODO: update to entity

    @Column(name = "vendor")
    private String vendor;   // TODO: update to entity

    @Column(name = "category")
    private String category;   // TODO: update to entity

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AssetTypeEnum type;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_audited_at")
    private Date lastAuditedAt;

    @Column(name = "location")
    private String location;   // TODO: update to entity

    @Enumerated(EnumType.STRING)
    @Column(name = "current_status")
    private AssetStatusEnum currentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_condition")
    private AssetQualityConditionEnum currentCondition;

    @Column(name = "custodian")
    private String custodian;   // TODO: update to entity

    // TODO: private List<Record> records;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Asset that = (Asset) o;
        return Objects.equals(id, that.id);
    }
}
