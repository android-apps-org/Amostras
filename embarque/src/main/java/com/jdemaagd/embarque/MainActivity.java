package com.jdemaagd.embarque;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.jdemaagd.embarque.databinding.ActivityMainBinding;
import com.jdemaagd.embarque.info.FakeDataUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        BoardingPassInfo fakeBoardingInfo = FakeDataUtils.generateFakeBoardingPassInfo();
        displayBoardingPassInfo(fakeBoardingInfo);
    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {

        mBinding.tvPassengerName.setText(info.passengerName);
        mBinding.flightInfo.tvOriginAirport.setText(info.originCode);
        mBinding.flightInfo.tvFlightCode.setText(info.flightCode);
        mBinding.flightInfo.tvDestinationAirport.setText(info.destCode);

        SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.timeFormat), Locale.getDefault());
        String boardingTime = formatter.format(info.boardingTime);
        String departureTime = formatter.format(info.departureTime);
        String arrivalTime = formatter.format(info.arrivalTime);

        mBinding.tvBoardingTime.setText(boardingTime);
        mBinding.tvDepartureTime.setText(departureTime);
        mBinding.tvArrivalTime.setText(arrivalTime);

        long totalMinutesUntilBoarding = info.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minutesLessHoursUntilBoarding =
                totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);

        String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat,
                hoursUntilBoarding,
                minutesLessHoursUntilBoarding);

        mBinding.tvBoardingInCountdown.setText(hoursAndMinutesUntilBoarding);
        mBinding.boardingInfo.tvTerminal.setText(info.departureTerminal);
        mBinding.boardingInfo.tvGate.setText(info.departureGate);
        mBinding.boardingInfo.tvSeat.setText(info.seatNumber);
    }
}