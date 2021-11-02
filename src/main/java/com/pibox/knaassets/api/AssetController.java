package com.pibox.knaassets.api;

import com.pibox.knaassets.asset.Asset;
import com.pibox.knaassets.asset.AssetService;
import com.pibox.knaassets.api.models.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/assets")
public class AssetController {

    private final AssetService assetService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/{serial_number}")
    public ResponseEntity<Asset> getAssetBySerialNumber(@PathVariable("serial_number") String serialNumber) {
        Asset asset = assetService.getAssetBySerialNumber(serialNumber);
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpResponse> addAsset(@RequestBody Asset asset) {
        Asset newAsset = assetService.addAsset(asset);
        LOGGER.info("Asset: '" + newAsset.getTitle() + "' has been added");
        return response(HttpStatus.CREATED, "Asset has been added");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, message),
                httpStatus);
    }
}
