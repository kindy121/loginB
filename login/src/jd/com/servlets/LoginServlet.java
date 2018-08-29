package jd.com.servlets;

import com.google.gson.Gson;
import jd.com.dto.User;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.print("****************");
        doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        ServletInputStream is;
        ServletOutputStream out;
        try {
            is = req.getInputStream();
            int nRead = 1;
            int nTotalRead = 0;
            byte[] bytes = new byte[10240];
            while (nRead > 0) {
                nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
                if (nRead > 0)
                    nTotalRead = nTotalRead + nRead;
            }
            is.close();
            String str = new String(bytes, 0, nTotalRead, "utf-8");
            System.out.println(str);
           //
            // JSONObject json=JSONObject.fromObject(str);
           // System.out.println(json.getString("username"));


            Gson gson=new Gson();
            User user=gson.fromJson(str,User.class);
            String password=user.getPassword();
            String username=user.getUsername();
            out=resp.getOutputStream();
            OutputStreamWriter writer=new OutputStreamWriter(out);
            String code;

            if(username!=null && username!="" && password!=null && password!="" && username.contains("admin") && password.equals("123456")){
                //resp.sendRedirect("http://localhost:8082");
                code="success";
                writer.write(code);
            }else{
                code="failure";
                writer.write(code);
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
