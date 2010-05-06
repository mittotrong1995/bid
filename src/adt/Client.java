/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adt;

/**
 *
 * @author sanja
 */
public class Client {
private String ip;

    public Client() {
        this.ip = null;
    }

    public Client(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
