package ua.training.model.entity;

import java.util.Objects;

public class Parcel {
    private String type;
    private float length;
    private float width;
    private float height;
    private float weight;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Parcel{" +
                "type='" + type + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcel parcel = (Parcel) o;
        return Float.compare(parcel.length, length) == 0 && Float.compare(parcel.width, width) == 0 && Float.compare(parcel.height, height) == 0 && Float.compare(parcel.weight, weight) == 0 && type.equals(parcel.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, length, width, height, weight);
    }

    public static ParcelBuilder builder (){
        return new ParcelBuilder();
    }

    public static class ParcelBuilder {
        private final Parcel newParcel;

        private ParcelBuilder(){
            newParcel = new Parcel();
        }


    }
}
