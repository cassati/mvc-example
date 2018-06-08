/**
 * 
 */
package com.example.framework.core.common.utils;

import com.example.framework.web.config.BaseConfig;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.Region;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类描述：POI导出工具类
 * @author: chenfm
 * @version 2.0
 * @date： 日期：2015-9-23 时间：上午10:18:08
 */
public class POIExcelUtil {
	/**
	 * @author: chenfm
	 * @version 2.0
	 * @date： 日期：2015-9-23 时间：上午10:31:52
	 * @param response
	 * @param title_names 标题名称  如title_names.add(列名;宽度(0为自适应);变量名;数据类型;位置：1左 2中 3右)
	 * @param listData	  数据集
	 * @param path       服务器地址
	 * @param excel_name excel(sleet)名称 标题名称(包含title_no表示不要标题)
	 * @throws IOException
	 */
	@SuppressWarnings({ "static-access", "deprecation" })
	public static void exportExcel(HttpServletResponse response, List<String> title_names, List<Map<String, Object>> listData, String path, String excel_name) throws IOException {
		Integer col_num = title_names.size();
		int count = 0;
		String title = excel_name.contains("title_no")?excel_name.replace("title_no", ""):excel_name;
		/** 导出excel表格 */
		HSSFWorkbook wb = new HSSFWorkbook(); 			// 创建excel工作簿对象
		HSSFSheet sheet = wb.createSheet(title); 	// 创建excel工作表对象
		sheet.setDisplayGridlines(false);
		//样式设置
		HSSFCellStyle style = CreateStyle(wb);
		//字体设置
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short)16);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		HSSFRow row;HSSFCell cell;
		if(!excel_name.contains("title_no")){
			//第一行
			row = sheet.createRow((short)count);count++;
			for (int i = 0; i < col_num; i++) {
				cell = row.createCell((short)i);
				cell.setCellStyle(style);
			}
			sheet.addMergedRegion(new Region(0,(short)0,0,(short)(col_num-1)));
			row.setHeight((short)(2*265));
			cell = row.createCell((short)0);
			cell.setCellValue(title);
			cell.setCellStyle(style);
			cell.getCellStyle().setFont(font);
			// 插入图片
	        BufferedImage bufferImg = null;
	        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
	        bufferImg = ImageIO.read(new File(path+"static/images/welcom_logo.png"));
	        ImageIO.write(bufferImg, "png", byteArrayOut);
	        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();     
	        HSSFClientAnchor anchor = new HSSFClientAnchor(50,50,1000,155,(short) 0,0,(short)0,0); 
	        anchor.setAnchorType(3); 
	        //插入图片    
	        patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
		}
		//样式设置
		HSSFCellStyle style_cell = CreateStyle(wb);
		//字体设置
		HSSFFont font_title = wb.createFont();
		font_title.setBoldweight(font.BOLDWEIGHT_BOLD);
		style_cell.setFont(font_title);
		List<Integer> wids = new ArrayList<Integer>();
		// 标题行
		row = sheet.createRow((short)count);count++;
		for (int i = 0; i < col_num; i++) {
			cell = row.createCell((short)i);
			String value = "";
			value = title_names.get(i).split(";")[0];
//			int with = Integer.valueOf(title_names.get(i).split(";")[1]);
			cell.setCellValue(new HSSFRichTextString(value));
			if(i == 0){
				sheet.setColumnWidth((short)i, (short)(19*300));
			}else{
				sheet.setColumnWidth((short)i, (short)("列名宽度".getBytes().length*300));
			}
			wids.add(i,"列名宽度".getBytes().length);
			cell.setCellStyle(style_cell);
		}
		
		//样式设置
		HSSFCellStyle data_style = CreateStyle(wb);
		HSSFCellStyle align_style = CreateStyle(wb);
		// 数据行
	    for (Map<String, Object> o : listData) {
	    	row = sheet.createRow(count);
	    	for (int i = 0; i < col_num; i++) {
				cell = row.createCell((short)i);
				String[] ss = title_names.get(i).split(";");
				if(ss.length>2){
					Object va = null;
					if(ss[2].contains(".")){
						@SuppressWarnings("unchecked")
                        Map<String, Object> os = (Map<String, Object>) o.get(ss[2].substring(0,ss[2].indexOf(".")));
						va = os.get(ss[2].substring(ss[2].indexOf(".")+1));
					}else{
						va = o.get(ss[2]);
					}
					if(ss.length>3 && ss[3].toString().equals("double") && ValidateUtil.isMoney((va+"").replace("-", ""))){
						cell.setCellValue(Double.valueOf(va+""));
					}else{
						cell.setCellValue(va==null?"":va.toString());
					}
					if(Integer.valueOf(ss[1])==0 && i != 0 && va != null && va.toString().getBytes().length>wids.get(i)){
						Integer le = va.toString().getBytes().length;
						wids.add(i,le);
						//2017-08-18 zhoull 修改表格样式，满格换行，最大拉伸格子150
						boolean flag = wids.get(i).intValue()+3 > 150?true:false;
						if(flag){//满格换行
							align_style.setWrapText(true);
							data_style.setWrapText(true);
						}
						sheet.setColumnWidth((short)i, (short)((wids.get(i).intValue()+3 > 150?150:wids.get(i).intValue()+3)*256));
					}
					if(Integer.valueOf(ss[1])>0){
						sheet.setColumnWidth((short)i, (short)(int)(Integer.valueOf(ss[1])*10));
					}
					if(ss.length>4 && (ss[4].equals("1") || ss[4].equals("3"))){
						align_style.setAlignment(ss[4].equals("1")?HSSFCellStyle.ALIGN_LEFT:HSSFCellStyle.ALIGN_RIGHT);
						cell.setCellStyle(align_style);
					}else{
						cell.setCellStyle(data_style);
					}
				}
			}
		    count ++;
		}
	    
	    response.setContentType("application/msexcel");
	    response.setHeader("Content-disposition","inline;Filename="+toUtf8String(title+".xls"));
	    OutputStream os;
	    
		os = response.getOutputStream();
		// 写入excel文件
		wb.write(os);
		os.flush();
		os.close();
	}

	public static void exportExcelDownloadTask(List<String> title_names, List<Map<String, Object>> listData, String fileName, String excel_name) throws IOException {
		Integer col_num = title_names.size();
		int count = 0;
		String title = excel_name.contains("title_no")?excel_name.replace("title_no", ""):excel_name;
		/** 导出excel表格 */
		HSSFWorkbook wb = new HSSFWorkbook(); 			// 创建excel工作簿对象
		HSSFSheet sheet = wb.createSheet(title); 	// 创建excel工作表对象
		sheet.setDisplayGridlines(false);
		//样式设置
		HSSFCellStyle style = CreateStyle(wb);
		//字体设置
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short)16);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		HSSFRow row;HSSFCell cell;
		if(!excel_name.contains("title_no")){
			//第一行
			row = sheet.createRow((short)count);count++;
			for (int i = 0; i < col_num; i++) {
				cell = row.createCell((short)i);
				cell.setCellStyle(style);
			}
			sheet.addMergedRegion(new Region(0,(short)0,0,(short)(col_num-1)));
			row.setHeight((short)(2*265));
			cell = row.createCell((short)0);
			cell.setCellValue(title);
			cell.setCellStyle(style);
			cell.getCellStyle().setFont(font);
			// 插入图片
			String welcomeLogoPath = BaseConfig.getRealPath() + "static/images/welcom_logo.png";
			BufferedImage bufferImg = null;
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
	        bufferImg = ImageIO.read(new File(welcomeLogoPath));
	        ImageIO.write(bufferImg, "png", byteArrayOut);
	        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
	        HSSFClientAnchor anchor = new HSSFClientAnchor(50,50,1000,155,(short) 0,0,(short)0,0);
	        anchor.setAnchorType(3);
	        //插入图片
	        patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
		}
		//样式设置
		HSSFCellStyle style_cell = CreateStyle(wb);
		//字体设置
		HSSFFont font_title = wb.createFont();
		font_title.setBoldweight(font.BOLDWEIGHT_BOLD);
		style_cell.setFont(font_title);
		List<Integer> wids = new ArrayList<Integer>();
		// 标题行
		row = sheet.createRow((short)count);count++;
		for (int i = 0; i < col_num; i++) {
			cell = row.createCell((short)i);
			String value = "";
			value = title_names.get(i).split(";")[0];
//			int with = Integer.valueOf(title_names.get(i).split(";")[1]);
			cell.setCellValue(new HSSFRichTextString(value));
			if(i == 0){
				sheet.setColumnWidth((short)i, (short)(19*300));
			}else{
				sheet.setColumnWidth((short)i, (short)("列名宽度".getBytes().length*300));
			}
			wids.add(i,"列名宽度".getBytes().length);
			cell.setCellStyle(style_cell);
		}

		//样式设置
		HSSFCellStyle data_style = CreateStyle(wb);
		HSSFCellStyle align_style = CreateStyle(wb);
		// 数据行
	    for (Map<String, Object> o : listData) {
	    	row = sheet.createRow(count);
	    	for (int i = 0; i < col_num; i++) {
				cell = row.createCell((short)i);
				String[] ss = title_names.get(i).split(";");
				if(ss.length>2){
					Object va = null;
					if(ss[2].contains(".")){
						@SuppressWarnings("unchecked")
                        Map<String, Object> os = (Map<String, Object>) o.get(ss[2].substring(0,ss[2].indexOf(".")));
						va = os.get(ss[2].substring(ss[2].indexOf(".")+1));
					}else{
						va = o.get(ss[2]);
					}
					if(ss.length>3 && ss[3].toString().equals("double") && ValidateUtil.isMoney((va+"").replace("-", ""))){
						cell.setCellValue(Double.valueOf(va+""));
					}else{
						cell.setCellValue(va==null?"":va.toString());
					}
					if(Integer.valueOf(ss[1])==0 && i != 0 && va != null && va.toString().getBytes().length>wids.get(i)){
						Integer le = va.toString().getBytes().length;
						wids.add(i,le);
						sheet.setColumnWidth((short)i, (short)(wids.get(i).intValue()*300));
					}
					if(Integer.valueOf(ss[1])>0){
						sheet.setColumnWidth((short)i, (short)(int)(Integer.valueOf(ss[1])*10));
					}
					if(ss.length>4 && (ss[4].equals("1") || ss[4].equals("3"))){
						align_style.setAlignment(ss[4].equals("1")?HSSFCellStyle.ALIGN_LEFT:HSSFCellStyle.ALIGN_RIGHT);
						cell.setCellStyle(align_style);
					}else{
						cell.setCellStyle(data_style);
					}
				}
			}
		    count ++;
		}

		File file = new File(fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
		wb.write(os);
		os.flush();
		os.close();
	}

	/**创建新的HSSFCellStyle
	 * @author: chenfm
	 * @version 2.0
	 * @date： 日期：2015-9-23 时间：下午1:35:48
	 * @param wb
	 * @return
	 */
	public static HSSFCellStyle CreateStyle(HSSFWorkbook wb) {
		HSSFCellStyle style_cell = wb.createCellStyle();
		style_cell.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style_cell.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style_cell.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style_cell.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style_cell.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style_cell.setBorderTop(HSSFCellStyle.BORDER_THIN);
//		style_cell.setWrapText(true);
		return style_cell;
	}
	
	/**标题中文乱码处理
	 * @author: chenfm
	 * @version 2.0
	 * @date： 日期：2015-9-23 时间：上午10:49:07
	 * @param s
	 * @return
	 */
	public static String toUtf8String(String s){
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if (c >= 0 && c <= 255){
				sb.append(c);
			}else{
				byte[] b;
				try { 
					b = Character.toString(c).getBytes("utf-8");
				}catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0) k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}
