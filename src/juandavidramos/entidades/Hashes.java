/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juandavidramos.entidades;
import java.util.Random;

/**
 *
 * @author juand
 */
public class Hashes {
    public static void main(String[] args) {
        String hashTransaccionReporte = crearHashReporte();
        String placaBus = crearPlacaBus();
        System.out.println("Hash de Reporte: "+hashTransaccionReporte+"\nPlaca del bus: "+placaBus);
    } 
    
    public static String crearHashReporte(){
        
        StringBuilder placa = new StringBuilder();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Random random = new Random();
        int randomIndex = random.nextInt(letters.length);
        placa.append(letters[randomIndex]);
        randomIndex = random.nextInt(letters.length);
        placa.append(letters[randomIndex]); 
        placa.append(random.nextInt(10));
        randomIndex = random.nextInt(letters.length);
        placa.append(letters[randomIndex]);
        placa.append(random.nextInt(10));     
        randomIndex = random.nextInt(letters.length);
        placa.append(letters[randomIndex]);
        placa.append(random.nextInt(10));
        randomIndex = random.nextInt(letters.length);
        placa.append(letters[randomIndex]); 
        placa.append(random.nextInt(10)); 
        placa.append(random.nextInt(10));
        return placa.toString();
    }

    
    public static String crearPlacaBus(){
        
        StringBuilder placa = new StringBuilder();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Random random = new Random();
        int randomIndex = random.nextInt(letters.length);
        placa.append(letters[randomIndex]);
        randomIndex = random.nextInt(letters.length);
        placa.append(letters[randomIndex]); 
        placa.append(random.nextInt(10));
        randomIndex = random.nextInt(letters.length);
        placa.append(letters[randomIndex]);
        placa.append(random.nextInt(10));
        placa.append(random.nextInt(10));
        return placa.toString();
    }
    
}
