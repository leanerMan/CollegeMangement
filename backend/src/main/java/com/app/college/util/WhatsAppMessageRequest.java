package com.app.college.util;

import lombok.Data;

@Data
public class WhatsAppMessageRequest {
	
	private String number;
    private String instance_id;
    private String access_token;
    private String message;
    private String type;

}
