package com.pibox.knaassets.asset;

import com.pibox.knaassets.asset.enums.AssetQualityConditionEnum;
import com.pibox.knaassets.asset.enums.AssetStatusEnum;
import com.pibox.knaassets.asset.enums.AssetTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    Asset getAssetEntityBySerialNumber(String serialNumber);

    Asset getAssetEntityByBarCode(Long barCode);

    List<Asset> findAllByCreatedAt(Date createdAt);

    List<Asset> findAllByManufacturer(String manufacturer);

    List<Asset> findAllByVendor(String vendor);

    List<Asset> findAllByCategory(String category);

    List<Asset> findAllByType(AssetTypeEnum type);

    List<Asset> findAllByCurrentStatus(AssetStatusEnum currentStatus);

    List<Asset> findAllByCurrentCondition(AssetQualityConditionEnum currentCondition);

    List<Asset> findAllByCustodian(String custodian);
}
