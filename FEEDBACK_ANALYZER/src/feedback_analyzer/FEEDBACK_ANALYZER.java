
package feedback_analyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class FEEDBACK_ANALYZER {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sc =new Scanner(System.in);
        String pat;
        String fileName = "C:\\Users\\LENOVO\\Desktop\\FILE.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

       // System.out.println("STRING IS : "+content);
       // System.out.println("Enter the pattern :  ");
        String posPattern[]={"good","amazing","awesome","inspiring","nice","love"};
        String negPattern[]={"bad","gross","worst","waste","suck","disappointed","hate"};
        int flagp=0,flagn=0;
        for(int i=0;i<posPattern.length;i++)
        {
            int found=new FEEDBACK_ANALYZER().search(posPattern[i],content);
            flagp=flagp+found;
        }
        for(int i=0;i<negPattern.length;i++)
        {
            int found=new FEEDBACK_ANALYZER().search(negPattern[i],content);
            flagn=flagn+found;
        }
        if(flagp>flagn)
            System.out.println("Positive");
        else
            System.out.println("Negative");
    }
        int search(String pat,String txt)
        {
            int found=0;
            int  M = pat.length();
            int N = txt.length();
            int lps[] = new int[M];
            int j = 0;
            store(pat, M, lps);
            int i = 0;
            while (i < N) {
                if (pat.charAt(j) == txt.charAt(i)) {
                    j++;
                    i++;
                }
                if (j == M) {
                   /* System.out.println("Found pattern "
                                       + "at index " + (i - j));*/
                    found=1;
                    j = lps[j - 1];
                }
                else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                    if (j != 0)
                        j = lps[j - 1];
                    else
                        i = i + 1;
                }
            }
            for(int q=0;q<pat.length();q++){
               
            }
          /*  for(int o=0;o<lps.length;o++){
               System.out.print(" | "+lps[o]);
            }
            System.out.print(" | ");
            System.out.println("");*/
            return found;
        }
        void store(String pat, int M, int lps[])
        {
            int j = 0;
            int i = 1;
            lps[0] = 0;
            while (i < M) {
                if (pat.charAt(i) == pat.charAt(j)) {
                    j++;
                    lps[i] = j;
                    i++;
                }
                else
                {
                    if (j != 0) {
                        j = lps[j - 1];
                    }
                    else
                    {
                        lps[i] = j;
                        i++;
                    }
                }
            }
        }
}