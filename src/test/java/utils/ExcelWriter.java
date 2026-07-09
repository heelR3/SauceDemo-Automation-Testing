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

		try (FileInputStream fis = new FileInputStream(FILE_PATH)) {

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

	public static void createCartHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Cart");

			if (sheet == null) {

				sheet = workbook.createSheet("Cart");

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.createCell(0).setCellValue("Product Name");
				header.createCell(1).setCellValue("Price");
				header.createCell(2).setCellValue("Action");
				header.createCell(3).setCellValue("Quantity");
				header.createCell(4).setCellValue("Execution Time");
				header.createCell(5).setCellValue("Browser");

			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to create Cart Header.", e);

		}

	}

	public static void clearCartSheet() {

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

	public static void writeCartAction(Product product, String action) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Cart");

			if (sheet == null) {

				sheet = workbook.createSheet("Cart");

			}

			int rowNumber = sheet.getLastRowNum() + 1;

			Row row = sheet.createRow(rowNumber);

			row.createCell(0).setCellValue(product.getName());

			row.createCell(1).setCellValue(product.getPrice());

			row.createCell(2).setCellValue(action);

			row.createCell(3).setCellValue(1);

			row.createCell(4)
					.setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

			row.createCell(5).setCellValue(ConfigReader.getProperty("browser"));

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

	public static void createCheckoutHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Checkout");

			if (sheet == null) {

				sheet = workbook.createSheet("Checkout");

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.createCell(0).setCellValue("Test Case ID");

				header.createCell(1).setCellValue("First Name");

				header.createCell(2).setCellValue("Last Name");

				header.createCell(3).setCellValue("Postal Code");

				header.createCell(4).setCellValue("Result");

				header.createCell(5).setCellValue("Error Message");

				header.createCell(6).setCellValue("Execution Time");

				header.createCell(7).setCellValue("Browser");

			}

			saveWorkbook(workbook);

		}

		catch (Exception e) {

			throw new RuntimeException("Unable to create Checkout Header.", e);

		}

	}

	public static void clearCheckoutSheet() {

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

	public static void writeCheckoutResult(String testCaseId, String firstName, String lastName, String postalCode,

			String result, String errorMessage) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Checkout");

			if (sheet == null) {

				sheet = workbook.createSheet("Checkout");

			}

			int rowNumber = sheet.getLastRowNum() + 1;

			Row row = sheet.createRow(rowNumber);

			row.createCell(0).setCellValue(testCaseId);

			row.createCell(1).setCellValue(firstName);

			row.createCell(2).setCellValue(lastName);

			row.createCell(3).setCellValue(postalCode);

			row.createCell(4).setCellValue(result);

			row.createCell(5).setCellValue(errorMessage);

			row.createCell(6)

					.setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

			row.createCell(7).setCellValue(ConfigReader.getProperty("browser"));

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
	public static void createLogoutHeader() {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Logout");

			if (sheet == null) {

				sheet = workbook.createSheet("Logout");

			}

			if (sheet.getRow(0) == null) {

				Row header = sheet.createRow(0);

				header.createCell(0).setCellValue("Test Case ID");
				header.createCell(1).setCellValue("Result");
				header.createCell(2).setCellValue("Execution Time");
				header.createCell(3).setCellValue("Browser");

			}

			saveWorkbook(workbook);

		} catch (Exception e) {

			throw new RuntimeException("Unable to create Logout Header.", e);

		}

	}

	public static void clearLogoutSheet() {

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

	public static void writeLogoutResult(String testCaseId, String result) {

		try {

			XSSFWorkbook workbook = openWorkbook();

			Sheet sheet = workbook.getSheet("Logout");

			if (sheet == null) {

				sheet = workbook.createSheet("Logout");

			}

			int rowNumber = sheet.getLastRowNum() + 1;

			Row row = sheet.createRow(rowNumber);

			row.createCell(0).setCellValue(testCaseId);
			row.createCell(1).setCellValue(result);

			row.createCell(2)
					.setCellValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

			row.createCell(3).setCellValue(ConfigReader.getProperty("browser"));

			saveWorkbook(workbook);

		} catch (Exception e) {

			throw new RuntimeException("Unable to write Logout Result.", e);

		}

	}
	
	/*
	 *
	 * ====== ERROR MESSAGE =====
	 *
	 */
	public static void createErrorHeader() {
		
	    try {
	
	        XSSFWorkbook workbook = openWorkbook();
	
	        Sheet sheet = workbook.getSheet("ErrorMessages");
	
	        if (sheet == null) {
	
	            sheet = workbook.createSheet("ErrorMessages");
	
	        }
	
	        if (sheet.getRow(0) == null) {
	
	            Row header = sheet.createRow(0);
	
	            header.createCell(0).setCellValue("Test Case ID");
	            header.createCell(1).setCellValue("Module");
	            header.createCell(2).setCellValue("Expected Error");
	            header.createCell(3).setCellValue("Actual Error");
	            header.createCell(4).setCellValue("Result");
	            header.createCell(5).setCellValue("Execution Time");
	            header.createCell(6).setCellValue("Browser");
	
	        }
	
	        saveWorkbook(workbook);
	
	    }
	
	    catch (Exception e) {
	
	        throw new RuntimeException(
	                "Unable to create ErrorMessages Header.",
	                e);
	
	    }
	
	}
	
	public static void clearErrorSheet() {
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
	
	public static void writeErrorResult(
	        String testCaseId,
	        String module,
	        String expectedError,
	        String actualError,
	        String result) {
	
	    try {
	
	        XSSFWorkbook workbook = openWorkbook();
	
	        Sheet sheet = workbook.getSheet("ErrorMessages");
	
	        if (sheet == null) {
	
	            sheet = workbook.createSheet("ErrorMessages");
	
	        }
	
	        int rowNumber = sheet.getLastRowNum() + 1;
	
	        Row row = sheet.createRow(rowNumber);
	
	        row.createCell(0).setCellValue(testCaseId);
	        row.createCell(1).setCellValue(module);
	        row.createCell(2).setCellValue(expectedError);
	        row.createCell(3).setCellValue(actualError);
	        row.createCell(4).setCellValue(result);
	
	        row.createCell(5).setCellValue(
	                LocalDateTime.now().format(
	                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
	
	        row.createCell(6).setCellValue(
	                ConfigReader.getProperty("browser"));
	
	        saveWorkbook(workbook);
	
	    }
	
	    catch (Exception e) {
	
	        throw new RuntimeException(
	                "Unable to write ErrorMessages Result.",
	                e);
	
	    }
	
	}
	public static void createUIValidationHeader() {
		 
	    try {
	 
	        XSSFWorkbook workbook = openWorkbook();
	 
	        Sheet sheet = workbook.getSheet("UIValidation");
	 
	        if (sheet == null) {
	            sheet = workbook.createSheet("UIValidation");
	        }
	 
	        if (sheet.getRow(0) == null) {
	 
	            Row header = sheet.createRow(0);
	 
	            header.createCell(0).setCellValue("Test Case ID");
	            header.createCell(1).setCellValue("UI Validation");
	            header.createCell(2).setCellValue("Result");
	            header.createCell(3).setCellValue("Execution Time");
	            header.createCell(4).setCellValue("Browser");
	        }
	 
	        saveWorkbook(workbook);
	 
	    } catch (Exception e) {
	 
	        throw new RuntimeException("Unable to create UIValidation Header.", e);
	 
	    }
	}
	/*
	 *
	 * ====== UI Validation =====
	 *
	 */
	public static void clearUIValidationSheet() {
		 
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
	
	public static void writeUIValidationResult(String testCaseId,
            String validation,
            String result) {

try {

		XSSFWorkbook workbook = openWorkbook();
		
		Sheet sheet = workbook.getSheet("UIValidation");
		
		if (sheet == null) {
		sheet = workbook.createSheet("UIValidation");
		}
		
		int rowNum = sheet.getLastRowNum() + 1;
		
		Row row = sheet.createRow(rowNum);
		
		row.createCell(0).setCellValue(testCaseId);
		row.createCell(1).setCellValue(validation);
		row.createCell(2).setCellValue(result);
		
		row.createCell(3).setCellValue(
		LocalDateTime.now().format(
		DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
		
		row.createCell(4).setCellValue(
		ConfigReader.getProperty("browser"));
		
		saveWorkbook(workbook);
		
		} catch (Exception e) {
		
		throw new RuntimeException("Unable to write UIValidation Result.", e);
		
		}
		}
				

}
