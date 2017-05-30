package Category.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Category.action.Action;
import Category.action.CategoryClick;
import Category.action.CategoryClick2;
import Category.action.CategoryFilter;
import Category.action.CategoryFilter2;
import Category.action.MainAction;



@WebServlet("*.csh")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CategoryController() {
        super();
    }
    
    public void doGetPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
    	String getURI = request.getRequestURI();
    	String getContextURI = request.getContextPath();
    	String myDo = getURI.substring(getContextURI.length()+1,getURI.length());  
    	
    	Action action = null;
    	if(myDo.equals("category.csh")){
    		action = new CategoryClick();
    		 action.execute(request, response);
    	}else if(myDo.equals("categoryfilter.csh")){
    		action = new CategoryFilter();
    		action.execute(request, response);
    	}else if(myDo.equals("main.csh")){
    		action = new MainAction();
    		action.execute(request, response);
    	}else if(myDo.equals("categoryfilter2.csh")){
    		action = new CategoryFilter2();
   		 action.execute(request, response);
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetPost(request,response);
	}
	
	

}
