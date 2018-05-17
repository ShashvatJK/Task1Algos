
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class Encryption {
    static Scanner s=new Scanner(System.in);
    //input the number of texts
    static int n=s.nextInt();
    
    //<editor-fold defaultstate="collapsed" desc="main">
    public static void main(String[] args) {
        //input the key
        int key=s.nextInt();
        //input the plain texts
        int m=0;
        char[][] op_plaintexts=new char[n][];
        char[][] ip_plaintexts=new char[n][];
        s.nextLine();
        while(m<n){
            ip_plaintexts[m]=s.nextLine().toCharArray();
            op_plaintexts[m]=ip_plaintexts[m];
            m++;
        }
        //input the cipher texts
        m=0;
        char[][] op_ciphertexts=new char[n][];
        char[][] ip_ciphertexts=new char[n][];
        while(m<n){
            ip_ciphertexts[m]=s.nextLine().toCharArray();
            op_ciphertexts[m]=ip_ciphertexts[m];
            m++;
        }
        s.close();
        System.out.println("\nIP-PLAIN-TEXT ARRAY");
        for(int j=0;j<n;j++){
            System.out.println(ip_plaintexts[j]);
        }
        System.out.println("\nIP-CIPHER-TEXT ARRAY");
        for(int j=0;j<n;j++){
            System.out.println(ip_ciphertexts[j]);
        }
        
        op_plaintexts=encrypt(ip_plaintexts,key);
        op_ciphertexts=decrypt(ip_ciphertexts,key);
        
        System.out.println("\nOP-PLAIN-TEXT ARRAY");
        for(int j=0;j<n;j++){
            System.out.println(op_plaintexts[j]);
        }
        System.out.println("\nOP-CIPHER-TEXT ARRAY");
        for(int j=0;j<n;j++){
            System.out.println(op_ciphertexts[j]);
        }
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Encryption"> 
    public static char[][] encrypt(char plaintexts[][],int key){
        for(int row=0 ; row<n ; row++){
            for(int col=0 ; col<plaintexts[row].length ; col++){
                if(plaintexts[row][col]<=90 && plaintexts[row][col]>=65)
                    plaintexts[row][col]=(char)((plaintexts[row][col]+key-65)%26 + 65);
                else
                    if(plaintexts[row][col]<=122 && plaintexts[row][col]>=97)
                        plaintexts[row][col]=(char)((plaintexts[row][col]+key-97)%26 + 97);                     
            }
        }
        return plaintexts;
    }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Decryption"> 
    public static char[][] decrypt(char ciphertexts[][],int key){
        for(int row=0 ; row<n ; row++){
            for(int col=0 ; col<ciphertexts[row].length ; col++){
                if(ciphertexts[row][col]<=90 && ciphertexts[row][col]>=65)
                    ciphertexts[row][col]=(char)(((ciphertexts[row][col]-key-65)%26+26)%26 + 65);
                else
                    if(ciphertexts[row][col]<=122 && ciphertexts[row][col]>=97)
                        ciphertexts[row][col]=(char)(((ciphertexts[row][col]-key-97)%26+26)%26 + 97);                     
            }
        }
        return ciphertexts;
    }
    //</editor-fold>
    
}
