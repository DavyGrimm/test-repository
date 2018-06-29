import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Main  {
    public static void main(String[] args) {
        staticFileLocation("/public");

        before((request, response) -> {
            request.attribute("name", "Attribute");
            request.attribute("job", "unemployed");
        });

        get("/", (rq, rs) -> {
            Map<String, String> model = new HashMap<>();
            model.put("name", rq.attribute("name"));
            model.put("prace", rq.attribute("job"));
            return new ModelAndView(model, "hello.hbs");
        }, new HandlebarsTemplateEngine());

        post("/submit", (rq, rs) -> {
            Map<String, String> model = new HashMap<>();
            rq.attribute("username", rq.queryParams("username"));
            model.put("username", rq.attribute(rq.queryParams("username")));
            //rs.redirect("/submit");
            return new ModelAndView(model, "submit.hbs");
        }, new HandlebarsTemplateEngine());
    }

}


