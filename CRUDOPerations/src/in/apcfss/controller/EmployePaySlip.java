package in.apcfss.controller;

import in.apcfss.dto.Employee;
import in.apcfss.dto.Employeslip;
import in.apcfss.service.CRUDOPerations;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployePaySlip extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	{
		PrintWriter out = null;
		res.setContentType("text/html");  
        int basic_sal=10000;
        int basic_pay=0;
        List<Employeslip> employeslipList = null;
        List<Employee> employeesList = null;
		try
		{
			out=res.getWriter();  
	        out.println("<h1>Employees List</h1>"); 
			employeslipList = CRUDOPerations.Employepayslip(req, res);
			employeesList=CRUDOPerations.viewEmployee(req,res);
			out.print("<table border='1' width='100%'");  
	        out.print("<tr><th>EmpId</th><th>Name</th><th>Pancard</th><th>grosstotal</th><th>dayspresent</th><th>basicpay</th><th>DA</th><th>HRA</th><th>PF</th><th>PT</th><th>NETSALARY</th></tr>");  
	        basic_pay = (int) ((0.4)*(basic_sal));

			for(Employee e:employeesList)
			{
				
				 out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td>" +
				 		"<td>"+e.getPancard()+"</td>");  
				
					
			}
	        for(Employeslip emp:employeslipList){
	        	out.print("<td>"+emp.getGrosstotal()+"</td><td>"+emp.getDayspresent()+"</td> <td>"+emp.getBasicpay()+"</td><td>"+emp.getDa()+"</td><td>"+emp.getHra()+"</td><td>"+emp.getPf()+"</td><td>"+emp.getPt()+"</td><td>"+emp.getNetsalary()+"</td></tr>");
	        }
			
	        out.print("</table>");  
	        
	        
		}
		catch(Exception e)
		{
			out.print("<h5>Problem in processing your request. Please try again.</h5>");  
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
	}

}
