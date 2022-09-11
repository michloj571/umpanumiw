package pl.polsl.umpa.esp1.sprinkler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


public record SprinklerDataReadRequest(String pumpURL) {
    
}

