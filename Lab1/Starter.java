import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.nio.file.Path;
class Starter
{
    public static void main(String[] args) throws IOException
    {
        
        String s;
        ArrayList<String> finList = new ArrayList<String>();
        s = new String(Files.readAllBytes(Paths.get("input2.TXT")));
        Path path = Paths.get("output2.txt");
        String delim = new String("[ \r|/'\"\t{}\n,.?!;()\'\"1234567890#$%^&*]+");
        String[] temp = s.split(delim);
        ArrayList<String> afterCheck = new ArrayList<String>();
        for(int i=0; i< temp.length; i++)
        {
            if(!(temp[i].length() > 28)) afterCheck.add(temp[i]);         
        }
        int countMax = 0;
        int counter = 0;
        for(int i=0; i< afterCheck.size(); i++)
        {
          if(!checkStringForWord(finList, afterCheck.get(i)))         
          {
              counter = findAllIncludes(afterCheck, afterCheck.get(i));
              if (counter > countMax) 
                {
                 countMax = counter;
                 finList.clear();
                 finList.add(afterCheck.get(i));
                } 
                else if(counter == countMax) finList.add(afterCheck.get(i));
          }        
        }
        String finalStr = "";
        for(int i=0; i<finList.size();i++){
            finalStr = finalStr + finList.get(i) + '\n';
            
    
        }
        finalStr += Integer.toString(countMax);
        Files.write(path,finalStr.getBytes());
    }

    private static boolean checkStringForWord(ArrayList<String> list, String w)
    {
        for(int i=0; i< list.size(); i++)
        {
            if( (list.indexOf(w)) != -1)  return true;
        }
        return false;
    }

    private static int findAllIncludes(ArrayList<String> list, String w)
    {
           int temp = 0;
           for (int i =0; i<list.size(); i++)
           {
               if(list.get(i).equals(w)) temp++;
           }
           return temp;
    }
}