package com.telerikacademy.oop.wim.models.contracts;

import com.telerikacademy.oop.wim.models.enums.Severity;

public interface Bug extends WorkItems {

    Severity getSeverity();
}
