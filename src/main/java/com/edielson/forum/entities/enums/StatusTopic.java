package com.edielson.forum.entities.enums;

public enum StatusTopic {
    
    NAO_RESPONDIDO(1),
	NAO_SOLUCIONADO(2),
	SOLUCIONADO(3),
	FECHADO(4);

	private int code;

    private StatusTopic(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StatusTopic valueOf(int code) {
        for  (StatusTopic value : StatusTopic.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid StatusTopic code");
    }
}