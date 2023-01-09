package com.example.userservicemsa;

import org.springframework.restdocs.snippet.Attributes;

import static org.springframework.restdocs.snippet.Attributes.key;

public interface DocumentFormatGenerator {

    static Attributes.Attribute getRequire(boolean value) { // (2)
        return key("require").value(value);
    }
}