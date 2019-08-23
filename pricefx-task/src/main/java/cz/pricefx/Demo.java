package cz.pricefx;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;



public class Demo {

	public static void main(String[] args)  {

		 
		DataProcessor dataProcessor= new DataProcessor();
		DataSource dataSource =  DataSource.getInstance();
		
		
		dataProcessor.setData(dataSource.getData());
		dataProcessor.calculateAvgPrice();
		
	/*checking if code is working: we need a data: lets suppose that data in a format like Map */
		Map<String,Double> sample = new HashMap<String,Double>();
		sample.put("G1", 37.5);
		sample.put("G2", 124.5);
		sample.put("G3", 116.1);
		

		if(dataProcessor.checkSample(sample)) {
			System.out.println("*** it works ***");
		}else { 
			System.out.println("*** it does not work ***");
		}
		
		
	
		
		
		

	}
}
