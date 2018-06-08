/**
 * 
 */
package com.example.framework.core.common.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 类描述：解析excel工具类
 * 
 * @author: caisb
 * @version 2.0
 */
public class ResolveExcelUtil {
	/**
	 * 获取合并单元格的值
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if (row >= firstRow && row <= lastRow) {

				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell);
				}
			}
		}

		return null;
	}
	
	/**
	 * 获取合并单元格涉及的单元格数量
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static int getMergedCellCount(Sheet sheet, int row, int column) {
		int mergedCellCount = 0;
		int sheetMergeCount = sheet.getNumMergedRegions();

		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			mergedCellCount = ca.getNumberOfCells();
			if (row == firstRow && row == lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					mergedCellCount=ca.getNumberOfCells();
					break;
				}
			}
//			break;
		}

		return mergedCellCount;
	}

	/**
	 * 判断是否是合并行
	 * 
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	public static boolean isMergedRow(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row == firstRow && row == lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 * 
	 * @param sheet
	 * @param row
	 *            行下标
	 * @param column
	 *            列下标
	 * @return
	 */
	public static boolean isMergedRegion(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断sheet页中是否含有合并单元格
	 * 
	 * @param sheet
	 * @return
	 */
	public static boolean hasMerged(Sheet sheet) {
		return sheet.getNumMergedRegions() > 0 ? true : false;
	}

	/**
	 * 合并单元格
	 * 
	 * @param sheet
	 * @param firstRow
	 *            开始行
	 * @param lastRow
	 *            结束行
	 * @param firstCol
	 *            开始列
	 * @param lastCol
	 *            结束列
	 */
	public static void mergeRegion(Sheet sheet, int firstRow, int lastRow,
			int firstCol, int lastCol) {
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol,
				lastCol));
	}

	/**
	 * 获取单元格的值
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell){
	      
	    if(cell == null) return "";  
	      
	    if(cell.getCellType() == Cell.CELL_TYPE_STRING){  
	          
	        return cell.getStringCellValue().trim();  
	          
	    }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){  
	          
	        return String.valueOf(cell.getBooleanCellValue()).trim();
	          
	    }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){  
	          
	        return cell.getCellFormula().trim() ;  
	          
	    }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
	          
	        return String.valueOf(cell.getNumericCellValue()).trim();
	          
	    }  
	    return "";  
	}
}
