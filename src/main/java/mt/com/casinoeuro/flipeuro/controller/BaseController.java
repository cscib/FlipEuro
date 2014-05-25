package mt.com.casinoeuro.flipeuro.controller;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

/**
 * An abstract class to be the parent of other controllers.
 *
 * @author cscib
 * @version 1.0.0
 * @since 25/05/2014 23:16
 */
public abstract class BaseController {

    /**
     * Reads an entire JSON object from the input stream, converting it to the specified type {@code T}.
     *
     * @param stream The {@link InputStream} from which the JSON object is to be read.
     * @param type   The type of class the object is to be converted to.
     * @param <T>    The type placeholder.
     * @return A new instance of type {@code T}.
     * @throws IOException An error occurred while reading from the input stream.
     */
    public static <T> T objectFromStream(InputStream stream, Class<T> type) throws IOException {
        ObjectMapper mapper = createConfiguredObjectMapper();
        return mapper.readValue(stream, type);
    }

    /**
     * Writes an entire object to JSON to the output stream.
     *
     * @param stream The {@link OutputStream} to which the object is to be written.
     * @param object The object to write.
     * @throws IOException An error occurred while writing to the output stream.
     */
    public static void objectToStream(OutputStream stream, Object object) throws IOException {
        ObjectMapper mapper = createConfiguredObjectMapper();
        mapper.writeValue(stream, object);
    }

    /**
     * Creates a new and configured instance of a {@code Jackson} {@link ObjectMapper}
     * instance. The following (explicitly defined) configurations apply:
     * <ul>
     * <li>{@code SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING} is enabled</li>
     * <li>{@code DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING} is enabled</li>
     * <li>{@code SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS} is disabled</li>
     * <li>Date format is set to {@code dd-MM-yyyy}</li>
     * </ul>
     *
     * @return A new configured {@code ObjectMapper} instance.
     */
    private static ObjectMapper createConfiguredObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING);
        mapper.disable(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return mapper;
    }
}
