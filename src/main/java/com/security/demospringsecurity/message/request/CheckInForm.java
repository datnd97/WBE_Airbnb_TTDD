package com.security.demospringsecurity.message.request;

import lombok.Data;

@Data
public class CheckInForm {
    private Boolean isCheckin;
    private Long homeId;
}
