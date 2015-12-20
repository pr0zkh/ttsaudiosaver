package org.ttsaudiosaver.web.model.dao.translation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.ttsaudiosaver.web.model.TranslationPair;
import org.ttsaudiosaver.web.model.dao.AbstractDAO;

@Repository("translationPairDao")
public class TranslationPairDAOImpl extends AbstractDAO implements TranslationPairDAO {
	
	private static final String DELETE_BY_ID_QUERY = "delete from translation_pairs where translation_pair_id = :translation_pair_id";
	private static final String DELETE_BY_AUDIO_FILE_ID_QUERY = "delete from translation_pairs where file_id = :file_id";

	@Override
	@Transactional
	public void saveTranslationPair(TranslationPair translationPair) {
		persist(translationPair);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<TranslationPair> findAllTranslationPairs() {
		Criteria criteria = getSession().createCriteria(TranslationPair.class);
		return (List<TranslationPair>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<TranslationPair> findTranslationPairsByFileIds(List<String> ids) {
		Criteria criteria = getSession().createCriteria(TranslationPair.class);
		criteria.add(Restrictions.in("fileId", ids));
		return criteria.list();
	}

	@Override
	@Transactional
	public void deleteById(String translationPairId) {
		Query query = getSession().createSQLQuery(DELETE_BY_ID_QUERY);
		query.setString("translation_pair_id", translationPairId);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void deleteByAudioFileId(String translationPairFileId) {
		Query query = getSession().createSQLQuery(DELETE_BY_AUDIO_FILE_ID_QUERY);
		query.setString("fileId", translationPairFileId);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public TranslationPair findById(String translationPairId) {
		Criteria criteria = getSession().createCriteria(TranslationPair.class);
		criteria.add(Restrictions.eq("translation_pair_id", translationPairId));
		return (TranslationPair) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public TranslationPair findByAudioFileId(String translationPairFileId) {
		Criteria criteria = getSession().createCriteria(TranslationPair.class);
		criteria.add(Restrictions.eq("file_id", translationPairFileId));
		return (TranslationPair) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public void updateTranslationPair(TranslationPair translationPair) {
		getSession().update(translationPair);
	}

}