package meet_eat.data.location.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import meet_eat.data.location.Coordinate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public final class Geocoding {

    private static final String BASE_URL = "https://nominatim.openstreetmap.org/%s?format=json&limit=1%s";
    private static final String SEARCH_OPERATION = "search";
    private static final String REVERSE_OPERATION = "reverse";
    private static final String PARAMETER_POSTCODE = "&postalcode=%s";
    private static final String PARAMETER_CITY = "&city=%s";

    private Geocoding() {
    }

    public static Coordinate getCoordinateFromPostcode(String postcode) {
        String params = String.format(PARAMETER_POSTCODE, postcode);
        String url = String.format(BASE_URL, SEARCH_OPERATION, params);
        HttpMessageConverter<?>[] messageConverters = getMessageConvertersWithEnabledDeserializationFeatures(
                DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT,
                DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);

        return getForObject(url, Coordinate.class, messageConverters);
    }

    public static Coordinate getCoordinateFromCityName(String cityName) {
        String params = String.format(PARAMETER_CITY, cityName);
        String url = String.format(BASE_URL, SEARCH_OPERATION, params);
        HttpMessageConverter<?>[] messageConverters = getMessageConvertersWithEnabledDeserializationFeatures(
                DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT,
                DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);

        return getForObject(url, Coordinate.class, messageConverters);
    }

    private static <T> T getForObject(String url, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, responseType);
    }

    private static <T> T getForObject(String url, Class<T> responseType, HttpMessageConverter<?>... messageConverters) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(messageConverters));
        return restTemplate.getForObject(url, responseType);
    }

    private static MappingJackson2HttpMessageConverter[] getMessageConvertersWithEnabledDeserializationFeatures(
            DeserializationFeature... features) {

        MappingJackson2HttpMessageConverter[] converters = new MappingJackson2HttpMessageConverter[features.length];
        for (int i = 0; i < features.length; i++) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(features[i]);

            MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
            messageConverter.setObjectMapper(objectMapper);

            converters[i] = messageConverter;
        }
        return converters;
    }
}