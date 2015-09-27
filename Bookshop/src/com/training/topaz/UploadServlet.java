package com.training.topaz;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.imageio.spi.OutputStreamImageOutputStreamSpi;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		PrintWriter out=response.getWriter();
		String filePath="images\\";
		String filePathExt="C:\\workspace\\Discussion Board\\WebContent\\userdata\\1.jpg";
		//String filePath="D:\\Pages\\";
		File file;
		int maxMemSize=1024*1024;
		int maxFileSize=1024*1024;
		String contentType=request.getContentType();
		//System.out.println(contentType.indexOf("multipart/form-data"));
		if(contentType.indexOf("multipart/form-data")>=0)
		{
			DiskFileItemFactory factory = new DiskFileItemFactory();
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);
		      // Location to save data that is larger than maxMemSize.
		      factory.setRepository(new File("c:\\temp"));

		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
		      try{ 
		         // Parse the request to get file items.
		         List<?> fileItems = upload.parseRequest(request);

		         // Process the uploaded file items
		         Iterator<?> i = fileItems.iterator();
		         while ( i.hasNext () ) 
		         {
		        	 //File f=(File) i.next();
		            FileItem fi = (FileItem)i.next();
		            if ( !fi.isFormField () )	
		            {
		            // Get the uploaded file parameters
		            String fieldName = fi.getFieldName();
		            String fileName = fi.getName();
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();
		            System.out.println(fileName.lastIndexOf("\\"));
		            // Write the file
		            if( fileName.lastIndexOf("\\") >= 0 ){
		            file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
		            }else{
		            file = new File( filePath + 
		            fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }
		            fi.write( file ) ;

		            }
		            }
		      }
		         catch(Exception e){
		        	 e.printStackTrace();
		         }
		      
		}
		
	
	}

}
