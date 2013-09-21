package com.mathsheetsforyou;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MathSheetsForYouServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// System.out.println("Posted :)");
		String strChildName = request.getParameter("childName");
		String strSheetType = request.getParameter("quesType");
		String strTotalQuestions = request.getParameter("totalQuestions");
		String strMaxSum = request.getParameter("maxSum");
		String strMaxNum = request.getParameter("maxNum");
		String strAllowZero = request.getParameter("allowZero");
		String strEmailResults = request.getParameter("emailToCB");
		String strEmailResultsTo = request.getParameter("emailTo");

		if(strEmailResults != null){
			sendPdfInMail(strChildName, strSheetType, strTotalQuestions, strMaxSum, strMaxNum, strAllowZero, strEmailResultsTo, request, response);
		} else {
			generatePdfToPrint(strChildName, strSheetType, strTotalQuestions, strMaxSum, strMaxNum, strAllowZero, response);
		}
	}
	
	/**
	 * Generate PDF file with the sums as per the user criteria and push the file back to the browser for handling/download.
	 * 
	 * @param strChildName
	 * @param strSheetType
	 * @param strTotalQuestions
	 * @param strMaxSum
	 * @param strMaxNum
	 * @param strAllowZero
	 * @param response
	 * @throws IOException
	 */
	private void generatePdfToPrint(String strChildName, String strSheetType,
			String strTotalQuestions, String strMaxSum, String strMaxNum,
			String strAllowZero, HttpServletResponse response)
			throws IOException {
		MBUtil util = new MBUtil();
		Map<Integer, MBSum> map = util.generateSums(strSheetType,
				Integer.parseInt(strTotalQuestions),
				Integer.parseInt(strMaxSum), Integer.parseInt(strMaxNum), 0,
				strAllowZero);
		ByteArrayOutputStream baos = util.generateSheetPdf(strChildName,
				strSheetType, Integer.parseInt(strTotalQuestions), map);

		response.setHeader("Content-disposition",
				"attachment; filename=mathsheetsforyou" + getFileSuffix()
						+ ".pdf");
		response.setHeader("Content-Description", "File Transfer");
		response.setHeader("Content-Type", "application/octet-stream");
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

	
	/**
	 * Generate PDF file with the sums as per the user criteria and push the file back to the browser for handling/download.
	 * 
	 * @param strChildName
	 * @param strSheetType
	 * @param strTotalQuestions
	 * @param strMaxSum
	 * @param strMaxNum
	 * @param strAllowZero
	 * @param response
	 * @throws IOException
	 */
	private void sendPdfInMail(String strChildName, String strSheetType,
			String strTotalQuestions, String strMaxSum, String strMaxNum,
			String strAllowZero, String strEmailResultsTo, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		MBUtil util = new MBUtil();
		Map<Integer, MBSum> map = util.generateSums(strSheetType,
				Integer.parseInt(strTotalQuestions),
				Integer.parseInt(strMaxSum), Integer.parseInt(strMaxNum), 0,
				strAllowZero);
		
		ByteArrayOutputStream baos = util.generateSheetPdf(strChildName,
				strSheetType, Integer.parseInt(strTotalQuestions), map);

		byte[] bytesOut = baos.toByteArray();
		
		// Mailing logic here
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String fromAddress = (String)request.getSession().getAttribute("USER_NAME");
        String msgBody = "Dear User, \nPlease see attached PDF that you generated at http://mathsheetsforyou.appspot.com\n\nThanks for using this free service,\n- MathSheetsForYou Team";

        try {
            Message msg = new MimeMessage(session);
            
            Multipart multipart = new MimeMultipart();
            
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(msgBody);
            
            
            MimeBodyPart messagePDFPart = new MimeBodyPart();
            DataSource dataSource = new ByteArrayDataSource(bytesOut, "application/pdf");
            messagePDFPart.setHeader("Content-Transfer-Encoding", "base64");
            messagePDFPart.setDataHandler(new DataHandler(dataSource));
            messagePDFPart.setFileName("mathsheetsforyou-"+getFileSuffix()+".pdf");
            
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(messagePDFPart);
            
            msg.setHeader("Content-Type", "multipart/mixed");
            msg.setFrom(new InternetAddress(fromAddress, "MathSheetsForYou as " + fromAddress));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(strEmailResultsTo, "MathSheetsForYou User"));
            msg.setSubject("Your MathSheetsForYou generated Worksheet");
            msg.setSentDate(new Date());
            msg.setContent(multipart);
            
            
            Transport.send(msg);

        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        }
		
		/*
		response.setHeader("Content-disposition",
				"attachment; filename=mathsheetsforyou" + getFileSuffix()
						+ ".pdf");
		response.setHeader("Content-Description", "File Transfer");
		response.setHeader("Content-Type", "application/octet-stream");
		response.setHeader("Content-Type", "application/force-download");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Cache-Control", "must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setContentLength(baos.size());
		*/
		
		ServletOutputStream sos;
		sos = response.getOutputStream();
		baos.writeTo(sos);
		sos.flush();
	}

	
	
	/**
	 * Get the File Suffix (Date format in 'yyMMddHHmmss')
	 * @return
	 */
	private String getFileSuffix() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		return sdf.format(new Date());
	}

}
