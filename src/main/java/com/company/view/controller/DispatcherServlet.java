package com.company.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.Model2.board.BoardDAO;
import com.company.Model2.board.BoardDO;
import com.company.Model2.user.UserDAO;
import com.company.Model2.user.UserDO;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DispatcherServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));

		if (path.equals("/login.do")) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			UserDO userDO = new UserDO();
			userDO.setId(id);
			userDO.setPassword(password);

			UserDAO userDAO = new UserDAO();
			UserDO user = userDAO.getUser(userDO);

			if (user != null) {
				
				 HttpSession session = request.getSession(); 
				 session.setAttribute("IdKey",id); 
				 response.sendRedirect("getBoardList.do");


			} else {
				response.sendRedirect("login.jsp");
			}

		} else if (path.equals("/getBoardList.do")) {

			// 검색 대상(제목 또는 작성자) 및 검색 텍스트 객체를 저장할 변수 설정
			String searchField = ""; // 검색 대상(제목 또는 작성자)
			String searchText = ""; // 검색 텍스트 객체 레퍼런스 변수

			if (request.getParameter("searchCondition") != "" && request.getParameter("searchKeyword") != "") { // &&(and)

				searchField = request.getParameter("searchCondition");
				searchText = request.getParameter("searchKeyword");
			}
			// BoardDAO 클래스 객체 생성
			BoardDAO boardDAO = new BoardDAO();

			List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);

			/*
			 * HttpSession session = request.getSession(); session.setAttribute("boardList",
			 * boardList);
			 */

			/* response.sendRedirect("getBoardList.jsp"); */

			request.setAttribute("boardList", boardList);
			RequestDispatcher rd = request.getRequestDispatcher("getBoardList.jsp");
			rd.forward(request, response);

		} else if (path.equals("/getBoard.do")) {
			System.out.println("게시글 상세보기 처리됨");

			String seq = request.getParameter("seq");

			BoardDO boardDO = new BoardDO();
			boardDO.setSeq(Integer.parseInt(seq));

			BoardDAO boardDAO = new BoardDAO();
			BoardDO board = boardDAO.getBoard(boardDO);

			HttpSession session = request.getSession();
			session.setAttribute("board", board);

			response.sendRedirect("getBoard.jsp");

		} else if (path.equals("/insertBoard.do")) {
			System.out.println("게시글 입력 처리됨");

			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");

			BoardDO boardDO = new BoardDO();

			boardDO.setTitle(title);
			boardDO.setWriter(writer);
			boardDO.setContent(content);

			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(boardDO);

			response.sendRedirect("getBoardList.do");

		} else if (path.equals("/updateBoard.do")) {

			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			String seq = request.getParameter("seq");
			
			BoardDO boardDO = new BoardDO();
			boardDO.setTitle(title);
			boardDO.setWriter(writer);
			boardDO.setContent(content);
			boardDO.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateBoard(boardDO);
			
			
			response.sendRedirect("getBoardList.do");
			
		} else if (path.equals("/deleteBoard.do")) {

			String seq = request.getParameter("seq");
			
			BoardDO boardDO = new BoardDO();

			boardDO.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(boardDO);
			
			response.sendRedirect("getBoardList.do");
			
		} else if (path.equals("/logout.do")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
	}

}
