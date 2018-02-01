package output;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OutputImage {

	public static void createImage(int[][] data, File outFile,    
            Integer width, Integer height) throws Exception {    
        // 创建图片    
        BufferedImage image = new BufferedImage(width, height,    
                BufferedImage.TYPE_INT_BGR);    
        
        for(int i = 0; i < data.length; i++) {
        	for(int j = 0; j < data[i].length ;j++) {
        	//	int temp = data[i][j];
        	//	System.out.print(temp);
        		image.setRGB(i, j, data[i][j]);
        		}
        	  }
       
        ImageIO.write(image, "png", outFile);
        // 输出png图片    
    }    
//	
//	public static void main(String[] args) throws Exception {    
//		BufferedImage bimg = ImageIO.read(new File("/Users/twelve/workspace/test.jpg"));
//        int width = bimg.getWidth();
//        int height = bimg.getHeight();
//        int [][] data = new int[width][height];
//        //方式一：通过getRGB()方式获得像素矩阵
//        //此方式为沿Height方向扫描
//        //System.out.println(width +"+"+ height);
//        
//		for (int i = 0; i < bimg.getWidth(); i++) {
//			for (int j = 0; j < bimg.getHeight(); j++) {
//				data[i][j] = bimg.getRGB(i, j) + 16777216;
//			}
//
//		}
//		createImage(data, new File("/Users/twelve/workspace/test.png"), 766, 1026);
//	}
	

	public static void writeExcelbyPoi() {

		String filePath = "/Users/twelve/workspace/style.xlsx";
		// 创建列表
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 创建一个工作表sheet
		Sheet sheet = workbook.createSheet();
		Row row = null;
		Cell cell = null;

		for (int i = 0; i < 2000; i++) {
			row = sheet.createRow(i);
			for (int j = 0; j < 2000; j++) {
				cell = row.createCell(j);
				cell.setCellValue(0);
			}
			// 创建一个文件
			OutputStream out;
			try {
				out = new FileOutputStream(filePath);
				workbook.write(out);
				// 关闭输出流
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}


