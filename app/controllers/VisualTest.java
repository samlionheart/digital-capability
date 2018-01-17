package controllers;

import com.google.inject.Inject;
import models.DigitalRole;
import models.SurveyModel;
import play.mvc.Result;
import play.twirl.api.Html;
import play.twirl.api.HtmlFormat;
import scala.collection.JavaConversions;
import utils.TimeProvider;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daniel.rothig on 27/09/2016.
 *
 * Main controller
 */
public class VisualTest extends PageController {

    private TimeProvider timeProvider;

    @Inject
    public VisualTest(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }


    public Result index() {

        Map<DigitalRole, BigDecimal> data = new HashMap<>();
        data.put(DigitalRole.BUSINESS_ANALYST, new BigDecimal("80.0"));
        data.put(DigitalRole.CONTENT_DESIGNER, new BigDecimal("50.0"));
        data.put(DigitalRole.DELIVERY_MANAGER, new BigDecimal("10.0"));
        data.put(DigitalRole.DESIGNER, new BigDecimal("20.0"));
        data.put(DigitalRole.USER_RESEARCHER, new BigDecimal("65.0"));


        Html html = HtmlFormat.fill(JavaConversions.asScalaBuffer(Arrays.asList(
                views.html.Survey.answer.render(data),
                views.html.Survey.questions.render(SurveyModel.make()),

                views.html.Home.index.render(),
                views.html.Roles.businessAnalyst.render(),
                views.html.Roles.contentDesigner.render(),
                views.html.Roles.deliveryManager.render(),
                views.html.Roles.designer.render(),
                views.html.Roles.userResearcher.render()

                //views.html.Home.index.render()

        )).toList());

        return ok(page(html));
    }

    public Result extremeData() {

        Map<DigitalRole, BigDecimal> extremedata = new HashMap<>();
        extremedata.put(DigitalRole.BUSINESS_ANALYST, new BigDecimal("100.0"));
        extremedata.put(DigitalRole.CONTENT_DESIGNER, new BigDecimal("0.0"));
        extremedata.put(DigitalRole.DELIVERY_MANAGER, new BigDecimal("100.0"));
        extremedata.put(DigitalRole.DESIGNER, new BigDecimal("0.0"));
        extremedata.put(DigitalRole.USER_RESEARCHER, new BigDecimal("100.0"));

        return ok(page(views.html.Survey.answer.render(extremedata)));
    }
}


