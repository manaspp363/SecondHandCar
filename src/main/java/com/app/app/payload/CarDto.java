package com.app.app.payload;

public class CarDto {
    private Long id;
    private Long brandId;
    private Long fuelId;
    private Long modelId;
    private Long transmissionId;
    private Long yearId;
    private int priceUk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(Long transmissionId) {
        this.transmissionId = transmissionId;
    }

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public int getPriceUk() {
        return priceUk;
    }

    public void setPriceUk(int priceUk) {
        this.priceUk = priceUk;
    }
}
