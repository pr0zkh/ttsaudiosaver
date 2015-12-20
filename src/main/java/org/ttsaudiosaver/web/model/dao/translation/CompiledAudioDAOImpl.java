package org.ttsaudiosaver.web.model.dao.translation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.ttsaudiosaver.web.model.CompiledAudio;
import org.ttsaudiosaver.web.model.dao.AbstractDAO;

@Repository("compiledAudioDao")
public class CompiledAudioDAOImpl extends AbstractDAO implements CompiledAudioDAO {
	
	private static final String DELETE_BY_ID_QUERY = "delete from compiled_audios where compiled_audio_id = :compiled_audio_id";
	private static final String DELETE_BY_AUDIO_FILE_ID_QUERY = "delete from compiled_audios where file_id = :file_id";

	@Override
	@Transactional
	public void saveCompiledAudio(CompiledAudio compiledAudio) {
		persist(compiledAudio);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CompiledAudio> findAllCompiledAudios() {
		Criteria criteria = getSession().createCriteria(CompiledAudio.class);
		return (List<CompiledAudio>) criteria.list();
	}

	@Override
	@Transactional
	public void deleteById(String compiledAudioId) {
		Query query = getSession().createSQLQuery(DELETE_BY_ID_QUERY);
		query.setString("compiled_audio_id", compiledAudioId);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void deleteByAudioFileId(String compiledAudioFileId) {
		Query query = getSession().createSQLQuery(DELETE_BY_AUDIO_FILE_ID_QUERY);
		query.setString("file_id", compiledAudioFileId);
		query.executeUpdate();
	}

	@Override
	@Transactional
	public CompiledAudio findById(String compiledAudioId) {
		Criteria criteria = getSession().createCriteria(CompiledAudio.class);
		criteria.add(Restrictions.eq("compiled_audio_id", compiledAudioId));
		return (CompiledAudio) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public CompiledAudio findByAudioFileId(String compiledAudioFileId) {
		Criteria criteria = getSession().createCriteria(CompiledAudio.class);
		criteria.add(Restrictions.eq("file_id", compiledAudioFileId));
		return (CompiledAudio) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public void updateCompiledAudio(CompiledAudio compiledAudio) {
		getSession().update(compiledAudio);
	}

}
