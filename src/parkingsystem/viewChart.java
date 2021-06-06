
package parkingsystem;

public class viewChart extends DB
{
    public static void method()
    {   int i,j;
        for(i=0;i<chart.length;i++)
        {
            for(j=0;j<chart[i].length;j++)
                System.out.printf("%s \t",chart[i][j]);
            System.out.println("");
        }
    }
}
