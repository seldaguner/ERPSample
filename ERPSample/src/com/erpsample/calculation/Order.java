package com.erpsample.calculation;

public class Order {
	
public int siparisId;
	
    public int malNo;
    
    public double miktar;
    
    public double birimFiyat;
    
    Order(int siparisId, int malNo, double miktar, double birimFiyat) {
        this.siparisId = siparisId;
        this.malNo = malNo;
        this.miktar = miktar;
        this.birimFiyat = birimFiyat;
    }

}
