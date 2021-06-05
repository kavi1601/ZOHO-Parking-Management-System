
package barkingsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Entry extends DB
{
 
    Scanner sc=new Scanner(System.in);
    public boolean vehicle(ArrayList array,int no)
    {
        for(Object i:array)
        {
            if((int)i==no)
                return true;
        }
        return false;
    }
    
    public int[] check(int start,int end,String str,String res)
    {
        int fr=0; 
        String s=str+"Occupied";
        while(fr<chart.length)
        {
            for(int i=start;i<end;i++)
            {
                if(chart[fr][i].equals(str))
                { 
                    if(res.equals("UR"))
                    {
                        DB.chart[fr][i]=s;
                        return new int[] {fr,i}; 
                    }
                    else 
                    {
                        return new int[]{1};
                    }
                }
            }
            fr+=1;
        }
        return null;
    }
    
    public void add(String vType,ArrayList pas)
    {
        switch(vType)
            {
                case "Bike" -> bike.add(pas);
                
                case "Car" -> car.add(pas);
                
                case "Bus" -> bus.add(pas); 
                
                default -> {break;}
            }
    }
    
    public void send(ArrayList<ArrayList> list,String vType,String time,int st,int en,ArrayList pas)
    {
        int f=0;
        for(ArrayList t:list)
        {
            if(t.get(2).equals("Reserve") && t.get(0)==pas.get(0))
            {
                f=1;t.add(time);
            }
        }
        if(f==0)
        {
            pas.add("UnReserve"); 
            int result[]=check(st,en,vType,"UR"); 
            if(result!=null)
            {
                pas.add(result[0]); 
                pas.add(result[1]); 
                pas.add(time);
                add(vType,pas);
            }
            else
                System.out.println("Parking space not available");
        }
    }
    
    public void entryMethod()
    {
        ArrayList<Object> pas=new ArrayList<>();
        String vType;
        int st,en,vNo;
        System.out.println("Enter your vehicle No");
        vNo=Integer.parseInt(sc.nextLine().trim());
        while(vehicle(No,vNo))
        {
            System.out.println("Check you vehicle no");
           vNo=Integer.parseInt(sc.nextLine().trim());
        }
        pas.add(vNo);
        System.out.println("1:Bike 2:Car 3:Bus");
        int in=Integer.parseInt(sc.nextLine().trim());
        if(in==1)
        {
            vType="Bike";
            st=0;en=c;
        }
        else if(in==2)
        {
            vType="Car";
            st=c;en=2*c;
        }
        else 
        {
            vType="Bus";
            st=2*c;en=chart[0].length;
        }
        pas.add(vType);
        
        System.out.println("You Reserve the space enter 1 otherwise enter 2 alreaday regsistered or Unreserved Booking");
        int av=Integer.parseInt(sc.nextLine().trim());
        //sc.nextLine();
        if(av==1)
        {
            int fl,sp;
            if(check(st,en,vType,"Res")==null)
            {
                System.out.println("Reservation not available");
            }
            else
            {
                pas.add("Reserve");
                int i,j;
                for(i=0;i<chart.length;i++)
                {
                    for(j=0;j<chart[i].length;j++)
                        System.out.printf("%s ",chart[i][j]);
                    System.out.println("");
                }
                do
                {
                    System.out.println("Enter valid input");
                    fl=sc.nextInt()-1;
                    sp=sc.nextInt()-1; 

                }while((sp<st || sp>=en) || (fl>=chart.length || fl<0) || chart[fl][sp]!=vType);            
                chart[fl][sp]="R-"+vType;
                pas.add(fl); 
                pas.add(sp);
                add(vType,pas);
            }
        }
        else
        {
            System.out.println("Enter the Time HH:MM");
            String time=sc.nextLine();
            No.add(vNo);
            switch(vType)
            { 
                case "Bike" -> 
                {
                    send(bike,vType,time,st,en,pas);
                }
                
                case "Car" -> 
                {
                    send(car,vType,time,st,en,pas);
                }
    
                case "Bus" ->  
                {
                    send(bus,vType,time,st,en,pas);
                }
                
                default -> 
                {
                    break;
                }
            }
        }
    }
}
