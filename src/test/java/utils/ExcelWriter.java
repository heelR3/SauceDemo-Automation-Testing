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

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;

import model.Product;

public class ExcelWriter {

	private static final String FILE_PATH = ConfigReader.getProperty("excelPath");

	private static final String SHEET_NAME = ConfigReader.getProperty("inventorySheet");

	private ExcelWriter() {
	}

	private static CellStyle createHeaderStyle(XSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();

		XSSFFont font = workbook.createFont();

		font.setBold(true);
		font.setFontHeightInPoints((short) 12);

		style.setFont(font);

		style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());

		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		style.setAlignment(HorizontalAlignment.CENTER);

		style.setVerticalAlignment(VerticalAlignment.CENTER);

		style.setWrapText(true);

		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		return style;
	}

	private static CellStyle createDataStyle(XSSFWorkbook workbook) {

		CellStyle style = workbook.createCellStyle();

		style.setWrapText(false);

		style.setVerticalAlignment(VerticalAlignment.CENTER);

		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		return style;
	}

	private static synchronized XSSFWorkbook openWorkbook() throws IOException {

		try (FileInputStream fis = new FileInputStream(FILE_PATH)) {

			return new XSSFWorkbook(fis);
		}
	}

	private static synchronized void saveWorkbook(XSSFWorkbook workbook) throws IOException {

		FileOutputStream fos = new FileOutputStream(FILE_PATH);

		workbook.write(fos);

		fos.close();

		workbook.close();

	}

	public static synchronized void clearInventorySheet() {

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

	public static synchronized void writeInventoryProducts(List<Product> products) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet(SHEET_NAME);

			if (sheet == null) {

				sheet = workbook.createSheet(SHEET_NAME);

			}

			String executionTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

			String browser = BrowserContext.getBrowser();

			int rowNumber = sheet.getLastRowNum() + 1;
			CellStyle dataStyle = createDataStyle(workbook);
			for (Product product : products) {

				Row row = sheet.createRow(rowNumber++);

				row.setHeightInPoints(30);

				Cell cell0 = row.createCell(0);
				cell0.setCellValue(product.getName());
				cell0.setCellStyle(dataStyle);

				Cell cell1 = row.createCell(1);
				cell1.setCellValue(product.getPrice());
				cell1.setCellStyle(dataStyle);

				Cell cell2 = row.createCell(2);
				cell2.setCellValue(product.getDescription());
				cell2.setCellStyle(dataStyle);

				Cell cell3 = row.createCell(3);
				cell3.setCellValue(product.getImageUrl());
				cell3.setCellStyle(dataStyle);

				Cell cell8 = row.createCell(8);
				cell8.setCellValue(executionTime);
				cell8.setCellStyle(dataStyle);

				Cell cell9 = row.createCell(9);
				cell9.setCellValue(browser);
				cell9.setCellStyle(dataStyle);

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
	public static synchronized void writeSortingResult(List<Product> products, int columnIndex) {

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
	public static synchronized void createInventoryHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet(SHEET_NAME);

			if (sheet == null) {

				sheet = workbook.createSheet(SHEET_NAME);

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.setHeightInPoints(25);

				CellStyle headerStyle = createHeaderStyle(workbook);

				String[] headers = { "Name", "Price", "Description", "Image URL", "A-Z", "Z-A", "Low-High", "High-Low",
						"Execution Time", "Browser" };

				for (int i = 0; i < headers.length; i++) {

					Cell cell = header.createCell(i);

					cell.setCellValue(headers[i]);

					cell.setCellStyle(headerStyle);

				}

				sheet.createFreezePane(0, 1);

				sheet.setColumnWidth(0, 30 * 256); // Name

				sheet.setColumnWidth(1, 12 * 256); // Price

				sheet.setColumnWidth(2, 80 * 256); // Description

				sheet.setColumnWidth(3, 40 * 256); // Image URL

				sheet.setColumnWidth(4, 35 * 256);
				sheet.setColumnWidth(5, 35 * 256);
				sheet.setColumnWidth(6, 35 * 256);
				sheet.setColumnWidth(7, 35 * 256);

				sheet.setColumnWidth(8, 25 * 256); // Execution Time

				sheet.setColumnWidth(9, 12 * 256); // Browser
			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to create Inventory header.", e);

		}
	}

// CART

	public static synchronized void createCartHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Cart");

			if (sheet == null) {

				sheet = workbook.createSheet("Cart");

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.setHeightInPoints(25);

				CellStyle headerStyle = createHeaderStyle(workbook);

				String[] headers = { "Product Name", "Price", "Action", "Quantity", "Execution Time", "Browser" };

				for (int i = 0; i < headers.length; i++) {

					Cell cell = header.createCell(i);

					cell.setCellValue(headers[i]);

					cell.setCellStyle(headerStyle);
				}

				sheet.createFreezePane(0, 1);

				sheet.setColumnWidth(0, 35 * 256);
				sheet.setColumnWidth(1, 12 * 256);
				sheet.setColumnWidth(2, 15 * 256);
				sheet.setColumnWidth(3, 12 * 256);
				sheet.setColumnWidth(4, 25 * 256);
				sheet.setColumnWidth(5, 12 * 256);
			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to create Cart Header.", e);

		}
	}

	public static synchronized void clearCartSheet() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Cart");

			if (sheet == null) {

				sheet = workbook.createSheet("Cart");

			}

			int lastRow = sheet.getLastRowNum();

			for (int i = lastRow; i >= 1; i--) {

				Row row = sheet.getRow(i);

				if (row != null) {

					sheet.removeRow(row);

				}

			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to clear Cart Sheet.", e);

		}

	}

	public static synchronized void writeCartAction(Product product, String action) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Cart");

			if (sheet == null) {

				sheet = workbook.createSheet("Cart");

			}

			int rowNumber = sheet.getLastRowNum() + 1;

			Row row = sheet.createRow(rowNumber);

			row.setHeightInPoints(25);

			CellStyle dataStyle = createDataStyle(workbook);

			Cell cell0 = row.createCell(0);
			cell0.setCellValue(product.getName());
			cell0.setCellStyle(dataStyle);

			Cell cell1 = row.createCell(1);
			cell1.setCellValue(product.getPrice());
			cell1.setCellStyle(dataStyle);

			Cell cell2 = row.createCell(2);
			cell2.setCellValue(action);
			cell2.setCellStyle(dataStyle);

			Cell cell3 = row.createCell(3);
			cell3.setCellValue(1);
			cell3.setCellStyle(dataStyle);

			Cell cell4 = row.createCell(4);
			cell4.setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			cell4.setCellStyle(dataStyle);

			Cell cell5 = row.createCell(5);
			cell5.setCellValue(BrowserContext.getBrowser());
			cell5.setCellStyle(dataStyle);

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to write Cart Action.", e);

		}
	}

	/*
	 * 
	 * ====== Checkout Method =====
	 * 
	 */

	public static synchronized void createCheckoutHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Checkout");

			if (sheet == null) {

				sheet = workbook.createSheet("Checkout");

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.setHeightInPoints(25);

				CellStyle headerStyle = createHeaderStyle(workbook);

				String[] headers = { "Test Case ID", "First Name", "Last Name", "Postal Code", "Result",
						"Error Message", "Execution Time", "Browser" };

				for (int i = 0; i < headers.length; i++) {

					Cell cell = header.createCell(i);

					cell.setCellValue(headers[i]);

					cell.setCellStyle(headerStyle);
				}

				sheet.createFreezePane(0, 1);

				sheet.setColumnWidth(0, 18 * 256);
				sheet.setColumnWidth(1, 20 * 256);
				sheet.setColumnWidth(2, 20 * 256);
				sheet.setColumnWidth(3, 15 * 256);
				sheet.setColumnWidth(4, 12 * 256);
				sheet.setColumnWidth(5, 40 * 256);
				sheet.setColumnWidth(6, 25 * 256);
				sheet.setColumnWidth(7, 12 * 256);
			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to create Checkout Header.", e);

		}
	}

	public static synchronized void clearCheckoutSheet() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Checkout");

			if (sheet == null) {

				sheet = workbook.createSheet("Checkout");

			}

			int lastRow = sheet.getLastRowNum();

			for (int i = lastRow; i >= 1; i--) {

				Row row = sheet.getRow(i);

				if (row != null) {

					sheet.removeRow(row);

				}

			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to clear Checkout Sheet.", e);

		}

	}

	public static synchronized void writeCheckoutResult(String testCaseId, String firstName, String lastName,
			String postalCode, String result, String errorMessage) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Checkout");

			if (sheet == null) {

				sheet = workbook.createSheet("Checkout");

			}

			int rowNumber = sheet.getLastRowNum() + 1;

			Row row = sheet.createRow(rowNumber);

			row.setHeightInPoints(25);

			CellStyle dataStyle = createDataStyle(workbook);

			Cell cell0 = row.createCell(0);
			cell0.setCellValue(testCaseId);
			cell0.setCellStyle(dataStyle);

			Cell cell1 = row.createCell(1);
			cell1.setCellValue(firstName);
			cell1.setCellStyle(dataStyle);

			Cell cell2 = row.createCell(2);
			cell2.setCellValue(lastName);
			cell2.setCellStyle(dataStyle);

			Cell cell3 = row.createCell(3);
			cell3.setCellValue(postalCode);
			cell3.setCellStyle(dataStyle);

			Cell cell4 = row.createCell(4);
			cell4.setCellValue(result);
			cell4.setCellStyle(dataStyle);

			Cell cell5 = row.createCell(5);
			cell5.setCellValue(errorMessage);
			cell5.setCellStyle(dataStyle);

			Cell cell6 = row.createCell(6);
			cell6.setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			cell6.setCellStyle(dataStyle);

			Cell cell7 = row.createCell(7);
			cell7.setCellValue(BrowserContext.getBrowser());
			cell7.setCellStyle(dataStyle);

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to write Checkout Result.", e);

		}
	}

	/*
	 * 
	 * ====== Logout Method =====
	 * 
	 */
	public static synchronized void createLogoutHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Logout");

			if (sheet == null) {

				sheet = workbook.createSheet("Logout");

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.setHeightInPoints(25);

				CellStyle headerStyle = createHeaderStyle(workbook);

				String[] headers = { "Test Case ID", "Result", "Execution Time", "Browser" };

				for (int i = 0; i < headers.length; i++) {

					Cell cell = header.createCell(i);

					cell.setCellValue(headers[i]);

					cell.setCellStyle(headerStyle);
				}

				sheet.createFreezePane(0, 1);

				sheet.setColumnWidth(0, 20 * 256);
				sheet.setColumnWidth(1, 15 * 256);
				sheet.setColumnWidth(2, 25 * 256);
				sheet.setColumnWidth(3, 12 * 256);
			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to create Logout Header.", e);

		}
	}

	public static synchronized void clearLogoutSheet() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Logout");

			if (sheet == null) {

				sheet = workbook.createSheet("Logout");

			}

			int lastRow = sheet.getLastRowNum();

			for (int i = lastRow; i >= 1; i--) {

				Row row = sheet.getRow(i);

				if (row != null) {

					sheet.removeRow(row);

				}

			}

			saveWorkbook(workbook);

		} catch (Exception e) {

			throw new RuntimeException("Unable to clear Logout Sheet.", e);

		}

	}

	public static synchronized void writeLogoutResult(String testCaseId, String result) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Logout");

			if (sheet == null) {

				sheet = workbook.createSheet("Logout");

			}

			int rowNumber = sheet.getLastRowNum() + 1;

			Row row = sheet.createRow(rowNumber);

			row.setHeightInPoints(25);

			CellStyle dataStyle = createDataStyle(workbook);

			Cell cell0 = row.createCell(0);
			cell0.setCellValue(testCaseId);
			cell0.setCellStyle(dataStyle);

			Cell cell1 = row.createCell(1);
			cell1.setCellValue(result);
			cell1.setCellStyle(dataStyle);

			Cell cell2 = row.createCell(2);
			cell2.setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			cell2.setCellStyle(dataStyle);

			Cell cell3 = row.createCell(3);
			cell3.setCellValue(BrowserContext.getBrowser());
			cell3.setCellStyle(dataStyle);

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to write Logout Result.", e);

		}
	}

	/*
	 *
	 * ====== ERROR MESSAGE =====
	 *
	 */
	public static synchronized void createErrorHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("ErrorMessages");

			if (sheet == null) {

				sheet = workbook.createSheet("ErrorMessages");

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.setHeightInPoints(25);

				CellStyle headerStyle = createHeaderStyle(workbook);

				String[] headers = { "Test Case ID", "Module", "Expected Error", "Actual Error", "Result",
						"Execution Time", "Browser" };

				for (int i = 0; i < headers.length; i++) {

					Cell cell = header.createCell(i);

					cell.setCellValue(headers[i]);

					cell.setCellStyle(headerStyle);
				}

				sheet.createFreezePane(0, 1);

				sheet.setColumnWidth(0, 20 * 256); // Test Case ID
				sheet.setColumnWidth(1, 20 * 256); // Module
				sheet.setColumnWidth(2, 50 * 256); // Expected Error
				sheet.setColumnWidth(3, 50 * 256); // Actual Error
				sheet.setColumnWidth(4, 15 * 256); // Result
				sheet.setColumnWidth(5, 25 * 256); // Execution Time
				sheet.setColumnWidth(6, 12 * 256); // Browser
			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to create ErrorMessages Header.", e);

		}
	}

	public static synchronized void clearErrorSheet() {
		try {
			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("ErrorMessages");

			if (sheet == null) {
				sheet = workbook.createSheet("ErrorMessages");
			} else {
				int lastRow = sheet.getLastRowNum();

				for (int i = lastRow; i >= 0; i--) {
					Row row = sheet.getRow(i);
					if (row != null) {
						sheet.removeRow(row);
					}
				}
			}

			saveWorkbook(workbook);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static synchronized void writeErrorResult(String testCaseId, String module, String expectedError,
			String actualError, String result) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("ErrorMessages");

			if (sheet == null) {

				sheet = workbook.createSheet("ErrorMessages");

			}

			int rowNumber = sheet.getLastRowNum() + 1;

			Row row = sheet.createRow(rowNumber);

			row.setHeightInPoints(35);

			CellStyle dataStyle = createDataStyle(workbook);

			Cell cell0 = row.createCell(0);
			cell0.setCellValue(testCaseId);
			cell0.setCellStyle(dataStyle);

			Cell cell1 = row.createCell(1);
			cell1.setCellValue(module);
			cell1.setCellStyle(dataStyle);

			Cell cell2 = row.createCell(2);
			cell2.setCellValue(expectedError);
			cell2.setCellStyle(dataStyle);

			Cell cell3 = row.createCell(3);
			cell3.setCellValue(actualError);
			cell3.setCellStyle(dataStyle);

			Cell cell4 = row.createCell(4);
			cell4.setCellValue(result);
			cell4.setCellStyle(dataStyle);

			Cell cell5 = row.createCell(5);
			cell5.setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			cell5.setCellStyle(dataStyle);

			Cell cell6 = row.createCell(6);
			cell6.setCellValue(BrowserContext.getBrowser());
			cell6.setCellStyle(dataStyle);

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to write ErrorMessages Result.", e);

		}
	}

	/*
	 *
	 * ====== UI Validation =====
	 *
	 */

	public static synchronized void createUIValidationHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("UIValidation");

			if (sheet == null) {

				sheet = workbook.createSheet("UIValidation");

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.setHeightInPoints(25);

				CellStyle headerStyle = createHeaderStyle(workbook);

				String[] headers = { "Test Case ID", "UI Validation", "Result", "Execution Time", "Browser" };

				for (int i = 0; i < headers.length; i++) {

					Cell cell = header.createCell(i);

					cell.setCellValue(headers[i]);

					cell.setCellStyle(headerStyle);
				}

				sheet.createFreezePane(0, 1);

				sheet.setColumnWidth(0, 20 * 256);
				sheet.setColumnWidth(1, 40 * 256);
				sheet.setColumnWidth(2, 15 * 256);
				sheet.setColumnWidth(3, 25 * 256);
				sheet.setColumnWidth(4, 12 * 256);
			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to create UIValidation Header.", e);

		}
	}

	public static synchronized void clearUIValidationSheet() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("UIValidation");

			if (sheet == null) {
				sheet = workbook.createSheet("UIValidation");
			}

			int lastRow = sheet.getLastRowNum();

			for (int i = lastRow; i >= 1; i--) {

				Row row = sheet.getRow(i);

				if (row != null) {
					sheet.removeRow(row);
				}
			}

			saveWorkbook(workbook);

		} catch (Exception e) {

			throw new RuntimeException("Unable to clear UIValidation Sheet.", e);

		}
	}

	public static synchronized void writeUIValidationResult(String testCaseId, String validation, String result) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("UIValidation");

			if (sheet == null) {

				sheet = workbook.createSheet("UIValidation");

			}

			int rowNum = sheet.getLastRowNum() + 1;

			Row row = sheet.createRow(rowNum);

			row.setHeightInPoints(25);

			CellStyle dataStyle = createDataStyle(workbook);

			Cell cell0 = row.createCell(0);
			cell0.setCellValue(testCaseId);
			cell0.setCellStyle(dataStyle);

			Cell cell1 = row.createCell(1);
			cell1.setCellValue(validation);
			cell1.setCellStyle(dataStyle);

			Cell cell2 = row.createCell(2);
			cell2.setCellValue(result);
			cell2.setCellStyle(dataStyle);

			Cell cell3 = row.createCell(3);
			cell3.setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			cell3.setCellStyle(dataStyle);

			Cell cell4 = row.createCell(4);
			cell4.setCellValue(BrowserContext.getBrowser());
			cell4.setCellStyle(dataStyle);

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to write UIValidation Result.", e);

		}
	}

	// Session Management

	public static synchronized void clearSessionSheet() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("SessionManagement");

			if (sheet == null) {

				sheet = workbook.createSheet("SessionManagement");

			}

			int lastRow = sheet.getLastRowNum();

			for (int i = lastRow; i >= 1; i--) {

				Row row = sheet.getRow(i);

				if (row != null) {

					sheet.removeRow(row);

				}
			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to clear SessionManagement Sheet.", e);

		}
	}

	public static synchronized void createSessionHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("SessionManagement");

			if (sheet == null) {

				sheet = workbook.createSheet("SessionManagement");

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.setHeightInPoints(25);

				CellStyle headerStyle = createHeaderStyle(workbook);

				String[] headers = { "Test Case ID", "Scenario", "Result", "Execution Time", "Browser" };

				for (int i = 0; i < headers.length; i++) {

					Cell cell = header.createCell(i);

					cell.setCellValue(headers[i]);

					cell.setCellStyle(headerStyle);

				}

				sheet.createFreezePane(0, 1);

				sheet.setColumnWidth(0, 20 * 256);
				sheet.setColumnWidth(1, 40 * 256);
				sheet.setColumnWidth(2, 15 * 256);
				sheet.setColumnWidth(3, 25 * 256);
				sheet.setColumnWidth(4, 12 * 256);
			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to create SessionManagement Header.", e);

		}
	}

	public static synchronized void writeSessionResult(String testCaseId, String scenario, String result) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("SessionManagement");

			if (sheet == null) {

				sheet = workbook.createSheet("SessionManagement");

			}

			int rowNum = sheet.getLastRowNum() + 1;

			Row row = sheet.createRow(rowNum);

			row.setHeightInPoints(25);

			CellStyle dataStyle = createDataStyle(workbook);

			Cell cell0 = row.createCell(0);
			cell0.setCellValue(testCaseId);
			cell0.setCellStyle(dataStyle);

			Cell cell1 = row.createCell(1);
			cell1.setCellValue(scenario);
			cell1.setCellStyle(dataStyle);

			Cell cell2 = row.createCell(2);
			cell2.setCellValue(result);
			cell2.setCellStyle(dataStyle);

			Cell cell3 = row.createCell(3);
			cell3.setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
			cell3.setCellStyle(dataStyle);

			Cell cell4 = row.createCell(4);
			cell4.setCellValue(BrowserContext.getBrowser());
			cell4.setCellStyle(dataStyle);

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to write SessionManagement Result.", e);

		}
	}

	// Duration Comparison of Browser
	public static synchronized void clearPerformanceSheet() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("PerformanceComparison");

			if (sheet == null) {

				sheet = workbook.createSheet("PerformanceComparison");

			}

			int lastRow = sheet.getLastRowNum();

			for (int i = lastRow; i >= 1; i--) {

				Row row = sheet.getRow(i);

				if (row != null) {

					sheet.removeRow(row);

				}
			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to clear PerformanceComparison Sheet.", e);

		}
	}

	public static synchronized void createPerformanceHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("PerformanceComparison");

			if (sheet == null) {

				sheet = workbook.createSheet("PerformanceComparison");

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.setHeightInPoints(25);

				CellStyle headerStyle = createHeaderStyle(workbook);

				String[] headers = { "Test Case ID", "Chrome (ms)", "Edge (ms)", "Faster Browser", "Difference (ms)",
						"Winner %" };

				for (int i = 0; i < headers.length; i++) {

					Cell cell = header.createCell(i);

					cell.setCellValue(headers[i]);

					cell.setCellStyle(headerStyle);
				}

				sheet.createFreezePane(0, 1);

				sheet.setColumnWidth(0, 25 * 256);
				sheet.setColumnWidth(1, 18 * 256);
				sheet.setColumnWidth(2, 18 * 256);
				sheet.setColumnWidth(3, 20 * 256);
				sheet.setColumnWidth(4, 18 * 256);
				sheet.setColumnWidth(5, 15 * 256);
			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to create Performance Header.", e);

		}
	}

	public static synchronized void writePerformanceComparison(String testCaseId, long chromeTime, long edgeTime) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("PerformanceComparison");

			if (sheet == null) {

				sheet = workbook.createSheet("PerformanceComparison");
			}

			int rowNumber = sheet.getLastRowNum() + 1;

			Row row = sheet.createRow(rowNumber);

			row.setHeightInPoints(25);

			CellStyle dataStyle = createDataStyle(workbook);

			String faster;

			long difference;

			double winnerPercentage;

			if (chromeTime < edgeTime) {

				faster = "Chrome";

				difference = edgeTime - chromeTime;

				winnerPercentage = ((double) difference / edgeTime) * 100;
			}

			else {

				faster = "Edge";

				difference = chromeTime - edgeTime;

				winnerPercentage = ((double) difference / chromeTime) * 100;
			}

			Cell cell0 = row.createCell(0);
			cell0.setCellValue(testCaseId);
			cell0.setCellStyle(dataStyle);

			Cell cell1 = row.createCell(1);
			cell1.setCellValue(chromeTime);
			cell1.setCellStyle(dataStyle);

			Cell cell2 = row.createCell(2);
			cell2.setCellValue(edgeTime);
			cell2.setCellStyle(dataStyle);

			Cell cell3 = row.createCell(3);
			cell3.setCellValue(faster);
			cell3.setCellStyle(dataStyle);

			Cell cell4 = row.createCell(4);
			cell4.setCellValue(difference);
			cell4.setCellStyle(dataStyle);

			Cell cell5 = row.createCell(5);
			cell5.setCellValue(String.format("%.2f%%", winnerPercentage));
			cell5.setCellStyle(dataStyle);

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to write Performance Comparison.", e);

		}
	}

	public static synchronized String getTestCaseIdByScenarioName(String scenarioName) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Test Cases");

			if (sheet == null) {

				return scenarioName;
			}

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				Row row = sheet.getRow(i);

				if (row == null) {

					continue;
				}

				String testCaseId = row.getCell(1).getStringCellValue().trim();

				String testCaseName = row.getCell(2).getStringCellValue().trim();

				if (scenarioName.equalsIgnoreCase(testCaseName)) {

					workbook.close();

					return testCaseId;
				}
			}

			workbook.close();

			return scenarioName;

		}

		catch (Exception e) {

			return scenarioName;
		}
	}

}
