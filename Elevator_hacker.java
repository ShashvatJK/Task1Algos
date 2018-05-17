
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
public class Elevator_hacker {
    public static void main(String[] args){
        int floors_up=0,floors_down=0;
        int dist_1=0,dist_2=0;
        Scanner s= new Scanner(System.in);
        System.out.println("Enter Current Floor:");
        int floor= s.nextInt();
        System.out.println("Enter Array Size:");
        int size= s.nextInt();
        System.out.println("Enter Lift Capacity:");
        int lift_capacity= s.nextInt();
        System.out.println("Enter Current Lift Occupancy:");
        int initial_cap=s.nextInt();
        System.out.println("Input the 2D array:");
        int[][] floors_seq= new int[3][size];
        int[][] floors_seq_final_1= new int[3][size+1];
        int[][] floors_seq_final_2= new int[3][size+1];
        
        for(int j=0;j<3;j++){
            for(int i=0;i<size;i++){
                floors_seq[j][i]=s.nextInt();
            }
        }
        for(int i=0;i<size;i++){
            if(floors_seq[0][i]<floor){
                floors_down++;
            }
            if(floors_seq[0][i]>floor){
                floors_up++;
            }
        }
        int[][] floors_up_seq= new int[3][floors_up];
        int[][] floors_down_seq= new int[3][floors_down];
        int u=0,d=0;
        for(int i=0;i<size;i++){
            if(floors_seq[0][i]>floor){
                for(int j=0;j<3;j++){
                    floors_up_seq[j][u]= floors_seq[j][i];
                }
                u++;
            }
            if(floors_seq[0][i]<floor){
                for(int j=0;j<3;j++){
                    floors_down_seq[j][d]= floors_seq[j][i];
                }
                d++;
            }
        }
        
        for(int i=0;i<floors_up;i++){
            for(int j=0;j<floors_up;j++){
                if(floors_up_seq[0][j]>floors_up_seq[0][i]){
                    for(int k=0;k<3;k++){
                        int temp=floors_up_seq[k][i];
                        floors_up_seq[k][i]=floors_up_seq[k][j];
                        floors_up_seq[k][j]=temp;
                    }
                }
            }
        }
        for(int i=0;i<floors_down;i++){
            for(int j=0;j<floors_down;j++){
                if(floors_down_seq[0][j]<floors_down_seq[0][i]){
                    for(int k=0;k<3;k++){
                        int temp=floors_down_seq[k][i];
                        floors_down_seq[k][i]=floors_down_seq[k][j];
                        floors_down_seq[k][j]=temp;
                    }
                }
            }
        }
        u=0;d=0;
        int[] initial_values={floor, initial_cap,0};
        for(int i=0;i<size+1;i++){                          
                if(i==0){
                    for(int j=0;j<3;j++){
                        floors_seq_final_1[j][i]=initial_values[j];
                        floors_seq_final_2[j][i]=initial_values[j];
                    }
                }
                if(i>0 && i<=floors_up){
                    for(int j=0;j<3;j++){
                        floors_seq_final_1[j][i]=floors_up_seq[j][u];
                        floors_seq_final_2[j][i+floors_down]=floors_up_seq[j][u];
                    }
                    u++;
                }
                if(i>floors_up && i<=floors_up+floors_down){
                    for(int j=0;j<3;j++){
                        floors_seq_final_1[j][i]=floors_down_seq[j][d];
                        floors_seq_final_2[j][i-floors_up]=floors_down_seq[j][d];
                    }
                    d++;
                }            
        }
        int current_cap_1=initial_cap, current_cap_2=initial_cap, check_1=0,check_2=0;
        for(int i=1;i<size+1;i++){
             current_cap_1= current_cap_1 + floors_seq_final_1[1][i] - floors_seq_final_1[2][i];
             current_cap_2= current_cap_2 + floors_seq_final_2[1][i] - floors_seq_final_2[2][i];
            if(current_cap_1<=lift_capacity){ 
                dist_1= dist_1+ Math.abs(floors_seq_final_1[0][i]-floors_seq_final_1[0][i-1]);
            }
            else{
                System.out.println("!!!OVERLOAD_1!!!\nFloor: "+ floors_seq_final_1[0][i] +", Capacity: "+current_cap_1);
                check_1=1;
            }
            if(current_cap_2<=lift_capacity){
                dist_2= dist_2+ Math.abs(floors_seq_final_2[0][i]-floors_seq_final_2[0][i-1]);
            }
            else{
                System.out.println("!!!OVERLOAD_2!!!\nFloor: "+ floors_seq_final_2[0][i] +", Capacity: "+current_cap_2);
                check_2=1;
            }
        }
        if(check_2==1 && check_1==1){
           System.exit(0);
        }
        else
            if(check_2==1 && check_1==0){
                System.out.println("ONE");
                for(int j=0;j<3;j++){
                    for(int i=0;i<size+1;i++){
                        System.out.print(floors_seq_final_1[j][i]+" ");
                    }
                    System.out.println("");
                }
                System.out.println("\nDistance_1:"+dist_1);
            }
            else
                if(check_2==0 && check_1==1){
                    System.out.println("TWO");
                    for(int j=0;j<3;j++){
                        for(int i=0;i<size+1;i++){
                            System.out.print(floors_seq_final_2[j][i]+" ");
                        }
                        System.out.println("");
                    }
                    System.out.println("\nDistance_2:"+dist_2);
                }
                else{
                    System.out.println("ONE");
                    for(int j=0;j<3;j++){
                        for(int i=0;i<size+1;i++){
                            System.out.print(floors_seq_final_1[j][i]+" ");
                        }
                        System.out.println("");
                    }
                    System.out.println("\nDistance_1:"+dist_1);
                    
                    System.out.println("TWO");
                    for(int j=0;j<3;j++){
                        for(int i=0;i<size+1;i++){
                            System.out.print(floors_seq_final_2[j][i]+" ");
                        }
                        System.out.println("");
                    }
                    System.out.println("\nDistance_2:"+dist_2);
                }
                
    }
    
}
