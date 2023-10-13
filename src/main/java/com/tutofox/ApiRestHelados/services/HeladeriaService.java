/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tutofox.ApiRestHelados.services;

import com.tutofox.ApiRestHelados.entity.Heladeria;
import java.util.List;


public interface HeladeriaService {
       public List<Heladeria> findAll();
       public Heladeria save(Heladeria heladeria);
       public Heladeria findById(Long id);
       public void delete(Heladeria heladeria);
}
