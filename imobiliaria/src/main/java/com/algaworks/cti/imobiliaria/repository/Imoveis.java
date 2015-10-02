package com.algaworks.cti.imobiliaria.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.cti.imobiliaria.model.Imovel;
import com.algaworks.cti.imobiliaria.repository.filter.ImovelFilter;

@Repository
public class Imoveis {

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void guardar(Imovel imovel) {
		manager.persist(imovel);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Imovel> filtrar(ImovelFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Imovel.class);
		
		if (StringUtils.isNotBlank(filtro.getBairro())) {
			criteria.add(Restrictions.ilike("bairro", filtro.getBairro(), MatchMode.ANYWHERE));
		}
		
		if (filtro.getTipo() != null) {
			criteria.add(Restrictions.eq("tipo", filtro.getTipo()));
		}
		
		if (filtro.getValorInicial() != null) {
			criteria.add(Restrictions.ge("valor", filtro.getValorInicial()));
		}
		
		if (filtro.getValorFinal() != null) {
			criteria.add(Restrictions.le("valor", filtro.getValorFinal()));
		}
		
		return criteria.list();
	}
	
}
