package com.epam.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * Servlet implementation class NewFileServlet
 */
public class NewFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		response.setContentType("text/html");
		String absolutePath = request.getParameter("uploadfile");
		File file = new File(absolutePath);
		String fileName=file.getName();
		PrintWriter out = response.getWriter();
		out.println(absolutePath);
		UploadFile uploadFile = new UploadFile();
		//extracting file properties of any type.....
		uploadFile.fileProperties(file,absolutePath);
	
		//String message = "";
		//String[] exe = file.getName().split("\\.");
		//for (int i = 0; i < exe.length; i++) {
			if (fileName.contains(".txt") || fileName.contains(".text")) {
				System.out.println("this is new servlet.....");
				 uploadFile.readTextfile(file);
			} 
			else if (fileName.contains(".docx") || fileName.contains(".doc")) {
				// WordExtractor extractor = null;
				FileInputStream fis = new FileInputStream(file.getAbsolutePath());
				XWPFDocument document = new XWPFDocument(fis);
				XWPFWordExtractor extractor = new XWPFWordExtractor(document);
				List list=document.getParagraphs();
				uploadFile.setParagraphCount(list.size());

				// extractor = new WordExtractor(document);
				String[] fileData = null;
				// XWPFWordExtractor fileData = extractor.getText();
				
				uploadFile.setMessage("plz Upload a readable text file to know more information..");
				// HWPFDocument doc=new HWPFDocument(file.getAbsoluteFile());
				// WordExtractor we=new WordExtractor();
			}
			else {
				uploadFile.otherFiles(file);
				//uploadFile.setMessage(file.getName() + " is not a Readable File");
			}

		//}

		request.setAttribute("myfile", uploadFile);
		RequestDispatcher dispatch = request.getRequestDispatcher("final.jsp");
		dispatch.forward(request, response);

	}

}
