package com.pibox.knaassets.Asset;

import com.pibox.knaassets.enums.AssetQualityConditionEnum;
import com.pibox.knaassets.enums.AssetStatusEnum;
import com.pibox.knaassets.enums.AssetTypeEnum;
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
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private UUID uuid;

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

    @Column(name = "current_location")
    private String currentLocation;   // TODO: update to entity

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
        AssetEntity that = (AssetEntity) o;
        return Objects.equals(uuid, that.uuid);
    }
}
