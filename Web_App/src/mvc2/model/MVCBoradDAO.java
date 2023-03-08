//package mvc2.model;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//
//import common.JDBConnection;
//
//public class MVCBoradDAO extends JDBConnection {
//    public MVCBoradDAO(ServletContext application) {
//        super(application);
//    }
//
//    public int updateEdit(MVCBoardDTO dto) { // 동하
//        int result = 0;
//        String sqlcorrection = "UPDATE BOARD SET title= ?,content=? WHERE num =?";
//        String sqlSearchId = "SELECT num FROM BOARD WHERE num=?";
//        try {
//            psmt = con.prepareStatement(sqlSearchId);
//            psmt.setString(1, dto.getIdx());
//            rs = psmt.executeQuery();
//
//            if (rs.next()) {
//                psmt = con.prepareStatement(sqlcorrection);
//                psmt.setString(1, dto.getTitle());
//                psmt.setString(2, dto.getContent());
//                psmt.setString(3, dto.getIdx());
//                psmt.executeUpdate();
//
//                result = 1;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//
//    public int insertWrite(MVCBoardDTO dto) { // 아현
//    	int result = 0;
//
//        String sql = "INSERT INTO BOARD VALUES(SEQ_BOARD_NUM.NEXTVAL, ?, ?, ?, SYSDATE, ?)";
//
//        String title = dto.getTitle();
//        String content = dto.getContent();
//        String visitcount = "0";
//
//        try {
//            psmt = con.prepareStatement(sql);
//
//            psmt.setString(1, title);
//            psmt.setString(2, content);
//            psmt.setString(3, id);
//            psmt.setString(4, visitcount);
//            // DB에 게시판내용 입력
//            psmt.executeUpdate();
//            return 1;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(result);
//        return 0;
//
//    }
//
//
//
//
//
//    public void updateVisitCount(String num) {  // 명진
//
//        String sql = "UPDATE board SET visitcount = visitcount+1 WHERE num = ?";
//
//
//        try {
//            psmt = con.prepareStatement(sql);
//            psmt.setString(1, num);
//            psmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public MVCBoardDTO selectView(String num) { // 명진
//
//        //String sql = "SELECT * FROM board where num = ?";
//
//        String sql = "select a.id, a.num, a.title, a.content, b.name, a.postdate, a.visitcount from board a inner join member b on a.id = b.id where num = ?";
//
//        MVCBoardDTO dto = new MVCBoardDTO();
//
//        try {
//            psmt = con.prepareStatement(sql);
//            psmt.setString(1, num);
//            rs = psmt.executeQuery();
//
//            if (rs.next()) {
//                dto.setIdx(rs.getString(2));
//                dto.setTitle(rs.getString(3));
//                dto.setContent(rs.getString(4));
//                dto.setName(rs.getString(5));
//                dto.setPostdate(rs.getString(6));
//                dto.setVisitcount(rs.getString(7));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dto;
//
//    }
//
//    public int selectCount(Map<String, Object> param) { // 연우
//        int totalCount = 0;
//
//        String sql = "";
//
//        try {
//            if (param.get("searchWord") == null) {
//                sql = "SELECT COUNT(*) FROM BOARD";
//                psmt = con.prepareStatement(sql);
//                rs = psmt.executeQuery();
//                if (rs.next()) {
//                    totalCount = rs.getInt(1);
//                }
//                return totalCount;
//
//            } else if (param.get("searchWord").equals("")) {
//                sql = "SELECT COUNT(*) FROM BOARD";
//                psmt = con.prepareStatement(sql);
//                rs = psmt.executeQuery();
//                if (rs.next()) {
//                    totalCount = rs.getInt(1);
//                }
//                return totalCount;
//
//            } else {
//                if (param.get("searchField").equals("title")) {
//                    try {
//                        sql = "SELECT COUNT(*) FROM BOARD WHERE title like ?";
//                        psmt = con.prepareStatement(sql);
//                        psmt.setString(1, "%" + param.get("searchWord") + "%");
//                        rs = psmt.executeQuery();
//                        if (rs.next()) {
//                            totalCount = rs.getInt(1);
//                        }
//                        System.out.println(totalCount);
//                        return totalCount;
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                } else if (param.get("searchField").equals("content")) {
//                    try {
//                        sql = "SELECT COUNT(*) FROM BOARD WHERE content like ?";
//                        psmt = con.prepareStatement(sql);
//                        psmt.setString(1, "%" + param.get("searchWord") + "%");
//                        rs = psmt.executeQuery();
//                        if (rs.next()) {
//                            totalCount = rs.getInt(1);
//                        }
//                        return totalCount;
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return totalCount;
//    }
//
//    public List<MVCBoardDTO> selectList(Map<String, Object> param) { // 동영
//        List<MVCBoardDTO> boardList = new ArrayList<>();
//
//        try {
//            if (param.get("searchWord") == null) { // null아니고 조건 ""일수도
//                String sql = "SELECT * FROM BOARD ORDER BY NUM DESC";
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(sql);
//
//                while (rs.next()) {
//                    MVCBoardDTO dto = new MVCBoardDTO();
//                    dto.setNum(rs.getString(1));
//                    dto.setTitle(rs.getString(2));
//                    dto.setContent(rs.getString(3));
//                    dto.setId(rs.getString(4));
//                    dto.setPostdate(rs.getString(5));
//                    dto.setVisitcount(rs.getString(6));
//
//                    boardList.add(dto);
//                }
//            } else if (param.get("searchWord").equals("")) {
//                String sql = "SELECT * FROM BOARD ORDER BY NUM DESC";
//                stmt = con.createStatement();
//                rs = stmt.executeQuery(sql);
//
//                while (rs.next()) {
//                    MVCBoardDTO dto = new MVCBoardDTO();
//                    dto.setIdx(rs.getString(1));
//                    dto.setTitle(rs.getString(2));
//                    dto.setContent(rs.getString(3));
//                    dto.set(rs.getString(4));
//                    dto.setPostdate(rs.getString(5));
//                    dto.setVisitcount(rs.getString(6));
//
//                    boardList.add(dto);
//                }
//            } else {
//                if (param.get("searchField").equals("title")) {
//                    String sql = "SELECT * FROM BOARD WHERE TITLE LIKE ? ORDER BY NUM DESC";
//                    psmt = con.prepareStatement(sql);
//                    psmt.setString(1, "%" + param.get("searchWord") + "%");
//                    rs = psmt.executeQuery();
//                    while (rs.next()) {
//                        MVCBoardDTO dto = new MVCBoardDTO();
//                        dto.setNum(rs.getString(1));
//                        dto.setTitle(rs.getString(2));
//                        dto.setContent(rs.getString(3));
//                        dto.setId(rs.getString(4));
//                        dto.setPostdate(rs.getString(5));
//                        dto.setVisitcount(rs.getString(6));
//
//                        boardList.add(dto);
//                    }
//
//                } else if (param.get("searchField").equals("content")) {
//                    String sql = "SELECT * FROM BOARD WHERE CONTENT LIKE ? ORDER BY NUM DESC";
//                    psmt = con.prepareStatement(sql);
//                    psmt.setString(1, "%" + param.get("searchWord") + "%");
//                    rs = psmt.executeQuery();
//                    while (rs.next()) {
//                        MVCBoardDTO dto = new MVCBoardDTO();
//                        dto.setNum(rs.getString(1));
//                        dto.setTitle(rs.getString(2));
//                        dto.setContent(rs.getString(3));
//                        dto.setId(rs.getString(4));
//                        dto.setPostdate(rs.getString(5));
//                        dto.setVisitcount(rs.getString(6));
//
//                        boardList.add(dto);
//                    }
//
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return boardList;
//    }
//
//    // 검색 조건에 맞는 게시물 목록 반환 기능(페이징 처리 지원)
//    public List<MVCBoardDTO> selectListPage(Map<String, Object> param) { // 동영
//        List<MVCBoardDTO> boardList = new ArrayList<>();
//
//        try {
//            if (param.get("searchWord") == null) { // null아니고 조건 ""일수도
//                String sql = "SELECT * "
//                		+ "FROM ( SELECT Tb.*, ROWNUM rNum "
//                		+ "     FROM ( SELECT * FROM BOARD ORDER BY NUM DESC) Tb)"
//                		+ "WHERE rNum BETWEEN ? AND ?";
//                psmt = con.prepareStatement(sql);
//                psmt.setString(1, param.get("start").toString());
//                psmt.setString(2, param.get("end").toString());
//                rs = psmt.executeQuery();
//
//                while (rs.next()) {
//                	// 한 행(게시물 하나)의 데이터를 DTO에 저장
//                    MVCBoardDTO dto = new MVCBoardDTO();
//                    dto.setNum(rs.getString(1));
//                    dto.setTitle(rs.getString(2));
//                    dto.setContent(rs.getString(3));
//                    dto.setId(rs.getString(4));
//                    dto.setPostdate(rs.getString(5));
//                    dto.setVisitcount(rs.getString(6));
//
//                    boardList.add(dto); // 반환할 결과 목록에 하나의 행(게시물 하나, dto) 추가
//                }
//			} /*
//				 * else if (param.get("searchWord").equals("")) { String sql = "SELECT * " +
//				 * "FROM ( SELECT Tb.*, ROWNUM rNum " +
//				 * "     FROM ( SELECT * FROM BOARD ORDER BY NUM DESC) Tb)" +
//				 * "WHERE rNum BETWEEN ? AND ?"; psmt = con.prepareStatement(sql);
//				 * psmt.setString(1, param.get("start").toString()); psmt.setString(2,
//				 * param.get("end").toString()); rs = psmt.executeQuery();
//				 *
//				 * while (rs.next()) { MVCBoardDTO dto = new MVCBoardDTO();
//				 * dto.setNum(rs.getString(1)); dto.setTitle(rs.getString(2));
//				 * dto.setContent(rs.getString(3)); dto.setId(rs.getString(4));
//				 * dto.setPostdate(rs.getString(5)); dto.setVisitcount(rs.getString(6));
//				 *
//				 * boardList.add(dto); } }
//				 */else {
//                if (param.get("searchField").equals("title")) {
//                    String sql =  "SELECT * "
//                    		+ "FROM ( SELECT Tb.*, ROWNUM rNum "
//                    		+ "     FROM ( SELECT * FROM BOARD WHERE TITLE LIKE ? ORDER BY NUM DESC) Tb)"
//                    		+ "WHERE rNum BETWEEN ? AND ?";
//                    psmt = con.prepareStatement(sql);
//                    psmt.setString(1, "%" + param.get("searchWord") + "%");
//                    psmt.setString(2, param.get("start").toString());
//                    psmt.setString(3, param.get("end").toString());
//                    rs = psmt.executeQuery();
//                    while (rs.next()) {
//                        MVCBoardDTO dto = new MVCBoardDTO();
//                        dto.setNum(rs.getString(1));
//                        dto.setTitle(rs.getString(2));
//                        dto.setContent(rs.getString(3));
//                        dto.setId(rs.getString(4));
//                        dto.setPostdate(rs.getString(5));
//                        dto.setVisitcount(rs.getString(6));
//
//                        boardList.add(dto);
//                    }
//
//                } else if (param.get("searchField").equals("content")) {
//                	String sql =  "SELECT * "
//                    		+ "FROM ( SELECT Tb.*, ROWNUM rNum "
//                    		+ "     FROM ( SELECT * FROM BOARD WHERE CONTENT LIKE ? ORDER BY NUM DESC) Tb)"
//                    		+ "WHERE rNum BETWEEN ? AND ?";
//                    psmt = con.prepareStatement(sql);
//                    psmt.setString(1, "%" + param.get("searchWord") + "%");
//                    psmt.setString(2, param.get("start").toString());
//                    psmt.setString(3, param.get("end").toString());
//                    rs = psmt.executeQuery();
//                    while (rs.next()) {
//                        MVCBoardDTO dto = new MVCBoardDTO();
//                        dto.setNum(rs.getString(1));
//                        dto.setTitle(rs.getString(2));
//                        dto.setContent(rs.getString(3));
//                        dto.setId(rs.getString(4));
//                        dto.setPostdate(rs.getString(5));
//                        dto.setVisitcount(rs.getString(6));
//
//                        boardList.add(dto);
//                    }
//
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return boardList;
//    }
//
//
//}