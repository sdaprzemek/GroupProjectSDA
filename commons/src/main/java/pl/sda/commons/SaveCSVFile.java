package pl.sda.commons;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.csv.CSVFormat.INFORMIX_UNLOAD_CSV;
import static pl.sda.commons.FileProcessors.checkHeaders;
import static pl.sda.commons.FileProcessors.processFields;

public class SaveCSVFile implements SaveFile {

    private static Logger logger = LoggerFactory.getLogger(SaveCSVFile.class);

    private static <T> CSVPrinter createCSVComponents(T object, String fileNameWithPath) throws IOException {

        String savePath = fileNameWithPath + ".csv";
        return new File(savePath).exists() ?
                print(savePath, INFORMIX_UNLOAD_CSV.withSkipHeaderRecord()) :
                print(savePath, INFORMIX_UNLOAD_CSV.withHeader(checkHeaders(object)));
    }

    private static CSVPrinter print(String savePath, CSVFormat csvFormat) throws IOException {
        FileWriter out = new FileWriter(savePath, true);
        return new CSVPrinter(out, csvFormat.withDelimiter(';'));
    }

    public <T> void save(List<T> objectList, String fileNameWithPath) {

        T object = objectList.get(0);

        try {
            CSVPrinter printer = createCSVComponents(object, fileNameWithPath);
            for (T t : objectList) {
                List<String> values = new ArrayList<>();
                processFields(t, values);
                printer.printRecord(values);
            }
            printer.flush();
            printer.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}