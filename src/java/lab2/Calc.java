package lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Определите веб-форму, содержащую две формы отправки. 
//Первая форма отправки содержит два текстовых поля ввода, а также кнопки "Сложить" и "Очистить". 
//Данные этой формы должны отправляться методом GET, а сервер должен возвращать сумму введённых чисел. 
//Вторая форма отправки также содержит два текстовых поля ввода, а также кнопки "Умножить" и "Очистить". 
//Данные этой формы должны отправляться методом POST, а сервер должен возвращать произведение введённых чисел.
//В случае, когда результат вычисления не может быть корректно отображаться 
//(например, при вводе не числовых значений или при выходе результата за пределы представления типов данных) 
//сервер должен вернуть сообщение с описанием ошибки.
@WebServlet(name = "Calc", urlPatterns = {"/Calc"})
public class Calc extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");

        String n1Str = request.getParameter("n1");
        String n2Str = request.getParameter("n2");

        //String add = request.getParameter("b1");
        String clear = request.getParameter("b2");

        List<String> list = new ArrayList<>();
        list.add("Fgh");
        list.add(0, "dfg");
        
        try (PrintWriter out = response.getWriter()) {

            if (n1Str == null || n2Str == null) {
                out.println("<h1> возможно вы ввели некорректные числа. </h1>");
                out.println("<h1><a href=\"index.jsp\">Попробовать ввести числа еще раз</a></h1>");
                return;
            }

            n1Str = n1Str.trim();
            n2Str = n2Str.trim();

            //если нажал очистить - делаю редирект на главную страницу - там поля уже очищены.
            if (clear != null) { //&& clear.equals("Очистить")
                //здесь редирект, во втором методе - форвард, для разнообразия.
                response.sendRedirect("/j200lab2");
            }

            Pattern intPattern = Pattern.compile("[0-9]+");
            Matcher intMatcher1 = intPattern.matcher(n1Str);
            Matcher intMatcher2 = intPattern.matcher(n2Str);
            //если введенная строка соответствует [0-9]+ - то есть int - добавляю к ней ".0" - т.е. перевожу строку в формат double и считаю потом все в double.
            if (intMatcher1.matches()) {
                n1Str += ".0";
            }
            if (intMatcher2.matches()) {
                n2Str += ".0";
            }

            Pattern doublePattern = Pattern.compile("[0-9]*.[0-9]+");
            Matcher doubleMatcher1 = doublePattern.matcher(n1Str);
            Matcher doubleMatcher2 = doublePattern.matcher(n2Str);

            //ЗАГОЛОВОК
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Calc</title>");
            out.println("</head>");
            out.println("<body>");

            //если обе строки подпадают под double - считаем.
            if (doubleMatcher1.matches() && doubleMatcher2.matches()) {
                out.println("<h1>Результаты вычислений:</h1>");
                try {
                    double nOneDouble = Double.parseDouble(n1Str);
                    double nTwoDouble = Double.parseDouble(n2Str);
                    double answer = nOneDouble + nTwoDouble;
                    out.println("<h1>Сумма чисел " + nOneDouble + " и " + nTwoDouble + " равна: </h1>");
                    out.println("<h1>" + answer + "</h1>");
                } catch (NumberFormatException de) {
                    out.println("<h1>Double ParseException возможно вы ввели некорректные числа. </h1>");
                }

                //ссылка перехода на страницу
                out.println("<h1><a href=\"index.jsp\">Посчитать ещё</a></h1>");
                out.println("<p>(Результат вычислений не будет сохранен)</p>");
            } //иначе если хотя бы одно число не матчится ни в дабл ни в инт - то пишем о некорректном вводе и просим ввести еще раз.
            else {
                out.println("<h1> возможно вы ввели некорректные числа. </h1>");
                out.println("<h1><a href=\"index.jsp\">Попробовать ввести числа еще раз</a></h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String n3Str = request.getParameter("n3");
        String n4Str = request.getParameter("n4");
        n3Str = n3Str.trim();
        n4Str = n4Str.trim();

        String mult = request.getParameter("b3");
        String clear = request.getParameter("b4");

        if (clear != null) { //&& clear.equals("Очистить") // < --этот параметр необязательно читать
            //вернуться на страницу:
            //response.sendRedirect("/j200lab2");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Calc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Результаты вычислений:</h1>");
            if (n3Str != null && n4Str != null) {
                try {
                    int nOne = Integer.parseInt(n3Str);
                    int nTwo = Integer.parseInt(n4Str);
                    int answer = nOne * nTwo;
                    out.println("<h1>Произведение чисел " + n3Str + " и " + n4Str + " равно: </h1>");
                    out.println("<h1>" + answer + "</h1>");
                } catch (NumberFormatException e) {
                    try {
                        double nOneDouble = Double.parseDouble(n3Str);
                        double nTwoDouble = Double.parseDouble(n4Str);
                        double answer = nOneDouble * nTwoDouble;
                        out.println("<h1>Произведение чисел " + nOneDouble + " и " + nTwoDouble + " равно: </h1>");
                        out.println("<h1>" + answer + "</h1>");
                    } catch (NumberFormatException de) {
                        out.println("<h1> возможно вы ввели некорректные числа. </h1>");
                    }
                }
            }
            //ссылка перехода на страницу
            out.println("<h1><a href=\"index.jsp\">Посчитать ещё</a></h1>");
            out.println("<p>(Результат вычислений не будет сохранен)</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
