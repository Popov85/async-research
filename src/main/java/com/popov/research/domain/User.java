package com.popov.research.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;
    
    public void doSmth() {
    	String str = new String();
    	Integer num = Integer.valueOf(1);
    	log.debug("Str = {}", str);
    }
}