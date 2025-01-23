package dev.lumen.enums;

public enum Month {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    private final int monthNumber;

    Month(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public static Month fromNumber(int monthNumber) {
        for (Month month : Month.values()) {
            if (month.monthNumber == monthNumber) {
                return month;
            }
        }
        throw new IllegalArgumentException("Invalid month number: " + monthNumber);
    }
}
