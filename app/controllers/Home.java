package controllers;

import play.mvc.Result;

import java.util.Objects;

/**
 * Created by daniel.rothig on 27/09/2016.
 *
 * Main controller
 */
public class Home extends PageController {
    public Result index() {return ok(page(views.html.Home.index.render())); }

    public Result roles(String role) {
        String r = role.toLowerCase();
        if (r.equals("business analyst")) return ok(page(views.html.Roles.businessAnalyst.render()));
        if (r.equals("content designer")) return ok(page(views.html.Roles.contentDesigner.render()));
        if (r.equals("delivery manager")) return ok(page(views.html.Roles.deliveryManager.render()));
        if (r.equals("designer")) return ok(page(views.html.Roles.designer.render()));
        if (r.equals("user researcher")) return ok(page(views.html.Roles.userResearcher.render()));

        return status(404, "Unknown role");
    }
}


