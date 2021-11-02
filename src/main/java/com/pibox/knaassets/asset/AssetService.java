package com.pibox.knaassets.asset;

import org.springframework.stereotype.Service;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Asset getAssetBySerialNumber(String serialNumber) {
        return assetRepository.getAssetEntityBySerialNumber(serialNumber);
    }

    public Asset addAsset(Asset asset) {
        return assetRepository.save(asset);
    }
}
