<%-- 
    Document   : index
    Created on : Mar 11, 2021, 8:23:05 PM
    Author     : ENVY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Калькулятор</title>
        <style>
            body{
                background-color: #AAFFAA;
            }

            #box{
                position: absolute;<%--абсолютное позиционирование--%>
                top: 50%; <%--смещение верх лев угла--%>
                left: 50%;
                margin: -75px 0px 0px -193px; <%--отступ верх право низ лево--%>
                padding: 10px;

                width: 360px;
                height: 150px;

                border: dotted blue 1px;

            }
            td{
                text-align:center;
            }

        </style>    
    </head>
    <body>
        <!--<h1>Hello World!</h1>-->
        <%--комментарий для jsp страниц--%>
        <!--комментарий для html страниц-->
        <div id="box">
            <form name="add" action="Calc" method="GET">
                <table>         <%--таблица--%>
                    <tr>        <%--table raw--%>
                        <td><input type="text" name="n1" size="12"/></td>
                        <td><input type="text" name="n2" size="12"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="b1" value="Сложить"/></td>
                        <td><input type="submit" name="b2" value="Очистить"/></td>
                    </tr>

                </table>
            </form>
            <form name="mult" action="Calc" method="POST">
                <table>         <%--таблица--%>
                    <tr>        <%--table raw--%>
                        <td><input type="text" name="n3" size="12"/></td>
                        <td><input type="text" name="n4" size="12"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="b3" value="Умножить"/></td>
                        <td><input type="submit" name="b4" value="Очистить"/></td>
                    </tr>

                </table>
            </form>            
            <!--ТЕКСТ КАКОЙ-ТО-->
        </div>
    </body>
</html>
