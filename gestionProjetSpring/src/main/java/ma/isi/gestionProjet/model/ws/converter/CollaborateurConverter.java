/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.isi.gestionProjet.model.ws.converter;

 
import org.springframework.stereotype.Component;

 
import ma.isi.gestionProjet.bean.Collaborateur;
import ma.isi.gestionProjet.common.util.NumberUtil;
import ma.isi.gestionProjet.model.ws.vo.CollaborateurVo;

/**
 *
 * @author user
 */
@Component
public class CollaborateurConverter  extends AbstractConverter<Collaborateur, CollaborateurVo>{

    @Override
    public Collaborateur toItem(CollaborateurVo vo) {
        if (vo != null) {
             Collaborateur item = new  Collaborateur();

            if (vo.getId()!= null) {
                item.setId(vo.getId());
            }
              if (vo.getNom()!= null) {
                item.setNom(vo.getNom());
            }
              if (vo.getPrenom()!= null) {
                  item.setPrenom(vo.getPrenom());
            }
              if (vo.getMatricule()!= null) {
                  item.setMatricule(vo.getMatricule());
            }
              if (vo.getEstdisponible()!= null) {
                  item.setEstdisponible (NumberUtil.toInt(vo.getEstdisponible()));
              }
            /*  if (vo.getEstdisponible()!= null) {
                  item.setEstdisponible(NumberUtil.toInt(vo.getEstdisponible()));
              } */
              
            return item;
        }
        return null;
    }

    @Override
    public CollaborateurVo toVo(Collaborateur item) {
        if (item != null) {
             CollaborateurVo vo = new CollaborateurVo();

            if (item.getId()!= 0) {
                vo.setId(item.getId());
            }
            if (item.getNom()!= null) {
                vo.setNom(item.getNom());
            }
            if (item.getPrenom()!= null) {
                vo.setPrenom(item.getPrenom());
            }
            if (item.getMatricule()!= null) {
                vo.setMatricule(item.getMatricule());
            }
            if (vo.getEstdisponible()!= null) {
                item.setEstdisponible(NumberUtil.toInt(vo.getEstdisponible()));
            }
           /* vo.setEstdisponible(NumberUtil.doubleToString(item.getEstdisponible()));
            return vo;*/
        }
        return null;
    }
    
}
