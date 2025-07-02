
package com.app.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car_images")
public class CarImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "front_view", length = 500)
    private String frontView;

    @Column(name = "rear_view", length = 500)
    private String rearView;

    @Column(name = "left_side_view", length = 500)
    private String leftSideView;

    @Column(name = "right_side_view", length = 500)
    private String rightSideView;

    @Column(name = "interior_dashboard", length = 500)
    private String interiorDashboard;

    @Column(name = "steering_wheel", length = 500)
    private String steeringWheel;

    @Column(name = "speedometer", length = 500)
    private String speedometer;

    @Column(name = "gear_console", length = 500)
    private String gearConsole;

    @Column(name = "car_seats", length = 500)
    private String carSeats;

    @Column(name = "rear_seats", length = 500)
    private String rearSeats;

    @Column(name = "door_panels", length = 500)
    private String doorPanels;

    @Column(name = "car_boot", length = 500)
    private String carBoot;

    @Column(name = "engine_bay", length = 500)
    private String engineBay;

    @Column(name = "headlights", length = 500)
    private String headlights;

    @Column(name = "taillights", length = 500)
    private String taillights;

    @Column(name = "fog_lamps", length = 500)
    private String fogLamps;

    @Column(name = "roof", length = 500)
    private String roof;

    @Column(name = "side_mirrors", length = 500)
    private String sideMirrors;

    @Column(name = "alloy_wheels", length = 500)
    private String alloyWheels;

    @Column(name = "tyres", length = 500)
    private String tyres;

    @Column(name = "exhaust", length = 500)
    private String exhaust;

    @Column(name = "number_plate_front", length = 500)
    private String numberPlateFront;

    @Column(name = "number_plate_rear", length = 500)
    private String numberPlateRear;

    @Column(name = "hood_open", length = 500)
    private String hoodOpen;

    @Column(name = "trunk_open", length = 500)
    private String trunkOpen;

    @Column(name = "infotainment_system", length = 500)
    private String infotainmentSystem;

    @Column(name = "ac_vents", length = 500)
    private String acVents;

    @Column(name = "glove_box", length = 500)
    private String gloveBox;

    @Column(name = "fuel_lid", length = 500)
    private String fuelLid;

    @Column(name = "wipers", length = 500)
    private String wipers;

    @Column(name = "sunroof", length = 500)
    private String sunroof;

    @Column(name = "roof_rails", length = 500)
    private String roofRails;

    @Column(name = "mudguards", length = 500)
    private String mudguards;

    @Column(name = "brake_lights", length = 500)
    private String brakeLights;

    @Column(name = "steering_controls", length = 500)
    private String steeringControls;

    @Column(name = "seat_belts", length = 500)
    private String seatBelts;

    @Column(name = "hand_brake", length = 500)
    private String handBrake;

    @Column(name = "power_windows", length = 500)
    private String powerWindows;

    @Column(name = "cabin_lights", length = 500)
    private String cabinLights;

    @Column(name = "rear_defogger", length = 500)
    private String rearDefogger;

    @Column(name = "rear_wiper", length = 500)
    private String rearWiper;

    @Column(name = "radiator_grill", length = 500)
    private String radiatorGrill;

    @Column(name = "spare_tyre", length = 500)
    private String spareTyre;

    @Column(name = "jack_toolkit", length = 500)
    private String jackToolkit;

    @Column(name = "car_key", length = 500)
    private String carKey;

    @Column(name = "airbags", length = 500)
    private String airbags;

    @Column(name = "battery", length = 500)
    private String battery;
}
