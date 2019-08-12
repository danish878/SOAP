package org.danny.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

    private String errorMessaage;
    private int errorCode;
    private String documentation;

    public ErrorMessage() {
        // TODO Auto-generated constructor stub
    }

    public ErrorMessage(String errorMessaage, int errorCode, String documentation) {
        super();
        this.errorMessaage = errorMessaage;
        this.errorCode = errorCode;
        this.documentation = documentation;
    }

    public String getErrorMessaage() {
        return errorMessaage;
    }

    public void setErrorMessaage(String errorMessaage) {
        this.errorMessaage = errorMessaage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

}
