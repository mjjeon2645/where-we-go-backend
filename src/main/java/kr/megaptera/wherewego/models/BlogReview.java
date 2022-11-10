package kr.megaptera.wherewego.models;

import kr.megaptera.wherewego.dtos.*;

import javax.persistence.*;

@Entity
public class BlogReview {
    @Id
    @GeneratedValue
    private Long id;

    private Long placeId;

    private String title;

    private String author;

    private String date;

    private String imageSource;

    private String url;

    public BlogReview() {
    }

    public BlogReview(Long id, Long placeId, String title, String author, String date,
                      String imageSource, String url) {
        this.id = id;
        this.placeId = placeId;
        this.title = title;
        this.author = author;
        this.date = date;
        this.imageSource = imageSource;
        this.url = url;
    }

    public BlogReviewDto toDto() {
        return new BlogReviewDto(id, placeId, title, author, date, imageSource, url);
    }

    public Long id() {
        return id;
    }

    public Long placeId() {
        return placeId;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public String date() {
        return date;
    }

    public String imageSource() {
        return imageSource;
    }

    public String url() {
        return url;
    }
}
