import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class DonorRegistration extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		int contact_no = Integer.parseInt(req.getParameter("contact_no"));
		String donor_type = req.getParameter("donor_type");

		PrintWriter print = res.getWriter();
		print.println("<html><body><center>Thank you for registering with us "+name+"!");
	}
}