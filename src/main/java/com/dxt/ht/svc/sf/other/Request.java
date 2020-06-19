package com.dxt.ht.svc.sf.other;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Request")
public class Request<T> {
    @XStreamAsAttribute
    private String service;
    @XStreamAsAttribute
    private String lang;

    private String Head;


    @XStreamAlias("Body")
    private Body Body;





    public Request(String service,PlaceOrderParameter body) {
        this.service = service;
        this.lang = "zh-CN";
        this.Head = "SLKJ2019";
        this.Body = new Body(body);
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        Head = head;
    }




    @Override
    public String toString() {
        return "Request{" +
                "service='" + service + '\'' +
                ", lang='" + lang + '\'' +
                ", Head='" + Head + '\'' +
                ", body=" + Body +
                '}';
    }
}
