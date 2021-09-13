package ua.training.model.entity;

public class Tariff {
    float uahPerKilometerDistance;
    float uahPerMillimeterLength;
    float uahPerMillimeterWidth;
    float uahPerMillimeterHeight;
    float uahPerKilogramWeight;
    float additional;


    public float getUahPerKilometerDistance() {
        return uahPerKilometerDistance;
    }

    public void setUahPerKilometerDistance(float uahPerKilometerDistance) {
        this.uahPerKilometerDistance = uahPerKilometerDistance;
    }

    public float getUahPerMillimeterLength() {
        return uahPerMillimeterLength;
    }

    public void setUahPerMillimeterLength(float uahPerMillimeterLength) {
        this.uahPerMillimeterLength = uahPerMillimeterLength;
    }

    public float getUahPerMillimeterWidth() {
        return uahPerMillimeterWidth;
    }

    public void setUahPerMillimeterWidth(float uahPerMillimeterWidth) {
        this.uahPerMillimeterWidth = uahPerMillimeterWidth;
    }

    public float getUahPerMillimeterHeight() {
        return uahPerMillimeterHeight;
    }

    public void setUahPerMillimeterHeight(float uahPerMillimeterHeight) {
        this.uahPerMillimeterHeight = uahPerMillimeterHeight;
    }

    public float getUahPerKilogramWeight() {
        return uahPerKilogramWeight;
    }

    public void setUahPerKilogramWeight(float uahPerKilogramWeight) {
        this.uahPerKilogramWeight = uahPerKilogramWeight;
    }

    public float getAdditional() {
        return additional;
    }

    public void setAdditional(float additional) {
        this.additional = additional;
    }
}
