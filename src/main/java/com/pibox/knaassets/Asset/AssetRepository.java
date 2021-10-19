package com.pibox.knaassets.Asset;

import com.pibox.knaassets.enums.AssetQualityConditionEnum;
import com.pibox.knaassets.enums.AssetStatusEnum;
import com.pibox.knaassets.enums.AssetTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface AssetRepository extends JpaRepository<AssetEntity, UUID> {

    AssetEntity getAssetEntityBySerialNumber(String serialNumber);

    AssetEntity getAssetEntityByBarCode(Long barCode);

    List<AssetEntity> findAllByCreatedAt(Date createdAt);

    List<AssetEntity> findAllByManufacturer(String manufacturer);

    List<AssetEntity> findAllByVendor(String vendor);

    List<AssetEntity> findAllByCategory(String category);

    List<AssetEntity> findAllByType(AssetTypeEnum type);

    List<AssetEntity> findAllByCurrentStatus(AssetStatusEnum currentStatus);

    List<AssetEntity> findAllByCurrentCondition(AssetQualityConditionEnum currentCondition);

    List<AssetEntity> findAllByCustodian(String custodian);
}
