package org.ttsaudiosaver.web.model.dao.translation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ttsaudiosaver.web.model.CompiledAudio;
import org.ttsaudiosaver.web.model.dao.AbstractDAO;

@Repository("compiledAudioDao")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CompiledAudioDAOImpl extends AbstractDAO implements CompiledAudioDAO {

	@Override
	public void saveCompiledAudio(CompiledAudio compiledAudio) {
		getHibernateTemplate().persist(compiledAudio);
	}

	@Override
	public void deleteCompiledAudio(CompiledAudio compiledAudio) {
		getHibernateTemplate().delete(compiledAudio);
	}

	@Override
	public CompiledAudio findCompiledAudioById(Integer compiledAudioId) {
		CompiledAudio audio = getHibernateTemplate().get(CompiledAudio.class, compiledAudioId);
		getHibernateTemplate().initialize(audio.getPairsIncluded());
		getHibernateTemplate().initialize(audio.getUsers());
		return audio;
	}

	@Override
	public void updateCompiledAudio(CompiledAudio compiledAudio) {
		getHibernateTemplate().update(compiledAudio);
	}
}