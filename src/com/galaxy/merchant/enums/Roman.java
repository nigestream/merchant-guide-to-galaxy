package com.galaxy.merchant.enums;

public enum Roman {

    I(1, 'I'), V(5, 'V'), X(10, 'X'), L(50, 'L'), C(100, 'C'), D(500, 'D'), M(1000, 'M');

    private int arabic;
    private Character roman;

    Roman(int index, Character roman) {
        this.arabic = index;
        this.roman = roman;
    }

    public int getArabic() {
        return arabic;
    }

    public Character getRoman() {
        return this.roman;
    }

    public static int getArabic(Character roman) {
        for (Roman re : Roman.values()) {
            if (re.getRoman() == roman) {
                return re.getArabic();
            }
        }
        return 0;
    }
}

