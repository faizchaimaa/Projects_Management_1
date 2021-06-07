/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.isi.gestionProjet.model.ws.converter;

 
import org.springframework.stereotype.Component;

import ma.isi.gestionProjet.bean.Competence;
import ma.isi.gestionProjet.model.ws.vo.CompetenceVo;

/**
 *
 * @author user
 */
@Component
public class CompetenceConverter  extends AbstractConverter<Competence, CompetenceVo>{

    @Override
    public Competence toItem(CompetenceVo vo) {
        if (vo != null) {
             Competence item = new  Competence();

            if (vo.getId()!= null) {
                item.setId(vo.getId());
            }
              if (vo.getTitre()!= null) {
                item.setTitre(vo.getTitre());
            }
              if (vo.getDescription()!= null) {
                  item.setDescription(vo.getDescription());
              }
            return item;
        }
        return null;
    }

    @Override
    public CompetenceVo toVo(Competence item) {
        if (item != null) {
             CompetenceVo vo = new CompetenceVo();

            if (item.getId()!= 0) {
                vo.setId(item.getId());
            }
            if (item.getTitre()!= null) {
                vo.setTitre(item.getTitre());
            }
            if (item.getDescription()!= null) {
                vo.setDescription(item.getDescription());
            }
            return vo;
        }
        return null;
    }
    
}
