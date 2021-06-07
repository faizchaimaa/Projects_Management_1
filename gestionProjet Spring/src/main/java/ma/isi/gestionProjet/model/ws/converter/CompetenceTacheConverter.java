/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.isi.gestionProjet.model.ws.converter;


import org.springframework.stereotype.Component;

import ma.isi.gestionProjet.bean.CompetenceTache;
import ma.isi.gestionProjet.model.ws.vo.CompetenceTacheVo;

/**
 *
 * @author user
 */
@Component
public class CompetenceTacheConverter extends AbstractConverter<CompetenceTache, CompetenceTacheVo>{

    @Override
    public CompetenceTache toItem(CompetenceTacheVo vo) {
        if (vo != null) {
             CompetenceTache item = new  CompetenceTache();

            if (vo.getId()!= null) {
                item.setId(vo.getId());
            }
            if (vo.getCompetenceVo()!= null) {
                item.setCompetence(new CompetenceConverter().toItem(vo.getCompetenceVo()));
            }
            if(vo.getTacheVo() != null) {
                item.setTache(new TacheConverter().toItem(vo.getTacheVo()));
            }
            return item;
        }
        return null;
    }

    @Override
    public CompetenceTacheVo toVo(CompetenceTache item) {
         if (item != null) {
             CompetenceTacheVo vo = new CompetenceTacheVo();

            if (item.getId()!= 0) {
                vo.setId(item.getId());
            }
            if (item.getCompetence()!= null) {
                vo.setCompetenceVo(new CompetenceConverter().toVo(item.getCompetence()));
            }
            if (item.getTache() != null) {
                vo.setTacheVo(new TacheConverter().toVo(item.getTache()));
            }
            return vo;
        }
        return null;
    }
    
}
