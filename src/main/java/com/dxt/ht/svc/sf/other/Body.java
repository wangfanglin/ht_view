package com.dxt.ht.svc.sf.other;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Body")
class Body {
    @XStreamAlias("order")
    private PlaceOrderParameter order;

    public Body(PlaceOrderParameter order) {
        this.order = order;
    }

    public PlaceOrderParameter getOrder() {
        return order;
    }

    public void setOrder(PlaceOrderParameter order) {
        this.order = order;
    }
}