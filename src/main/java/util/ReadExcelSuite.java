package main.java.util;
/**
 * The ReadExcelSuite class is used to read excel suite
 *
 * @author Shilpashree_V
 * @version 1.0
 * @since February 2015
 * 
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcelSuite {
	public static void main(String[] args) {
		ReadExcelSuite read = new ReadExcelSuite();
		read.getNumberOfSheetsinSuite();
	}

	public List<String> getNumberOfSheetsinSuite() {
		List<String> listOfSheets = new ArrayList<String>();

		try {
			Workbook workbook = WorkbookFactory
					.create(new File(
							"D:\\Selenium Framework development\\TestSuitePrototype.xlsx"));
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

			}

		} catch (InvalidFormatException | IOException e) {

			MainTestNG.LOGGER.severe(e.getMessage());
		}
		return listOfSheets;

	}

	public static String readCell(int rowNum, int ColNum) {
		Workbook workbook;
		String cellValue = null;
		try {
			workbook = WorkbookFactory.create(new File(
					"D:\\Selenium Framework development\\TestCaseSheet.xlsx"));
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(rowNum);
			Cell cell = row.getCell(ColNum);
			cellValue = cell.getStringCellValue();
		} catch (InvalidFormatException | IOException e) {

			MainTestNG.LOGGER.severe("InvalidFormatException");
		}
		return cellValue;
	}
}
