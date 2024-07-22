package meetween.backend.place.infra;

public interface RestTemplatePlaceRequester {
    <T> T requestRestTemplate(int pageNo);
}
