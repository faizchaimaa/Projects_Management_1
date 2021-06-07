/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.isi.gestionProjet.model.ws.converter;


import org.springframework.stereotype.Component;

import ma.isi.gestionProjet.bean.CompetenceCollaborateur;
import ma.isi.gestionProjet.model.ws.vo.CompetenceCollaborateurVo;

/**
 *
 * @author user
 */
@Component
public class CompetenceCollaborateurConverter extends AbstractConverter<CompetenceCollaborateur, CompetenceCollaborateurVo>{

    @Override
    public CompetenceCollaborateur toItem(CompetenceCollaborateurVo vo) {
        if (vo != null) {
             CompetenceCollaborateur item = new  CompetenceCollaborateur();

            if (vo.getId()!= null) {
                item.setId(vo.getId());
            }
            if (vo.getCompetenceVo()!= null) {
                item.setCompetence(new CompetenceConverter().toItem(vo.getCompetenceVo()));
            }
            if(vo.getCollaborateurVo() != null) {
                item.setCollaborateur(new CollaborateurConverter().toItem(vo.getCollaborateurVo()));
            }
            return item;
        }
        return null;
    }

    @Override
    public CompetenceCollaborateurVo toVo(CompetenceCollaborateur item) {
         if (item != null) {
             CompetenceCollaborateurVo vo = new CompetenceCollaborateurVo();

            if (item.getId()!= 0) {
                vo.setId(item.getId());
            }
            if (item.getCompetence()!= null) {
                vo.setCompetenceVo(new CompetenceConverter().toVo(item.getCompetence()));
            }
            if (item.getCollaborateur() != null) {
                vo.setCollaborateurVo(new CollaborateurConverter().toVo(item.getCollaborateur()));
            }
            return vo;
        }
        return null;
    }
    
}
