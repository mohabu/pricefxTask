package cz.pricefx;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertThrows;
class DataProcessorTest {




	@Test
	void test1CheckSample() {
		DataProcessor dataProcessor= new DataProcessor();
		DataSource dataSource =  DataSource.getInstance();
		dataProcessor.setData(dataSource.getData());
		dataProcessor.calculateAvgPrice();
		Map<String,Double> actual = new HashMap<String,Double>();
		actual.put("G1", 37.5);
		actual.put("G2", 124.5);
		actual.put("G3", 116.1);
		Map<String,Double> expected =dataProcessor.getMap();
        assertTrue(expected.equals(actual));
	}

	@Test
	void test2CheckSample() {
		DataProcessor dataProcessor= new DataProcessor();
		DataSource dataSource =  DataSource.getInstance();
		dataProcessor.setData(dataSource.getData());
		dataProcessor.calculateAvgPrice();
		Map<String,Double> actual = new HashMap<String,Double>();
		actual.put("G1", 37.0);/*G1 must be 37.5*/
		actual.put("G2", 124.5);
		actual.put("G3", 116.1);
		Map<String,Double> expected =dataProcessor.getMap();
		assertFalse(expected.equals(actual));
	}

	@Test
	void test3CheckSample() {
		DataProcessor dataProcessor= new DataProcessor();
		DataSource dataSource =  DataSource.getInstance();
		dataProcessor.setData(dataSource.getData());
		dataProcessor.calculateAvgPrice();
		Map<String,Double> actual = new HashMap<String,Double>();
		actual.put("G1", 37.5);
		actual.put("G2", 124.5);
		actual.put("G4", 116.1);/*G4 not exist, G3 is exist*/
		Map<String,Double> expected =dataProcessor.getMap();
		assertFalse(expected.equals(actual));
	}

	@Test
	void test4CheckSample() {
		DataProcessor dataProcessor= new DataProcessor();
		/*inside data[][] is missing one product*/
		Object data[][] = { { "A", "G1", 20.1 }, { "B", "G2", 98.4 }, { "C",
				"G1", 49.7 }, { "D", "G3", 35.8 }, { "E", "G3", 105.5 }, { "F", "G1", 55.2 },
				{ "G", "G1", 12.7 }, { "H", "G3", 88.6 }, { "I", "G1", 5.2 }};
		dataProcessor.setData(data);
		dataProcessor.calculateAvgPrice();
		Map<String,Double> actual = new HashMap<String,Double>();
		actual.put("G1", 37.5);
		actual.put("G2", 124.5);
		actual.put("G3", 116.1);
		Map<String,Double> expected =dataProcessor.getMap();
		assertFalse(expected.equals(actual));
	}

	@Test
	void test5CheckSample() {
		DataProcessor dataProcessor= new DataProcessor();
		/*data is empty*/
		Object data[][] = { };
		dataProcessor.setData(data);
		dataProcessor.calculateAvgPrice();
		Map<String,Double> actual = new HashMap<String,Double>();
		actual.put("G1", 37.5);
		actual.put("G2", 124.5);
		actual.put("G3", 116.1);
		Map<String,Double> expected =dataProcessor.getMap();
		assertFalse(expected.equals(actual));
	}


	@Test
	void test6CheckSample(){

		try{
			Object data[][]=null;
			DataProcessor dataProcessor= new DataProcessor();
			dataProcessor.calculateAvgPrice();
			dataProcessor.setData(data);
			Map<String,Double> actual = new HashMap<String,Double>();
			actual.put("G1", 37.5);
			actual.put("G2", 124.5);
			actual.put("G3", 116.1);
			Map<String,Double> expected =dataProcessor.getMap();
		}catch(NullPointerException ex){
			ex.getMessage();
		}
	}



}
