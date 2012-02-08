
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monodaipoly.servicio;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author jperez
 */
@Service
public class TareaProgramadaImpl implements TareaProgramada {


    @Override
    @Scheduled(fixedRate=5000) //Cada 5 segundos
    //@Scheduled(fixedDelay=5000) //Cada 5 segundos. El tiempo se cuenta desde que terminó la anterior ejecución
    //@Scheduled(cron="*/5 * * * * MON-FRI") Cada cin minutos de lunes a viernes
    public void hacer() {
        System.out.println("Tarea programada");
    }

}
