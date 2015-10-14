package test;

import java.util.Calendar;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		Double startHour;
		Double startMinute;
		Double finishHour;
		Double finishMinute;
		Double diifHour;
		Calendar calendar = Calendar.getInstance();

		// startHour = (double) 16;
		// startMinute = (double) 48;
		startHour = (double) calendar.get(Calendar.HOUR);
		startMinute = (double) calendar.get(Calendar.MINUTE);
		System.out.println("start time :" + startHour + ":" + startMinute);
		Thread.sleep(5000);
		// finishHour = (double) 19;
		// finishMinute = (double) 13;
		finishHour = (double) calendar.get(Calendar.HOUR);
		finishMinute = (double) calendar.get(Calendar.MINUTE);
		// System.out.println("startDate :" + finishDate);
		diifHour = Math.rint(diifCalc(startHour, startMinute, finishHour, finishMinute) * 10.0) / 10.0;
		System.out.println("Diff :" + diifHour);
	}

	public static Double diifCalc(Double startHour, Double startMinute, Double finishHour, Double finishMinute) {
		Double diff;
		diff = (double) (finishHour - startHour);
		if (finishMinute < startMinute) {
			diff--;
			diff = diff + ((60 - startMinute + finishMinute) / 60);
		} else {
			diff = diff + ((finishMinute - startMinute) / 60);
		}
		return diff;
	}
}
