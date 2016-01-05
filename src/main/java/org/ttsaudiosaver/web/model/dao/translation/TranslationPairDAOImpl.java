package org.ttsaudiosaver.web.model.dao.translation;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ttsaudiosaver.web.model.TranslationPair;
import org.ttsaudiosaver.web.model.dao.AbstractDAO;

@Repository("translationPairDao")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class TranslationPairDAOImpl extends AbstractDAO implements TranslationPairDAO {

	@Override
	public void saveTranslationPair(TranslationPair translationPair) {
		getHibernateTemplate().persist(translationPair);
	}

	@Override
	public void deleteTranslationPair(TranslationPair translationPair) {
		getHibernateTemplate().delete(translationPair);
	}

	@Override
	public TranslationPair findTranslationPairById(String translationPairId) {
		TranslationPair pair = getHibernateTemplate().get(TranslationPair.class, translationPairId);
		getHibernateTemplate().initialize(pair.getCompiledAudios());
		return pair;
	}
	
	@Override
	public TranslationPair findTranslationPairByFileId(String fileId) {
		TranslationPair pair = null;
		List<?> pairs = getHibernateTemplate().findByNamedQueryAndNamedParam(TranslationPair.Constants.QUERY_FIND_BY_FILE_ID, TranslationPair.Constants.PARAM_FILE_ID, fileId);
		if(!pairs.isEmpty()) {
			pair = (TranslationPair)pairs.get(0);
		}
		getHibernateTemplate().initialize(pair.getCompiledAudios());
		return pair;
	}

	@Override
	public void updateTranslationPair(TranslationPair translationPair) {
		getHibernateTemplate().update(translationPair);
	}
}