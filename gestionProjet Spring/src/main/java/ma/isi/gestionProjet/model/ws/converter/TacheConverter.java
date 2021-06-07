package ma.isi.gestionProjet.model.ws.converter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
 
import org.springframework.stereotype.Component;

import ma.isi.gestionProjet.bean.Tache;
import ma.isi.gestionProjet.common.util.NumberUtil;
import ma.isi.gestionProjet.model.ws.vo.TacheVo;

 
/**
 *
 * @author user
 */
@Component
public class TacheConverter  extends AbstractConverter<Tache, TacheVo>{

    @Override
    public Tache toItem(TacheVo vo) {
        if (vo != null) {
             Tache item = new  Tache();

            if (vo.getId()!= null) {
                item.setId(vo.getId());
            }
              if (vo.getIntitule()!= null) {
                item.setIntitule(vo.getIntitule());
            }
              if (vo.getDatedebut()!= null) {
                  item.setDatedebut(vo.getDatedebut());
              }
              if (vo.getDatefin()!= null) {
                  item.setDatefin(vo.getDatefin());
              }
              if (vo.getProjetVo()!= null) {
                  item.setProjet(new ProjetConverter().toItem(vo.getProjetVo()));
              }
              if (vo.getVolumehoraire()!= null) {
                  item.setVolumehoraire (NumberUtil.toInt(vo.getVolumehoraire()));
              }
              if (vo.getNombrecollaborateurs()!= null) {
                  item.setNombrecollaborateurs (NumberUtil.toInt(vo.getNombrecollaborateurs()));
              }
             
            return item;
        }
        return null;
    }

    @Override
    public TacheVo toVo(Tache item) {
        if (item != null) {
             TacheVo vo = new TacheVo();

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
            if (item.getProjet() != null) {
                vo.setProjetVo(new ProjetConverter().toVo(item.getProjet()));
            }
            
            if (vo.getVolumehoraire()!= null) {
                item.setVolumehoraire (NumberUtil.toInt(vo.getVolumehoraire()));
            }
            if (vo.getNombrecollaborateurs()!= null) {
                item.setNombrecollaborateurs(NumberUtil.toInt(vo.getNombrecollaborateurs()));
            }
           
            return vo;
        }
        return null;
    }
    
}
