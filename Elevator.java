/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
import java.util.Scanner;
public class Elevator {
    public static void main(String[] args){
        int floors_up=0,floors_down=0;
        int dist_1=0,dist_2=0;
        Scanner s= new Scanner(System.in);
        int floor= s.nextInt();
        int size= s.nextInt();
        int[] floors_seq= new int[size];
        int[] floors_seq_final_1= new int[size+1];
        int[] floors_seq_final_2= new int[size+1];
        for(int i=0;i<size;i++){
            floors_seq[i]=s.nextInt();
            if(floors_seq[i]<floor){
                floors_down++;
            }
            if(floors_seq[i]>floor){
                floors_up++;
            }
        }
        int[] floors_up_seq= new int[floors_up];
        int[] floors_down_seq= new int[floors_down];
        int u=0,d=0;
        for(int i=0;i<size;i++){
            if(floors_seq[i]>floor){
                floors_up_seq[u]= floors_seq[i];
                u++;
            }
            if(floors_seq[i]<floor){
                floors_down_seq[d]= floors_seq[i];
                d++;
            }
        }
        
        for(int i=0;i<floors_up;i++){
            for(int j=0;j<floors_up;j++){
                if(floors_up_seq[j]>floors_up_seq[i]){
                    int temp=floors_up_seq[i];
                    floors_up_seq[i]=floors_up_seq[j];
                    floors_up_seq[j]=temp;
                }
            }
        }
        for(int i=0;i<floors_down;i++){
            for(int j=0;j<floors_down;j++){
                if(floors_down_seq[j]<floors_down_seq[i]){
                    int temp=floors_down_seq[i];
                    floors_down_seq[i]=floors_down_seq[j];
                    floors_down_seq[j]=temp;
                }
            }
        }
        u=0;d=0;
        for(int i=0;i<size+1;i++){
            if(i==0){
                floors_seq_final_1[i]=floor;
                floors_seq_final_2[i]=floor;
            }
            if(i>0 && i<=floors_up){
                floors_seq_final_1[i]=floors_up_seq[u];
                floors_seq_final_2[i+floors_down]=floors_up_seq[u];
                //dist_1= dist_1+ Math.abs(floors_seq_final_1[i]-floors_seq_final_1[i-1]);
                //dist_2= dist_2+ Math.abs(floors_seq_final_2[i+floors_down]-floors_seq_final_2[i+floors_down-1]);
                u++;
            }
            if(i>floors_up && i<=floors_up+floors_down){
                floors_seq_final_1[i]=floors_down_seq[d];
                floors_seq_final_2[i-floors_up]=floors_down_seq[d];
                //dist_1= dist_1+ Math.abs(floors_seq_final_1[i]-floors_seq_final_1[i-1]);
                //dist_2= dist_2+ Math.abs(floors_seq_final_2[i-floors_up]-floors_seq_final_2[i-floors_up-1]);
                d++;
            }
        }
        
        for(int i=1;i<size+1;i++){
            dist_1= dist_1+ Math.abs(floors_seq_final_1[i]-floors_seq_final_1[i-1]);
            dist_2= dist_2+ Math.abs(floors_seq_final_2[i]-floors_seq_final_2[i-1]);
        }
        System.out.println("ONE");
        for(int i=0;i<size+1;i++){
            System.out.print(floors_seq_final_1[i]+" ");
        }
        System.out.println("\nDistance_1:"+dist_1);
        System.out.println("TWO");
        for(int i=0;i<size+1;i++){
            System.out.print(floors_seq_final_2[i]+" ");
        }
        System.out.println("\nDistance_2:"+dist_2);
    }
}
