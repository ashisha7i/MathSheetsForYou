package com.mathsheetsforyou;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class MBUtil {

	private static final int NO_OF_COLUMNS = 7;

	public Map<Integer, MBSum> generateSums(String type, int count, int maxSum, int maxNum,
			int maxDiff, String allowZero) {
		
		Map<Integer, MBSum> results = new HashMap<Integer, MBSum>();

		
		if (type != null) {
			if (type.equals("add")) {
				results = generateAdditionSumsData(count, maxSum, allowZero);
			} else if (type.equals("sub")) {
				results = generateSubtractionSumsData(count, maxNum, allowZero);
			}
		}
		return results;
	}

	private Map<Integer, MBSum> generateSubtractionSumsData(int count, int maxNum, String allowZero) {
		int counter = 0;
		int high = maxNum;
		int low = 1;
		int numA, numB;

		if(allowZero != null){
			low = 0; // If Zero allowed 
		}
		
		Map<Integer, MBSum> sumMap = new HashMap();

		Random r = new Random();
		while (true) {
			numA = r.nextInt(high - low) + low; // Random number between 'high'
												// and 'low'
			numB = r.nextInt(high - low) + low; // Random number between 'high'
												// and 'low'

			// Check if the sum is what we want and the first number is bigger
			// than the second number
			if ((numA + numB <= maxNum) && (numA > numB)) {

				MBSum sum = new MBSum();
				sum.setNumberA(numA);
				sum.setNumberB(numB);

				// Check for uniqueness (add only if unique)
				if (!sumMap.containsValue(sum)) {
					counter++;
					sumMap.put(Integer.valueOf(counter), sum);
					//System.out.printf("%02d) A: %-2d, B: %-2d, Sum: %-2d\n",counter, numA, numB, numA + numB);
				}

				if (counter >= count) {
					break;
				}
			}
		}
		return sumMap;
	}

	/**
	 * Method to generate the Addition sums
	 * 
	 * @param count
	 * @param maxSum
	 * @return
	 */
	private Map<Integer, MBSum> generateAdditionSumsData(int count, int maxSum, String allowZero) {
		int counter = 0;
		int high = maxSum;
		int low = 1;
		int numA, numB;

		if(allowZero != null){
			low = 0;
		}
		
		Map<Integer, MBSum> sumMap = new HashMap<Integer, MBSum>();

		Random r = new Random();
		while (true) {
			numA = r.nextInt(high - low) + low; // Random number between 'high'
												// and 'low'
			numB = r.nextInt(high - low) + low; // Random number between 'high'
												// and 'low'

			// Check if the sum is what we want and the first number is bigger
			// than the second number
			if ((numA + numB <= maxSum) && (numA > numB)) {

				MBSum sum = new MBSum();
				sum.setNumberA(numA);
				sum.setNumberB(numB);
				
				// TODO :: Disabled the unique check for now
				
				// Check for uniqueness (add only if unique)
				if (!sumMap.containsValue(sum)) {
					counter++;
					sumMap.put(Integer.valueOf(counter), sum);
					//System.out.printf("%02d) A: %-2d, B: %-2d, Sum: %-2d\n",
							//counter, numA, numB, numA + numB);
				}

				if (counter >= count) {
					break;
				}
			}
		}
		return sumMap;
	}

	public ByteArrayOutputStream generateSheetPdf(String childName, String type, int count, Map<Integer, MBSum> dataMap) {

		ByteArrayOutputStream baosPDF = new ByteArrayOutputStream();
		String sign = "";
		if(type.equals("add")){
			sign="+";
		} else if(type.equals("sub")){
			sign = "-";
		}
		
		Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
		try {
			PdfWriter writer = PdfWriter.getInstance(doc, baosPDF);
			writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));

			HeaderFooter event = new HeaderFooter();
            writer.setPageEvent(event);
            
			doc.open();
			
			Paragraph p1 = null;
			String printName = "                  ";
			if(childName != null && !childName.trim().equals("")){
				printName = String.format("%-18s", childName);
			}
			
			Chunk nameUnderline = new Chunk(printName);
		    nameUnderline.setUnderline(0.1f, -2f);
		    BaseColor bc = new BaseColor(228, 227, 230);
		    
		    // Create header table for sheet
		    float[] layout = new float[] {16.66f,16.66f,16.66f,16.66f,16.66f,16.7f};
		    Font fontHeader1 = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
		    Font fontHeader2 = FontFactory.getFont(FontFactory.COURIER, 11);
		    PdfPTable th = new PdfPTable(layout);
		    PdfPCell ch1 = new PdfPCell(new Phrase("Name : \n",fontHeader1)); 
		    ch1.setBorder(PdfPCell.NO_BORDER);
		    PdfPCell ch2 = new PdfPCell(new Phrase(printName, fontHeader1));
		    ch2.setColspan(3);
		    ch2.setBorder(PdfPCell.NO_BORDER);
		    PdfPCell ch3 = new PdfPCell(new PdfPCell(new Phrase("Date : ", fontHeader1)));
		    ch3.setBorder(PdfPCell.NO_BORDER);
		    PdfPCell ch4 = new PdfPCell(new PdfPCell(new Phrase("", fontHeader1)));
		    ch4.setBorder(PdfPCell.NO_BORDER);
		    PdfPCell ch5 = new PdfPCell(new Phrase("Correct :", fontHeader2));
		    ch5.setBackgroundColor(bc);
		    ch5.setPadding(2f);
		    PdfPCell ch6 = new PdfPCell(new Phrase(""));
		    PdfPCell ch7 = new PdfPCell(new Phrase("Wrong : ", fontHeader2));
		    ch7.setBackgroundColor(bc);
		    ch7.setPadding(2f);
		    PdfPCell ch8 = new PdfPCell(new Phrase(""));
		    PdfPCell ch9 = new PdfPCell(new Phrase("Score : ", fontHeader2));
		    ch9.setBackgroundColor(bc);
		    ch9.setPadding(2f);
		    PdfPCell ch10 = new PdfPCell(new Phrase(""));
		    
		    th.addCell(ch1);
		    th.addCell(ch2);
		    th.addCell(ch3);
		    th.addCell(ch4);
		    th.addCell(ch5);
		    th.addCell(ch6);
		    th.addCell(ch7);
		    th.addCell(ch8);
		    th.addCell(ch9);
		    th.addCell(ch10);

		    
		    
		    
			/*
		    p1 = new Paragraph();
			p1.setFont(FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD));
			p1.add(new Chunk("Name : "));
			p1.add(nameUnderline);
			p1.add(new Chunk(" Start : _________ End : _________    Score : __________"));
			*/
					
			Paragraph p2 = new Paragraph();

			PdfPTable t = new PdfPTable(NO_OF_COLUMNS);
			t.setSpacingBefore(2);
			t.setSpacingAfter(2);

			// PdfPCell c1 = new PdfPCell(new Phrase("Header1"));
			// t.addCell(c1);
			//
			// PdfPCell c2 = new PdfPCell(new Phrase("Header2"));
			//
			// t.addCell(c2);
			//
			// PdfPCell c3 = new PdfPCell(new Phrase("Header3"));

			// t.addCell(c3);

			PdfPCell defaultCell = t.getDefaultCell();
			defaultCell.setBorder(PdfPCell.NO_BORDER);
			defaultCell.setPadding(5);

			Iterator<Integer> itr = dataMap.keySet().iterator();
			
			while (itr.hasNext()) {
				Integer key = (Integer) itr.next();
				MBSum sum = (MBSum) dataMap.get(key);
				PdfPCell cell = new PdfPCell();
				Chunk sumText1 = new Chunk(String.format("%4d",sum.getNumberA()),FontFactory
						.getFont(FontFactory.COURIER, 14));
				Chunk sumText2 = new Chunk(String.format("%1s%3d\n", sign, sum.getNumberB()),FontFactory
						.getFont(FontFactory.COURIER, 14));
				sumText2.setUnderline(0.7f, -2f);
				Chunk sumAnsSpaces = new Chunk("____",FontFactory.getFont(FontFactory.COURIER, 14));
				//sumAnsSpaces.setUnderline(0.1f, -2f);
				cell.addElement(sumText1);
				cell.addElement(sumText2);
				cell.addElement(sumAnsSpaces);
				cell.setBorder(PdfPCell.NO_BORDER);
				
				
				t.addCell(cell);
			}

			int fillerCount = NO_OF_COLUMNS - (count % NO_OF_COLUMNS);
			//System.out.println("--->" + fillerCount);
			for (int i = 0; i < fillerCount; i++) {
				t.addCell("");
			}

			doc.add(th);
			doc.add(t);

			//doc.add(p1);
			//doc.add(p2);
			doc.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return baosPDF;
	}
	
	 /** Inner class to add a header and a footer. */
    static class HeaderFooter extends PdfPageEventHelper {

        public void onEndPage (PdfWriter writer, Document document) {
            Rectangle rect = writer.getBoxSize("art");
            //switch(writer.getPageNumber() % 2) {
            //    case 0:
                    ColumnText.showTextAligned(writer.getDirectContent(),
                        Element.ALIGN_RIGHT, new Phrase("Page " + writer.getCurrentPageNumber(),FontFactory.getFont(FontFactory.COURIER, 8)),
                        rect.getRight(), rect.getTop()-10, 90);
           //         break;
           //     case 1:
           //         ColumnText.showTextAligned(writer.getDirectContent(),
           //             Element.ALIGN_LEFT, new Phrase("odd header"),
           //             rect.getLeft(), rect.getTop(), 0);
           //         break;
           // }
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase( "Created By Free Math Sheet Generator @ http://mathsheetsforyou.appspot.com", FontFactory.getFont(FontFactory.COURIER, 8)),
                    (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
        }
    }

}
