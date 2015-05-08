package com.sds.icto.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sds.icto.guestbook.dao.GuestBookDao;
import com.sds.icto.guestbook.vo.GuestBookVo;
import com.sds.icto.web.Action;

@WebServlet("/GuestBookServlet")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String a = request.getParameter("a");
		
		Action action = null;
		if("delete".equals(a)){
			String id = request.getParameter("id");
			System.out.println(id);
			request.setAttribute("id", id);
			RequestDispatcher requestDispatcher = 
				    request.getServletContext().getRequestDispatcher("/view/deleteform.jsp");
			requestDispatcher.forward(request, response);
	
		}else if("deletedo".equals(a)){
			
			try {
				String password = request.getParameter("password");
				String no = request.getParameter("id");
				GuestBookDao dao = new GuestBookDao();
				System.out.println(no+":"+password);
				dao.guestBookDelete(no, password);
				response.sendRedirect("/guestBook2/GuestBookServlet");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		else if("add".equals(a)){
			
			try {
				request.setCharacterEncoding("utf-8");
				
				GuestBookVo vo = new GuestBookVo();
				
				String name = request.getParameter("name");
				String password = request.getParameter("pass");	
				String content = request.getParameter("content");
				
				vo.setName(name);
				vo.setPassword(password);
				vo.setMessage(content);
				
				GuestBookDao dao = new GuestBookDao();
				dao.guestBookInsert(vo);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			response.sendRedirect("/guestBook2/GuestBookServlet");
		}
		else{
			try {
				GuestBookDao dao = new GuestBookDao();
				List<GuestBookVo> list;
				list = dao.fetch();
				request.setAttribute("list", list);
				RequestDispatcher requestDispatcher = 
					    request.getServletContext().getRequestDispatcher("/view/show_guestBookList.jsp");
				requestDispatcher.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
