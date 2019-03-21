package com.mercadolibre.util;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

public class VelocityUtil {

    public static String render(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
