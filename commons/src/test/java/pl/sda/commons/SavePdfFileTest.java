package pl.sda.commons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SavePdfFileTest {

    private static final String filePathString = "raports/output";
    private static final String[] headers = {"id", "name", "surname"};
    private final List<MockObject> mockObjectList = new ArrayList<>();
    private SavePdfFile savePdfFile = new SavePdfFile();

    @Before
    public void createTestFile() {

        mockObjectList.add(new MockObject(1, "Jan", "Nowak"));
        mockObjectList.add(new MockObject(2, "Anna", "Nowak"));
        mockObjectList.add(new MockObject(3, "Piotr", "Adamek"));
        mockObjectList.add(new MockObject(4, "Andrzej", "Kowalski"));
        mockObjectList.add(new MockObject(5, "Katarzyna", "Krotos"));
        mockObjectList.add(new MockObject(6, "John", "Doe"));
        mockObjectList.add(new MockObject(7, "Hannibal", "Lecter"));
    }

    @Test
    public void isFileCreated() throws IOException {
        //when
        savePdfFile.save(mockObjectList, filePathString);

        //then
        File f = new File("raports/output.pdf");
        Assert.assertTrue(f.exists());
    }

    @Test
    public void areHeadersCorrect() {
        //when
        String[] dataGenerated = FileProcessors.checkHeaders(mockObjectList.get(0));

        //then
        Assert.assertArrayEquals(headers, dataGenerated);
    }

    @Test
    public void isFileNotEmpty() throws IOException {
        //when
        savePdfFile.save(mockObjectList, filePathString);

        //then
        File f = new File("raports/output.pdf");
        Assert.assertFalse(f.length() == 0);
    }
}
