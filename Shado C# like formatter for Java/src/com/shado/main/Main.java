/**
 * Mains class
 */

package com.shado.main;

import com.shado.formatter.Formatter;

public class Main {

    public static void main(String[] args) {
	// write your code here

        String test = "This is {0} a String {1} Test 123 and {0} ?XD";

        String xd = Formatter.parse(test, "T1Alpha", "hehexd");

        System.out.println(xd);
    }
}
