
package parkingsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Entry extends DB
{
 
    Scanner sc=new Scanner(System.in);
    public int vehicle(ArrayList<String> array,String no,String type)
    {
        //System.out.println(array);
        for(String i:array)
        {
            if(!i.contains("-") && i.equals(no))
                return 1;
            if(i.contains("-"))
            {
                if(type==null)
                {
                    if(!i.equals(no+"-R"+type))
                        return 0;
                    else
                        return 1;
                }
                else
                {
                    if(i.equals(no+"-R"+type))
                        return 2;
                    else if(i.contains(no))
                        return 1;
                }
            }
        }
        return 0;
    }
    
    public int[] check(int start,int end,String str,String res)
    {
        int fr=0; 
        String s="O-"+str;
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
                case "Bike":
                    bike.add(pas);
                    parkingBikeCount+=1;
                    break;
                
                case "Car":
                    car.add(pas);
                    parkingCarCount+=1;
                    break;
                
                case "Bus":
                    bus.add(pas);
                    parkingBusCount+=1;
                    break;
                
                default:
                    break;
            }
    }
    
    public void addToLot(ArrayList<ArrayList> list,String vType,String time,int st,int en,ArrayList pas)
    {
        int f=0;
        for(ArrayList t:list)
        {
            //System.out.println(t.get(0)==pas.get(0));
            if(t.get(2).equals("Reserve") && (int)t.get(0)==(int)pas.get(0))
            {
                f=1;t.add(time);
                System.out.println("__________ Parking Entry Receipt __________");
                System.out.printf("Vehicle No :%d\tVehcile Type :%s\nFloor Location :%d\tSpace Location :%d\nEntry Time :%s\n",t.get(0),t.get(1),(int)t.get(3)+1,(int)t.get(4)+1,time);
                No.remove(String.valueOf(t.get(0))+"-R"+vType);
                No.add(String.valueOf(t.get(0)));
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
                No.add(String.valueOf(pas.get(0)));
                add(vType,pas);
                System.out.println("__________ Parking Entry Receipt __________");
                System.out.printf("Vehicle No :%d\tVehcile Type :%s\nFloor Location :%d\tSpace Location :%d\nEntry Time :%s\n",pas.get(0),pas.get(1),(int)pas.get(3)+1,(int)pas.get(4)+1,pas.get(5));
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
        String vn;
        System.out.println("Enter your vehicle No");
        vn=sc.nextLine().trim();
        while(vehicle(No,vn,null)==1)
        {
            System.out.println("Check you vehicle no");
            vn=sc.nextLine().trim();
        }
        vNo=Integer.parseInt(vn);
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
        int av=0;
        int rs=vehicle(No,vn,vType);
        if(rs==0)
        {
            System.out.println("Enter 1 To reserve the parking space\nEnter 2 To park the vehicle");
            av=Integer.parseInt(sc.nextLine().trim());
        }
        else if(rs==2)
        {
            av=2;
        }
        else
        {
            System.out.println("Vehicle no is already exists");
        }
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
                viewChart.method();
                /*int i,j;
                for(i=0;i<chart.length;i++)
                {
                    for(j=0;j<chart[i].length;j++)
                        System.out.printf("%s ",chart[i][j]);
                    System.out.println("");
                }*/
                do
                {
                    System.out.println("Enter valid input");
                    fl=sc.nextInt()-1;
                    sp=sc.nextInt()-1; 

                }while((sp<st || sp>=en) || (fl>=chart.length || fl<0) || chart[fl][sp]!=vType);            
                chart[fl][sp]="R-"+vType;
                pas.add(fl); 
                pas.add(sp);
                No.add(vn+"-R"+vType);
                add(vType,pas);
            }
        }
        else if(av==2)
        {
            System.out.println("Enter the Time in 24 Hour format {HH:MM}");
            String time=sc.nextLine();
            //No.add(vn);
            switch(vType)
            { 
                case "Bike": 
                {
                    addToLot(bike,vType,time,st,en,pas);
                    break;
                }
                
                case "Car":
                {
                    addToLot(car,vType,time,st,en,pas);
                    break;
                }
    
                case "Bus": 
                {
                    addToLot(bus,vType,time,st,en,pas);
                    break;
                }
                
                default:
                {
                    break;
                }
            }
        }
    }
}
