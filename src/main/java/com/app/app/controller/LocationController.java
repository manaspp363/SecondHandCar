package com.app.app.controller;

import com.app.app.service.GeoLocationService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/v1/location")

public class LocationController {
    private GeoLocationService geoLocationService;

    public LocationController(GeoLocationService geoLocationService) {
        this.geoLocationService = geoLocationService;
    }

    @GetMapping("/api")
    public ResponseEntity<String> getIpAddress() {
        String publicIpAddress = geoLocationService.getIpAddress();
        String ipInfo_API = "512b45f68e7a84";
        String latAndLon = geoLocationService.getIpInfo(publicIpAddress, ipInfo_API);
        String[] parts = latAndLon.split(",");
        double latitude = Double.parseDouble(parts[0].trim());
        double longitude = Double.parseDouble(parts[1].trim());
        String geoCode_Api = "67cb1ad29043c647897249gboe21099";
        String address = geoLocationService.reverseGeoCode(geoCode_Api, latitude, longitude);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
}
