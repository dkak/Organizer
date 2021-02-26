import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {

	public static void main(String[] args) {

		ArrayList<Product> productsList=new ArrayList<Product>();
		
    	ArrayList<String> exampleList = new ArrayList<String>();
    	{  
    		
    		//Reading Excel File
    		try{  
    			
    			
    			File file = new File("C:\\Users\\JK\\Desktop\\stock.xlsx");   //creating a new file instance  
    			
    			FileInputStream fis =new FileInputStream(file);
    			
    			XSSFWorkbook wb =new XSSFWorkbook(fis);  //creating Workbook instance that refers to .xlsx file  

    			
    			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object  
	    		
    			DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale

    			
    			//Counting the written rows
    			int totalRows=0; 
    			
    			Cell cell=sheet.getRow(totalRows).getCell(0);

    			while( !formatter.formatCellValue(cell).isEmpty()) {
    				totalRows++;
        			cell=sheet.getRow(totalRows).getCell(0);

    			}
    			
    			
    			//Reading File By Row-Column
    			int i=0;
	    		while(i<totalRows)                 
	    		{  
	    			if(i==0) i++; //avoid the column titles
	    			
			    	Row row = sheet.getRow(i);    
	    		
	    			String text="";
	    			
		    		for(int j=0; j<row.getLastCellNum(); j++)   
		    		{  	
		    			cell = sheet.getRow(i).getCell(j);
		    			if(j>0) {
			    			text+=formatter.formatCellValue(cell)+"__";
		    			}
		    		} 
		    		exampleList.add(text);
		    		i++;
	    		}  
    		}
    		
    		catch(Exception e)  
    		{      			
    			e.printStackTrace();  
    		}
    	}  
    	
    	for(String s : exampleList) {
    		
    		String[] table=s.split("__");
    		Product p1= new Product(table[0],table[1],table[2]);
    		
    		productsList.add(p1);
    	}
   
    	new FilterJList(productsList);

	}  
	

}

	

