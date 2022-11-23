package kr.megaptera.wherewego.dtos;

public class ChildDeleteDto {
    private Long childId;

    public ChildDeleteDto() {
    }

    public ChildDeleteDto(Long childId) {
        this.childId = childId;
    }

    public Long getChildId() {
        return childId;
    }
}
