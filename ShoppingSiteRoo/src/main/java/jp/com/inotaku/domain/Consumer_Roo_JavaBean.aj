// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package jp.com.inotaku.domain;

import jp.com.inotaku.domain.Consumer;

privileged aspect Consumer_Roo_JavaBean {
    
    public String Consumer.getConsumerName() {
        return this.consumerName;
    }
    
    public void Consumer.setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }
    
    public String Consumer.getPassword() {
        return this.password;
    }
    
    public void Consumer.setPassword(String password) {
        this.password = password;
    }
    
    public String Consumer.getEmail() {
        return this.email;
    }
    
    public void Consumer.setEmail(String email) {
        this.email = email;
    }
    
    public int Consumer.getPoint() {
        return this.point;
    }
    
    public void Consumer.setPoint(int point) {
        this.point = point;
    }
    
}
