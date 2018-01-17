package controllers;

import play.mvc.Controller;
import play.twirl.api.Html;
import scala.Option;

/**
 * Created by daniel.rothig on 06/10/2016.
 *
 * Controller capable of wrapping views into a layout template. This decouples views from their layouts,
 * allowing them to be rendered as components, e.g. for the VisualTest controller.
 *
 */
class PageController extends Controller {
    protected Html page(Html content) {
        return views.html.common.page.govukTemplateDefaults.render("RD Digital Capability", content);
    }
}
