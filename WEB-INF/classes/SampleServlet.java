import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class SampleServlet extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		HttpSession session = req.getSession();
		String name = (String)session.getAttribute("name");
		pw.println(name);
	}
}