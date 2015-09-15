package test;

public class date {

	public static void main(String[] args) throws InterruptedException {

		dateRead();
	}

	public static void dateRead() throws InterruptedException {

		java.util.Calendar calendar = java.util.Calendar.getInstance(
				java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
		calendar.setTime(new java.util.Date());

		// Calendar Curr_Date = Calendar.getInstance();
		// System.out.println(Curr_Date);

		int curr_year = calendar.get(java.util.Calendar.YEAR);
		System.out.println(curr_year);

		int curr_month = calendar.get(java.util.Calendar.MONTH) + 1;
		System.out.println(curr_month);

		int curr_day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
		System.out.println(curr_day);

	}
}