package history.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import history.action.Action;
import history.action.ActionForward;
import history.action.DeleteAction;
import history.action.DeleteHistoryAction;
import history.action.InsertHistoryAction;
import history.action.ListAction;
import history.action.RemoconAction;
import history.model.HistoryDao;

/**
 * Servlet implementation class HistoryController
 */
@WebServlet("*.history")
public class HistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public HistoryController() {
        super();
    }

    public void doProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
     String url = request.getRequestURL().toString();
   	 System.out.println(url);
   	 
   	 Action action =null;
   	 ActionForward forward = null;
   	 
   	 if(url.indexOf("listAction.history") !=-1){
   		 action = new ListAction();
   		 try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
   	 }else if(url.indexOf("remoconAction.history") !=-1){
   		 action = new RemoconAction();
   		 try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
   	 }else if(url.indexOf("deleteAction.history") !=-1){
   		 action = new DeleteAction();
   		 try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
   	 }else if(url.indexOf("deleteHistoryAction.history") !=-1){
   		 action = new DeleteHistoryAction();
   		 try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
   	 }else if(url.indexOf("insertAction.history") !=-1){
   		 action = new InsertHistoryAction();
   		 try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
   	 }
   	if(forward != null){
		if(forward.isRedirect()){
			response.sendRedirect(forward.getPath());
		}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 doProcess(request, response);
	}

}
