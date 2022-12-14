package kr.megaptera.wherewego.dtos;

import java.time.*;

public class CreatedAdminLogDto {
    private Long id;

    private Long adminId;

    private EventDto eventDto;

    private String reason;

    private LocalDateTime createdAt;

    public CreatedAdminLogDto() {
    }

    public CreatedAdminLogDto(Long id, Long adminId, EventDto eventDto, String reason,
                              LocalDateTime createdAt) {
        this.id = id;
        this.adminId = adminId;
        this.eventDto = eventDto;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public EventDto getEventDto() {
        return eventDto;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
