package com.pibox.knaassets.Asset;

import com.pibox.knaassets.models.HttpResponse;
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
    public ResponseEntity<AssetEntity> getAssetBySerialNumber(@PathVariable("serial_number") String serialNumber) {
        AssetEntity asset = assetService.getAssetBySerialNumber(serialNumber);
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpResponse> addAsset(@RequestBody AssetEntity assetEntity) {
        AssetEntity newAsset = assetService.addAsset(assetEntity);
        LOGGER.info("Asset: '" + newAsset.getTitle() + "' has been added");
        return response(HttpStatus.CREATED, "Asset has been added");
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(httpStatus.value(), httpStatus, message),
                httpStatus);
    }
}
