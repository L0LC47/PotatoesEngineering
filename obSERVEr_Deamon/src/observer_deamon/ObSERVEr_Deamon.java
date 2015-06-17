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
        String[] res;
        String serial = "44444ddddd";
        String queryGet = "SELECT uv.email, vo.targa, a.velocita FROM usr_veicolo as uv, "
                + "veicolo_observer as vo, allarme as a WHERE uv.targa = vo.targa AND "
                + "vo.serial = ? AND vo.inizio < CURRENT_DATE AND "
                + "(vo.fine >= CURRENT_DATE OR vo.fine IS NULL) AND vo.serial = a.observer";
        res = ds.Query(queryGet, serial);
        System.out.println(res[0] + res[1]);
        String query = "INSERT INTO storico(observer, usr, targa, velocita, "
                + "posizione, istante) VALUES ('" + serial + "', '" + res[0] + ""
                + "' , '" + res[1] + "' , ?, ?, ?)";
        while (true) {
            Thread.sleep(1000 * 60 * 5 );
            d = new java.util.Date();
            time = new Timestamp(d.getTime());
            //time = System.currentTimeMillis() / 300; //Per campionare ogni secondo
            vel = (short) Math.floor((Math.random() * 200) + 1);
            pos = "01.123456,02.123456";
            sendMsg(vel, Integer.parseInt(res[2]));
            //ds.Update(query, vel, pos, time);
        }
    }

    private static void sendMsg(int velocitaAttuale, int limite) {
        if (velocitaAttuale > limite) {
            System.out.println("*************************\nInviata Email\n"
                    + "Inviato SMS\nTesto messaggi: "
                    + "\nVelocità superata!\n\tlimite: " + limite + "\n\tVeloci"
                    + "tà rivlevata: " + velocitaAttuale);
        }
    }
}
