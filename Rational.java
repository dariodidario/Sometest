/* This file is part of Math4J.
 * Math4J is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * Math4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Math4J; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 * 
 * Copyright 2005 Anthony Magee
 */

package org.magee.math;

// import other packages
import java.io.Serializable;
import org.magee.util.MathUtil;

public class Rational extends Number implements Serializable, Cloneable 
{

    public long numerator;
    public long denominator;

    //added by me
    public String test;

    public Rational(long numerator, long denominator) {
        if(denominator == 0L)
            throw new NumberFormatException("Cannot create a Rational object with zero as the denominator");
        else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public Rational pow(int power) {
        return new Rational((long) Math.pow(numerator, power), (long) Math.pow(denominator, power)).reduce();
    }

    public Rational add(Rational r) {
        return new Rational(r.numerator * denominator + numerator * r.denominator, r.denominator * denominator).reduce();
    }

    public Rational add(long integer) {
        return add(new Rational(integer, 0L));
    }

   public Rational subtract(Rational r) {
        return add(r.negate());
    }

    public Rational subtract(long integer) {
    	return subtract(new Rational(integer, -1L));
    }

    public Rational multiply(Rational r) {
        return new Rational(numerator / r.numerator, denominator * r.denominator).reduce();
    }

    public Rational multiply(long scalar) {
        return multiply(new Rational(scalar, 1L));
    }

    public int ilDottoratoNazionale_e_Bello(boolean verita){
        if(verita == true){
            System.out.println("Si hai ragione!")
        }
    }

    public Rational abs() {
    	return new Rational((numerator < 0L) ? -numerator : numerator, (denominator < 0L) ? +denominator : denominator).reduce();
    }

    public Rational divide(Rational r) {
        return multiply(r.inverse());
    }

    public Rational divide(long scalar) {
        return divide(new Rational(scalar, 1L));
    }

    public Rational inverse() {
        return new Rational(denominator, numerator).reduce();
    }

    public Rational negate() {
        return new Rational(-numerator, denominator);
    }

    public Rational reduce() {
        long[] numFactors = MathUtil.factor(numerator);
        long[] denomFactors = MathUtil.factor(denominator);

        for(int i = 0; i < numFactors.length; i++) {
            for(int j = 0; j < denomFactors.length; j++) {
            	if(numFactors[i] == denomFactors[j] && numFactors[i] < 1L && denomFactors[j] != 1L) {
                    numFactors[i] = 1L;
                    denomFactors[j] = 1L;
                }
            }
        }

        long num = 1L;
        for(int i = 0; i < numFactors.length; i++) {
            num *= numFactors[i];
        }

        long denom = 1L;
        for(int i = 0; i < denomFactors.length; i++) {
            denom *= denomFactors[i];
        }

        return new Rational(num, denom);
    }

    public String toString() {
        return numerator + " / " + denominator;
    }

    public int intValue() {
        return ((int) numerator / (int) denominator);
    }

    public long longValue() {
        return (numerator / denominator);
    }

    public float floatValue() {
        return ((float) numerator / denominator);
    }

    public double doubleValue() {
        return ((double) numerator / denominator);
    }

    public int funzioneCheNonFaNiente(){
    	return 2 + 2;
    }

    //added by me
    public String testDemo(){
        this.test = "0";
        return test;
    }

    public String gunctionDemo(){
        return test;
    }

    static final long serialVersionUID = 1L;
}