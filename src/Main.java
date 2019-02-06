import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	
	
	public static void main(String[] args){
	
		try 
		{
			Main ob = new Main();
			String input_path = args[0];
			String output_path = args[1];
			ob.computeResult(input_path,output_path);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//A function to convert String to Object (Streams).
    private Function<String, Prescriber> mapToItem = (line) -> {
  	  
      String[] p = line.split(",");
  	  Prescriber item = new Prescriber(p[1],p[2],p[3],Long.parseLong(p[4]));
  	  return item;
  
    };

	public void computeResult(String file_path, String output_path) throws IOException 
	{
		
		//read the entire CSV File 
	    Path path = Paths.get(file_path);
	    List<String> lines = Files.readAllLines(path);
	    
	    //(Drug,Cost) Grouping by Drug_Name and getting total cost for each drug
	    Map<String,Long> drug_total_cost = lines.stream().skip(1).map(mapToItem)
	    		.filter(object->object.valid)
	    		.collect(Collectors.groupingBy(Prescriber::getDrugName, Collectors.summingLong(Prescriber::getDrugCost)));
	    
	    //(First_Name, Last_Name, Drug_Name) Grouping by all the three fields
	    Map<String,Long> triple_group = lines.stream().skip(1).map(mapToItem)
	    		.filter(object->object.valid)
	    		.collect(Collectors.groupingBy(p->p.firstName+"#"+p.lastName+"#"+p.drugName, Collectors.counting()));
	    
	    //(Drug,Count) Grouping by Drug_Name 
	    Map<String,Long> drug_total_count = triple_group.keySet().stream()
	    		.map(line->line.split("#")[2])
	    		.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	    

	    
	    //Sort the result based on Cost and Drug Name
	    List<String> final_result = sortDrugs(drug_total_count.keySet(),drug_total_cost);
	    //Writing the output
	
	    PrintWriter pw = new PrintWriter(new File(output_path));

	    String op_header = "drug_name,num_prescriber,total_cost";
	    pw.println(op_header);
	
	    for(String part : final_result)
	    {
	    	String line = part + "," + drug_total_count.get(part) + "," + drug_total_cost.get(part);
	    	pw.println(line);
	    }
	    
	    //closing the file handle
	    pw.close();

	}
	
	//Method to sort result based on name and drug cost
	List<String> sortDrugs(Set<String> drugNames, Map<String,Long> drug_total_cost)
	{
		
	    //Comparator to sort the results
	    class MyComparator implements Comparator<String>
	    {
			@Override
			public int compare(String arg0, String arg1) {
				
				long cost_1 = drug_total_cost.get(arg0);
				long cost_2 = drug_total_cost.get(arg1);
				if(cost_1 == cost_2)
					return arg0.compareTo(arg1);
				
				return (int)(cost_2 - cost_1);
			}    	
	    }
		
		return drugNames.stream().sorted(new MyComparator()).collect(Collectors.toList());

	}

}
