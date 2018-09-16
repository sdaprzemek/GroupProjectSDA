package pl.sda.commons;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveTest {

    private static final String filePathString = "raports/output";
    private final List<MockObject> mockObjectList = new ArrayList<>();
    SavePdfFile savePdfFile = new SavePdfFile();
    SaveCSVFile saveCSVFile = new SaveCSVFile();
    SaveExcelFile saveExcelFile = new SaveExcelFile();


    @Test
    public void saveFileFinalTestIsSavingAllFormats() throws IOException {
        //given
        mockObjectList.add(new MockObject(1, "Jan", "Nowak"));
        mockObjectList.add(new MockObject(2, "Anna", "Nowak"));
        mockObjectList.add(new MockObject(3, "Piotr", "Adamek"));
        mockObjectList.add(new MockObject(4, "Andrzej", "Kowalski"));

        //when
        savePdfFile.save(mockObjectList, filePathString);
        saveCSVFile.save(mockObjectList, filePathString);
        saveExcelFile.save(mockObjectList, filePathString);

        //then
        File pdf = new File("raports/output.pdf");
        File csv = new File("raports/output.csv");
        File xls = new File("raports/output");

        Assert.assertTrue(xls.exists() && pdf.exists() && csv.exists());
    }
}