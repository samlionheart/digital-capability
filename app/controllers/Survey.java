package controllers;

import models.SurveyModel;
import org.flywaydb.core.internal.dbsupport.sybase.ase.SybaseASEDbSupport;
import play.mvc.Result;

import java.util.Map;
import java.util.Set;

/**
 * Created by daniel.rothig on 27/09/2016.
 *
 * Controller for the question workflow
 */
public class Survey extends PageController {
    public Result questions() {
        return ok(page(views.html.Survey.questions.render(SurveyModel.make())));
    }

    public Result answer() {
        Map<String, String[]> params = request().body().asFormUrlEncoded();
        SurveyModel survey = SurveyModel.makeWithAnswers(params);
        return ok(page(views.html.Survey.answer.render(survey.getScores())));
    }
}


