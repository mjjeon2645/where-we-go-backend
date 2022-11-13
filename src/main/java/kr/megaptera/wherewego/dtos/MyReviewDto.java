package kr.megaptera.wherewego.dtos;

public class MyReviewDto {
    private Long placeId;

    private String dateOfVisit;

    private Long rate;

    private String body;

    public MyReviewDto() {
    }

    public MyReviewDto(Long placeId, String dateOfVisit, Long rate, String body) {
        this.placeId = placeId;
        this.dateOfVisit = dateOfVisit;
        this.rate = rate;
        this.body = body;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public String getDateOfVisit() {
        return dateOfVisit;
    }

    public Long getRate() {
        return rate;
    }

    public String getBody() {
        return body;
    }
}
