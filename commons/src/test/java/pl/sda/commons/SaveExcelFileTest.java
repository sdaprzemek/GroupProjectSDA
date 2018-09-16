package pl.sda.commons;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveExcelFileTest {

    private SaveExcelFile saveExcelFile = new SaveExcelFile();
    private File file = new File("src/test/java/pl/sda/commons/ExcelFileTest.xls");

    @Before
    public void createTestFile() {
        List<MockObject> mockObjectList = new ArrayList<>();
        mockObjectList.add(new MockObject(1, "name1", "surmane1"));
        mockObjectList.add(new MockObject(2, "name2", "surmane2"));
        mockObjectList.add(new MockObject(3, "name3", "surmane3"));
        mockObjectList.add(new MockObject(4, "name4", "surmane4"));
        mockObjectList.add(new MockObject(5, "name5", "surmane5"));
        mockObjectList.add(new MockObject(6, "name6", "surmane6"));
        mockObjectList.add(new MockObject(7, "name7", "surmane7"));
        try {
            saveExcelFile.save(mockObjectList, "src/test/java/pl/sda/commons/ExcelFileTest.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ifFileExistsTest() {
        Assert.assertTrue(file.exists());
    }

    @Test
    public void fileSizeNotZeroTest() {
        Assert.assertTrue(file.length() > 0);
    }

    @After
    public void deleteTestFile() {
        file.delete();
    }
}
