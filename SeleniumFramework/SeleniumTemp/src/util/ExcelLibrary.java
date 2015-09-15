package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Reporter;

public class ExcelLibrary {
	static Map<String, Workbook> workbooktable = new HashMap<String, Workbook>();
	public static Map<String, Integer> dict = new Hashtable<String, Integer>();
	public static List list = new ArrayList();
	static ReadConfigProperty config = new ReadConfigProperty();

	public static Workbook getWorkbook(String path) {
		Workbook workbook = null;
		if (workbooktable.containsKey(path)) {
			workbook = workbooktable.get(path);
		} else {

			try {

				workbook = WorkbookFactory.create(new File(path));
				workbooktable.put(path, workbook);

			} catch (FileNotFoundException e) {
				MainTestNG.LOGGER.info("FileNotFoundException" + e);
			} catch (InvalidFormatException e) {
				MainTestNG.LOGGER.info("InvalidFormatException" + e);
			} catch (IOException e) {
				MainTestNG.LOGGER.info("IOException" + e);
			}

		}
		return workbook;
	}

	public static List<String> getNumberOfSheetsinSuite(String testPath) {
		List<String> listOfSheets = new ArrayList<String>();

		Workbook workbook = getWorkbook(testPath);

		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			listOfSheets.add(workbook.getSheetName(i));
		}

		return listOfSheets;
	}

	public static List<String> getNumberOfSheetsinTestDataSheet(String testPath) {
		List<String> listOfSheets = new ArrayList<String>();

		Workbook workbook = getWorkbook(testPath);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			if (!(workbook.getSheetName(i)).equalsIgnoreCase(config
					.getConfigValues("TestCase_SheetName"))) {
				listOfSheets.add(workbook.getSheetName(i));

			}
		}
		return listOfSheets;

	}

	/**
	 * Get the total rows present in excel sheet
	 */
	public static int getRows(String testSheetName, String pathOfFile)
			throws InvalidFormatException, IOException {
		Workbook workbook = getWorkbook(pathOfFile);
		Reporter.log("getting total number of rows");

		Sheet sheet = workbook.getSheet(testSheetName);

		return sheet.getLastRowNum();

	}

	/**
	 * Get the total columns inside excel sheet
	 */
	public static int getColumns(String testSheetName, String pathOfFile)
			throws InvalidFormatException, IOException {
		Workbook workbook = getWorkbook(pathOfFile);
		Reporter.log("getting total number of columns");
		Sheet sheet = workbook.getSheet(testSheetName);
		return sheet.getRow(0).getLastCellNum();

	}

	public static List getColumnNames(String testSheetName, String pathOfFile,
			int j) throws InvalidFormatException, IOException {
		Workbook workbook = getWorkbook(pathOfFile);
		Sheet sheet = workbook.getSheet(testSheetName);

		for (int i = 0; i <= j; i++) {
			if (sheet.getRow(0).getCell(i) != null) {
				list.add(sheet.getRow(0).getCell(i).getStringCellValue()
						.toString());
			}
		}

		return list;

	}

	public static void getNumberOfRowsPerColumn(String testSheetName,
			String pathOfFile, int j) throws InvalidFormatException,
			IOException {
		Workbook workbook = getWorkbook(pathOfFile);
		Sheet sheet = workbook.getSheet(testSheetName);
		int totColumns = sheet.getRow(0).getLastCellNum();
		for (int i = 0; i <= totColumns; i++) {
			if (sheet.getRow(0).getCell(i) != null) {
				list.add(sheet.getRow(0).getCell(i).getStringCellValue()
						.toString());
			}
		}
	}

	/**
	 * Read the content of the cell
	 */
	public static String readCell(int rowNum, int colNum, String testSheetName,
			String pathOfFile) {
		Workbook workbook;
		String cellValue = null;

		workbook = getWorkbook(pathOfFile);
		Sheet sheet = workbook.getSheet(testSheetName);
		Row row = sheet.getRow(rowNum);
		if (row != null) {
			Cell cell = row.getCell(colNum);
			if (cell != null) {
				DataFormatter dataFormatter = new DataFormatter();
				String data = dataFormatter.formatCellValue(cell);
				cellValue = data;
			}
		}
		return cellValue;
	}

	public void clean() {
		workbooktable.clear();
		list.clear();
	}

}