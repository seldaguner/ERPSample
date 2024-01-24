package com.erpsample.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;

public class CalculateERP {
	
	public void calculate(Order[] record) {
		
		try {
			
			BigDecimal orderTotal = BigDecimal.ZERO;
			BigDecimal total = BigDecimal.ZERO;
			BigDecimal totalAverage = BigDecimal.ZERO;
			BigDecimal averageAmount = BigDecimal.ZERO;
			
			int goodsNumber = 0;
			int orderNumber = 0;
			int totalNumberOfGoods = 0;
			
			LinkedHashMap<Integer, BigDecimal> goods = new LinkedHashMap<>();
			LinkedHashMap<Integer, Integer> goodsAmount = new LinkedHashMap<>();
			LinkedHashMap<Integer, BigDecimal> goodsAverage = new LinkedHashMap<>();
			LinkedHashMap<String, Integer> orderAmount = new LinkedHashMap<>();
			
			for(var i=0; i<record.length; i++) {
				
				Order s = record[i];
				//System.out.println("Sipariş No: " + s.siparisId + " Mal no: " + s.malNo + " Miktarı: " + s.miktar + " Birim fiyatı: " + s.birimFiyat);
				
				orderTotal = new BigDecimal(String.valueOf(s.birimFiyat)).multiply(new BigDecimal(String.valueOf(s.miktar)));
				int numberOfDecimals = Math.max(0, orderTotal.stripTrailingZeros().scale());
				if (numberOfDecimals > 0) {
					orderTotal = orderTotal.setScale(numberOfDecimals, RoundingMode.DOWN);
				}
				//System.out.println("Girilen sipariş için toplam tutar: " + orderTotal);
				
				total = total.add(orderTotal);
				
				goodsNumber = (int)s.miktar;
				
				if (goods.containsKey(s.malNo)) {
					BigDecimal totalExists = goods.get(s.malNo); 
					orderTotal = orderTotal.add(totalExists);	
					
					int amountExists = goodsAmount.get(s.malNo);
					goodsNumber = goodsNumber + amountExists ;
					
					BigDecimal averageExists = goodsAverage.get(s.malNo); 
					totalAverage = totalAverage.add(averageExists);	
				}
				
				goods.put(s.malNo, orderTotal);
				
				goodsAmount.put(s.malNo, goodsNumber);
				
				totalAverage = goods.get(s.malNo).divide(BigDecimal.valueOf(goodsAmount.get(s.malNo)), 3, RoundingMode.HALF_EVEN);
				goodsAverage.put(s.malNo, totalAverage);
				
				totalNumberOfGoods = totalNumberOfGoods + (int)s.miktar;
				
				orderNumber = (int)s.miktar;
				
				if (orderAmount.containsKey(s.siparisId + "-" + s.malNo)) {
					int orderAmountExists = orderAmount.get(s.siparisId + "-" + s.malNo);
					orderNumber = orderNumber + orderAmountExists ;
				}
				
				orderAmount.put(s.siparisId + "-" + s.malNo, + orderNumber);
			}
			
			averageAmount = total.divide(BigDecimal.valueOf((double)totalNumberOfGoods), 3, RoundingMode.HALF_EVEN);
			
		
			System.out.println("Tüm siparişler için toplam tutar: " + total);
			System.out.println("Ürün tutarları:  " + goods);
			System.out.println("Ürün miktarları:  " + goodsAmount);
			System.out.println("Ürün ortalama fiyatları:  " + goodsAverage);
			System.out.println("Tüm ürünler için ortalama fiyat:  " + averageAmount);
			System.out.println("Tüm ürün sayısı:  " + totalNumberOfGoods);
			System.out.println("Siparişe ait ürün sayıları:  " + orderAmount);
			
			System.out.println("-------------------------------------------");
			
			System.out.println("Answer a: " + total);
			System.out.println("Answer b: " + averageAmount);
			System.out.println("Answer c: " + goodsAverage);
			System.out.println("Answer d: " + orderAmount);
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		
	}

	public static void main(String[] args) {
		
		Order[] record = new Order[15];
		
		record[0] = new Order(1000, 2000, 12, 100.51);
		record[1] = new Order(1000, 2001, 31, 200);
		record[2] = new Order(1000, 2002, 22, 150.86);
		record[3] = new Order(1000, 2003, 41, 250);
		record[4] = new Order(1000, 2004, 55, 244);
		
		record[5] = new Order(1001, 2001, 88, 44.531);
		record[6] = new Order(1001, 2002, 121, 88.11);
		record[7] = new Order(1001, 2004, 74, 211);
		record[8] = new Order(1001, 2002, 14, 88.11);
		
		record[9] = new Order(1002, 2003, 2, 12.1);
		record[10] = new Order(1002, 2004, 3, 22.3);
		record[11] = new Order(1002, 2003, 8, 12.1);
		record[12] = new Order(1002, 2002, 16, 94);
		record[13] = new Order(1002, 2005, 9, 44.1);
		record[14] = new Order(1002, 2006, 19, 90);
		
		new CalculateERP().calculate(record);

	}

}
