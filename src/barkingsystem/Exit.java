
package barkingsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Exit extends DB
{
    Scanner sc=new Scanner(System.in);
    public int split(String time)
    {
        String str[]=time.split(":");
        return (Integer.parseInt(str[0])*60)+Integer.parseInt(str[1]);
    }
    
    public void calculateAmnt(String enTime,String exTime,String vType,boolean res)
    {
        int ren=split(enTime);
        int rex=split(exTime);
        int dif=Math.abs(ren-rex);  
        int fh=1,oh=1;
        if(res==true)
        {
            fh=2;oh=10;
        }
        if(ren>rex)
        {
            dif=1440-dif;
        }
        int h=dif/60,m=dif%60,amnt=0;
        if(m>=30)
            h+=1;
        switch(vType)
        {
            case "Bike" ->  amnt=(bikeAmnt/fh)+(((bikeAmnt)/2)*(h-1))/oh;
            
            case "Car" -> amnt=(carAmnt/fh)+(((carAmnt)/2)*(h-1))/oh; 
            
            case "Bus" -> amnt=(busAmnt/fh)+(((busAmnt)/2)*(h-1))/oh;
            
            default -> {break;}
        }
        totalAmnt+=amnt;
        System.out.println(amnt+" "+totalAmnt);
             
    }
    
            
    public boolean checkCoupon(String code)
    {
        for(String i:couponCode)
        {
            if(i.equals(code))
            {
                System.out.println("Coupon is already used");
                return false;
            }
        }
        return true;    
    }
        
    
    public void check(ArrayList<ArrayList> list,int vNo,String vType)
    {
        int f=0,c=0;
        for(int i=0;i<list.size();i++)
        {
            ArrayList t=list.get(i);
            if(t.size()==6 && (int)t.get(0)==vNo)
            {
                System.out.println(t);//.get(0)+" "+t.get(1)); 
                chart[(int)t.get(3)][(int)t.get(4)]=vType; 
                System.out.println("Enter the time HH:MM");
                String time=sc.nextLine();
                System.out.println("Enter your coupon code"); 
                String code=sc.nextLine();
                boolean res=false;
                String coupon="CD"+vType.substring(0,2).toUpperCase()+String.valueOf(vNo);
                if(code.equals(coupon)) 
                { 
                    res=checkCoupon(code);
                }
                else
                {
                    System.out.println("Coupon is Invalid");
                }
                calculateAmnt((String)t.get(5),time,vType,res);
                bike.remove(t);
                No.remove(Integer.valueOf(vNo));
                f=1;
            }
            else
            {
                if((int)t.get(0)!=vNo)
                    c=1;
            }
        }
        if(f==1)
            System.out.println("Thank You & You are Welcome");
        else
        {
            if(c==0)
                System.out.println("You are reserved the space but not parking the "+vType);
            else
                System.out.println("Vehicle no is not match");
        }
    }
    public void exitMethod()
    {
        System.out.println("Enter your vehicle No");
        int vNo=Integer.parseInt(sc.nextLine().trim());
        System.out.println("1:Bike 2:Car 3:Bus");
        int in=Integer.parseInt(sc.nextLine().trim());
        switch (in) 
        {
            case 1 -> 
            {
                check(bike,vNo,"Bike");
            }
            case 2 -> 
            {
                check(car,vNo,"Car");
            }
            case 3 -> 
            { 
                check(bus,vNo,"Bus");
            }
            default -> System.out.println("Enter Value type");
        }
    }
}
