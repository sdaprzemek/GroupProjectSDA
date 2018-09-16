package pl.sda.commons;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SaveCSVFileTest {

    private SaveCSVFile saveCSV = new SaveCSVFile();
    private List<MockObject> list = new ArrayList<>();
    private String[] HEADERS = {"id", "name", "surname"};

    @Before
    public void setUp() {
        list.add(new MockObject(5, "John", "Doe"));
        list.add(new MockObject(6, "James", "Doe"));
        list.add(new MockObject(7, "Jack", "Doe"));
    }

    @Test
    public void checkIfFileExists() {
        //when
        saveCSV.save(list, "carList10");
        File file = new File("carList10.csv");

        //then
        assertTrue(file.exists());
        cleanup();
    }

    @Test
    public void givenCSVFile_whenRead_thenContentsAsExpected() throws IOException {

        saveCSV.save(list, "carList");

        //when
        List<String> objectsValues = new ArrayList<>();
        Reader in = new FileReader("carList.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            objectsValues.add(record.get(0));
        }

        //then
        assertEquals("5;John;Doe", objectsValues.get(0));
        in.close();
        cleanup();
    }

    @Test
    public void givenCSVFileSavedTwoTimes_whenRead_thenCheckAppendingText() throws IOException {

        saveCSV.save(list, "carList2");
        saveCSV.save(list, "carList2");

        //when
        List<String> objectsValues = new ArrayList<>();
        Reader in = new FileReader("carList2.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            objectsValues.add(record.get(0));
        }

        //then
        assertEquals(6, objectsValues.size());
        in.close();
        cleanup();

    }

    @Test
    public void givenCVSFile_whenRead_thenCheckHeadersSavedCorrectly() throws IOException {

        saveCSV.save(list, "carList3");

        //when
        List<String> objectsValues = new ArrayList<>();
        Reader in = new FileReader("carList3.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
        for (CSVRecord record : records) {
            objectsValues.add(record.get(0));
        }

        //then
        assertEquals("id;name;surname", objectsValues.get(0));
        in.close();
        cleanup();
    }

    @After
    public void cleanup() {
        //given
        String carList = "carList.csv";
        String carList2 = "carList2.csv";
        String carList3 = "carList3.csv";
        String carList10 = "carList10.csv";
        File file = new File(carList);
        File file2 = new File(carList2);
        File file3 = new File(carList3);
        File file4 = new File(carList10);

        //when
        if (file.exists()) {
            file.delete();
        }
        if (file2.exists()) {
            file2.delete();
        }
        if (file3.exists()) {
            file3.delete();
        }
        if (file4.exists()) {
            file4.delete();
        }
    }
}