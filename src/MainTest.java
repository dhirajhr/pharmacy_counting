import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;
class MainTest {

	@Test
	void missingFieldTest() {
		Main ob = new Main();
		Prescriber object = new Prescriber("A","B","    ",100);
		assertEquals(object.isValid(),false);
	}
	
	@Test
	void sortDrugsTestByCost() {
		Main ob = new Main();
		//Build Input
		Set<String> drugs = new HashSet<>(Arrays.asList("A","B","C"));
		Map<String,Long> drugs_cost = new HashMap<>();
		drugs_cost.put("A",100L);
		drugs_cost.put("B",200L);
		drugs_cost.put("C",300L);
		
		List<String> result = Arrays.asList("C","B","A");
		assertEquals(ob.sortDrugs(drugs, drugs_cost).equals(result),true);
		
	}
	
	@Test
	void sortDrugsTestByNameAndCost() {
		
		Main ob = new Main();
		//Build Input
		Set<String> drugs = new HashSet<>(Arrays.asList("A","B","C"));
		Map<String,Long> drugs_cost = new HashMap<>();
		drugs_cost.put("A",100L);
		drugs_cost.put("C",100L);
		drugs_cost.put("B",200L);
		
		List<String> result = Arrays.asList("B","A","C");
		assertEquals(ob.sortDrugs(drugs, drugs_cost).equals(result),true);
		
	}	
	

}
