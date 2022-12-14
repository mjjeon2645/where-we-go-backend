package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.*;

@Entity
public class AdminLog {
    @Id
    @GeneratedValue
    private Long id;

    private Long adminId;

    private Event event;

    private String reason;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public AdminLog() {
    }

    public AdminLog(Long adminId, Event event, String reason) {
        this.adminId = adminId;
        this.event = event;
        this.reason = reason;
    }

    public AdminLog(Long id, Long adminId, Event event, String reason,
                    LocalDateTime createdAt) {
        this.id = id;
        this.adminId = adminId;
        this.event = event;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public Long id() {
        return id;
    }

    public Long adminId() {
        return adminId;
    }

    @Embedded
    public Event event() {
        return event;
    }

    public String reason() {
        return reason;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public CreatedAdminLogDto toDto() {
        return new CreatedAdminLogDto(id, adminId, event.toDto(), reason, createdAt);
    }
}
