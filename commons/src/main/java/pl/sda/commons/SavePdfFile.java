package pl.sda.commons;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class SavePdfFile implements SaveFile {

    private static final String FORMAT = ".pdf";

    private static Logger logger = LoggerFactory.getLogger(SavePdfFile.class);

    @Override
    public <T> void save(List<T> object, String fileNameWithPath) throws IOException {
        createFile(fileNameWithPath + FORMAT);
        Document document = new Document();
        String[] headers = FileProcessors.checkHeaders(object.get(0));

        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileNameWithPath + FORMAT));
        } catch (DocumentException e) {
            logger.error(e.getMessage());
        }
        PdfPTable table = new PdfPTable(headers.length);
        setupTableLabels(table, headers);
        generateTableCellsWithData(object, table);
        document.open();
        try {
            document.add(table);
        } catch (DocumentException e) {
            logger.error(e.getMessage());
        }
        document.close();
    }

    private <T> void generateTableCellsWithData(List<T> object, PdfPTable table) {
        for(int i = 0; i < object.size(); i++) {
            for (Field field : object.get(i).getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(object.get(i));
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
                addObjectValueIntoTableCell(table, value);
            }
        }
    }

    private void addObjectValueIntoTableCell(PdfPTable table, Object value) {
        if (value != null) {
            table.addCell(String.valueOf(value));
        }
    }

    private void createFile(String fileNameWithPath) {
        File file = new File(fileNameWithPath + FORMAT);
        file.getParentFile().mkdirs();
    }

    private void setupTableLabels(PdfPTable table, String[] headers) {
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        for (String header : headers) {
            table.addCell(header);
        }
        table.setHeaderRows(1);
        PdfPCell[] cells = table.getRow(0).getCells();
        for (int j = 0; j < cells.length; j++) {
            cells[j].setBackgroundColor(BaseColor.GRAY);
        }
    }
}
