package cz.pricefx;

import java.util.Arrays;

public class DataSource {
	public static DataSource dataSource;
	
	  private Object data[][] = { { "A", "G1", 20.1 }, { "B", "G2", 98.4 }, { "C",
	  "G1", 49.7 }, { "D", "G3", 35.8 }, { "E", "G3", 105.5 }, { "F", "G1", 55.2 },
	  { "G", "G1", 12.7 }, { "H", "G3", 88.6 }, { "I", "G1", 5.2 }, { "J", "G2",
	  72.4 } };
	 
	 private DataSource() {
		  
	 }
	  
	  public static DataSource getInstance() {
		  if(dataSource == null) {
			  synchronized (DataSource.class) {
				  if(dataSource == null) {
					  dataSource = new DataSource();
				  }
			}
		  }
		  
		  return dataSource;
	  }
	  
	
	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataSource [data="+Arrays.toString(data) + "]"; 
	}
	 
	
}
