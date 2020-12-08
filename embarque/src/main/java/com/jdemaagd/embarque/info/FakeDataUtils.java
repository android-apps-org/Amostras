package com.jdemaagd.embarque.info;

import com.jdemaagd.embarque.BoardingPassInfo;
import com.jdemaagd.embarque.R;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class FakeDataUtils {

    /**
     * Generates fake boarding pass data to be displayed
     * @return fake boarding pass data
     */
    public static BoardingPassInfo generateFakeBoardingPassInfo() {

        BoardingPassInfo bpi = new BoardingPassInfo();

        bpi.passengerName = "MR. RANDOM PERSON";
        bpi.flightCode = "UD 777";
        bpi.originCode = "JFK";
        bpi.destCode = "DCA";

        long now = System.currentTimeMillis();

        long randomMinutesUntilBoarding = (long) (Math.random() * 30);
        long totalBoardingMinutes = 40;
        long randomFlightLengthHours = (long) (Math.random() * 5 + 1);

        long boardingMillis = now + minutesToMillis(randomMinutesUntilBoarding);
        long departure = boardingMillis + minutesToMillis(totalBoardingMinutes);
        long arrival = departure + hoursToMillis(randomFlightLengthHours);

        bpi.boardingTime = new Timestamp(boardingMillis);
        bpi.departureTime = new Timestamp(departure);
        bpi.arrivalTime = new Timestamp(arrival);
        bpi.departureTerminal = "3A";
        bpi.departureGate = "33C";
        bpi.seatNumber = "1A";
        bpi.barCodeImageResource = R.drawable.art_plane;

        return bpi;
    }

    private static long minutesToMillis(long minutes) {
        return TimeUnit.MINUTES.toMillis(minutes);
    }

    private static long hoursToMillis(long hours) {
        return TimeUnit.HOURS.toMillis(hours);
    }
}