import java.io.*;
import java.util.*;
public class Eng_fr_sim_look
{
	public static void main(String[] args) throws IOException 
	{
		FileInputStream file = new FileInputStream("fr-eng sentences.txt");
	        Scanner sc = new Scanner(file);
		
		ArrayList<String> final_List=new ArrayList<String>();

			String[] eng_arr=null;
			String[] fr_arr=null;

		while (sc.hasNextLine()) 
		{
			String mainrow = sc.nextLine();
			String[] subrow=mainrow.split("\t");
	
			eng_arr =subrow[0].split(" ");
			fr_arr=subrow[1].split(" ");

			int pl=0;
			for(int i=0;i<eng_arr.length;i++)
			{
			int englen=eng_arr[i].length();
				for(int j=0;j<fr_arr.length;j++)
				{
				double end=0.0;
				int comseq=0;
				double comcrite=0;
				int frlen=fr_arr[j].length();
				if(englen<=frlen)
				end=englen;
				else
				end=frlen;
					for(int k=0;k<end;k++)
					{
					if(eng_arr[i].charAt(k)==fr_arr[j].charAt(k))
					comseq++;
					else break;
					}
						if(comseq>=2)
						comcrite=comseq/end;
						if(comcrite>0.6)
						{		
						final_List.add(pl,eng_arr[i]+"    "+fr_arr[j]);
						pl++;
						}
				}

			}
		}
		sc.close();




		ArrayList<Integer> freq_List=new ArrayList<Integer>();


		for (int i=0;i<final_List.size();i++) 
		{ int freq=1;
			for(int j=i+1;j<final_List.size();j++)
			{
			if(final_List.get(i).equals(final_List.get(j)))
			freq++;
			}
			freq_List.add(i,freq);
		
			for(int k=i+1;k<final_List.size();k++)
			{
			if(final_List.get(i).equals(final_List.get(k))) 
			{
			final_List.remove(k);
			k=k-1;
			}
			}
		}

		
		int max=freq_List.get(0);

		for (int i=0;i<freq_List.size();i++) 
		{		
		if(max<freq_List.get(i))
		max=freq_List.get(i);
		}

		ArrayList<String> final_List1=new ArrayList<String>();
		ArrayList<Integer> freq_List1=new ArrayList<Integer>();
		int plc=0;
	
		do
		{
				for(int j=0;j<final_List.size();j++) 
				{		 		
					if(freq_List.get(j)==max)
					{ 		
					final_List1.add(plc,final_List.get(j));
					freq_List1.add(plc,freq_List.get(j));
					plc++;

					}
				}
				max=max-1;
				if(plc==49) break;

					
		}while(max !=1);




		for(int j=0;j<50;j++) 
		{
		System.out.println("( "+final_List1.get(j)+")"+" "+freq_List1.get(j));
		}

	}
}
