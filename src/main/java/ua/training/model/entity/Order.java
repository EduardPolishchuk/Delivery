package ua.training.model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private LocalDate receivingDate;
    private LocalDate requestDate;
    private long id;
    private User userSender;
    private Parcel parcel;
    private City cityTo;
    private City cityFrom;
    private OrderStatus status;


    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(LocalDate receivingDate) {
        this.receivingDate = receivingDate;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public City getCityTo() {
        return cityTo;
    }

    public void setCityTo(City cityTo) {
        this.cityTo = cityTo;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(receivingDate, order.receivingDate) && Objects.equals(requestDate, order.requestDate) && Objects.equals(userSender, order.userSender) && Objects.equals(parcel, order.parcel) && Objects.equals(cityTo, order.cityTo) && Objects.equals(cityFrom, order.cityFrom) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(receivingDate, requestDate, id, userSender, parcel, cityTo, cityFrom, status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "receivingDate=" + receivingDate +
                ", requestDate=" + requestDate +
                ", id=" + id +
                ", userSender=" + userSender +
                ", parcel=" + parcel +
                ", cityTo=" + cityTo +
                ", cityFrom=" + cityFrom +
                ", status=" + status +
                '}';
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private final Order newOrder;

        private OrderBuilder() {
            newOrder = new Order();
        }

        public OrderBuilder receivingDate(LocalDate receivingDate) {
            newOrder.setReceivingDate(receivingDate);
            return this;
        }

        public OrderBuilder requestDate(LocalDate requestDate) {
            newOrder.setRequestDate(requestDate);
            return this;
        }

        public OrderBuilder id(long id) {
            newOrder.setId(id);
            return this;
        }

        public OrderBuilder status(OrderStatus status) {
            newOrder.setStatus(status);
            return this;
        }

        public OrderBuilder userSender(User user) {
            newOrder.setUserSender(user);
            return this;
        }

        public OrderBuilder parcel(Parcel parcel) {
            newOrder.setParcel(parcel);
            return this;
        }

        public OrderBuilder cityTo(City cityTo) {
            newOrder.setCityTo(cityTo);
            return this;
        }

        public OrderBuilder cityFrom(City cityFrom) {
            newOrder.setCityFrom(cityFrom);
            return this;
        }

        public Order build() {
            return newOrder;
        }
    }

    public enum OrderStatus {
        WAITING_FOR_CONFIRM,
        WAITING_FOR_PAYMENT,
        PARCEL_DELIVERY,
        DELIVERED,
        CANCELED
    }
}
