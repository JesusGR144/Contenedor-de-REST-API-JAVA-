/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tutofox.ApiRestHelados.dao;

import com.tutofox.ApiRestHelados.entity.Heladeria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HeladeriaDao extends JpaRepository<Heladeria, Long>{
    
}
