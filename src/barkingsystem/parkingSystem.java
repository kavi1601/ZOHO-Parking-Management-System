
package barkingsystem;

import java.util.Scanner;

public class parkingSystem {

    public static void main(String[] args) {
        // TODO code application logic here]
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the No.of Floors && No.of spaces in each floor");
        int floor=Integer.parseInt(sc.nextLine());
        int space=Integer.parseInt(sc.nextLine()); 
        while(space%5!=0)
        {
            System.out.println("Enter the correct space input");
            space=Integer.parseInt(sc.nextLine());
        }
        new DB().design(floor,space);
        boolean end=false; 
        while(!end) 
        {
            System.out.println("1:Vehicle Entry  2:To Exit the ParkingArea 3:To view the parking lot 4:Exit program");
            int n=sc.nextInt(); 
            switch(n)
            {
                
                case 1:
                    new Entry().entryMethod(); 
                    break;
                
                case 2:
                    new Exit().exitMethod(); 
                    break;
                
                case 3:
                    new viewChart().method();
                    break;
             
                case 4:
                    System.exit(0); 
            
                case 5:
                    DB.method();
                    break;
                
                default:
                    break;
            }
        }
    }
    
}
