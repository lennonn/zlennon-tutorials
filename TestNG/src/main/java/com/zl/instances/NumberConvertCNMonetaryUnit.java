package com.zl.instances;

import java.math.BigDecimal;

public class NumberConvertCNMonetaryUnit {
	// 数字大写常量
	private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆",
			"伍", "陆", "柒", "捌", "玖" };
	// 汉语中货币单位大写常量
	private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元",
			"拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
			"佰", "仟" };
	// 特殊字符整，负
	private static final String CN_FULL = "整";
	private static final String CN_NEGATIVE = "负";
	private static final String CN_ZEOR_FULL = "零元" + CN_FULL;
	// 默认精度，2
	private static final int MONEY_PRECISION = 2;

	public String convertNumber(BigDecimal moneny) {
		StringBuffer sb = new StringBuffer();
		int numMoneny = moneny.signum();
		if (numMoneny == 0) {
			return CN_ZEOR_FULL;
		}

		// 四舍五入
		long number = moneny.movePointRight(MONEY_PRECISION).setScale(0, 4)
				.abs().longValue();
		long scale = number % 100;
		int numUnit = 0;
		int numIndex = 0;
		boolean getZero = false;
		// 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
		if (!(scale > 0)) {
			numIndex = 2;
			number = number / 100;
			getZero = true;
		}
		if ((scale > 0) && (!(scale % 10 > 0))) {
			numIndex = 1;
			number = number / 10;
			getZero = true;
		}
		int zeroSize = 0;
		while (true) {
			if (number <= 0) {
				break;
			}
			// 每次获取到最后一个数
			numUnit = (int) (number % 10);
			if (numUnit > 0) {
				if ((numIndex == 9) && (zeroSize >= 3)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
				}
				if ((numIndex == 13) && (zeroSize >= 3)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
				}
				sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
				sb.insert(0, CN_UPPER_NUMBER[numUnit]);
				getZero = false;
				zeroSize = 0;
			} else {
				++zeroSize;
				if (!(getZero)) {
					sb.insert(0, CN_UPPER_NUMBER[numUnit]);
				}
				if (numIndex == 2) {
					if (number > 0) {
						sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
					}
				} else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
				}
				getZero = true;
			}
			// 让number每次都去掉最后一个数
			number = number / 10;
			++numIndex;
		}
		// 如果numMoneny == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
		if (numMoneny == -1) {
			sb.insert(0, CN_NEGATIVE);
		}
		// 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
		if (!(scale > 0)) {
			sb.append(CN_FULL);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		NumberConvertCNMonetaryUnit numberConvertCNMonetaryUnit = new NumberConvertCNMonetaryUnit();
		System.out.println(numberConvertCNMonetaryUnit
				.convertNumber(new BigDecimal(1688.9999)));
	}
}
