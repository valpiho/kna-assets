package com.pibox.knaassets.Asset;

import com.pibox.knaassets.enums.AssetStatusEnum;
import com.pibox.knaassets.enums.AssetTypeEnum;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
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

    private String assetId;

    private String title;

    private String shortDescription;

    private String longDescription;

    private String manufacturer;

    private String vendor;

    private String category;

    @Enumerated(EnumType.STRING)
    private AssetTypeEnum type;

    private String location;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Enumerated(EnumType.STRING)
    private AssetStatusEnum status;

    private BigDecimal unitCost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AssetEntity that = (AssetEntity) o;
        return Objects.equals(uuid, that.uuid);
    }
}
