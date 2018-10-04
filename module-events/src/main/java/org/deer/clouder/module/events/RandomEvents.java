package org.deer.clouder.module.events;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile(value = {"cloud", "heroku", "local"})
public class RandomEvents {

    private final Random randomGeneratorWithFallback = new Random(27L);
    private final Random randomGeneratorWithoutFallback = new Random(134L);
    private final Random randomGeneratorDifferentThreadpool = new Random(274L);

    @Scheduled(fixedRate = 200)
    @HystrixCommand(commandKey = "eventWithFallback", fallbackMethod = "randomEventFallback", threadPoolKey = "randomEvents")
    public String triggerRandomEventWithFallback() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(randomGeneratorWithFallback.nextInt(42));
        return passOrFail(randomGeneratorWithFallback.nextInt(145) < 2);
    }

    @Scheduled(fixedRate = 200)
    @HystrixCommand(commandKey = "eventWithoutFallback", threadPoolKey = "randomEvents")
    public String triggerRandomEventWithoutFallback() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(randomGeneratorWithoutFallback.nextInt(27));
        return passOrFail(randomGeneratorWithoutFallback.nextInt(57) < 2);
    }

    @Scheduled(fixedRate = 200)
    @HystrixCommand(commandKey = "eventWithoutFallback2", threadPoolKey = "otherRandomEvents")
    public String triggerRandomEventDifferentThreadPool() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(randomGeneratorDifferentThreadpool.nextInt(34));
        return passOrFail(randomGeneratorDifferentThreadpool.nextInt(25) < 2);
    }

    private String passOrFail(final boolean negativeCondition) {
        if (negativeCondition) {
            throw new IllegalStateException("Command failed for whatever reason");
        } else {
            return "Success";
        }
    }

    public String randomEventFallback() {
        return "Default";
    }
}
