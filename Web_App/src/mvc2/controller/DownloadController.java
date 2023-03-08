package mvc2.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
@WebServlet(/mvc2/download.do)
public class DownloadController extends HttpServlet {
    protected void download(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // download() 메서드는 request, response 내장 객체와 디렉터리명, 저장된 파일명, 원본 파일명을 매개변수로 전달받음
        String dirName = req.getParameter("dirName"); // 디렉토리명
        String savedFileName = req.getParameter("savedFileName"); // 저장된 파일명
        String originFileName = req.getParameter("originFileName"); // 원본 파일명

        //서블릿에서 디렉터리의 물리적 경로를 얻어오는 방법
        String dirPath = req.getRealPath("dirName");

        File file = new File(dirPath + "/" + originFileName);

        try {
            //파일을 찾아 입력 스트림 생성
            /*resp.setContentType(“text/html; charset=UTF-8”);
            InputStream inputStream = getFileInputStream(dirPath);*/

            //파일명 깨짐을 방지하기 위한 처리
            String client = req.getHeader("User-Agent");

            String downName = "";
            //User-Agent를 통해 클라이언트의 웹 브라우저의 종류를 확인
            if(client.indexOf("WOW64") == -1) {
                //인터넷 익스플로러일때와 그 외의 경우를 구분하여 캐릭터셋을 설정
                downName = new String(originFileName.getBytes("UTF-8"),"ISO-8859-1");
            } else {
                downName = new String(originFileName.getBytes("KSC5601"),"ISO-8859-1");
            }
            //파일 다운로드를 위한 응답 헤드를 설정
            resp.setHeader("Content-Disposition", "attachment;filename=\"" + downName + "\";");


            //download.jsp에는 있는 코드이나 여기서는 주석으로 처리 - jsp에서는 이 코드가 없으면 예외가 발생하지만 서블릿에서는 발생하지 않음
        } catch (FileNotFoundException e) {

        }
        // 새로운 출력 스트림을 생성
        FileInputStream fileInputStream = new FileInputStream(file);
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        // 읽어온 내용을 파일로 출력
        byte b [] = new byte[1024];
        int data = 0;
        while((data=(fileInputStream.read(b, 0, b.length))) != -1) {
            servletOutputStream.write(b, 0, data);
        }
        // 입력 스트림과 출력 스트림을 닫음
        fileInputStream.close();
        ServletOutputStream.close();

    }




    //파일 다운로드 서블릿

    //파일을 다운로드

    //다운로드 횟수를 증가시킴


}
