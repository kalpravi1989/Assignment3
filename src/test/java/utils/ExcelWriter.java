package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Base.BaseClass;

public class ExcelWriter extends BaseClass {

	public void writeDatatoExcel(List<String> data, String SheetName) {

		try {
			Workbook workbook = new XSSFWorkbook();
			FileOutputStream fileOut = new FileOutputStream(excelPath);

			Sheet sheet = workbook.createSheet(SheetName);

			for (int i = 0; i < data.size(); i++) {
				Row row = sheet.createRow(i);
				Cell cell = row.createCell(0);
				cell.setCellValue(data.get(i));
			}

			workbook.write(fileOut);

			// Print a success message
			System.out.println("Data written to Excel successfully!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
