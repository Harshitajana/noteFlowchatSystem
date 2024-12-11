package com.flow.baseRest;

import java.util.UUID;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponse {

	private UUID deletedId;
	private HttpStatus httpStatus;

}
