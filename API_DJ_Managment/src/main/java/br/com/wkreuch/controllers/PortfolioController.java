package br.com.wkreuch.controllers;

import br.com.wkreuch.data.dtos.PortfolioCreateDto;
import br.com.wkreuch.data.dtos.PortfolioResponseDto;
import br.com.wkreuch.data.dtos.PortfolioUpdateDto;
import br.com.wkreuch.models.Portfolio;
import br.com.wkreuch.services.PortfolioService;
import br.com.wkreuch.utils.mapper.PortfolioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/portfolio")
@Tag(name = "Portfolio", description = "Endpoints for Managements Portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Adds a full Portfolio", description = "Adds a new Portfolio by passing in a JSON", tags = {"Portfolio"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201", content = @Content(schema = @Schema(implementation = PortfolioResponseDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PortfolioResponseDto create(@RequestBody @Valid PortfolioCreateDto portfolioCreateDto) {
        Portfolio newPortfolio = PortfolioMapper.convert(portfolioCreateDto);
        return PortfolioMapper.convert(service.create(newPortfolio, portfolioCreateDto.getIdDj()), true);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Updates a full Portfolio", description = "Updates a Portfolio by passing in a JSON", tags = {"Portfolio"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PortfolioResponseDto.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PortfolioResponseDto update(@PathVariable(value = "id") Long id, @RequestBody @Valid PortfolioUpdateDto portfolioUpdateDto) {
        Portfolio updatePortfolio = PortfolioMapper.convert(portfolioUpdateDto);
        return PortfolioMapper.convert(service.update(id, updatePortfolio),true);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds all Portfolios", description = "Finds all Portfolios", tags = {"Portfolio"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PortfolioResponseDto.class)))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public List<PortfolioResponseDto> findAll() {
        return PortfolioMapper.convert(service.findAll(),true);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds a Portfolio", description = "Finds one Portfolio", tags = {"Portfolio"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PortfolioResponseDto.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public PortfolioResponseDto findById(@PathVariable(value = "id") Long id) {
        return PortfolioMapper.convert(service.findById(id),true);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Deletes a Portfolio", description = "Deletes one Portfolio", tags = {"Portfolio"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
