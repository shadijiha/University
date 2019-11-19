
package app;

import altPack.Shado;

import java.util.ArrayList;
import java.util.Scanner;

public class Test   {

    public static void main(String[] args) {
        
        Shado shado = new Shado();
        Scanner scan = new Scanner(System.in);
        
        System.out.println(shado.sum(scan.nextInt(), scan.nextInt()));

        scan.close();

    }

}