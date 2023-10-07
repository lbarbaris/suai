import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class BookServlet extends HttpServlet {
    NoteBook notes;
    Long unknownNumber = 0L;

    public void init(ServletConfig config) {
        notes = new NoteBook();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        PrintWriter out = response.getWriter();

        if( uri.equals("/servlet-13/AddNewName") ) {
            out.println("<html>\n<title>\n Add Name </title> \n<body>\n");
            out.println("<a href=\"/servlet-13\">Home</a>\n");
            out.println("<br>\n<br>\n");
            out.println("Add Name\n");
            out.println("<form action=\"BookServlet\" method=\"POST\">");

            out.println("Number: <select name=\"nameaction\">\n");
            out.println("<option>none</option>");
            for (HashMap.Entry<Long, String> entry : notes.getNumbers().entrySet()) {
                out.println("<option> +" + entry.getKey() +"</option>\n");
            }
            out.println("Name: <input name=\"name\" />\n" + "<br>\n");
            out.println("\n<input type=\"submit\" value=\"Submit\" />");

            out.println("</select>\n");
            out.println("<br>");
            out.println("<br>\n");

            out.println("</body>\n</html>");

        }
        else if( uri.equals("/servlet-13/AddPhone") ) {
            out.println("<html>\n<title>\n Add Phone </title> \n<body>\n");
            out.println("<html>\n<body>\n");
            out.println("<a href=\"/servlet-13\">Home</a>\n");
            out.println("<br>");
            out.println("<br>\n");
            out.println("<form action=\"BookServlet\" method=\"POST\">");

            out.println("Add to name: <select name=\"numberaction\">\n");
            out.println("<option>none</option>");
            for (HashMap.Entry<Long, String> entry : notes.getNumbers().entrySet()) {
                out.println("<option>" + entry.getValue() +"</option>\n");
            }
            out.println("Name: <input name=\"number\" />\n" + "<br>\n");
            out.println("\n<input type=\"submit\" value=\"Submit\" />");

            out.println("</select>\n");
            out.println("<br>");
            out.println("<br>\n");



            out.println("</body>\n</html>");

        }
        else if( uri.equals("/servlet-13/ReplacePhone") ){
            out.println("<html>\n<title>\n Replace Phone </title> \n<body>\n");
            out.println("<html>\n<body>\n");
            out.println("<a href=\"/servlet-13\">Home</a>\n");
            out.println("<br>");
            out.println("<br>\n");
                        out.println("<form action=\"BookServlet\" method=\"POST\">");

            out.println("Replace number: <select name=\"replaceaction\">\n");
            for (HashMap.Entry<Long, String> entry : notes.getNumbers().entrySet()) {
                out.println("<option>" + entry.getKey() +"</option>\n");
            }
            out.println("Name: <input name=\"replacenumber\" />\n" + "<br>\n");
            out.println("\n<input type=\"submit\" value=\"Submit\" />");

            out.println("</select>\n");
            out.println("<br>");
            out.println("<br>\n");



            out.println("</body>\n</html>");
        }
        else if( uri.equals("/servlet-13/") ){
            out.println(getMainPage());
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String name = null;
        String number = null;
        String nameaction = null;
        String numberaction = null;
        String replaceaction = null;
        String replacenumber = null;
        try{
            name = request.getParameter("name");
            numberaction = request.getParameter("numberaction");
            nameaction = request.getParameter("nameaction");
            number = request.getParameter("number");
            replaceaction = request.getParameter("replaceaction");
            replacenumber = request.getParameter("replacenumber");
        }
        catch (Exception e){
        }

        PrintWriter out = response.getWriter();
        out.println("<html>\n<body>\n");
        if (numberaction != null){
            if (numberaction.indexOf("none")==0){
                notes.add("Unknown", Long.parseLong(number));
            }
            else{
                notes.add(numberaction,Long.parseLong(number));
            }
        }
        if (nameaction != null){
            if (nameaction.indexOf("none")==0){
                notes.add(name, unknownNumber);
                unknownNumber++;
            }
            else{
                notes.add(name,Long.parseLong(nameaction));
            }
        }
        if (replaceaction != null){
                notes.replace(Long.parseLong(replaceaction), Long.parseLong(replacenumber));
        }
        notes.saveToTextFile("save.txt");

        out.println(getMainPage());
        out.println("</html>\n</body>\n");
    }

    public String getMainPage(){

        StringBuilder sb = new StringBuilder();
        sb.append("<a href=\"/servlet-13/AddNewName\">Add Name</a>\n");
        sb.append("<br>\n");
        sb.append("<br>\n");
        sb.append("<a href=\"/servlet-13/AddPhone\">Add Phone</a>\n");
        sb.append("<br>\n");
        sb.append("<br>\n");
        sb.append("<a href=\"/servlet-13/ReplacePhone\">Replace Phone</a>\n");
        sb.append("<br>\n");
        sb.append("<br>\n");
        for (HashMap.Entry<Long, String> entry : notes.getNumbers().entrySet()) {
            sb.append("<p> +" + entry.getKey() + ", " + entry.getValue() + "</p>\n");
        }

        sb.append("</html>\n</body>\n");
        return sb.toString();
    }

}
