package kr.megaptera.wherewego.admins;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.errorDtos.*;
import kr.megaptera.wherewego.exceptions.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("admin-users")
public class AdminUserController {
    private final GetUserService getUserService;
    private final GetBookmarkService getBookmarkService;
    private final GetChildService getChildService;
    private final DeleteUserService deleteUserService;

    public AdminUserController(GetUserService getUserService,
                               GetBookmarkService getBookmarkService,
                               GetChildService getChildService,
                               DeleteUserService deleteUserService) {
        this.getUserService = getUserService;
        this.getBookmarkService = getBookmarkService;
        this.getChildService = getChildService;
        this.deleteUserService = deleteUserService;
    }

    @GetMapping
    public UsersDto users() {
        List<User> all = getUserService.users();

        List<UserDto> users = all.stream().map(User::toDto).toList();

        return new UsersDto(users);
    }

    @GetMapping("{id}")
    public UserWithBookmarkedPlacesDto users(
        @PathVariable Long id
    ) {
        User found = getUserService.user(id);
        List<BookmarkedPlaceDto> bookmarkedPlacesLists = getBookmarkService.bookmarks(id);
        List<ChildDto> children = getChildService.children(id);

        return new UserWithBookmarkedPlacesDto(found.toDto(), bookmarkedPlacesLists, children);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSelectedUser(
        @PathVariable Long id
    ) {
        deleteUserService.deleteUser(id);

    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto userNotFoundError() {
        return new UserNotFoundErrorDto();
    }
}
