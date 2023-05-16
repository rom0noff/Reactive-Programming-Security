package uz.reactiveprogramming.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.reactiveprogramming.ReactiveProgramming1Application;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class RunnerLine implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Logger logger = LogManager.getLogger(ReactiveProgramming1Application.class);
        String clock = "HH:mm:ss";
        String date = "dd-MM-yyyy";

        SimpleDateFormat simpleClock = new SimpleDateFormat(clock);
        SimpleDateFormat simpleDate = new SimpleDateFormat(date);

        String nowClock = simpleClock.format(new Date());
        String nowDate = simpleDate.format(new Date());
        StringBuilder message = new StringBuilder();
        message.append("start reactive security  ---------->  ").append(nowClock).append(" < ____ > ").append(nowDate);
        logger.info(message);
    }
}
