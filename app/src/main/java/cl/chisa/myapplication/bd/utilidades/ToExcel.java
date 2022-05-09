package cl.chisa.myapplication.bd.utilidades;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import cl.chisa.myapplication.bd.clases.Excel;

public class ToExcel {

//    public static void writeExcel(String dir, String[] cabeceras, List<Object> data) throws Exception {
    public static void writeExcel(String dir, String[] cabeceras, Vector<Excel> data) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(0, "Hoja excel");

       // CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
      //  headerStyle.setFont(font);

       // CellStyle style = workbook.createCellStyle();
       // style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        //style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < cabeceras.length; ++i) {
            String header = cabeceras[i];
            HSSFCell cell = headerRow.createCell(i);
           // cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }

        for (int i = 0; i < data.size(); ++i) {
            HSSFRow dataRow = sheet.createRow(i + 1);

            Excel d = (Excel) data.get(i);

            String Fecha = (String) d.getFecha();
            String Rut = (String) d.getRut();
            String Docente = (String) d.getDocente();
            String Sede = (String) d.getSede();
            String Asignatura = (String) d.getAsignatura();
            String Inicio = (String) d.getInicio();
            String Termino = (String) d.getTermino();
            String Horas = (String) d.getHoras();

            dataRow.createCell(0).setCellValue(Fecha);
            dataRow.createCell(1).setCellValue(Rut);
            dataRow.createCell(2).setCellValue(Docente);
            dataRow.createCell(3).setCellValue(Sede);
            dataRow.createCell(4).setCellValue(Asignatura);
            dataRow.createCell(5).setCellValue(Inicio);
            dataRow.createCell(6).setCellValue(Termino);
            dataRow.createCell(7).setCellValue(Horas);
        }

        HSSFRow dataRow = sheet.createRow(1 + data.size());
        HSSFCell total = dataRow.createCell(1);
        total.setCellType(CellType.FORMULA);
        //total.setCellStyle(style);
        total.setCellFormula(String.format("SUM(B2:B%d)", 1 + data.size()));

        FileOutputStream file = new FileOutputStream( dir+"/data.xls");
        workbook.write(file);
        file.close();
    }
}