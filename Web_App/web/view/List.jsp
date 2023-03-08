<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="mvc2.model.MVCBoradDAO"%>
<%@ page import="mvc2.model.MVCBoardDTO"%>
<%@ page import="utils.BoardPage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비회원제 게시판</title>
</head>
<body>
<%--<jsp:include page="../Common/Link.jsp" />  <!-- 공통 링크 -->--%>


<h2>파일 첨부형 게시판 - 목록 보기(List)</h2>-현재페이지 : <%= pageNum %> (전체 : <%= totalPages %>)
<!-- 검색폼 -->
<form method="get">
    <table border="1" width="90%">
        <tr>
            <td align="center">
                <select name="searchField">
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                </select>
                <input type="text" name="searchWord" />
                <input type="submit" value="검색하기" />
            </td>
        </tr>
    </table>
</form>
<!-- 게시물 목록 테이블(표) -->
<table border="1" width="90%">
    <!-- 각 칼럼의 이름 -->
    <tr>
        <th width="10%">번호</th>
        <th width="50%">제목</th>
        <th width="15%">작성자</th>
        <th width="10%">조회수</th>
        <th width="15%">작성일</th>
        <th width="15%">첨부</th>
    </tr>
    <!-- 목록의 내용 -->
<%--    <%--%>
<%--        if (boardLists.isEmpty()) {--%>
<%--            // 게시물이 하나도 없을 때--%>

<%--    %>--%>
    <c:choose test="${empty boardLists}" var="result">
        <tr>
            <td colspan="5" align="center">
                등록된 게시물이 없습니다^^*
            </td>
        </tr>
    </c:choose>

    <c:choose>
        <c:when test="${empty boardLists}">
            <tr>
                <td colspan="5" align="center">
                    등록된 게시물이 없습니다^^*
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach varStatus="loop" items="boardLists">
                ${totalCount - (((pageNum - 1) * pageSize)+loop.index)}
                <tr align="center">
                    <td>${ virtualNum }</td>  <!--게시물 번호-->
                    <td align="left">  <!--제목(+ 하이퍼링크)-->
                        <a href="View.jsp?num=<%= dto.getIdx() %>"><%= dto.getTitle() %></a>
                    </td>
                    <td align="center"><%= dto.getName() %></td>          <!--작성자 이름-->
                    <td align="center"><%= dto.getVisitcount() %></td>  <!--조회수-->
                    <td align="center"><%= dto.getPostdate() %></td>    <!--작성일-->
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>

<%--    <%--%>
<%--    }--%>
<%--    else {--%>
<%--        // 게시물이 있을 때--%>
<%--        int virtualNum = 0;  // 화면상에서의 게시물 번호--%>
<%--        int countNum = 0;--%>

<%--        for (MVCBoardDTO dto : boardLists)--%>
<%--        {--%>
<%--            //  virtualNum = totalCount--;  // 전체 게시물 수에서 시작해 1씩 감소--%>
<%--            virtualNum = totalCount - (((pageNum - 1) * pageSize)+countNum++);--%>
<%--    %>--%>


<%--    <tr align="center">--%>
<%--        <td><%= virtualNum %></td>  <!--게시물 번호-->--%>
<%--        <td align="left">  <!--제목(+ 하이퍼링크)-->--%>
<%--            <a href="View.jsp?num=<%= dto.getIdx() %>"><%= dto.getTitle() %></a>--%>
<%--        </td>--%>
<%--        <td align="center"><%= dto.getName() %></td>          <!--작성자 이름-->--%>
<%--        <td align="center"><%= dto.getVisitcount() %></td>  <!--조회수-->--%>
<%--        <td align="center"><%= dto.getPostdate() %></td>    <!--작성일-->--%>
<%--    </tr>--%>
<%--    <%--%>
<%--            }--%>
<%--        }--%>
<%--    %>--%>
</table>
<!--목록 하단의 [글쓰기] 버튼-->
<table border="1" width="90%">
    <tr align="center">
        <!-- 페이징 처리 출력 -->
        <td>
            <%= BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI()) %>
        </td>

        <!-- 글쓰기 버튼 -->
        <td><button type="button" onclick="location.href='Write.jsp';">글쓰기
        </button></td>
    </tr>
</table>
</body>
</html>