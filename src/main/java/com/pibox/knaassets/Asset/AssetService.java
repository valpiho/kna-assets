package com.pibox.knaassets.Asset;

import org.springframework.stereotype.Service;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public AssetEntity getAssetBySerialNumber(String serialNumber) {
        return assetRepository.getAssetEntityBySerialNumber(serialNumber);
    }

    public AssetEntity addAsset(AssetEntity assetEntity) {
        return assetRepository.save(assetEntity);
    }
}
