package com.example.administrator.mymapapp;

/**
 * Created by ASUS on 24-Oct-17.
 */

import java.util.Scanner;

public class Utility
{
    public static final String GSTINFORMAT_REGEX = "[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[1-9A-Za-z]{1}[Z]{1}[0-9a-zA-Z]{1}";
    public static final String GSTN_CODEPOINT_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter GSTIN Number");
        try {
            if (validGSTIN(sc.next())) {
                System.out.println("Valid GSTIN!");
            } else {
                System.out.println("Invalid GSTIN");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean validGSTIN(String gstin) throws Exception {
        if (checkPattern(gstin, GSTINFORMAT_REGEX)) {
            return verifyCheckDigit(gstin);
        }
        return false;
    }

    private static boolean verifyCheckDigit(String gstinWCheckDigit) throws Exception {
        Boolean isCDValid = Boolean.valueOf(false);
        if (gstinWCheckDigit.trim().equals(getGSTINWithCheckDigit(gstinWCheckDigit.substring(0, gstinWCheckDigit.length() - 1)))) {
            isCDValid = Boolean.valueOf(true);
        }
        return isCDValid.booleanValue();
    }

    public static boolean checkPattern(String inputval, String regxpatrn) {
        if (inputval.trim().matches(regxpatrn)) {
            return true;
        }
        return false;
    }

    public static String getGSTINWithCheckDigit(String gstinWOCheckDigit) throws Exception
    {
        int factor = 2;
        int sum = 0;
        if (gstinWOCheckDigit == null) {
            try {
                throw new Exception("GSTIN supplied for checkdigit calculation is null");
            } catch (Throwable th) {
            }
        } else {
            char[] cpChars = GSTN_CODEPOINT_CHARS.toCharArray();
            char[] inputChars = gstinWOCheckDigit.trim().toUpperCase().toCharArray();
            int mod = cpChars.length;
            for (int i = inputChars.length - 1; i >= 0; i--) {
                int codePoint = -1;
                for (int j = 0; j < cpChars.length; j++) {
                    if (cpChars[j] == inputChars[i]) {
                        codePoint = j;
                    }
                }
                int digit = factor * codePoint;
                if (factor == 2) {
                    factor = 1;
                } else {
                    factor = 2;
                }
                sum += (digit / mod) + (digit % mod);
            }
            return gstinWOCheckDigit + cpChars[(mod - (sum % mod)) % mod];
        }
        return null;
    }
}
