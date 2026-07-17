package servlet;

import controller.AdvisorNextQuestionController;

import model.AdvisorQuestion;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.ResultSet;

@WebServlet("/advisorNextQuestion")
public class AdvisorNextQuestionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    AdvisorNextQuestionController controller =
            new AdvisorNextQuestionController();
    
    @Override
    protected void doGet(

            HttpServletRequest request,

            HttpServletResponse response)

            throws IOException {

        response.setHeader(
                "Access-Control-Allow-Origin",
                "https://insurance-frontend-woad.vercel.app");

        response.setContentType(
                "application/json");

        PrintWriter out =
                response.getWriter();

        String insuranceType =
                request.getParameter(
                        "insuranceType");

        int questionOrder =
                Integer.parseInt(

                        request.getParameter(

                                "questionOrder"

                        )

                );

        Gson gson =
                new Gson();

        AdvisorQuestion question =
                new AdvisorQuestion();
        
        try {

            ResultSet rs =
                    controller.getNextQuestion(

                            insuranceType,

                            questionOrder

                    );

            if(rs.next()) {

                question.setQuestionId(

                        rs.getInt(

                                "question_id"

                        )

                );

                question.setQuestion(

                        rs.getString(

                                "question"

                        )

                );

                question.setInputType(

                        rs.getString(

                                "input_type"

                        )

                );

                question.setOptions(

                        rs.getString(

                                "options"

                        )

                );

                question.setOptionType(

                        rs.getString(

                                "option_type"

                        )

                );

                question.setFinished(false);

            }

            else {

                question.setFinished(true);

            }

        }

        catch(Exception e){

            e.printStackTrace();

            question.setFinished(true);

        }
        
        out.print(

                gson.toJson(

                        question

                )

        );

        out.flush();

    }

    }