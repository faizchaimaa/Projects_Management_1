/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.isi.gestionProjet.model.ws.converter;


import org.springframework.stereotype.Component;

import ma.isi.gestionProjet.bean.Affectation;
import ma.isi.gestionProjet.common.util.NumberUtil;
import ma.isi.gestionProjet.model.ws.vo.AffectationVo;

/**
 *
 * @author user
 */
@Component
public class AffectationConverter extends AbstractConverter<Affectation, AffectationVo>{

    @Override
    public Affectation toItem(AffectationVo vo) {
        if (vo != null) {
             Affectation item = new  Affectation();

            if (vo.getId()!= null) {
                item.setId(vo.getId());
            }
            if (vo.getTacheVo()!= null) {
                item.setTache(new TacheConverter().toItem(vo.getTacheVo()));
            }
            if(vo.getCollaborateurVo() != null) {
                item.setCollaborateur(new CollaborateurConverter().toItem(vo.getCollaborateurVo()));
            }
            if (vo.getVolumehoraire()!= null) {
                item.setVolumehoraire (NumberUtil.toInt(vo.getVolumehoraire()));
            }
            return item;
        }
        return null;
    }

    @Override
    public AffectationVo toVo(Affectation item) {
         if (item != null) {
             AffectationVo vo = new AffectationVo();

            if (item.getId()!= 0) {
                vo.setId(item.getId());
            }
            if (item.getTache()!= null) {
                vo.setTacheVo(new TacheConverter().toVo(item.getTache()));
            }
            if (item.getCollaborateur() != null) {
                vo.setCollaborateurVo(new CollaborateurConverter().toVo(item.getCollaborateur()));
            }
            if (vo.getVolumehoraire()!= null) {
                item.setVolumehoraire (NumberUtil.toInt(vo.getVolumehoraire()));
            }
            return vo;
        }
        return null;
    }
    
}
