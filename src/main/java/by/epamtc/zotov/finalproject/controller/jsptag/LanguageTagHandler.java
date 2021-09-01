package by.epamtc.zotov.finalproject.controller.jsptag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class LanguageTagHandler extends TagSupport {
    private static final String LANGUAGE_ATTRIBUTE_NAME = "language";
    String defaultLanguage;

    @Override
    public int doStartTag() throws JspException {

        if(pageContext.getSession().getAttribute(LANGUAGE_ATTRIBUTE_NAME)==null) {
            pageContext.getSession().setAttribute(LANGUAGE_ATTRIBUTE_NAME, defaultLanguage);
        }
        return SKIP_BODY;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }
}