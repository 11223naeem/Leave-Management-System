package com.naeem.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "leaves")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String type;
    private String fromDate;
    private String toDate;
    private String reason;
    private String status;

    public Leave() {}

    public Leave(String username, String type, String fromDate, String toDate, String reason, String status) {
        this.username = username;
        this.type = type;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.status = status;
    }

    // GETTERS & SETTERS
    public int getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getFromDate() { return fromDate; }
    public void setFromDate(String fromDate) { this.fromDate = fromDate; }

    public String getToDate() { return toDate; }
    public void setToDate(String toDate) { this.toDate = toDate; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}