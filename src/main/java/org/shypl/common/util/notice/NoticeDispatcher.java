package org.shypl.common.util.notice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class NoticeDispatcher implements NoticeDispatchable {
	private final Map<Class<?>, HandlerList<?>> noticeTypesToHandlers = new ConcurrentHashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	public <N extends Notice> NoticeHandlerSub addNoticeHandler(Class<N> noticeClass, NoticeHandler<N> handler) {
		HandlerList<N> handlers = getHandlers(noticeClass);

		if (handlers == null) {
			handlers = (HandlerList<N>)noticeTypesToHandlers.computeIfAbsent(noticeClass, cls -> new HandlerList<>());
		}

		handlers.add(handler);

		return new NoticeHandlerSubImpl(this, noticeClass, handler);
	}

	@Override
	public <N extends Notice> void removeNoticeHandler(Class<N> noticeClass, NoticeHandler<N> handler) {
		HandlerList<N> handlers = getHandlers(noticeClass);

		if (handlers != null) {
			handlers.remove(handler);
		}
	}

	@Override
	public void removeNoticeHandler(NoticeHandler<?> handler) {
		for (HandlerList<?> list : noticeTypesToHandlers.values()) {
			list.remove(handler);
		}
	}

	@Override
	public void removeAllNoticeHandlers() {
		Iterator<Map.Entry<Class<?>, HandlerList<?>>> iterator = noticeTypesToHandlers.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Class<?>, HandlerList<?>> entry = iterator.next();
			entry.getValue().clear();
			iterator.remove();
		}
	}

	@Override
	public <N extends Notice> void dispatchNotice(N notice) {
		@SuppressWarnings("unchecked")
		HandlerList<N> handlers = getHandlers((Class<N>)notice.getClass());
		if (handlers != null) {
			handlers.handle(notice);
		}
	}

	@SuppressWarnings("unchecked")
	private <N extends Notice> HandlerList<N> getHandlers(Class<N> noticeClass) {
		return (HandlerList<N>)noticeTypesToHandlers.get(noticeClass);
	}

	private static class HandlerList<N extends Notice> {
		private final Set<NoticeHandler<N>> collection = new LinkedHashSet<>();

		public void add(NoticeHandler<N> handler) {
			synchronized (collection) {
				collection.add(handler);
			}
		}

		@SuppressWarnings("SuspiciousMethodCalls")
		public void remove(NoticeHandler<?> handler) {
			synchronized (collection) {
				collection.remove(handler);
			}
		}

		public void handle(N notice) {
			List<NoticeHandler<N>> list;

			synchronized (collection) {
				list = new ArrayList<>(collection);
			}

			for (NoticeHandler<N> handler : list) {
				handler.handleNotice(notice);
			}
		}

		public void clear() {
			collection.clear();
		}
	}
}
