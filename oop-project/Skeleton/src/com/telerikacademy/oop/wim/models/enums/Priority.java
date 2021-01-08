package com.telerikacademy.oop.wim.models.enums;

public enum Priority {
    HIGH,
    MEDIUM,
    LOW;
    
    @Override
    public String toString() {
        return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
    }
}
