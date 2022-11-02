package kr.megaptera.wherewego.dtos;

import java.util.*;

public class PositionsDto {
  private List<PositionDto> positionDtos;

  public PositionsDto() {
  }

  public PositionsDto(List<PositionDto> positionDtos) {
    this.positionDtos = positionDtos;
  }

  public List<PositionDto> getPositionDtos() {
    return positionDtos;
  }
}
