package pl.sda.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FileProcessors {

    private static Logger logger = LoggerFactory.getLogger(FileProcessors.class);

    public static <T> String[] checkHeaders(T someObject) {
        List<String> headers = new ArrayList<>();
        processFields(someObject, headers, true);
        return headers.toArray(new String[0]);
    }

    public static <T> void processFields(T t, List<String> values) {
        processFields(t, values, false);
    }

    public static <T> void processFields(T t,
                                         List<String> values,
                                         boolean isHeader) {
        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(t);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
            if (value != null && !isHeader) {
                values.add(value.toString());
            } else {
                values.add(field.getName());
            }
        }
    }
}
