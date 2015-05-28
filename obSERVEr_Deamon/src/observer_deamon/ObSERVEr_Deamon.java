/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer_deamon;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Alberto
 */
public class ObSERVEr_Deamon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        DataSource ds = DataSource.getInstance();
        Timestamp time;
        short vel;
        java.util.Date d = null;
        String pos;
        String query = "INSERT INTO storico(observer, velocita, posizione, istante) VALUES (0000000042, ?, ?, ?)";

        while (true) {
            Thread.sleep(1000);
            d=new java.util.Date();
            time = new Timestamp(d.getTime());
            //time = System.currentTimeMillis() / 1000;
            vel = (short) Math.floor((Math.random() * 200) + 1);
            pos = "01.123456,02.123456";
            ds.Query(query, vel, pos, time);
        }

    }

}
