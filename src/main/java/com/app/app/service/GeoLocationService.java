package com.app.app.service;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.data.util.TypeUtils.type;

@Service
public class GeoLocationService {
    private static final String API_KEY = "40cd515224d87b750ec644d45106c804";
    private static final String BASE_URL = "http://ipstack.com/";

    public String getGeoLocation(String ipAddress) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + ipAddress + "?access_key=" + API_KEY;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    public String getIpAddress() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://api64.ipify.org?format=json", String.class);
        String ipJson = response.getBody();
        JSONObject jsonObject = new JSONObject(ipJson);
        String ipAddress = jsonObject.getString("ip");
        System.out.println(ipAddress);
        return ipAddress;
    }

    public String getIpInfo(String ipAddress, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String urlString = "http://ipinfo.io/" + ipAddress + "/json?token=" + token;
        ResponseEntity<String> response = restTemplate.getForEntity(urlString, String.class);
        String ipJson = response.getBody();
        JSONObject jsonObject = new JSONObject(ipJson);
        String locationInfo = jsonObject.getString("loc");
        System.out.println(locationInfo);

        return locationInfo;
    }

    public String reverseGeoCode(String token, Double latitude, Double longitude) {
        String baseUrl = "https://geocode.maps.co/reverse?lat=" + latitude+"&lon=" +longitude+"&api_key="+ token;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> locationJson = restTemplate.getForEntity(baseUrl, String.class);
        String details = locationJson.getBody();
        JSONObject jsonObject = new JSONObject(details);
        String location = jsonObject.getString("display_name");

        return location;
    }

}
