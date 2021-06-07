/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.isi.gestionProjet.model.ws.converter;

 
import org.springframework.stereotype.Component;

 

import ma.isi.gestionProjet.bean.Projet;
import ma.isi.gestionProjet.common.util.NumberUtil;
import ma.isi.gestionProjet.model.ws.vo.ProjetVo;

/**
 *
 * @author user
 */
@Component
public class ProjetConverter  extends AbstractConverter<Projet, ProjetVo>{

    @Override
    public Projet toItem(ProjetVo vo) {
        if (vo != null) {
             Projet item = new  Projet();

            if (vo.getId()!= null) {
                item.setId(vo.getId());
            }
              if (vo.getIntitule()!= null) {
                item.setIntitule(vo.getIntitule());
            }
              if (vo.getNumprojet()!= null) {
                  item.setNumprojet(NumberUtil.toInt(vo.getNumprojet()));
              }
              if (vo.getVolumehoraire()!= null) {
                  item.setVolumehoraire (NumberUtil.toInt(vo.getVolumehoraire()));
              }
              if (vo.getNombrecollaborateurs()!= null) {
                  item.setNumprojet(NumberUtil.toInt(vo.getNombrecollaborateurs()));
              }
              if (vo.getDatedebut()!= null) {
                  item.setDatedebut(vo.getDatedebut());
              }
              if (vo.getDatefin()!= null) {
                  item.setDatefin(vo.getDatefin());
              }
              if (vo.getTacheVos()!= null) {
                  item.setTaches(new TacheConverter().toItem(vo.getTacheVos()));
              }
              
            return item;
        }
        return null;
    }

    @Override
    public ProjetVo toVo(Projet item) {
        if (item != null) {
             ProjetVo vo = new ProjetVo();

            if (item.getId()!= 0) {
                vo.setId(item.getId());
            }
            if (item.getIntitule()!= null) {
                vo.setIntitule(item.getIntitule());
            }
            if (item.getDatedebut()!= null) {
                vo.setDatedebut(item.getDatedebut());
            }
            if (item.getDatefin()!= null) {
                vo.setDatefin(item.getDatefin());
            } 
            if (item.getNumprojet()!= 0) {
            vo.setNumprojet(NumberUtil.intToString(item.getNumprojet()));
            }
            if (item.getVolumehoraire ()!= 0) {
                vo.setVolumehoraire (NumberUtil.intToString(item.getVolumehoraire()));
             }
            if (item.getNombrecollaborateurs()!= 0) {
                vo.setNombrecollaborateurs(NumberUtil.intToString(item.getNombrecollaborateurs()));
             }
            if (item.getTaches()!= null) {
                vo.setTacheVos(new TacheConverter().toVo(item.getTaches()));
            }
             
            return vo;
        }
        return null;
    }
    
}
