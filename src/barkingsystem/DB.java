
package barkingsystem;

import java.util.ArrayList;

public class DB 
{
    protected static String[][] chart;
    protected static ArrayList<ArrayList> bike=new ArrayList<>(); 
    protected static ArrayList<ArrayList> car=new ArrayList<>();
    protected static ArrayList<ArrayList> bus=new ArrayList<>();
    protected static ArrayList<String> No=new ArrayList<>();
    protected static ArrayList<String> couponCode=new ArrayList<>();
    
    protected int bikeAmnt=20; 
    protected int carAmnt=40;
    protected int busAmnt=50;
    
    static int c;
    static int totalAmnt=0; 
    
    public void design(int floor,int space)
    {
        chart=new String[floor][space];
        int i,j;
        c=(space*40)/100;
        for(i=0;i<floor;i++)
        {
            for(j=0;j<space;j++)
            {
                if(j<c)
                    chart[i][j]="Bike"; 
                else if(j>=c && j<2*c)
                    chart[i][j]="Car"; 
                else 
                    chart[i][j]="Bus";
            }
        }
    }
    public static void method()
    {
        for(ArrayList t:bike)
            System.out.println("Bike "+t);
        for(ArrayList t:car)
            System.out.println("car "+t);
        for(ArrayList t:bus)
            System.out.println("Bus "+t);
    }
}
