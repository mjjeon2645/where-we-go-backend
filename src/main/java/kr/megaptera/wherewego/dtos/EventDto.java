package kr.megaptera.wherewego.dtos;

public class EventDto {
    private Long code;

    private String title;

    public EventDto() {
    }

    public EventDto(Long code, String title) {
        this.code = code;
        this.title = title;
    }

    public Long getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
