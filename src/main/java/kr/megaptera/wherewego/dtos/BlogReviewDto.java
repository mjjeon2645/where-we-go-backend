package kr.megaptera.wherewego.dtos;

public class BlogReviewDto {
    private Long id;

    private Long placeId;

    private String title;

    private String author;

    private String date;

    private String imageSource;

    private String url;

    public BlogReviewDto() {
    }

    public BlogReviewDto(Long id, Long placeId, String title, String author, String date,
                         String imageSource, String url) {
        this.id = id;
        this.placeId = placeId;
        this.title = title;
        this.author = author;
        this.date = date;
        this.imageSource = imageSource;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getImageSource() {
        return imageSource;
    }

    public String getUrl() {
        return url;
    }
}
