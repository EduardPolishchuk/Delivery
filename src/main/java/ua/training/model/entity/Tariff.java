package ua.training.model.entity;

public class Tariff {
    long id;
    float uahPerKilometerDistance;
    float uahPerMillimeterLength;
    float uahPerMillimeterWidth;
    float uahPerMillimeterHeight;
    float uahPerKilogramWeight;
    float additional;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", uahPerKilometerDistance=" + uahPerKilometerDistance +
                ", uahPerMillimeterLength=" + uahPerMillimeterLength +
                ", uahPerMillimeterWidth=" + uahPerMillimeterWidth +
                ", uahPerMillimeterHeight=" + uahPerMillimeterHeight +
                ", uahPerKilogramWeight=" + uahPerKilogramWeight +
                ", additional=" + additional +
                '}';
    }
}
