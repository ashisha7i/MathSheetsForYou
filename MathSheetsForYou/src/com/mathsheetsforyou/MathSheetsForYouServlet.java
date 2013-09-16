package com.mathsheetsforyou;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MathSheetsForYouServlet extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		//System.out.println("Posted :)");
		String strChildName = request.getParameter("childName");
		String strSheetType = request.getParameter("quesType");
		String strTotalQuestions = request.getParameter("totalQuestions");
		String strMaxSum = request.getParameter("maxSum");
		String strMaxNum = request.getParameter("maxNum");
		String strAllowZero = request.getParameter("allowZero");

		MBUtil util = new MBUtil();
		Map<Integer, MBSum> map = util.generateSums(strSheetType,
				Integer.parseInt(strTotalQuestions),
				Integer.parseInt(strMaxSum), Integer.parseInt(strMaxNum), 0, strAllowZero);
		ByteArrayOutputStream baos = util.generateSheetPdf(strChildName, strSheetType,
				Integer.parseInt(strTotalQuestions), map);

		response.setHeader("Content-disposition", "attachment; filename=mathsheetsforyou"
				+ getFileSuffix() + ".pdf");
		response.setHeader("Content-Description", "File Transfer");
	    response.setHeader("Content-Type","application/octet-stream");
	    response.setHeader("Content-Type", "application/force-download");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Cache-Control", "must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setContentLength(baos.size());

		ServletOutputStream sos;
		sos = response.getOutputStream();
		baos.writeTo(sos);
		sos.flush();
	}
	
	
	private String getFileSuffix(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		return sdf.format(new Date());
	}

}
