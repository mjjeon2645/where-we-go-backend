package kr.megaptera.wherewego.services;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.repositories.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional
public class GetBlogReviewService {
    private final PlaceRepository placeRepository;

    public GetBlogReviewService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<BlogReviewDto> blogReviews(List<Map<String, String>> naverBlogs, Long placeId) {
        List<BlogReviewDto> blogReviews = new ArrayList<>();

        for (Map<String, String> map : naverBlogs) {
            String title = map.get("title");
            String author = map.get("blogger");
            String date = map.get("postDate");
            String convertedDate = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
            String url = map.get("blogLink");

            BlogReviewDto blogReviewDto =
                new BlogReviewDto(
                    placeId,
                    title,
                    author,
                    convertedDate,
//                    "https://user-images.githubusercontent.com/104840243/204121280-2e3dd883-76fa-4bba-983e-6b15d7f86c12.png",
                    "https://res.cloudinary.com/ds7ujh0mf/image/upload/v1670398470/top-3-image_g5mknk.png",
                    url);

            blogReviews.add(blogReviewDto);
        }

        return blogReviews;
    }

    public String keyword(Long placeId) {
        Place found = placeRepository.findById(placeId)
            .orElseThrow(PlaceNotFoundException::new);

        return found.name() + " 아기랑";
    }
}
