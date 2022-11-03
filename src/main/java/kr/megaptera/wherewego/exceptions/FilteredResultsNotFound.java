package kr.megaptera.wherewego.exceptions;

public class FilteredResultsNotFound extends RuntimeException {
  public FilteredResultsNotFound() {
    super("검색 결과가 없습니다");
  }
}
