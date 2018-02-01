package output;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class ColorMap {

	// poi内置颜色的RGB值
	static int[] c1 = { 0, 0, 0 };
	static int[] c2 = { 255, 255, 255 };
	static int[] c3 = { 255, 0, 0 };
	static int[] c4 = { 0, 255, 25 };
	static int[] c5 = { 81, 0, 255 };
	static int[] c6 = { 243, 255, 0 };
	static int[] c7 = { 255, 0, 247 };
	static int[] c8 = { 0, 255, 255 };
	static int[] c9 = { 149, 0, 0 };
	static int[] c10 = { 0, 108, 20 };
	static int[] c11 = { 31, 0, 116 };
	static int[] c12 = { 157, 115, 44 };
	static int[] c13 = { 153, 0, 103 };
	static int[] c14 = { 0, 131, 105 };
	static int[] c15 = { 202, 195, 105 };
	static int[] c16 = { 136, 127, 104 };
	static int[] c17 = { 98, 164, 255 };
	static int[] c18 = { 239, 0, 10 };
	static int[] c19 = { 249, 249, 108 };
	static int[] c20 = { 139, 0, 255 };
	static int[] c21 = { 255, 164, 39 };
	static int[] c22 = { 151, 82, 71 };
	static int[] c23 = { 160, 197, 115 };
	static int[] c24 = { 0, 208, 255 };
	static int[] c25 = { 199, 255, 255 };
	static int[] c26 = { 195, 255, 168 };
	static int[] c27 = { 246, 255, 118 };
	static int[] c28 = { 151, 205, 255 };
	static int[] c29 = { 255, 136, 164 };
	static int[] c30 = { 224, 134, 255 };
	static int[] c31 = { 255, 206, 117 };
	static int[] c32 = { 82, 79, 255 };
	static int[] c33 = { 0, 212, 117 };
	static int[] c34 = { 136, 216, 0 };
	static int[] c35 = { 255, 210, 0 };
	static int[] c36 = { 255, 148, 0 };
	static int[] c37 = { 255, 87, 0 };
	static int[] c38 = { 115, 100, 122 };
	static int[] c39 = { 159, 149, 119 };
	static int[] c40 = { 0, 51, 85 };
	static int[] c41 = { 0, 160, 86 };
	static int[] c42 = { 0, 59, 4 };
	static int[] c43 = { 56, 58, 0 };
	static int[] c44 = { 177, 40, 0 };
	static int[] c45 = { 178, 17, 81 };
	static int[] c46 = { 75, 28, 122 };
	static int[] c47 = { 60, 56, 44 };

	//poi的颜色RGB值
	static List<int[]> rgbList = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16,
			c17, c18, c19, c20, c21, c22, c23, c24, c25, c26, c27, c28, c29, c30, c31, c32, c33, c34, c35, c36, c37,
			c38, c39, c40, c41, c42, c43, c44, c45, c46, c47);
		
	// 通过RGB值获得poi颜色索引号
	public static short colorIndexSearch(int rgb) {
		
		Color mycolor = new Color(rgb);
		int r = mycolor.getRed();
		int g = mycolor.getGreen();
		int b = mycolor.getBlue();
		int colorIndex = 0;
		int temp = 99999;
		// 比较rgb值的平方根的最小值 获得poi单元格索引值
		for(int i = 0 ; i < rgbList.size(); i++) {
	    
		 int delR = rgbList.get(i)[0] - r;
		 int delG = rgbList.get(i)[1] - g;
		 int delB = rgbList.get(i)[2] - b;
		 int del = (int) Math.sqrt(delR*delR + delG*delG + delB*delB);
		 if(del == 0) {
			 colorIndex = i;
			 break;
		 }
		 if(del < temp) {
			 temp = del;
			 colorIndex = i;
		 }
		}
		// poi单元格索引值的计算
		// 索引值为8～63 除去27，32～39
		if (colorIndex >= 19 && colorIndex < 23) {
			colorIndex = colorIndex + 9;
		} else if (colorIndex >= 23) {
			colorIndex = colorIndex + 17;
		} else {
			colorIndex = colorIndex + 8;
		}
		return (short)colorIndex;
	}
}
