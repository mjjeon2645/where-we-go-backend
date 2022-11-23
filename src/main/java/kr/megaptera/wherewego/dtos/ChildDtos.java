package kr.megaptera.wherewego.dtos;

import java.util.*;

public class ChildDtos {
    private List<ChildDto> children;

    public ChildDtos() {
    }

    public ChildDtos(List<ChildDto> children) {
        this.children = children;
    }

    public List<ChildDto> getChildren() {
        return children;
    }
}
