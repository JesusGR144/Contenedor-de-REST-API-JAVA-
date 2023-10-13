/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tutofox.ApiRestHelados.services;

import com.tutofox.ApiRestHelados.dao.HeladeriaDao;
import com.tutofox.ApiRestHelados.entity.Heladeria;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HeladeriaServiceImplement implements HeladeriaService{
    
    @Autowired
    private HeladeriaDao heladeriaDao;

    @Override
    @Transactional
    public List<Heladeria> findAll() {
        return (List<Heladeria>) heladeriaDao.findAll();
    }

    @Override
    @Transactional
    public Heladeria save(Heladeria heladeria) {
        return heladeriaDao.save(heladeria);
    }

    @Override
    @Transactional
    public Heladeria findById(Long id) {
        return heladeriaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Heladeria heladeria) {
        heladeriaDao.delete(heladeria);
    }
    
}
