package com.sdzee.utils;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

@SuppressWarnings("serial")
public class TextShaping extends BodyTagSupport {

    @Override
    public int doAfterBody() throws JspException {
        try {
            BodyContent bodycontent = getBodyContent();
            String body = bodycontent.getString();
            JspWriter out = bodycontent.getEnclosingWriter();
            if (body != null) {
                out.print(nl2br(body));
            }
        } catch (IOException ioe) {
            throw new JspException("Error:" + ioe.getMessage());
        }
        return SKIP_BODY;
    }

    private static String nl2br(String s) {
        return s.replaceAll("\n", "<br>\n");
    }
	
}
