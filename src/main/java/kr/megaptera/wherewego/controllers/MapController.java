package kr.megaptera.wherewego.controllers;

import kr.megaptera.wherewego.dtos.*;
import kr.megaptera.wherewego.models.*;
import kr.megaptera.wherewego.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("map")
public class MapController {
  private MapService mapService;

  public MapController(MapService mapService) {
    this.mapService = mapService;
  }

  @GetMapping
  public PositionsDto places() {
    List<PositionDto> positionDtos = mapService.places()
        .stream().map(Place::toPositionDto)
        .toList();

    return new PositionsDto(positionDtos);
  }
}
