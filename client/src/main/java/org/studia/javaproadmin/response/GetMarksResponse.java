package org.studia.javaproadmin.response;

import lombok.Getter;
import lombok.Setter;
import org.studia.javaproadmin.entities.Test;

import java.util.List;

@Setter
@Getter
public class GetMarksResponse {
List<Test> tests;
}
