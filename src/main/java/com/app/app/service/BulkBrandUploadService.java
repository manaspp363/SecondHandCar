package com.app.app.service;

import com.app.app.entity.Brand;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BulkBrandUploadService {

    public List<Brand> carNameUpload() {
        File file = new File("/Users/manaspeeyushpandey/Downloads/car_brands.xlsx"); // Specify the exact file
        List<Brand> brandList = new ArrayList<>();

        if (!file.exists()) {
            System.err.println("Error: File not found at " + file.getAbsolutePath());
            return brandList; // Return empty list
        }

        try (InputStream inputStream = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Read first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() == 0) continue; // Skip header row

                Cell idCell = row.getCell(0);
                Cell nameCell = row.getCell(1);

                if (idCell == null || nameCell == null) continue; // Skip if any cell is missing

                try {
                    int brandId = (int) idCell.getNumericCellValue(); // ID is numeric
                    String brandName = nameCell.getStringCellValue().trim();

                    if (!brandName.isEmpty()) {
                        Brand brand = new Brand();
                        brand.setId((long) brandId);
                        brand.setName(brandName);
                        brandList.add(brand);
                    }
                } catch (Exception e) {
                    System.err.println("Skipping row " + row.getRowNum() + " due to error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return brandList;
    }
}
