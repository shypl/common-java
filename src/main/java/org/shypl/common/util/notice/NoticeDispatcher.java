package org.shypl.common.util.notice;

import org.shypl.common.util.Cancelable;
import org.shypl.common.util.Observers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NoticeDispatcher implements NoticeDispatchable {
	private final Map<Class<?>, Observers<?>> noticeTypesToHandlers = new ConcurrentHashMap<>();

	@Override
	public <N extends Notice> Cancelable addNoticeHandler(Class<N> noticeClass, NoticeHandler<N> handler) {
		Observers<NoticeHandler<N>> handlers = getHandlers(noticeClass);

		if (handlers == null) {
			handlers = createHandlers(noticeClass);
		}

		return handlers.add(handler);
	}

	@Override
	public <N extends Notice> void removeNoticeHandler(Class<N> noticeClass, NoticeHandler<N> handler) {
		Observers<NoticeHandler<N>> handlers = getHandlers(noticeClass);
		if (handlers != null) {
			handlers.remove(handler);
		}
	}

	@Override
	public <N extends Notice> void removeNoticeHandlers(Class<N> noticeClass) {
		Observers<NoticeHandler<N>> handlers = getHandlers(noticeClass);
		if (handlers != null) {
			handlers.removeAll();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeNoticeHandler(NoticeHandler<?> handler) {
		for (Observers<?> handlers : noticeTypesToHandlers.values()) {
			((Observers<NoticeHandler<?>>)handlers).remove(handler);
		}
	}

	@Override
	public void removeAllNoticeHandlers() {
		noticeTypesToHandlers.values().forEach(Observers::removeAll);
	}

	@Override
	public <N extends Notice> void dispatchNotice(N notice) {
		@SuppressWarnings("unchecked")
		Observers<NoticeHandler<N>> handlers = getHandlers((Class<N>)notice.getClass());
		if (handlers != null) {
			handlers.inform(handler -> handler.handleNotice(notice));
		}
	}

	@SuppressWarnings("unchecked")
	private <N extends Notice> Observers<NoticeHandler<N>> getHandlers(Class<N> noticeClass) {
		return (Observers<NoticeHandler<N>>)noticeTypesToHandlers.get(noticeClass);
	}

	@SuppressWarnings("unchecked")
	private <N extends Notice> Observers<NoticeHandler<N>> createHandlers(Class<N> noticeClass) {
		return (Observers<NoticeHandler<N>>)noticeTypesToHandlers.computeIfAbsent(noticeClass, cls -> new Observers<NoticeHandler<N>>());
	}
}
