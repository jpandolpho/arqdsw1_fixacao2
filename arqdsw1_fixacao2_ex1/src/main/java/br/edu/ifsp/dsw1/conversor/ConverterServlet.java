package br.edu.ifsp.dsw1.conversor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/main.do")
public class ConverterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Converter converter;
	
	public ConverterServlet() {
		converter = new Converter();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var out = response.getWriter();
		out.println("<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\n"
				+ "    <title>Conversor de Temperatura</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <div class=\"container-sm\">\n"
				+ "    	<h1>Conversor de Temperatura</h1>\n"
				+ "        <form action=\"main.do\" method=\"post\">\n"
				+ "            <div class=\"mb-3\">\n"
				+ "            <label for=\"temperatura\" class=\"form-label\">Temperatura</label>\n"
				+ "            <input class=\"form-control\" type=\"number\" name=\"text_temperature\" id=\"temperatura\" placeholder=\"Temperatura\" aria-label=\"temperatura\">\n"
				+ "            </div>\n"
				+ "            <div class=\"form-check\">\n"
				+ "                <input class=\"form-check-input\" type=\"radio\" name=\"radio_scale\" id=\"celsius\" value=\"celsius\" checked>\n"
				+ "                <label class=\"form-check-label\" for=\"celsius\">\n"
				+ "                Celsius\n"
				+ "                </label>\n"
				+ "            </div>\n"
				+ "            <div class=\"form-check\">\n"
				+ "                <input class=\"form-check-input\" type=\"radio\" name=\"radio_scale\" id=\"fahrenheit\" value=\"fahrenheit\">\n"
				+ "                <label class=\"form-check-label\" for=\"fahrenheit\">\n"
				+ "                Fahrenheit\n"
				+ "                </label>\n"
				+ "            </div>\n"
				+ "            <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\n"
				+ "      </form>\n"
				+ "    </div>\n"
				+ "</body>\n"
				+ "</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String temperature = request.getParameter("text_temperature");
		String scale = request.getParameter("radio_scale");
		
		double temp;
		try {
			temp = Double.parseDouble(temperature);
		}catch(NumberFormatException e) {
			temp = 0;
		}
		converter.setTemperatura(temp);
		
		if(scale.equals("fahrenheit")) {
			converter.setStrategy(new ToFahrenheit());
		}else{
			converter.setStrategy(new ToCelsius());
		}
		try(var out = response.getWriter()){
			out.println("<!DOCTYPE html>\n"
					+ "<html lang=\"en\">\n"
					+ "<head>\n"
					+ "    <meta charset=\"UTF-8\">\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
					+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">\n"
					+ "    <title>Conversor de Temperatura</title>\n"
					+ "</head>\n"
					+ "<body>\n"
					+ "    <div class=\"container-sm\">\n"
					+ "    	<h1>Conversor de Temperatura</h1>\n"
					+ "        <form action=\"main.do\" method=\"post\">\n"
					+ "            <div class=\"mb-3\">\n"
					+ "            <label for=\"temperatura\" class=\"form-label\">Temperatura</label>\n"
					+ "            <input class=\"form-control\" type=\"number\" name=\"text_temperature\" id=\"temperatura\" placeholder=\"Temperatura\" aria-label=\"temperatura\">\n"
					+ "            </div>\n"
					+ "            <div class=\"form-check\">\n"
					+ "                <input class=\"form-check-input\" type=\"radio\" name=\"radio_scale\" id=\"celsius\" checked>\n"
					+ "                <label class=\"form-check-label\" for=\"celsius\">\n"
					+ "                Celsius\n"
					+ "                </label>\n"
					+ "            </div>\n"
					+ "            <div class=\"form-check\">\n"
					+ "                <input class=\"form-check-input\" type=\"radio\" name=\"radio_scale\" id=\"fahrenheit\">\n"
					+ "                <label class=\"form-check-label\" for=\"fahrenheit\">\n"
					+ "                Fahrenheit\n"
					+ "                </label>\n"
					+ "            </div>\n"
					+ "            <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\n"
					+ "      </form>\n<br>\n"
					+ "      <div class=\"card\">\n"
					+ "        <div class=\"card-header\">\n"
					+ "          Convertido\n"
					+ "        </div>\n"
					+ "        <div class=\"card-body\">\n"
					+ "          <h5 class=\"card-title\">Conversão realizada</h5>\n"
					+ "          <p class=\"card-text\">"+converter.getTemperatura()+"=="+converter.converter()+"</p>\n"
					+ "        </div>\n"
					+ "      </div>\n"
					+ "    </div>\n"
					+ "</body>\n"
					+ "</html>");
		}
	}

}
