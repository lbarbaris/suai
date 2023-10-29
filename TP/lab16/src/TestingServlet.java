import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class TestingServlet extends HttpServlet {

    public void init(ServletConfig config) {
        try {
            loadFromTextFile("list.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    HashMap<String, ArrayList<String>> list = new HashMap<>();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String uri = request.getRequestURI();
        PrintWriter out = response.getWriter();
        out.println(getMainPage());

    }

    public String getMainPage() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n<body>\n");
        sb.append("<style>\n" +
                "    #p1 {\n" +
                "      display:none;\n" +
                "    }\n" +
                "\n" +
                "    ul {\n" +
                "      list-style-type: none;\n" +
                "    }\n" +
                "  </style>");
        sb.append("<script>\n" +
                "function show(id, id2) {\n" +
                "if (document.getElementById(id).style.display == 'none')\n" +
                "document.getElementById(id).style.display = 'inline';\n" +
                "else\n" +
                "document.getElementById(id).style.display = 'none';\n" +
                "if (document.getElementById(id2).textContent == '+')\n" +
                "document.getElementById(id2).textContent = '-';\n" +
                "else\n" +
                "document.getElementById(id2).textContent = '+';\n" +
                "}\n" +
                "</script>\n");

        sb.append("<ol>");
        int ulid = 0;
        int butid = 0;
        for (HashMap.Entry<String, ArrayList<String>> entry : list.entrySet()) {
            sb.append("<li>" + entry.getKey() + "<button id=\"but" + butid + "\" onclick=\"show('ul" + ulid +"', 'but" + butid + "')\">-</button>");
            sb.append("<ul id = \"ul" + ulid + "\">");
            for (int i = 0; i < entry.getValue().size(); i++){
                sb.append("<li>" + entry.getValue().get(i) + "</li>");
            }
            ulid++;
            butid++;
            sb.append("</ul>");
            sb.append("</li>");
        }
        sb.append("</ol>");
        sb.append("</body>\n</html>");
        return sb.toString();
    }




    private void loadFromTextFile(String filename) throws  IOException {
        BufferedReader fir = new BufferedReader(new FileReader(filename));
        String line;
        String key = null;
        while ((line = fir.readLine()) != null) {
            if (line.startsWith("    ")) {
                list.get(key).add(line.substring(3));
            }
            else{
                if (line.startsWith(" ")) {
                    key = line.substring(1);
                    list.put(key, new ArrayList<String>());
                }
            }
        }
        list.remove(null);
    }


}
