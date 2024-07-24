package com.example.trafficpoliceman.api;

import com.example.trafficpoliceman.controller.handler.ExceptionMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Car number", description = "Return unique car number")
public interface CarNumberApi {

    @GetMapping(value = "/random", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Return random unique and valid car number", tags = "traffic policeman")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return car number",
                    content = {
                            @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "No more unique and valid car numbers",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ExceptionMessage.class))
                            )
                    }
            )
    })
    String getRandomNumber();

    @GetMapping(value = "/next", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Return next unique and valid car number", tags = "traffic policeman")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Return car number",
                    content = {
                            @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Previous number not found",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ExceptionMessage.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "No more unique and valid car numbers",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = ExceptionMessage.class))
                            )
                    }
            )
    })
    String getNextNumber();
}
