package com.example.framework.core.common.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author youcb 
 * @date 2018-4-24 下午3:45:49
 * @version V1.0  
 */
public class QRCodeUtil {	
    private static final List<String> IMAGE_TYPE = new ArrayList<String>();  // 二维码图片格式
    static {  
        IMAGE_TYPE.add("jpg");  
        IMAGE_TYPE.add("png");  
    }  
	/**
	 * 生成二维码,path不能据对路径
	 * @param destUrl	目标地址
	 * @param path	二维码保存路径
	 * @return  
	 * @author youcb 
	 * @date 2018-4-24 下午4:59:00
	 * @version V1.0
	 */
	public static String createQRCode(String destUrl, String path){
		String sysPath=PathUtils.getSysPath()+path;
		File qrFile = new File(sysPath);
		try {
			if(!qrFile.exists()){
				String qrName = qrFile.getName();//文件名称
				String qrPath = sysPath.substring(0, sysPath.length()-qrName.length()-1);//文件路径
				File qrDir = new File(qrPath);
				if(!qrDir.exists() && !qrDir.isDirectory()){//生成二维码文件夹
					qrDir.mkdirs();
				}
				int width = 200; // 图像宽度  
		        int height = 200; // 图像高度  
		        String format = "png";// 图像类型
				Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
				// 编码
				hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
				// 纠错等级
				hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
				// 边框
				hints.put(EncodeHintType.MARGIN, 0);
				BitMatrix bitMatrix = new MultiFormatWriter().encode(destUrl,BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
				MatrixToImageWriter.writeToFile(bitMatrix, format, qrFile);
			}
			return path;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 生成二维码带logo,path不能据对路径
	 * @param destUrl 目标地址
	 * @param qrPath	二维码地址
	 * @param logoPath	 logo地址
	 * @return  
	 * @author youcb 
	 * @date 2018-4-26 上午9:54:00
	 * @version V1.0
	 */
	public static String createQRCodeOfLogo(String destUrl, String qrPath, String logoPath){
		File qrPic = new File(createQRCode(destUrl,qrPath));
		if(StringUtils.isBlank(logoPath)){logoPath="D:\\weix.png";}
        File logoPic = new File("D:\\weix.png");
        logoCreate(qrPic, logoPic,qrPath);
        return qrPath;
	}
	
	
    /**
     * 二维码添加logo
     * @param qrPic 二维码图片 
     * @param logoPic logo图片 
     * @param path 合成后的图片存储目录 
     * @return  
     * @author youcb 
     * @date 2018-4-26 上午10:00:29
     * @version V1.0
     */
     private static boolean logoCreate(File qrPic, File logoPic, String path) {
         try {  
             String imageType = path.substring(path.lastIndexOf(".") + 1).toLowerCase();
             if (!IMAGE_TYPE.contains(imageType)) {  
                 return false;  
             }  
             if (!qrPic.isFile() && !logoPic.isFile()) {  
                 return false;  
             }  
            //读取二维码图片，并构建绘图对象 
             BufferedImage image = ImageIO.read(qrPic);
             Graphics2D g = image.createGraphics();
               
            // 读取Logo图片
             BufferedImage logo = ImageIO.read(logoPic);
            //设置logo的大小,最多20%0 
             int widthLogo = logo.getWidth() > image.getWidth() * 2 / 10 ?(image.getWidth() * 2 / 10) : logo.getWidth();  
             int heightLogo = logo.getHeight() > image.getHeight() * 2 / 10 ?(image.getHeight() * 2 / 10) : logo.getHeight();  
                       
             // 计算图片放置位置，默认在中间  
             int x = (image.getWidth() - widthLogo) / 2;   
             int y = (image.getHeight() - heightLogo) / 2;  
               
             // 开始绘制图片  
             g.drawImage(logo, x, y, widthLogo, heightLogo,null );  
             g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);  
             //边框宽度  
             g.setStroke(new BasicStroke(2));
             //边框颜色  
             g.setColor(Color.WHITE);
             g.drawRect(x, y, widthLogo, heightLogo);  
               
             g.dispose();  
             logo.flush();  
             image.flush();  
               
             File newFile = new File(path);
             if (!newFile.exists()) {  
                 newFile.mkdirs();  
             }  
             ImageIO.write(image, imageType,newFile);
             return true;
         } catch (Exception e) {
             return false;  
         }  
     }  
     /**
      * 二维码解析
      * @param path
      * @return  
      * @author youcb 
      * @date 2018-4-26 上午9:59:49
      * @version V1.0
      */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Result qrCodeAnalyze(String path) {
         try {  
             MultiFormatReader formatReader = new MultiFormatReader();  
             File file = new File(path);
             if (file.exists()) {  
                 BufferedImage image = ImageIO.read(file);
                 LuminanceSource source = new BufferedImageLuminanceSource(image);  
                 Binarizer binarizer = new HybridBinarizer(source);  
                 BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
                 Map hints = new HashMap();
                 hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
                 Result result = formatReader.decode(binaryBitmap, hints);  
                 return result;  
             }  
         } catch (IOException e) {
             e.printStackTrace();  
         } catch (NotFoundException e) {  
             e.printStackTrace();  
         }  
         return null;  
     }  
	
	public static void main(String[] args) {
		createQRCode("https://passport.wehgc.com/reg/userReg/turnReg.htm?service=http%3A%2F%2Fsupplier.wehgc.com%2Fsys%2Flogin.do", "demo/codew.png");
		//createQRCodeOfLogo("http://www.wehgc.com/mall/", "D://hgc/codeLogo.png",null);
	}
}

