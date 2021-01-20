package in.apcfss.service;

import in.apcfss.dbplugin.DbPlugin;

import in.apcfss.dto.Employeslip;
import in.apcfss.dto.Employee;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CRUDOPerations {
	   public static String addEmployeattendence(HttpServletRequest req, HttpServletResponse res) throws Exception
		{
			String status=null;
			Connection conn = null;
			PreparedStatement ps = null;
			String dayspresent=null;
			String sql=null;
			int addattendenceresult=0;
			
			try{  
				dayspresent = req.getParameter("dayspresent");
	            conn=DbPlugin.getConnection();  
	            
			}catch(Exception ex){
	        	ex.printStackTrace();
	        	System.out.println("exception cated for employyeeattendence");
	        } 
			
			return status;
		   
		}

	public static String AddEmployee(HttpServletRequest req, HttpServletResponse res) throws Exception
	{	Employee employee = null;

		String ename=null;
		String dob=null;
		String qualify=null;
		String gender =null;
		String joiningdate =null;
		String pancard =null;
		String salary=null;
		String dayspresent=null;
		String emname=null;
		int basic_pay=0;
		int basic_sal=0;
		int da=0;
		int hra=0;
		int pf=0;
		int pt=0;
		int net_salary=0;
	ename = req.getParameter("user");
	dob = req.getParameter("dob");
	
	qualify = req.getParameter("qualification");
	gender = req.getParameter("gender");
	joiningdate = req.getParameter("joiningdate");
	pancard = req.getParameter("pancard");
	salary = req.getParameter("salary");
	dayspresent= req.getParameter("dayspresent");
	
		String status = null;
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		int empdetailsUpdateResult=0;
		ResultSet rs=null;
		Statement stmt = null;
		String sql=null;
		String sql1=null;
		int eid =0;
		int employesalaryupdate = 0;
		//dayspresent=addEmployeattendence.dayspresent;
		try
		{
			employee = new Employee();
			conn = DbPlugin.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement( );
			basic_sal = Integer.parseInt(salary);
			basic_pay = (int) ((0.4)*(basic_sal));
			da = (int) ((0.3)*(basic_sal));
			hra = (int) ((0.3)*(basic_sal));  
			pf = (int) ((0.125)*(basic_sal));
			if(basic_sal<15000){
				pt=150;
			}else{
				pt=200;
			} 
			net_salary=((basic_sal) - (pf+pt));
			System.out.println("net salary:"+net_salary);
			System.out.println("basic pay: "+basic_pay);
			sql="select nextval('emp_seqence') as emp_id";

			rs=stmt.executeQuery(sql);
			
			while(rs.next())
			{
				eid = rs.getInt("emp_id");
			}	
			ps=conn.prepareStatement("INSERT INTO public.employeedetailsform(id, name, qualification, gender, dateofjoining, pancard, dob,salary,dayspresent) VALUES(?, ?, ?, ?, ?, ?, ?,?,?)");
			
			ps.setInt(1, eid);
			ps.setString(2,ename);
			ps.setString(3, qualify);
			ps.setString(4, gender);
			ps.setString(5, joiningdate);
			ps.setString(6, pancard); 
			ps.setString(7, dob);
			ps.setString(8, salary);
			ps.setString(9, dayspresent);
			
			empdetailsUpdateResult=ps.executeUpdate();  
			System.out.println(empdetailsUpdateResult);
			if(empdetailsUpdateResult>0){
					
				ps1=conn.prepareStatement("INSERT INTO public.employesalary(id, empname, dayspresent, pan, basicpay, da, hra, grosstotal, pf,pt, netsalary)  VALUES("+eid+", ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
				ps1.setString(1, ename);
				ps1.setString(2, dayspresent);
				ps1.setString(3, pancard);
				ps1.setInt(4, basic_pay);
				ps1.setInt(5, da);
				ps1.setInt(6, hra);
				ps1.setInt(7, basic_sal);
				ps1.setInt(8,pf );
				ps1.setInt(9, pt);
				ps1.setInt(10, net_salary);
				employesalaryupdate=ps1.executeUpdate();
				if(employesalaryupdate>0){
					conn.commit();
					status="success...";
				}else{
					conn.rollback();
					status="failure...";
				} 
				
			}else{
				System.out.println("error in employeedetailsresult..");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception();
		}
		finally
		{
			ps.close();
			conn.close();
		}
		return status;
	}
    public static Employee getEmployeeById(int id){  
    	Employee employee=new Employee();  
         String sql=null;
        try{  
            Connection con=DbPlugin.getConnection();  
            sql = "SELECT * FROM public.employeedetailsform where id=?";
            PreparedStatement ps=con.prepareStatement(sql);  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
            	employee.setId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setQualification(rs.getString(3));
				employee.setGender(rs.getString(4));
				employee.setDoj(rs.getString(5));
				employee.setPancard(rs.getString(6));
				employee.setDob(rs.getString(7));
				employee.setSalary(rs.getString(8));
				employee.setDayspresent(rs.getString(9));
				

            }  
            con.close();  
        }catch(Exception ex){
        	ex.printStackTrace();
        	System.out.println("exception cated for getting id employyee");
        }  
          
        return employee;  
    }  

	
	public static String updateEmployee(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		String status = null;
		int updateResult=0;
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		String sql=null;
		
	
		String name=null;
		String dob=null;
		String qualify=null;
		String gender =null;
		String joiningdate =null;
		String pancard =null;
		String salary =null;
		String dayspresent =null;

		name = req.getParameter("name");
		dob = req.getParameter("dob");
		qualify = req.getParameter("qualification");
		gender = req.getParameter("gender");
		joiningdate = req.getParameter("joiningdate");
		pancard = req.getParameter("pancard");
		salary = req.getParameter("salary");
		dayspresent = req.getParameter("dayspresent");

		try
		{

			int id=Integer.parseInt(req.getParameter("id"));
			conn = DbPlugin.getConnection();
			conn.setAutoCommit(false);
			sql = "UPDATE public.employeedetailsform SET  name=?, qualification=?, gender=?, dateofjoining=?, pancard=?, dob=? WHERE id=?";
			ps = conn.prepareStatement(sql);

			ps.setString(1,name);
			ps.setString(2, qualify);
			ps.setString(3, gender);
			ps.setString(4, joiningdate);
			ps.setString(5, pancard); 
			ps.setString(6, dob);
//			ps.setString(7, salary);
//			ps.setString(8, dayspresent);
			ps.setInt(7,id);
			updateResult=ps.executeUpdate();  
			
			System.out.println(updateResult);
			if(updateResult>0){
				ps = conn.prepareStatement("UPDATE public.employesalary SET  empname=?, salary=?, dayspresent=?  WHERE id=?");
				ps.setString(1,name);
				ps.setString(2,salary);
				ps.setString(3,dayspresent);
				ps.setInt(4,id);
				conn.commit();
				status="update";
				
			}
			else{
				conn.rollback();
				status="updatefail";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception();
		}
		finally
		{
			ps.close();
			conn.close();
		}
		return status;
	}
	
	public static String deleteEmployee(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		String status = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String sql= null;
		int del_id=0;
		int deleteresult = 0;
		int deleteresult1 = 0;
		
		try
		{
			conn = DbPlugin.getConnection();
			conn.setAutoCommit(false);
			sql = "DELETE FROM public.employesalary form WHERE id=?";
			del_id = Integer.parseInt(req.getParameter("id"));
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, del_id);
			
			
			deleteresult=pstmt.executeUpdate();
			if(deleteresult>0){
				pstmt1=conn.prepareStatement("DELETE FROM public.employeedetailsform WHERE id=?");
				pstmt1.setInt(1, del_id);
				deleteresult1=pstmt1.executeUpdate();
				if(deleteresult1>0){
					conn.commit();
					status="success";					
				}else{
					conn.rollback();
					status="failure..";
				}

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception();
		}
		finally
		{
			pstmt.close();
			conn.close();
		}
		return status;
	}
	
	public static List<Employee> viewEmployee(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Employee> employeeList = new ArrayList<Employee>();
		String sql = null;
		ResultSet rs = null;
		Employee employee = null;
		try
		{
			conn = DbPlugin.getConnection();
			sql =" SELECT id, name, qualification, gender, dateofjoining, pancard, dob,salary,dayspresent FROM public.employeedetailsform";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setQualification(rs.getString("Qualification"));
				employee.setGender(rs.getString("gender"));
				employee.setDoj(rs.getString("dateofjoining"));
				employee.setPancard(rs.getString("pancard"));
				employee.setDob(rs.getString("dob"));
				employee.setSalary(rs.getString("salary"));
				employee.setSalary(rs.getString("dayspresent"));
				employeeList.add(employee);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception();
		}
		finally
		{
			pstmt.close();
			conn.close();
		}
		return employeeList;
	}
	public static List<Employeslip> Employepayslip(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Employeslip> employeeList = new ArrayList<Employeslip>();
		String sql = null;
		ResultSet rs = null;
		Employeslip employee = null;
        int basic_sal=10000;
        int basic_pay=0;
		int da=0;
		int hra=0;
		int pf=0;
		int pt=0;
		int net_salary=0;        
		try
		{
			conn = DbPlugin.getConnection();
			sql =" SELECT dayspresent, pan, basicpay, da, hra, grosstotal, pf, pt, netsalary FROM public.employesalary";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			basic_pay = (int) ((0.4)*(basic_sal));
			da = (int) ((0.3)*(basic_sal));
			hra = (int) ((0.3)*(basic_sal));  
			pf = (int) ((0.125)*(basic_sal));
			if(basic_sal<15000){
				pt=150;
			}else{
				pt=200;
			}
			net_salary=((basic_sal) - (pf+pt));			
			while(rs.next())
			{
				employee = new Employeslip();
				employee.setDayspresent(rs.getInt("dayspresent"));
				employee.setBasicpay(basic_pay);
				employee.setDa(da);
				employee.setHra(hra);
				employee.setGrosstotal(basic_sal);
				employee.setPf(pf);
				employee.setPt(pt);
				employee.setNetsalary(net_salary);

				employeeList.add(employee);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception();
		}
		finally
		{
			pstmt.close();
			conn.close();
		}
		return employeeList;
	}

}
