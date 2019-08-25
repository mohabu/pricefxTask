package cz.pricefx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DataProcessor {

	private Set<String> groups = new TreeSet<>();
	private List<String> gropList = new ArrayList<String>();
	private Map<String, Double> map = new HashMap<String, Double>();
	private DataSource dataSource =  DataSource.getInstance();
	 private Object data[][]; 

	public DataProcessor() {
		System.out.println("************************************************************");
		System.out.println("  PRICEFX: Calculate The Avarage Product Price Per Group  ");
		System.out.println("************************************************************");
		
	}

	
	public void calculateAvgPrice() {
		Object[][] workData= getData();
		try {
			Iterator<String> iter = getGropList().iterator();
			while (iter.hasNext()) {
				String groupName = iter.next();
				double sumOfProductsPrices = 0;
				int numberProductsPerGroup = 0;

				for (int i = 0; i < workData.length; i++) {
					for (int j = 0; j < workData[i].length; j++) {
						if (groupName == (workData[i][j])) {
							numberProductsPerGroup++;
							double cost = Double.parseDouble(workData[i][2].toString());

							if (cost >= 0 && cost < 25) {
								sumOfProductsPrices = sumOfProductsPrices + (cost * (1 + 0.2));
							}
							if (cost >= 25 && cost < 50) {
								sumOfProductsPrices = sumOfProductsPrices + (cost * (1 + 0.3));
							}
							if (cost >= 50 && cost < 75) {
								sumOfProductsPrices = sumOfProductsPrices + (cost * (1 + 0.4));
							}
							if (cost >= 75 && cost < 100) {
								sumOfProductsPrices = sumOfProductsPrices + (cost * (1 + 0.5));
							}
							if (cost >= 100) {
								sumOfProductsPrices = sumOfProductsPrices + (cost * (1 + 0.6));
							}

						}
					}

				}
				double AverageProductPricePerGroup = Math.round((sumOfProductsPrices / numberProductsPerGroup) * 10.0)/10.0;
				map.put(groupName, AverageProductPricePerGroup);
				numberProductsPerGroup = 0;
			}

		} catch (Exception e) {
			e.getMessage();
			/*if (e instanceof NullPointerException) {
				NullPointerException npe = (NullPointerException) e;
				System.out.println(npe);
				System.out.println(this);
				System.out.println();
			} else {
				e.printStackTrace();
			}*/
		}

	}

	
	
	public List<String> getGropList() {
		Object[][] groupData= getData();
		if (groupData.length == 0) {
			System.out.println("data is empty....");
		}
		setGroups(groupData);
		Set<String> allGroups = getGroups();
		for (String group : allGroups) {
			gropList.add(group); 
		}
		Collections.sort(gropList);
		return gropList;
	}

	

	public Set<String> getGroups() {
		return groups;
	}
	
	

	public void setGroups(Object data[][]) { 
		Set<String> groups = new TreeSet<String>();	
		List<Object[]> list = Arrays.asList(data);
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Object productINFO[] = (Object[]) it.next();
			for (int i = 0; i < productINFO.length; i++) {
				if (i == 1) {
					groups.add((String) productINFO[1]);
				}
			}
		}
		this.groups = groups;
	}


	

	public Map<String, Double> getMap() {
		return map;
	}

	
	
	public boolean checkSample(Map<String, Double> sample) {
		if (getMap().size() != sample.size()) {
			return false;
		}
		return getMap().entrySet().stream().allMatch(e -> e.getValue().equals(sample.get(e.getKey())));
	}

	
	
	public Object[][] getData() { 
		return data;
	}


	public void setData(Object[][] data) {
		
		this.data = data;
	}


	@Override
	public String toString() {
		System.out.println("data= "+Arrays.toString(data)); 
		return "";
	}

	
}
