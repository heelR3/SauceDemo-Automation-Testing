package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Product;

public class ExcelWriter {

	private static final String FILE_PATH = ConfigReader.getProperty("excelPath");

	private static final String SHEET_NAME = ConfigReader.getProperty("inventorySheet");

	private ExcelWriter() {
	}

	private static XSSFWorkbook openWorkbook() throws IOException {

	    try (FileInputStream fis =
	            new FileInputStream(FILE_PATH)) {

	        return new XSSFWorkbook(fis);

	    }
	}

	private static void saveWorkbook(XSSFWorkbook workbook) throws IOException {

		FileOutputStream fos = new FileOutputStream(FILE_PATH);

		workbook.write(fos);

		fos.close();

		workbook.close();

	}

	public static void clearInventorySheet() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			int sheetIndex = workbook.getSheetIndex(SHEET_NAME);

			if (sheetIndex != -1) {

				workbook.removeSheetAt(sheetIndex);

			}

			workbook.createSheet(SHEET_NAME);

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to clear Inventory Sheet", e);

		}

		}

	public static void writeInventoryProducts(List<Product> products) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet(SHEET_NAME);

			if (sheet == null) {

				sheet = workbook.createSheet(SHEET_NAME);

			}

			String executionTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

			String browser = ConfigReader.getProperty("browser");

			int rowNumber = 1;

			for (Product product : products) {

				Row row = sheet.createRow(rowNumber++);

				row.createCell(0).setCellValue(product.getName());

				row.createCell(1).setCellValue(product.getPrice());

				row.createCell(2).setCellValue(product.getDescription());

				row.createCell(3).setCellValue(product.getImageUrl());

				row.createCell(8).setCellValue(executionTime);

				row.createCell(9).setCellValue(browser);

			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to write Inventory Products", e);

		}

	}

	/**
	 * Writes sorting result into Inventory sheet.
	 *
	 * Column Mapping: 4 = A-Z 5 = Z-A 6 = Low-High 7 = High-Low
	 */
	public static void writeSortingResult(List<Product> products, int columnIndex) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet(SHEET_NAME);

			if (sheet == null) {

				throw new RuntimeException("Inventory sheet not found.");

			}

			int rowNumber = 1;

			for (Product product : products) {

				Row row = sheet.getRow(rowNumber);

				if (row == null) {

					row = sheet.createRow(rowNumber);

				}

				row.createCell(columnIndex).setCellValue(product.getName() + " - " + product.getPrice());

				rowNumber++;

			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to write sorting result.", e);

		}

	}

	/**
	 * Creates the Inventory sheet header if it is empty.
	 */
	public static void createInventoryHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet(SHEET_NAME);

			if (sheet == null) {

				sheet = workbook.createSheet(SHEET_NAME);

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.createCell(0).setCellValue("Name");
				header.createCell(1).setCellValue("Price");
				header.createCell(2).setCellValue("Description");
				header.createCell(3).setCellValue("Image URL");
				header.createCell(4).setCellValue("A-Z");
				header.createCell(5).setCellValue("Z-A");
				header.createCell(6).setCellValue("Low-High");
				header.createCell(7).setCellValue("High-Low");
				header.createCell(8).setCellValue("Execution Time");
				header.createCell(9).setCellValue("Browser");

			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to create Inventory header.", e);

		}

	}

}
