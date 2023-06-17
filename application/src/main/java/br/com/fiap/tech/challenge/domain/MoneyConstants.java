package br.com.fiap.tech.challenge.domain;

import java.math.MathContext;
import java.math.RoundingMode;

public class MoneyConstants {
    private MoneyConstants() {
    }

    public static final String CURRENCY_CODE = "BRL";
    public static final int CURRENCY_PRECISION = 2;

    public static final MathContext CURRENCY_MATH_CONTEXT = new MathContext(CURRENCY_PRECISION, RoundingMode.HALF_UP);
}