package com.condominium.online.condo.controller;

import com.condominium.online.condo.entity.ApartmentManager;
import com.condominium.online.condo.exceptions.InvalidUserException;
import com.condominium.online.condo.service.ApartmentManagerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/condo/apartmentManager")
@Api("v1 - Apartment Manager")
public class ApartmentManagerController {

    private ApartmentManagerService apartmentManagerService;

    @Autowired
    public ApartmentManagerController(ApartmentManagerService apartmentManagerService) {
        this.apartmentManagerService = apartmentManagerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create Apartment Manager")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public ApartmentManager saveApartmentManager(@RequestBody(required = true) ApartmentManager apartmentManager) throws InvalidUserException {
        return this.apartmentManagerService.saveApartmentManager(apartmentManager);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Apartment Manager")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ACCEPT", value = "ACCEPT", defaultValue = "application/json", required = false, dataType = "string", paramType = "header")})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "BAD_REQUEST"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR")})
    public void deleteApartmentManager(@PathVariable("id") long id) throws InvalidUserException{
        apartmentManagerService.deleteApartmentManager(id);
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Data Integrity Violation")
    @ExceptionHandler({DataIntegrityViolationException.class})
    public void dataIntegrityViolation() {
    }

}
