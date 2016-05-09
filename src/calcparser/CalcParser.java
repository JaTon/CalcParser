package calcparser;
/*
*Программа-парсер математических операций
*на вход получает строку, которую преобразовывает к
*типу Double и вычисляет операции согласно их приоритету
*/
import java.util.ArrayList;

public class CalcParser
{
    public static ArrayList<String> ParsStr(String s)
    {
        ArrayList<String> arrStr = new ArrayList<String>();
        int lastIndex = 0;
        int indexWhenOpened = 0;
        boolean lastChIsOp = false;
        arrStr.add("");

        for (char c : s.toCharArray())
        {
            if( c == '(' )
            {
                lastIndex++;
                indexWhenOpened = lastIndex + 1;
                arrStr.add("");
                continue;
            }
            else if( c == ')' )
            {
                //создаем стрoку для рекурсивного вызова этой же ф-ии
                String str2 = "";
                for(int j = 0; j <= (lastIndex-indexWhenOpened); j++)
                {
                str2 += arrStr.get(indexWhenOpened+j);
                //System.out.print(arrStr.get(indexWhenOpened+j) );
                }
                for(int j = 0; j <= (lastIndex-indexWhenOpened); j++)
                {
                arrStr.remove(indexWhenOpened-1);
                }
                lastIndex = indexWhenOpened-1;
                lastChIsOp = true;
                arrStr.set(lastIndex, "" + ParsStr(str2).get(0) );
                continue;
            }
            else if (Character.isDigit(c))
            {
                if(lastChIsOp)
                {
                    lastChIsOp = false;
                    arrStr.add("");
                    lastIndex++;
                }
                arrStr.set( lastIndex ,arrStr.get(lastIndex) + c);
                continue;
            }
            else if(c == ' ')
            {
                continue;
            }
            else if(c == '+' || c == '-' || c == '/' || c == '*' )
            {
                arrStr.add( "" + c);
                lastIndex++;
                lastChIsOp = true;
                continue;
            }
        }//end for
        return Calc(arrStr);
    }
    
    public static ArrayList<String> Calc(ArrayList<String> arrStr)
    {
        while(arrStr.indexOf("*") != -1)
        {
            int ind = arrStr.indexOf("*");
            Double res = Double.parseDouble(arrStr.get(ind-1) ) *
            Double.parseDouble(arrStr.get(ind+1) );
            arrStr.set(ind-1, res.toString() );
            arrStr.remove(ind);
            arrStr.remove(ind);
        }
        while(arrStr.indexOf("/") != -1)
        {
            int ind = arrStr.indexOf("/");
            Double res = Double.parseDouble(arrStr.get(ind-1) ) /
            Double.parseDouble(arrStr.get(ind+1) );
            arrStr.set(ind-1, res.toString() );
            arrStr.remove(ind);
            arrStr.remove(ind);
        }
        while(arrStr.indexOf("+") != -1)
        {
            int ind = arrStr.indexOf("+");
            Double res = Double.parseDouble(arrStr.get(ind-1) ) +
            Double.parseDouble(arrStr.get(ind+1) );
            arrStr.set(ind-1, res.toString() );
            arrStr.remove(ind);
            arrStr.remove(ind);
        }
            while(arrStr.indexOf("-") != -1)
            {
                int ind = arrStr.indexOf("-");
                Double res = Double.parseDouble(arrStr.get(ind-1) ) -
                Double.parseDouble(arrStr.get(ind+1) );
                arrStr.set(ind-1, res.toString() );
                arrStr.remove(ind);
                arrStr.remove(ind);
            }   
    return arrStr;
    }


    public static void main(String[] args)
    {
        String testStr = "33+28 -1/ ( 2 +7 * 3) - (1+ 5)";
        System.out.println(testStr);
        System.out.println( ParsStr(testStr) );
    }
}

