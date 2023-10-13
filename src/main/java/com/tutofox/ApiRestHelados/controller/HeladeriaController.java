/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tutofox.ApiRestHelados.controller;

import com.tutofox.ApiRestHelados.entity.Heladeria;
import com.tutofox.ApiRestHelados.services.HeladeriaService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Juan Manuel Gaytán
 */
@RestController
@RequestMapping("/api/v1")
public class HeladeriaController {
    
    @Autowired
    private HeladeriaService heladeriaService;
    
    @GetMapping(value = "/sabores")
    public ResponseEntity<Object> get(){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Heladeria> list = heladeriaService.findAll();
            return new ResponseEntity<Object>(list,HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return  new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping(value="/sabores/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        try {
            Heladeria data = heladeriaService.findById(id);
            return new ResponseEntity<Object>(data, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PostMapping(value="/sabores")
    public ResponseEntity<Object> create(@RequestBody Heladeria heladeria){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Heladeria res = heladeriaService.save(heladeria);
            return new ResponseEntity<Object>(res, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/sabores/{id}")
	public ResponseEntity<Object> update(@RequestBody Heladeria heladeria, @PathVariable Long id){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			Heladeria currentHeladeria = heladeriaService.findById(id);
			
			currentHeladeria.setSabor(heladeria.getSabor());
			currentHeladeria.setPrecio(heladeria.getPrecio());
			
			Heladeria res = heladeriaService.save(heladeria);
			
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@DeleteMapping("/sabores/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try { 
			Heladeria currentHeladeria = heladeriaService.findById(id); 
			heladeriaService.delete(currentHeladeria);
			map.put("deleted", true);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
}
