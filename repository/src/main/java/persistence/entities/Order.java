package persistence.entities;


import org.springframework.data.annotation.Id;

import java.util.List;

public class Order {
    @Id
    private String id;
    private Event event;
    private User user;
    private List<OrderItem> detail;

    public Order(){}
    public Order(Event event, User user, List<OrderItem> detail) {
        this.event = event;
        this.user = user;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getDetail() {
        return detail;
    }

    public void setDetail(List<OrderItem> detail) {
        this.detail = detail;
    }
}
