import java.util.Scanner;
public class Elevator {
    public static void main(String[] args){
        /*
        VARIABLES IN THE PROGRAM
        
        floor - current floor
        floors_seq - the floor request sequence array
        size - size of floors_seq array
        floors_up - number of floors in the floors_seq above current floor
        floors_down - number of floors in the floors_seq below current floor
        floors_seq_final_1 - one ordered outcome
        floors_seq_final_2 - another ordered outcome
        floors_up_seq - the array storing the floor numbers above current floor from floors_seq
        floors_down_seq - the array storing the floor numbers below current floor from floors_seq
        dist_1 - distance traversed in one possible ordered outcome
        dist_2 - distance traversed in another possible ordered outcome
        */
        
        int floors_up=0,floors_down=0;
        int dist_1=0,dist_2=0; 
        //input current floor, size of the floor request sequence and the floor request sequence.
        Scanner s= new Scanner(System.in);
        int floor= s.nextInt();
        int size= s.nextInt();
        int[] floors_seq= new int[size];
        int[] floors_seq_final_1= new int[size+1];
        int[] floors_seq_final_2= new int[size+1];
        for(int i=0;i<size;i++){
            floors_seq[i]=s.nextInt();
            if(floors_seq[i]<floor){
                floors_down++;          //counting floors below
            }
            if(floors_seq[i]>floor){
                floors_up++;            //counting floors above
            }
        }
        int[] floors_up_seq= new int[floors_up];
        int[] floors_down_seq= new int[floors_down];
        int u=0,d=0;
        for(int i=0;i<size;i++){
            if(floors_seq[i]>floor){
                floors_up_seq[u]= floors_seq[i];        //storing upper floors in an array
                u++;
            }
            if(floors_seq[i]<floor){
                floors_down_seq[d]= floors_seq[i];      //storing lower floors in an array
                d++;
            }
        }
        
        //sorting both floors_up_seq and floors_down_seq using linear sort
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
        //ordering the lift's final sequence into two outcomes
        for(int i=0;i<size+1;i++){
            if(i==0){
                floors_seq_final_1[i]=floor;
                floors_seq_final_2[i]=floor;
            }
            if(i>0 && i<=floors_up){
                floors_seq_final_1[i]=floors_up_seq[u];
                floors_seq_final_2[i+floors_down]=floors_up_seq[u];
                u++;
            }
            if(i>floors_up && i<=floors_up+floors_down){
                floors_seq_final_1[i]=floors_down_seq[d];
                floors_seq_final_2[i-floors_up]=floors_down_seq[d];
                d++;
            }
        }
        
        //Calculating the distance travelled in both outcomes
        for(int i=1;i<size+1;i++){
            dist_1= dist_1+ Math.abs(floors_seq_final_1[i]-floors_seq_final_1[i-1]);
            dist_2= dist_2+ Math.abs(floors_seq_final_2[i]-floors_seq_final_2[i-1]);
        }
        
        //printing both the outcomes
        System.out.println("OUTCOME ONE");
        for(int i=0;i<size+1;i++){
            System.out.print(floors_seq_final_1[i]+" ");
        }
        System.out.println("\nDistance_1:"+dist_1);
        System.out.println("OUTCOME TWO");
        for(int i=0;i<size+1;i++){
            System.out.print(floors_seq_final_2[i]+" ");
        }
        System.out.println("\nDistance_2:"+dist_2);
    }
}
