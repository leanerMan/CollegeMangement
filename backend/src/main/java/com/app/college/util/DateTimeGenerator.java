package com.app.college.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeGenerator {

	public static LocalDateTime getAutoLocalDateTime() {
		ZoneId zoneId = ZoneId.of("Asia/Calcutta");
		return LocalDateTime.now(zoneId);
	}
	
}
