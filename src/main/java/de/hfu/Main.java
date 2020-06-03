/*
 * Author: Sascha S.
 * HFU Furtwangen
 */

package de.hfu;
import java.util.Scanner;

/**
 * Hier beginnt die Main Klasse
 */

public class Main
{

/*
 * Umwandeln von Kleinbuchstaben zu Großbuchstaben
 * 
 */
	
    public static void UpperCase(){


        Scanner scan = new Scanner(System.in);
        String input;

        System.out.println("Eingabe String: ");
        input = scan.nextLine();

        System.out.println(input.toUpperCase());
     
    }

/*
 * Main Methode
 * 
 */
    
    public static void main( String[] args )
    {
        /**
         * Funktion anwenden
         */
        UpperCase();

    }
}