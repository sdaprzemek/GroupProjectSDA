package pl.sda.commons;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;

public interface SaveFile {

    <T> void save(List<T> object, String fileNameWithPath) throws IOException, InvalidFormatException;
}
