package HG_PDFUtility.v1;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.FileOutputStream;
import java.io.OutputStream;
// --- <<IS-END-IMPORTS>> ---

public final class util

{
	// ---( internal utility methods )---

	final static util _instance = new util();

	static util _newInstance() { return new util(); }

	static util _cast(Object o) { return (util)o; }

	// ---( server methods )---




	public static final void createPDF (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(createPDF)>> ---
		// @sigtype java 3.5
		// [i] field:0:required htmlContent
		// [i] field:0:required path
		// [i] field:0:required fileName
		// [o] field:0:required message
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
			String	htmlContent = IDataUtil.getString( pipelineCursor, "htmlContent" );
			String	path = IDataUtil.getString( pipelineCursor, "path" );
			String	fileName = IDataUtil.getString( pipelineCursor, "fileName" );
		pipelineCursor.destroy();
		String message=convertToPDF(htmlContent,path+"/"+fileName);
		
		// pipeline
		// pipeline
		IDataCursor pipelineCursor_1 = pipeline.getCursor();
		IDataUtil.put( pipelineCursor_1, "message", message );
		pipelineCursor_1.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static String convertToPDF(String htmlData,String fullPath)throws ServiceException
	{
		try{
			OutputStream os=new FileOutputStream(fullPath);
			ITextRenderer renderer= new ITextRenderer();
			renderer.setDocumentFromString(htmlData);
			renderer.layout();
			renderer.createPDF(os);
			return "Succesful";
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}
	// --- <<IS-END-SHARED>> ---
}

