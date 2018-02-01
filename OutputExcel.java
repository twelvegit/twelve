package output;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class OutputExcel {


	private static XSSFWorkbook workbook;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 inputImage();
		 System.out.println("excelover");
	}

	
	// 图像信息获取
	@SuppressWarnings("deprecation")
	public static void inputImage() {
		BufferedImage bimg;

		try {
			bimg = ImageIO.read(new File("/Users/twelve/workspace/test.jpg"));

			int width = bimg.getWidth();
			int height = bimg.getHeight();
			int[][] data = new int[width][height];

			// 制作excel
			String filePath = "/Users/twelve/workspace/image.xlsx";
			
			workbook = new XSSFWorkbook();
			// 创建一个工作表sheet
			Sheet sheet = workbook.createSheet();
			OutputStream out;
			Row row = null;
			Cell cell = null;
			XSSFCellStyle style = workbook.createCellStyle();
			
			// 容纳Cell的列表,以坐标为下标
			ArrayList<Cell> xyCellList = new ArrayList<Cell>();

			// 容纳RGB和索引值的列表
			Map<Integer, Short> rgbIndexMap = new HashMap<Integer, Short>();

			// 索引去重map
			Map<Short, Integer> indexMap = new HashMap<Short, Integer>();

			// RGB去重map
			Map<Integer, Integer> rgbMap = new HashMap<Integer, Integer>();

			// 通过getRGB()沿Height方向扫描获取图片信息
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					data[i][j] = bimg.getRGB(i, j);
					rgbMap.put(data[i][j], 1);
				}
			}

			// 获取rgb和索引对应关系map
			for (Integer c : rgbMap.keySet()) {
				short tempMap = ColorMap.colorIndexSearch(c);
				rgbIndexMap.put(c, tempMap);
				// 索引值去重
				indexMap.put(tempMap, 1);
			}

			// 将颜色索引值填入cell
			for (int i = 0; i < height; i++) {
				row = sheet.createRow(i);
				for (int j = 0; j < width ; j++) {
					cell = row.createCell(j);
					cell.setCellValue(rgbIndexMap.get(data[j][i]));
					xyCellList.add(cell);
				}
			}

			// 为cell设置style
			for (Short s : indexMap.keySet()) {
				style = workbook.createCellStyle();
				style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
				style.setFillForegroundColor((short) s);
				for (Cell c : xyCellList) {
					int temp = (int) c.getNumericCellValue();
					if (temp == s) {
						c.setCellStyle(style);
					}
				}	
			}

			out = new FileOutputStream(filePath);
			workbook.write(out);
			// 关闭输出流
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
