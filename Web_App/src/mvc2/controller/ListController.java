package mvc2.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import mvc2.model.MVCBoradDAO;
import mvc2.model.MVCBoardDTO;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ListController extends HttpServlet{

    MVCBoradDAO dao;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ServletContext application = this.getServletContext();
        MVCBoradDAO dao = new MVCBoradDAO(application);

        Map<String, Object> param = new HashMap<String, Object>();
        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");
        if (searchWord != null) {
            param.put("searchField", searchField);
            param.put("searchWord", searchWord);
        }
        int totalCount = dao.selectCount(param);// 게시물 수 확인

// 1. 전체 페이지 수 계산
        int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE")); // 10
        int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK")); // 5
        int totalPages = (int) Math.ceil((double) totalCount / pageSize); //전체 페이지수 계산 끝! 11

// 2. 현재 페이지 확인   2-1. 요청받은 페이지로 수정
        int pageNum = 1;
        String pageTemp = req.getParameter("pageNum");
        if (pageTemp != null && !pageTemp.equals("")) { //url을 직접 입력할 수도 있으니까! ""을 입력하면 안되도록
            pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정
        }

// 3. 목록에 출력할 게시물 범위 계산   3-1. 첫게시물 번호 start   3-2. 마지막 게시물 번호 end   3-3. param.put(start와 end)
        int start = (pageNum - 1) * pageSize + 1; //	현재 2페이지라고 하면, 첫번째 게시물 번호 11
        int end = pageNum * pageSize; // 20
        param.put("start", start);
        param.put("end", end);

        req.getRequestDispatcher("/view/List.jsp").forward(req, resp);

        List<MVCBoardDTO> boardLists = dao.selectListPage(param);  // 게시물 목록 받기
    }

        public void destroy() {
            dao.close();  // DB 연결 닫기
        }
    }

